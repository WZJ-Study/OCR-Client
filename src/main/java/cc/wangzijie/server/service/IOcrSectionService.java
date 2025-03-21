package cc.wangzijie.server.service;



import cc.wangzijie.server.entity.OcrSection;
import cc.wangzijie.server.entity.OcrSectionResult;
import javafx.scene.shape.Rectangle;

import java.util.List;

public interface IOcrSectionService {

    /**
     * 创建新增
     *
     * @param entity  实体类对象
     * @return 操作是否成功
     */
    boolean save(OcrSection entity);

    OcrSection createNewSection(Rectangle rect);
}
