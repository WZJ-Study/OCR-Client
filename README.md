# PT-OCR

## 简介

> 使用JavaFx开发的本地截图工具。
> 截图框选后进行OCR识别，OCR引擎使用RapidOCR，默认使用ONNX V4模型。

## OCR采集结果

1. 通过Http接口 ``GET http://localhost:8888/ocr/data`` 查询实时采集结果；
2. 采集结果保存到本地的Sqlite数据库；
3. 采集结果Json文件和截图保存到本地目录下；
