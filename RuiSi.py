import re
from multiprocessing.pool import Pool
import requests
from pyquery import PyQuery as pq
from requests import RequestException

#在睿思中通过演员名搜索其出演的电影,搜不到可能是没有种子。囧
url = 'http://rs.xidian.edu.cn/bt.php?mod=browse&c=10'#需要查询类别的首页，示例是电影分类
keyword = '大岛由加利'#演员名字
#Cookie可能过期，换成自己的即可
headers = {
        'Cookie': 'Q8qA_2132_saltkey=u22qH2nH; Q8qA_2132_lastvisit=1546613283; Q8qA_2132_lastcheckfeed=296836%7C1546670455; Q8qA_2132_auth=5c83NwYvO%2BUNHZfzR2V4UbrnYMwrlBIthAc2P3rcUxT3G77rpMY2vqrwd73lJQ%2FqrEmoOLcvmFHDHkLgIQSPPr7xMBw; Q8qA_2132_sid=c6YwXx; Q8qA_2132_lip=10.173.228.6%2C1548239272; Q8qA_2132_ulastactivity=d4caou6dFntCL5wShiL7eJQsdcc2fFvupV7wxtIGwj2sEYzZbIKn; Q8qA_2132_sendmail=1; Q8qA_2132_lastact=1548248736%09misc.php%09patch'
    }

def get_one_page(url):
    try:
        reponse = requests.get(url,headers=headers)
        return reponse.text
    except RequestException:
        print('网页请求失败')

def parse_one_home_page(html_home):
    base_url = 'http://rs.xidian.edu.cn'
    doc = pq(html_home)
    tbodys = doc('#wp > div.layout > div.col-main > div > table a').items()
    for tbody in tbodys:
        if tbody.text()[:4] == '[电影]':
            url_simple = base_url + tbody.attr.href[1:]
            html_simple = get_one_page(url_simple)
            if keyword_in_intro_or_not(get_intro(html_simple)):
                print(get_name(html_simple) + '\n' + url_simple)

def get_intro(html_simple):
    doc = pq(html_simple)
    td = doc('.t_f')
    return td.text()#每个帖子简介的html格式不统一，但都位于class=t_f的td标签中

def get_name(html_simple):
    doc = pq(html_simple)
    span = doc('#thread_subject')
    return span.text()

def keyword_in_intro_or_not(intro):
    result = re.search(keyword,intro,re.S)
    if result:
        return True

def main(offset):
    url_home = url + '&page=' + str(offset)
    html_home = get_one_page(url_home)
    parse_one_home_page(html_home)

if __name__ == '__main__':
    pool = Pool()
    pool.map(main,[i for i in range(100)])