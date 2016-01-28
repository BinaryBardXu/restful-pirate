##restful-pirate

pirate目标是提供一套完整的契约测试解决方案，并在此过程中学习和实践REST、DDD等技能。

pirate技术上采用spring-hateoas，数据库采用SQLLITE(暂定).目前，pirate只能提供一个类似于Mock平台的功能，功能设计和架构方面都存在着很多问题。下一阶段，在完善功能和架构的同时，也将研究REST安全处理方面的问题。欢迎有兴趣的同学，一起学习：）


##安装方法
1、下载[dolphin](https://github.com/xubitao/dolphin).

   pirate中的众多依赖都在dolphin，所以请务必先下载dolphin，并install到本地。

2、配置pirate.properities中的sqllite.dbpath, 比如：
```
sqllite.dbpath=/Users/xubitao/Documents/products/Pirate/sqllite/pirate.db
```

3、在scripts中的GlobalConfig里配置entrance地址， 比如：

![](https://github.com/xubitao/readme/blob/master/src/main/java/cn/xubitao/readme/images/globalConfig_entrance.png?raw=true)  

4、契约平台完整使用时，需要配备相应的客户端，客户端程序暂不提供，目前仅供服务端的学习和研究。
## API入口
```
http://localhost:8080/entrance
```
```
    {
        "description": "这里是契约测试平台的入口，请参照links选择你的操作。",
        "_links":
        {
            "self":
            {
                "href": "http://localhost:8080/entrance"
            },
            "providers":
            {
                "href": "http://localhost:8080/providers"
            },
            "missedRecords":
            {
                "href": "http://localhost:8080/records?isHit=0"
            },
            "statistic":
            {
                "href": "http://localhost:8080/statistic"
            }
        }
    }

```

##页面入口
```
http://localhost:8080/static/index.html
```

![](https://github.com/xubitao/readme/blob/master/src/main/java/cn/xubitao/readme/images/index.png?raw=true)  
