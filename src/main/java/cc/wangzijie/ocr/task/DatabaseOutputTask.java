package cc.wangzijie.ocr.task;

import cc.wangzijie.server.entity.OcrSectionResult;
import cc.wangzijie.server.service.IOcrSectionResultService;
import cc.wangzijie.ui.model.SettingsWindowModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@Slf4j
public class DatabaseOutputTask implements Runnable {

    /**
     * OCR识别结果数据库保存服务
     */
    private final IOcrSectionResultService ocrSectionResultService;

    private final List<OcrSectionResult> resultList;

    private final boolean enabledFlag;

    public DatabaseOutputTask(IOcrSectionResultService ocrSectionResultService, List<OcrSectionResult> resultList, SettingsWindowModel settingsWindowModel) {
        this.ocrSectionResultService = ocrSectionResultService;
        this.resultList = resultList;
        if (settingsWindowModel == null) {
            this.enabledFlag = true;
        } else {
            this.enabledFlag = settingsWindowModel.isCallbackHookEnabledFlag();
        }
    }

    @Override
    public void run() {
        if (!this.enabledFlag) {
            log.info("==== DatabaseOutputTask ==== 已禁用本地SQLite数据库，跳过！");
            return;
        }
        if (CollectionUtils.isEmpty(resultList)) {
            log.info("==== DatabaseOutputTask ==== 没有需要保存的结果数据，跳过！");
            return;
        }
        if (this.ocrSectionResultService == null) {
            log.error("==== DatabaseOutputTask ==== 数据库服务注入异常，请检查！");
            return;
        }
        this.ocrSectionResultService.saveBatch(resultList);
    }
}
