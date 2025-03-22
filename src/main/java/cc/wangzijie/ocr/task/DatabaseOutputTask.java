package cc.wangzijie.ocr.task;

import cc.wangzijie.server.entity.OcrSectionResult;
import cc.wangzijie.server.service.IOcrSectionResultService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class DatabaseOutputTask implements Runnable {

    /**
     * OCR识别结果数据库保存服务
     */
    private final IOcrSectionResultService ocrSectionResultService;

    private final List<OcrSectionResult> resultList;

    public DatabaseOutputTask(IOcrSectionResultService ocrSectionResultService, List<OcrSectionResult> resultList) {
        this.ocrSectionResultService = ocrSectionResultService;
        this.resultList = resultList;
    }

    @Override
    public void run() {
        if (this.ocrSectionResultService != null && CollectionUtils.isNotEmpty(resultList)) {
            this.ocrSectionResultService.saveBatch(resultList);
        }
    }
}
