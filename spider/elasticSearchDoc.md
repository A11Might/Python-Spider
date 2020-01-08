## 索引

- [Elasticsearch](#Elasticsearch)
    - [Elasticsearch术语](#Elasticsearch术语)
    - [配置config/elasticsearch.yml](#配置config/elasticsearch.yml)
    - [中文分词](#中文分词)
    - [使用命令行作为客户端](#使用命令行作为客户端)
    - [使用postman代替web客户端访问es](#用postman代替web客户端访问es)

- [Spring整合Elasticsearch](#Spring整合Elasticsearch)
    - [引入依赖](#引入依赖)
    - [配置Elasticsearch](#配置Elasticsearch)
    - [与redis冲突(netty)](#与redis冲突(netty))
    - [Spring Data Elasticsearch](#Spring-Data-Elasticsearch)

## Elasticsearch

- #### Elasticsearch术语

    - 索引 类型 文档 字段

        database table row col (对应mysql数据库)

    - 索引 文档 字段

        6.0版本之后

        table row col (对应mysql数据库)

    - 集群(多个服务器), 节点(其中一个服务器), 分片(将索引分为多个部分存储), 副本(对分片的备份, 一个副本可以包含多个副本)

- #### 配置config/elasticsearch.yml

    ```yml
    # 集群名字
    cluster.name: nowcoder
    # 数据存储位置
    path.data: d:\work\data\elasticsearch-6.4.3\data
    # 日志存储位置
    path.logs: d:\work\data\elasticsearch-6.4.3\logs
    ```

- #### 中文分词 

    - [elasticsearch-analysis-ik](https://github.com/medcl/elasticsearch-analysis-ik)

    - 需要解压到plugins\ik目录下

    - 可以添加自定义dic文件到plugins\ik\config目录中(添加后需配置到文件夹下IKAnalyzer.cfg.xml中)


- #### 使用命令行作为客户端

    配置环境变量后
    
    ```sh
    # 查看es集群健康状态
    # GET请求表示获取数据, 访问服务器的端口, ?v显示数据时显示标题
    curl -X GET "localhost:9200/_cat/health?v"

    # 查看es集群中的节点
    curl -X GET "localhost:9200/_cat/nodes?v"

    # 查看es集群中的索引
    curl -X GET "localhost:9200/_cat/indices?v"

    # 创建索引
    # PUT表示推送数据, /test为要建的索引名字
    curl -X PUT "localhost:9200/test"

    # 删除索引
    # DELETE表示删除数据, /test为要删的索引名字
    curl -X DELETE "localhost:9200/test"
    ```

- #### 用postman代替web客户端访问es

    - 查看es集群中的索引
        
        使用GET请求, 访问localhost:9200/_cat/indices?v

    - 向es集群中插入数据(若指定索引不存在, 会自动创建)

        使用PUT请求, 访问localhost:9200/test/_doc/1, 数据提交在请求体body中带上数据

        _doc为类型(mysql中的表名), 6.0后废弃, 仅起占位符作用; 1为插入数据id


    - 从es中查数据, 同上

        使用GET请求, 访问localhost:9200/test/_doc/1

    - 修改es中的数据
    
        插入新数据(底层先删除原来数据, 再新建新数据)

    - 删除数据, 同上

        使用DELETE请求, 访问localhost:9200/test/_doc/1

    - 搜索

        1. 使用GET请求, 访问localhost:9200/test/_search

        2. 有条件搜索

            使用GET请求, 访问localhost:9200/test/_search?q=title:keyword

            使用GET请求, 访问localhost:9200/test/_searchq=content:keyword

        3. 复杂搜索条件

             使用GET请求, 访问localhost:9200/test/_search
        通过请求体body提交复杂的搜索条件

            ```json
            {
                "query":{
                    "multi_match":{
                        "query":"西安",
                        "fields":["title","content"]
                    }
                }
            }
            ```

## Spring整合Elasticsearch

- #### 引入依赖
    - spring-boot-starter-data-elasticsearch

- #### 配置Elasticsearch

    `http9200; tcp9300`

    ```properties
    # ElasticsearchProperties
    spring.data.elasticsearch.cluster-name=nowcoder
    spring.data.elasticsearch.cluster-nodes=127.0.0.1:9300
    ```
- #### 与redis冲突(netty)

    在引导类application.class中添加

    ```java
    @PostConstruct
    public void init() {
        // 解决netty启动冲突问题
        // see Netty4Utils.setAvailableProcessors()
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
    ```

- #### Spring Data Elasticsearch
    - ElasticsearchRepository

        - 通过在实体类上加注解进行配置, 将实体类与es索引建立关联, Spring底层就会自动生成对应的实现类, 如下: 

            ```java
            //        索引名                     类型名         分片数       副本数
            @Document(indexName = "entityName", type = "_doc", shards = 6, replicas = 3)
            public class EntityName {

                @Id
                private int id;

                @Field(type = FieldType.Integer)
                private int userId;

                // ik_max_word: 存储时使用的分词器, 拆分出较多的词汇来增加搜索范围
                // ik_smart: 搜索时使用的分词器, 拆分出较少的词汇来满足搜索需要
                @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
                private String title;

                @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
                private String content;

                set()/get()/toString();
            }
            ```

        - 定义Repository接口

            es可以被看做是一个特殊的数据库

            ```java
            @Repository
            // 父接口elasticsearchRepository已经事先定义好对es服务器访问的增删改查各种方法, spring会自动实现
            //                                                       需要定义<接口处理实体的类型, 主键的类型>
            public interface EntityNameRepository extends ElasticsearchRepository<EntityName, Integer> {

            }
            ```
        
        - 使用

            ```java
            public class ElasticsearchService {

                @Autowired
                private EntityNameMapper entityNameMapper;

                @Autowired
                private EntityNameRepository entityNameRepository;

                // 特殊情况, 当repository解决不了时, 使用template
                @Autowired
                private ElasticsearchTemplate elasticsearchTemplate;

                // 向es中插入数据
                entityNameRepository.save(entityNameMapper.selectEntityNameById(1));
                entityNameRepository.saveAll(entityNameMapper.selectEntityNames(1, 0, 100, 0));

                // 在es中修改数据
                entityNameRepository.save(entityNameMapper.selectEntityNameById(1));

                // 从es中删除数据
                entityNameRepository.deleteById(1);
                entityNameRepository.deleteAll();

                // 在es中搜索
                // 1. 构建搜索条件(关键词, 排序方式, 分页方式, 高亮显示)
                SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.multiMatchQuery(keyword, "title", "content"))
                    .withSort(SortBuilders.fieldSort("userId").order(SortOrder.DESC))
                    .withPageable(PageRequest.of(current, limit)) // (当前页数, 每页数据数)
                    .withHighlightFields(
                            new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                            new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                    ).build();
                // 2. 搜索
                // 方法一
                // 底层获取到了高亮显示的值, 但是没有返回
                Page<DiscussPost> page = entityNameRepository.search(searchQuery);
                page.getTotalElements(); // 一共有多少条数据匹配
                page.getTotalPages(); // 按照当前分页条件一共有多少页
                page.getNumber(); // 当前处在第几页
                page.getSize(); // 每页显示几条数据

                // 方法二
                // 也是返回Page
                elasticsearchTemplate.queryForPage(searchQuery, EntityName.class, new SearchResultMapper() {
                    // 实现SearchResultMapper接口, 来自定义数据合并
                    @Override
                    public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                        // 搜索命中的数据
                        SearchHits hits = searchResponse.getHits();
                        if (hits.getTotalHits() <= 0) {
                            return null;
                        }

                        List<EntityName> entityNameList = new ArrayList<>();
                        for (SearchHit hit : hits) {
                            EntityName entityName = new EntityName();
                            // hit为json格式数据, 将json数据封装为map后取值
                            // 先将值toString()后再转为对应类型
                            String id = hit.getSourceAsMap().get("id").toString();
                            entityName.setId(Integer.valueOf(id));

                            String userId = hit.getSourceAsMap().get("userId").toString();
                            entityName.setUserId(Integer.valueOf(userId));

                            // 先将没有高亮的字段设置进去, 再设置有高亮的字段(不直接设置高亮字段, 防止若当前字段没有高亮, 会没有设置)
                            String title = hit.getSourceAsMap().get("title").toString();
                            entityName.setTitle(title);

                            String content = hit.getSourceAsMap().get("content").toString();
                            entityName.setContent(content);

                            // 处理高亮显示的结果
                            HighlightField titleField = hit.getHighlightFields().get("title");
                            if (titleField != null) {
                                // 设置第一段匹配段落的高亮
                                entityName.setTitle(titleField.getFragments()[0].toString());
                            }

                            HighlightField contentField = hit.getHighlightFields().get("content");
                            if (contentField != null) {
                                entityName.setContent(contentField.getFragments()[0].toString());
                            }

                            entityNameList.add(entityName);
                        }

                        return new AggregatedPageImpl(entityNameList, pageable,
                                hits.getTotalHits(), searchResponse.getAggregations(), searchResponse.getScrollId(), hits.getMaxScore());
                    }

                    @Override
                    public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                        return null;
                    }
                });
            }
            ```

    - ElasticsearchTemplate