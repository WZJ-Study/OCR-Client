package cc.wangzijie.server.service.impl;

import cc.wangzijie.server.entity.OcrSectionResult;
import cc.wangzijie.server.mapper.IOcrSectionResultMapper;
import cc.wangzijie.server.service.IOcrSectionResultService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.BatchResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class OcrSectionResultServiceImpl implements IOcrSectionResultService {

    @Resource
    private IOcrSectionResultMapper baseMapper;


    @Override
    public List<OcrSectionResult> searchList(String collectTimeBegin, String collectTimeEnd, String name) {
        LambdaQueryWrapper<OcrSectionResult> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(collectTimeBegin)) {
            lqw.ge(OcrSectionResult::getCollectTime, collectTimeBegin);
        }
        if (StringUtils.isNotBlank(collectTimeEnd)) {
            lqw.le(OcrSectionResult::getCollectTime, collectTimeEnd);
        }
        if (StringUtils.isNotBlank(name)) {
            lqw.like(OcrSectionResult::getName, name);
        }
        return baseMapper.selectList(lqw);
    }

    /**
     * 查询VO不分页列表
     *
     * @param entity 查询参数
     * @return 不分页列表
     */
    @Override
    public List<OcrSectionResult> getList(OcrSectionResult entity) {
        LambdaQueryWrapper<OcrSectionResult> lqw = Wrappers.lambdaQuery(entity);
        return baseMapper.selectList(lqw);
    }

    /**
     * 根据ID查询单条VO记录
     *
     * @param id ID
     * @return 单条记录
     */
    @Override
    public OcrSectionResult getById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 创建新增
     *
     * @param entity 实体类对象
     * @return 操作是否成功
     */
    @Override
    public boolean save(OcrSectionResult entity) {
        return baseMapper.insert(entity) > 0;
    }

    /**
     * 批量创建新增
     *
     * @param entityList 实体类对象列表
     * @return 操作是否成功
     */
    @Override
    public boolean saveBatch(Collection<OcrSectionResult> entityList) {
        List<BatchResult> batchResults = baseMapper.insert(entityList);
        log.info("批量新增：{}", batchResults);
        return true;
    }

    /**
     * 编辑修改
     *
     * @param entity 实体类对象
     * @return 操作是否成功
     */
    @Override
    public boolean updateById(OcrSectionResult entity) {
        return baseMapper.updateById(entity) > 0;
    }

    /**
     * 根据IDs批量删除（软删除）
     *
     * @param ids ID列表
     * @return 操作是否成功
     */
    @Override
    public boolean removeByIds(List<Long> ids) {
        return baseMapper.deleteByIds(ids) > 0;
    }
}
