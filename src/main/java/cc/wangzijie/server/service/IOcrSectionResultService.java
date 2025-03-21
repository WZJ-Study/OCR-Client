package cc.wangzijie.server.service;

import cc.wangzijie.server.entity.OcrSectionResult;

import java.util.Collection;
import java.util.List;

public interface IOcrSectionResultService {

    List<OcrSectionResult> searchList(String collectTimeBegin, String collectTimeEnd, String name);

    /**
     * 创建新增
     *
     * @param entity  实体类对象
     * @return 操作是否成功
     */
    boolean save(OcrSectionResult entity);

    /**
     * 批量创建新增
     *
     * @param entityList 实体类对象列表
     * @return 操作是否成功
     */
    boolean saveBatch(Collection<OcrSectionResult> entityList);


}
