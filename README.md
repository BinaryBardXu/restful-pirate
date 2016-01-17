##restful-pirate
契约测试解决方案。
pirate采用spring-hateoas目前数据库采用SQLLITE.使用时需要在pirate.properities中配置sqllite.dbpath, 比如：
``` 
sqllite.dbpath=/Users/xubitao/Documents/products/Pirate/sqllite/pirate.db
```
API入口：http://localhost:8080/entrance

```
    {
        "description": "这里是契约测试平台的入口，请参照links选择你的操作。",
        "links":
        [
            {
                "rel": "self",
                "href": "http://localhost:8080/entrance"
            },
            {
                "rel": "providers",
                "href": "http://localhost:8080/providers"
            },
            {
                "rel": "missedRecords",
                "href": "http://localhost:8080/records?isHit=0"
            },
            {
                "rel": "statistic",
                "href": "http://localhost:8080/statistic"
            }
        ]
    }
```

页面入口：http://localhost:8080/static/index.html

![](https://github.com/xubitao/readme/blob/master/src/main/java/cn/xubitao/readme/images/index.png?raw=true)  
