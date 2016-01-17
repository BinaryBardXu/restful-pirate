# restful-pirate
契约测试解决方案。

入口：
```http://localhost:8080/entrance```

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

