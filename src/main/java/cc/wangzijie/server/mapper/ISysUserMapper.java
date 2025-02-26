package cc.wangzijie.server.mapper;

import cc.wangzijie.server.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


public interface ISysUserMapper extends BaseMapper<SysUser> {

    String now();

}
