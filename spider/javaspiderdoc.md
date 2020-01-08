## 索引

- [HttpClient](#HttpClient)
    - [GET请求](#GET请求)
    - [POST请求](#POST请求)
    - [连接池](#连接池)
    - [请求参数](#请求参数)
- [Jsoup](#Jsoup)
    - [解析HTML](#从一个URL-文件或者字符串中解析HTML)
    - [获取元素和数据](#使用DOM或者CSS选择器来查找-取出数据)
- [WebMagic](#WebMagic)
    - [抽取元素API](#抽取元素API)
    - [获取结果API](#获取结果API)
    - [获取链接](#获取链接)
    - [使用Pipeline保存结果](#使用Pipeline保存结果)
    - [爬虫的配置, 启动和终止](#爬虫的配置-启动和终止)
    - [Scheduler组件](#Scheduler组件)
    - [使用代理](#使用代理)
- [定时任务](#定时任务)
- [网页去重](#网页去重)
- [slf4j配置](#slf4j)


## HttpClient

使用Java的HTTP协议客户端HttpClient来实现抓取网页数据

```java
// 创建HttpClient对象
CloseableHttpClient httpClient = HttpClients.createDefault();

// 创建HttpGet对象, 设置url访问地址
HttpGet httpGet = new HttpGet("http://rs.xidian.edu.cn/portal.php");

CloseableHttpResponse response = null;
try {
    // 使用HttpClient发起请求, 获取response
    response = httpClient.execute(httpGet);

    // 解析响应
    if (response.getStatusLine().getStatusCode() == 200) {
        // 解析html
        String content = EntityUtils.toString(response.getEntity(), "utf8"); 

        // 下载图片
        OutputStream os = new FileOutputStream(new File("c:\\path"));
        response.getEntity().writeTo(os); 
    }

} catch (IOException e) {
    e.printStackTrace();
} finally {
    try {
        // 关闭response
        response.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        // 关闭httpClient
        httpClient.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

- #### GET请求

    - 不带参数get请求

        ```java
        // 创建HttpGet对象, 设置url访问地址
        HttpGet httpGet = new HttpGet("http://rs.xidian.edu.cn/portal.php");
        ```

    - 带参数的get请求

        ```java
        // 设置请求地址是: http://rs.xidian.edu.cn/portal.php/search?keys=Java
        // 1. 创建URIBuilder
        URIBuilder uriBuilder = uriBuilder = new URIBuilder("http://rs.xidian.edu.cn/portal.php/search");
        // 2. 设置参数
        uriBuilder.setParameter("keys", "Java"); // 支持链式调用, 设置多个参数

        // 创建HttpGet对象, 设置url访问地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        ```

- #### POST请求

    - 不带参数post请求

        ```java
        // 创建HttpPost对象, 设置url访问地址
        HttpPost httpPost = new HttpPost("http://rs.xidian.edu.cn/portal.php");
        ```

    - 带参数的post请求

        url地址(http://rs.xidian.edu.cn/portal.php/search)中没有参数, 参数keys=java放到表单中进行提交

        ```java
        // 创建HttpPost对象, 设置url访问地址
        HttpPost httpPost = new HttpPost("http://rs.xidian.edu.cn/portal.php/search");

        // 1. 声明List集合, 封装表单中的参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("keys", "Java"));

        // 2. 创建表单的Entity对象, 第一个参数就是封装好的表单数据, 第二个参数就是编码
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf8");

        // 3. 设置表单的Entity对象到Post请求中
        httpPost.setEntity(formEntity);
        ```

- #### 连接池

    需要使用HttpClient时从连接池中取, 用完不用关闭(由连接池统一管理)

    ```java
    public static void main(String[] args) {
        // 创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        // 设置连接数(100个HttpClient)
        cm.setMaxTotal(100);

        // 设置每个主机的最大连接数(每个网站分10个HttpClient)
        cm.setDefaultMaxPerRoute(10);

        // 使用连接池管理器发起请求
        doGet(cm);
        doGet(cm);
    }

    private static void doGet(PoolingHttpClientConnectionManager cm) {
        // 不是每次创建新的HttpClient, 而是从连接池中获取HttpClient对象
        // 不能关闭HttpClient, 由连接池管理HttpClient
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }
    ```

- #### 请求参数

    由于网络或者目标服务器的原因, 请求需要较长时间才能完成, 可以自定义相关时间

    ```java
    // 配置请求信息
    RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) // 创建连接的最长时间, 单位是毫秒
            .setConnectionRequestTimeout(500) // 设置获取连接的最长时间, 单位是毫秒
            .setSocketTimeout(10 * 1000) // 设置数据传输的最长时间, 单位是毫秒
            .build();

    // 给请求设置请求信息
    httpGet.setConfig(config);
    ```

## Jsoup

HTML解析器, 可直接解析某个URL地址, HTML文本或者字符串

- #### 从一个URL 文件或者字符串中解析HTML

    - 从URL中解析HTML

        ```java
        // 解析url地址, 第一个参数是访问的url, 第二个参数是访问时候的超时时间
        Document document = Jsoup.parse(new URL("http://rs.xidian.edu.cn/portal.php"), 1000);
        ```

    - 从字符串中解析HTML

        ```java
        // 使用工具类读取文件, 获取字符串(commons-io)
        String content = FileUtils.readFileToString(new File("c:\\path"), "utf8");

        // 解析字符串
        Document document = Jsoup.parse(content);
        ```

    - 从文件中解析HTML

        ```java
        // 解析文件 "c:\\path\\text.html"
        Document document = Jsoup.parse(new File("c:\\path"), "utf8");
        ```

- #### 使用DOM或者CSS选择器来查找 取出数据

    - 获取元素
    
        - 使用dom来获取元素
    
            ```java
            // 根据id查询元素
            Element element = document.getElementById("idValue");
            // 根据标签查询元素
            element = document.getElementsByTag("tag").first();
            // 根据class查询元素
            element = document.getElementsByClass("classValues").first(); // 可以包含一个或多个classValue
            // 根据属性获取元素
            element = document.getElementsByAttribute("attributeKey").first();
            element = document.getElementsByAttributeValue("attributeKey", "attributeValue").first();
            ```

        - 使用Selector选择器获取元素

            ```java
            // 通过标签查找元素
            Elements elements = document.select("tag");
            // 通过id查找元素
            Element element = document.select("#idValue").first();
            // 通过class名称查找元素
            element = document.select(".classValue").first();
            // 利用属性查找元素
            element = document.select("[attributeKey]").first();
            // 利用属性值查找元素
            elements = document.select("[attributeValue]");
            ```

        - Selector选择器组合使用

            ```java
            // 元素 + id/class/属性名
            // el#idValue | el.classValue | el[attributeKey]
            // 任意组合
            // el[attributeKey].classValue
            Element element = document.select("tag#idValue").first();
            // ancestor child: 查找某个父元素下的所有子元素
            // parent > child: 查找某个父元素下的所有直接子元素
            // parent > *: 查找某个父元素下所有的所有直接子元素
            element = document.select(".classValue tag").first();
            // 可是使用:nth-child(n)选择第几个元素, 如下选择第一个元素
            // 注意：需要使用>, 就是直接子元素才可以选择第几个元素
            page.getHtml().css("div#news_div > ul > li:nth-child(1) a").toString();
            ```

    - 从元素中获取数据

        ```java
        // 从元素中获取id
        String str = "";
        str = element.id();
        // 从元素中中获取className
        str = element.className();
        Set<String> classSet = element.classNames();
        // 从元素中获取属性的值attr
        str = element.attr("id");
        // 从元素中获取所有属性attributes
        Attributes attributes = element.attributes(); // attributes.toString()
        // 从元素中获取文本内容text
        str = element.text();
        // 从多个元素中获取第index个元素的内容
        str = elements.first().text();
        str = elements.get(index).text();
        ```

## WebMagic

使用webmagic爬取网页

```java
public class JobProcessor implements PageProcessor {

    // 解析页面
    public void process(Page page) {
        // 得到page解析的地址
        page.getUrl();
        // 添加待解析的链接到队列中
        page.addTargetRequests(page.getHtml().links()
                // 所有符合https://www.jd.com/news.\\w+?.*正则表达式的url地址
                .regex("(https://www.jd.com/news.\\w+?.*)").all());
        // 解析返回的数据page, 并且把解析的结果放到ResultItems中(默认打印到控制台)
        page.putField("url", page.getHtml().css("div.mt").all);
    }

    private Site site = Site.me()
                .setCharset("UTF-8") // 编码
                .setSleepTime(1) // 抓取间隔时间
                .setTimeOut(1000 * 10) // 超时时间
                .setRetrySleepTime(3000) // 重试时间
                .setRetryTimes(3); // 重试次数

    public Site getSite() {
        return site;
    }

    // 主函数, 执行爬虫
    public static void main(String[] args) {
        Spider.create(new JobProcessor())
                .addUrl("https://www.jd.com/moreSubject.aspx")
                .run();
    }

}

```

page.getHtml()返回的是一个Html对象, 它实现了Selectable接口. 这个接口包含的方法分为两类: 抽取部分和获取结果部分

- #### 抽取元素API

    抽取元素API返回的都是一个Selectable接口, 支持链式调用的. 该接口实现 `Selectable.nodes()` 用于获取包含所有抽取的Selectable元素的列表, 可以使用 `get(index)` 方法选取第index个Selectable元素

    - XPath

        ```java
        // 获取属性class=mt的div标签里面的h1标签的内容
        page.getHtml().xpath("//div[@class=mt]/h1/text()");
        ```

    - CSS选择器

        ```java
        // div.mt>h1表示class为mt的div标签下的直接子元素h1标签中的内容
        // 不同于Jsoup的.select("div.mt>h1").text()方法取所有标签中的内容, .css("div.mt>h1", "text")只取最中间标签中的内容
        page.getHtml().css("div.mt>h1", "text").toString(); // css(css选择器, 需要获取属性的属性名)
        ```

    - 正则表达式

        ```java
        page.getHtml().css("div#news_div > ul > li:nth-child(1) a").regax(".*江苏.*").all();
        ```

        |方法|说明|示例|
        |---|---|---|
        |xpath(String xpath)|使用XPath选择|html.xpath("//div[@class='title']")|
        |$(String selector)|使用Css选择器选择|html.$("div.title")|
        |$(String selector,String attr)|使用Css选择器选择|html.$("div.title","text")|
        |css(String selector)|功能同$(), 使用Css选择器选择|html.css("div.title")|
        |links()|选择所有链接|html.links()|
        |regex(String regex)|使用正则表达式抽取|html.regex("\(.\*?)\")|

    

- #### 获取结果API

    链式调用结束时用于得到字符串类型结果(统一了XPath, CSS选择器或者正则表达式抽取后获得结果的API)

    |方法|说明|示例|
    |---|---|---|
    |get()|返回一条String类型的结果(返回第一条)|String link= html.links().get()|
    |toString()|同get(), 返回一条String类型的结果(返回第一条)|String link= html.links().toString()|
    |all()|返回所有抽取结果|List links= html.links().all()|

- #### 获取链接

    将新获得的需要解析的地址加入到待抓取的队列中去, 就会自动下载

    ```java
    page.addTargetRequests(page.getHtml().links()
                    // 所有符合https://www.jd.com/news.\\w+?.*正则表达式的url地址
                    .regex("(https://www.jd.com/news.\\w+?.*)").all());
    ```



- #### 使用Pipeline保存结果

    - 已有的Pipeline

        |类|说明|备注|
        |---|---|---|
        |ConsolePipeline|输出结果到控制台|抽取结果需要实现toString方法|
        |FilePipeline|保存结果到文件|抽取结果需要实现toString方法|
        |JsonFilePipeline|JSON格式保存结果到文件|	
        |ConsolePageModelPipeline|(注解模式)输出结果到控制台|
        |FilePageModelPipeline|(注解模式)保存结果到文件	|
        |JsonFilePageModelPipeline|(注解模式)JSON格式保存结果到文件|想持久化的字段需要有getter方法|


    - 默认使用 `ConsolePipeline` 这个内置的Pipeline来在控制台输出结果, 若想要把结果用保存到文件中, 只需将Pipeline的实现换成 `FilePipeline` 即可

        ```java
        public static void main(String[] args) {
            Spider.create(new JobProcessor())
                    // 初始访问url地址
                    .addUrl("https://www.jd.com/moreSubject.aspx")
                    .addPipeline(new FilePipeline("D:/webmagic/"))
                    .run();
        }
        ```

    - 也可以自己实现Pipeline接口, 自定义输出方式, Pipeline的接口定义如下: 

        ```java
        public interface Pipeline {
            // ResultItems保存了抽取结果, 它是一个Map结构
            // 在page.putField(key,value)中保存的数据
            // 可以通过ResultItems.get(key)获取
            public void process(ResultItems resultItems, Task task);
        }
        ```

    - 一个Spider可以有多个Pipeline, 使用Spider.addPipeline()即可增加一个Pipeline. 这些Pipeline都会得到处理

        ```java
        spider.addPipeline(new ConsolePipeline()).addPipeline(new FilePipeline())
        ```

- #### 爬虫的配置 启动和终止

    - Spider

        使用一个PageProcessor实现类创建Spider对象, 后使用run()启动爬虫, 同时可以通过set方法对Spider的其他组件(Downloader, Scheduler, Pipeline)进行设置

        |方法|说明|示例|
        |---|---|---|
        |create(PageProcessor)|创建Spider|Spider.create(new GithubRepoProcessor())|
        |addUrl(String…)|添加初始的URL|spider.addUrl("http://webmagic.io/docs/")|
        |thread(n)|开启n个线程|spider.thread(5)|
        |run()|启动, 会阻塞当前线程执行|spider.run()|
        |start()/runAsync()|异步启动, 当前线程继续执行|spider.start()|
        |stop()|停止爬虫|spider.stop()|
        |addPipeline(Pipeline)|添加一个Pipeline, 一个Spider可以有多个Pipeline|spider.addPipeline(new ConsolePipeline())|
        |setScheduler(Scheduler)|设置Scheduler, 一个Spider只能有个一个Scheduler|spider.setScheduler(new RedisScheduler())|
        |setDownloader(Downloader)|设置Downloader, 一个Spider只能有个一个Downloader|spider.setDownloader(new SeleniumDownloader())|
        |get(String)|同步调用, 并直接取得结果|ResultItems result = spider.get("http://webmagic.io/docs/")|
        |getAll(String...)|同步调用, 并直接取得一堆结果|List<ResultItems> results = spider.getAll("http://webmagic.io/docs/", "http://webmagic.io/xxx")|                                 



    - 爬虫配置Site

        Site.me()可以对爬虫和站点本身进行配置
        ```java
        private Site site = Site.me()
                .setCharset("UTF-8") // 编码
                .setSleepTime(1) // 抓取间隔时间
                .setTimeOut(1000 * 10) // 超时时间
                .setRetrySleepTime(3000) // 重试时间
                .setRetryTimes(3); // 重试次数
        ```

        |方法|说明|示例|
        |---|---|---|
        |setCharset(String)|设置编码|site.setCharset("utf-8")|
        |setUserAgent(String)|设置UserAgent|site.setUserAgent("Spider")|
        |setTimeOut(int)|设置超时时间, 单位是毫秒|site.setTimeOut(3000)|
        |setRetryTimes(int)|设置重试次数|site.setRetryTimes(3)|
        |setCycleRetryTimes(int)|设置循环重试次数|site.setCycleRetryTimes(3)|
        |addCookie(String,String)|添加一条cookie|site.addCookie("dotcomt_user","code4craft")|
        |setDomain(String)|设置域名, 需设置域名后, addCookie才可生效|site.setDomain("github.com")|
        |addHeader(String,String)|添加一条addHeader|site.addHeader("Referer","https://github.com")|
        |setHttpProxy(HttpHost)|设置Http代理|site.setHttpProxy(new HttpHost("127.0.0.1",8080))|

- #### Scheduler组件

    - 对待抓取的URL队列进行管理

        如下为WebMagic内置的Scheduler: 

        |类|说明|备注|
        |---|---|---|
        |DuplicateRemovedScheduler|抽象基类, 提供一些模板方法|继承它可以实现自己的功能|
        |QueueScheduler|使用内存队列保存待抓取URL||
        |PriorityScheduler|使用带有优先级的内存队列保存待抓取URL|耗费内存较QueueScheduler更大, 但是当设置了request.priority之后, 只能使用PriorityScheduler才可使优先级生效|
        |FileCacheQueueScheduler|使用文件保存抓取URL, 可以在关闭程序并下次启动时, 从之前抓取到的URL继续抓取|需指定路径, 会建立.urls.txt和.cursor.txt两个文件|
        |RedisScheduler|使用Redis保存抓取队列, 可进行多台机器同时合作抓取|需要安装并启动redis|

    - 对已抓取的URL进行去重

        - 去重部分被单独抽象成了一个接口: DuplicateRemover. 如下为WebMagic提供的两种去重方式: 

            |类|说明|
            |---|---|
            |HashSetDuplicateRemover|使用HashSet来进行去重, 占用内存较大|
            |BloomFilterDuplicateRemover|使用BloomFilter来进行去重, 占用内存较小, 但是可能漏抓页面|

            RedisScheduler是使用Redis的set进行去重, 其他的Scheduler默认都使用HashSetDuplicateRemover来进行去重

        - 如果要使用BloomFilter, 必须要加入以下依赖: 

            ```xml
            <!--WebMagic对布隆过滤器的支持-->
            <dependency>
                <groupId>com.google.guava</groupId>
                <artifactId>guava</artifactId>
                <version>16.0</version>
            </dependency>
            ```

            修改代码, 添加布隆过滤器
            ```java
            public static void main(String[] args) {
                Spider.create(new JobProcessor())
                        //初始访问url地址
                        .addUrl("https://www.jd.com/moreSubject.aspx")
                        .addPipeline(new FilePipeline("D:/webmagic/"))
                        .setScheduler(new QueueScheduler()
                                .setDuplicateRemover(new BloomFilterDuplicateRemover(10000000))) //参数设置需要对多少条数据去重
                        .thread(1)//设置线程数
                        .run();
            }
            ```

- #### 使用代理

    WebMagic使用的代理API是ProxyProvider, 由HttpClientDownloader设置

    |API|说明|
    |---|---|
    |HttpClientDownloader.setProxyProvider(ProxyProvider proxyProvider)|设置代理|

    - ProxyProvider有一个默认实现: SimpleProxyProvider. 它是一个基于简单Round-Robin的, 没有失败检查的ProxyProvider

        ```java
        @Scheduled(fixedDelay = 10000)
        public void testProxy() {
            // 创建下载器Downloader
            HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
            // 给下载器设置代理服务器信息
            httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("39.137.77.68",80)));

            Spider.create(new ProxyTest())
                    .addUrl("http://ip.chinaz.com/getip.aspx")
                    .setDownloader(httpClientDownloader) // 设置下载器
                    .run();
        }
        ```

    - 如果需要根据实际使用情况对代理服务器进行管理(例如校验是否可用, 定期清理, 添加代理服务器等), 需要自己实现APIProxyProvider

    - 可以访问网址http://ip.chinaz.com/getip.aspx 测试当前请求的ip

## 定时任务

Spring内置的Spring Task, 在引导类前加@EnableScheduling注解开启定时任务, 再在需要定时启动的方法前加@Scheduled注解启动定时任务, 其属性如下: 

- cron: cron表达式, 指定任务在特定时间执行

    - Cron表达式

        cron的表达式是字符串, 由七个被空格分开的子表达式组成, 用于描述个别细节的时间表, 代表如下: 

        1. Seconds: 有效值为数字[0, 59]
        2. Minutes: 有效值为数字[0, 59]
        3. Hours: 有效值数字[0, 23]
        4. Day-of-Month: 有效值数字[1, 31], 需要注意个别月份
        5. Month: 有效值数字[0, 11]或者字符串: JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC 
        6. Day-of-Week: 有效值数字[1, 7](其中1代表星期日)或者字符串: SUN, MON, TUE, WED, THU, FRI, SAT
        7. Year(可选字段)

        "/": 为特别单位, 表示为"每"如"0/15"表示每隔15分钟执行一次, "0"表示为从"0"分开始, "3/20"表示表示每隔20分钟执行一次, "3"表示从第3分钟开始执行
        "?": 表示每月的某一天, 或第周的某一天
        "L": 用于每月, 或每周, 表示为每月的最后一天, 或每个月的最后星期几如"6L"表示"每月的最后一个星期五"
        
        例: `0 0 12 ? * WED` 在每星期三下午12:00 执行, "*"代表整个时间段

- `fixedDelay` : 上一次任务执行完后多久再执行, 参数类型为long, 单位ms

- fixedDelayString: 与fixedDelay含义一样, 只是参数类型变为String

- fixedRate: 按一定的频率执行任务, 参数类型为long, 单位ms

- fixedRateString: 与fixedRate的含义一样, 只是将参数类型变为String

- `initialDelay` : 延迟多久再第一次执行任务, 参数类型为long, 单位ms

- initialDelayString: 与initialDelay的含义一样, 只是将参数类型变为String

- zone: 时区, 默认为当前时区, 一般没有用到

## 网页去重

不光url需要去重, 下载的内容也需要去重, 使用[simhash工程](https://github.com/CreekLou/simhash.git)


## slf4j

log4j.properties

```properties
log4j.rootLogger=DEBUG,A1

log4j.appender.A1=org.apache.log4j.ConsoleAppender
log4j.appender.A1.layout=org.apache.log4j.PatternLayout
log4j.appender.A1.layout.ConversionPattern=%-d{yyyy-MM-dd HH:mm:ss,SSS} [%t] [%c]-[%p] %m%n
```