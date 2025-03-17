package cc.wangzijie.server.service;

import cc.wangzijie.server.entity.OcrSectionResult;

import java.util.Collection;
import java.util.List;

public interface IOcrSectionResultService {

    List<OcrSectionResult> searchList(String collectTimeBegin, String collectTimeEnd, String name);

    /**
     * 查询VO不分页列表
     *
     * @param entity     查询参数
     * @return 不分页列表
     */
    List<OcrSectionResult> getList(OcrSectionResult entity);

    /**
     * 根据ID查询单条VO记录
     *
     * @param id      ID
     * @return 单条记录
     */
    OcrSectionResult getById(Long id);

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

    /**
     * 编辑修改
     *
     * @param entity  实体类对象
     * @return 操作是否成功
     */
    boolean updateById(OcrSectionResult entity);

    /**
     * 根据IDs批量删除（软删除）
     *
     * @param ids     ID列表
     * @return 操作是否成功
     */
    boolean removeByIds(List<Long> ids);


}
