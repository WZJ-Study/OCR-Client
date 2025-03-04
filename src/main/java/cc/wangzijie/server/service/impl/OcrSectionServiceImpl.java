package cc.wangzijie.server.service.impl;

import cc.wangzijie.server.entity.OcrSection;
import cc.wangzijie.server.mapper.IOcrSectionMapper;
import cc.wangzijie.server.service.IOcrSectionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import javafx.scene.shape.Rectangle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class OcrSectionServiceImpl implements IOcrSectionService {

    @Resource
    private IOcrSectionMapper baseMapper;


    /**
     * 查询VO不分页列表
     *
     * @param entity 查询参数
     * @return 不分页列表
     */
    @Override
    public List<OcrSection> getList(OcrSection entity) {
        LambdaQueryWrapper<OcrSection> lqw = Wrappers.lambdaQuery(entity);
        return baseMapper.selectList(lqw);
    }

    /**
     * 根据ID查询单条VO记录
     *
     * @param id ID
     * @return 单条记录
     */
    @Override
    public OcrSection getById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 创建新增
     *
     * @param entity 实体类对象
     * @return 操作是否成功
     */
    @Override
    public boolean save(OcrSection entity) {
        return baseMapper.insert(entity) > 0;
    }

    /**
     * 编辑修改
     *
     * @param entity 实体类对象
     * @return 操作是否成功
     */
    @Override
    public boolean updateById(OcrSection entity) {
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

    @Override
    public OcrSection createNewSection(Rectangle rect) {
        OcrSection entity = new OcrSection();
        entity.setId(IdWorker.getId());
        entity.setName("字段#" + IdWorker.getMillisecond());
        entity.fillByRect(rect);
        entity.displayPosition();
        entity.setType("文本");
        baseMapper.insert(entity);

        return entity;
    }
}
