# Enviroment
  - Elasticsearch 7.5.1 (IP: **172.17.80.26:9200**)
  - Kibana 7.5.1 (IP: **172.17.80.26:5601**)

# Exercise
## Task 1: 
  - Yêu cầu: Viết truy vấn DSL đưa ra các văn bản liên quan nhất đến các keyword : "an toàn", "đường bộ", "đường sắt" trong năm 2013.

  - Index dữ liệu thực hiện Task 1+2:
```
curl -H "Content-Type: application/json" -XPOST "172.17.80.26:9200/dantri/_bulk?pretty&refresh" --data-binary "@data/data_1.json"
```
  - **Query DSL Task 1**
```
GET dantri/_search
{
    "query": {
        "bool" : {
            "should": [
                {
                    "multi_match" : {
                    "query":  "an toàn",
                    "fields": [ "title", "description", "content"]
                }
            },
        {
            "multi_match" : {
                "query":  "đường bộ",
                    "fields": [ "title", "description", "content"]
                }
            },
        {
            "multi_match" : {
                "query":  "đường sắt",
                    "fields": [ "title", "description", "content"]
                }
            },
        {
            "range": {
                "time": {
                    "gte": 1356998400,
                    "lt": 1388534400
                        }
                    }
                }
            ]
        }
    }
}
```
## Task 2:
  - Yêu cầu: Viết truy vấn DSL đưa các văn bản có title bắt đầu bằng "Hà Nội", nhưng description lại không được phép chứa từ "Hà Nội", sắp xếp theo thời gian giảm dần (trường time) :
  - **Query DSL Task 2**
```
GET dantri/_search
{
    "query": {
        "bool" : {
            "must" : {
                "prefix": {
                "title.keyword": "Hà Nội"
            }
            },
            "must_not" :{
                "match_phrase": {
                    "description": {
                        "query": "Hà Nội"
                    }
                }
            }
        }
    },
    "sort" : [
        { "time" : "desc" }
    ]
}
```
## Task 3:
  - Chạy file ```__main__.py``` dùng pyvi tokenize các từ tiếng việt, sửa tên index tạo file json mới.
  - Truy cập server 172.17.80.26 với user "hadoop" & password : 1

``` 
ssh hadoop@712.17.80.26 
```

``` 
cd huyvv20/ 
```

  - Đẩy dữ liệu lên index ```title_suggest_huyvv20``` bằng câu lệnh:

```
curl -H "Content-Type: application/json" -XPOST "172.17.80.26:9200/_bulk?pretty&refresh" --data-binary "@huyvv20_data_3.json" 
```
  - Tạo index dữ liệu Task 3:
 ```
 PUT title_suggest_huyvv20
{
    "mappings": {
        "properties" : {
            "suggest_title" : {
                "type" : "completion"
            }
        }
    }
}
```
  - **Query Task 3**
```
GET /title_suggest_huyvv20/_search
    {
        "suggest": {
            "title-suggest": {
                "prefix": "ha",
                "completion": {
                    "field": "suggest_title",
                    "size": 30,
                    "fuzzy": {}
                }
            }
        }
    }
```

  - Call request sử dụng Pastman & Spring
```
http://localhost:8080/get/keyword/size
```
  - Trong đó:
    + ```keyword``` : từ cần search
    + ```size``` : số lượng từ cần trả về
    + Ví dụ: ```http://localhost:8080/get/thanh/10```
