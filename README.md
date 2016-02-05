##restful-pirate

pirate目标是提供一套完整的契约测试解决方案(Record+Replay)，并在此过程中学习和实践REST、DDD等技能。

pirate技术上采用spring-hateoas\spring-boot,数据层面采用Mybatis+mysql.目前，pirate只初步完成了Record方面的功能。下一阶段，在完善架构和Replay的同时，也将研究REST安全处理方面的问题。欢迎有兴趣的同学，一起学习：）


##安装方法
1、下载[dolphin](https://github.com/xubitao/dolphin).

   pirate中的众多依赖都在dolphin中，所以请务必先下载dolphin，并install到本地；

2、配置pirate.properities中的jdbc信息,默认的数据库配置可直接使用,但请勿破坏表结构；如果使用自己的MySQL数据库，`/src/main/resources/cn/xubitao/pirate/init_db.sql`中有创建表所需的SQL语句；

3、可以在IDE中从Application类的main方法中直接启动，也可以通过`mvn package`打成jar包后运行；

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
http://localhost:8080/index.html
```

![](https://github.com/xubitao/readme/blob/master/src/main/java/cn/xubitao/readme/images/index.png?raw=true)  
