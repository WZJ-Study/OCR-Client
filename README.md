使用JavaFx开发的本地截图工具。
截图框选后进行OCR识别，OCR引擎使用RapidOCR，默认使用ONNX V4模型。
采集结果可以通过Http接口进行实时查询：
GET http://localhost:8888/ocr/data
另外采集结果保存到本地的Sqlite数据库里。
