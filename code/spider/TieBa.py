import requests
from requests.exceptions import RequestException
from pyquery import PyQuery as pq
import os
from multiprocessing.pool import Pool

url = 'https://tieba.baidu.com/p/5993286218?see_lz=1'
url_good = 'https://tieba.baidu.com/f?kw=%E6%88%91%E7%9A%84%E8%8B%B1%E9%9B%84%E5%AD%A6%E9%99%A2&ie=utf-8&tab=good'

def get_one_page(url):
    try:
        url = url
        response = requests.get(url)
        return response.text
    except RequestException:
        print('第'+page_number+'页，网页请求失败')

def get_total_pages(html):   
    doc = pq(html)
    total_pages = doc('#thread_theme_7 > div.l_thread_info > ul > li:nth-child(2) > span:nth-child(2)').text()
    return total_pages

def get_title(html):
    doc = pq(html)
    title = doc('#j_core_title_wrap > h3').text()
    return title

def parse_one_page(html):
    doc = pq(html)
    images = doc('#pb_content .p_postlist .l_post .BDE_Image').items()
    return images

def download_images_to_folder(images,html):
    title = get_title(html)
    if not os.path.exists(title):
        os.mkdir(title)
    try:
        i = 1
        for image in images:
            response = requests.get(image.attr.src) 
            image_path = '{0}/{1}.jpg'.format(title,i)      
            with open(image_path,'wb') as f:
                f.write(response.content)
            print('保存'+title+'第'+str(i)+'张图片成功')
            i = i + 1
    except RequestException:
        print('保存图片失败')

def get_one_page_url(first_url):
    html = get_one_page(first_url)
    doc = pq(html)
    items_a = doc('#thread_list .j_thread_list .threadlist_title a').items()  
    for a in items_a:
        href = a.attr.href
        everyone_url = 'https://tieba.baidu.com'+ href +'?see_lz=1'
        if a.attr.title[:7] == '【雄英支援科】':
            yield (everyone_url)

def main(offset):
    url_good = 'https://tieba.baidu.com/f?kw=%E6%88%91%E7%9A%84%E8%8B%B1%E9%9B%84%E5%AD%A6%E9%99%A2&ie=utf-8&tab=good&cid=&pn=' + str(offset)
    for url in get_one_page_url(url_good):
        html = get_one_page(url)
        download_images_to_folder(parse_one_page(html),html)

if __name__ == '__main__':
    pool = Pool()
    pool.map(main,[i*50 for i in range(4)])
