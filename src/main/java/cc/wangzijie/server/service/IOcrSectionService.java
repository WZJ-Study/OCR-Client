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
     */
    void save(OcrSection entity);

    /**
     * 根据ID更新
     *
     * @param id 区域ID
     * @param name 区域名称
     * @param type 区域类型
     */
    void updateNameTypeById(Long id, String name, String type);

    OcrSection createNewSection(Rectangle rect);
}
