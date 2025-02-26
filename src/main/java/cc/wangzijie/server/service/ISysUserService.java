package cc.wangzijie.server.service;



import cc.wangzijie.server.entity.SysUser;

import java.util.List;

public interface ISysUserService {

    String now();

    /**
     * 查询VO不分页列表
     *
     * @param entity     查询参数
     * @return 不分页列表
     */
    List<SysUser> voList(SysUser entity);

    /**
     * 根据ID查询单条VO记录
     *
     * @param id      ID
     * @return 单条记录
     */
    SysUser voGetById(Long id);

    /**
     * 创建新增
     *
     * @param entity  实体类对象
     * @return 操作是否成功
     */
    boolean save(SysUser entity);

    /**
     * 编辑修改
     *
     * @param entity  实体类对象
     * @return 操作是否成功
     */
    boolean updateById(SysUser entity);

    /**
     * 根据IDs批量删除（软删除）
     *
     * @param ids     ID列表
     * @return 操作是否成功
     */
    boolean removeByIds(List<Long> ids);



}
