package cc.wangzijie.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@MapperScan(basePackages = {"cc.wangzijie.server.mapper"},
            sqlSessionFactoryRef = "sqlSessionFactory")
@Configuration
public class MybatisConfig {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    /**
     * 配置sqlite数据源
     *
     * @return sqlite数据源
     */
    @Bean(name = "sqliteDataSource")
    public DataSource sqliteDataSource() {
        return null;
    }

}
