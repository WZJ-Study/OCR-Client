package cc.wangzijie.server.service.impl;

import cc.wangzijie.server.entity.SysUser;
import cc.wangzijie.server.mapper.ISysUserMapper;
import cc.wangzijie.server.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    private ISysUserMapper baseMapper;

    @Override
    public String now() {
        return baseMapper.now();
    }

    /**
     * 查询VO不分页列表
     *
     * @param entity 查询参数
     * @return 不分页列表
     */
    @Override
    public List<SysUser> voList(SysUser entity) {
        LambdaQueryWrapper<SysUser> lqw = Wrappers.lambdaQuery(entity);
        return baseMapper.selectList(lqw);
    }

    /**
     * 根据ID查询单条VO记录
     *
     * @param id ID
     * @return 单条记录
     */
    @Override
    public SysUser voGetById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 创建新增
     *
     * @param entity 实体类对象
     * @return 操作是否成功
     */
    @Override
    public boolean save(SysUser entity) {
        return baseMapper.insert(entity) > 0;
    }

    /**
     * 编辑修改
     *
     * @param entity 实体类对象
     * @return 操作是否成功
     */
    @Override
    public boolean updateById(SysUser entity) {
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
