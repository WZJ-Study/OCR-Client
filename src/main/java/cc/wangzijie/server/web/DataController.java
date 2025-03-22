package cc.wangzijie.server.web;

import cc.wangzijie.server.entity.OcrSectionResult;
import cc.wangzijie.server.entity.OcrSectionResultDisplayVO;
import cc.wangzijie.ui.model.DataListAreaModel;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/ocr")
public class DataController {

    @Resource
    private DataListAreaModel dataListAreaModel;

    @GetMapping("/data")
    public List<OcrSectionResultDisplayVO> currentDataList() {
        ObservableList<OcrSectionResult> list = dataListAreaModel.getOcrSectionResultList();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(OcrSectionResult::toDisplay).collect(Collectors.toList());
    }

    @PostMapping("/callback")
    public String callback(@RequestBody String jsonData) {
        log.info("==== callback ==== 接收到JSON数据： {}", jsonData);
        return jsonData;
    }

}
