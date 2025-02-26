package cc.wangzijie.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"cc.wangzijie.server.mapper"})
public class MybatisConfig {

}
