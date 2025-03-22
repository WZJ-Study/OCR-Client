# PT-OCR

## 简介

> 使用JavaFx开发的本地截图工具。
> 截图框选后进行OCR识别，OCR引擎使用RapidOCR，默认使用ONNX V4模型。

## OCR采集结果

1. 通过Http接口 ``GET http://localhost:8888/ocr/data`` 可以在本机浏览器中查询实时采集结果；
2. 采集结果保存到本地的Sqlite数据库；
3. 采集结果Json文件和截图保存到本地目录下；
4. 通过配置Http回调钩子URL，可以将采集结果推送到远程服务器；
> 这里要求POST接口, 以RequestBody接收JSON文本，返回字符串。
> JSON文件数据结构示例：
> ```json
> {
>     "ipAddr": "172.28.137.29", 
>     "hostName": "PC-OFFICE-WZJ", 
>     "data": [
>         {
>             "name": "字段aaa", 
>             "position": "(73;226;159;162)", 
>             "type": "文本", 
>             "value": "OCR识别结果文本111",
>             "collectTime": "2025-03-22 16:38:43"
>         },
>         {
>             "name": "字段bbb", 
>             "position": "(12;345;456;568)", 
>             "type": "文本", 
>             "value": "OCR识别结果文本222",
>             "collectTime": "2025-03-22 16:38:43"
>         }
>     ],
>     "sendTime": "2025-03-22 16:38:43"
> }
> ```
