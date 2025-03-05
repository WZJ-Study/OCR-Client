package cc.wangzijie.config;

import cc.wangzijie.server.utils.DynamicDataSource;
import cc.wangzijie.server.utils.SqliteBuilder;
import cc.wangzijie.server.utils.SqliteUtils;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Slf4j
@MapperScan(basePackages = {"cc.wangzijie.server.mapper"},
            sqlSessionFactoryRef = "sqlSessionFactory")
@Configuration
public class MybatisConfig {

    @Value("${sqlite-db.url}")
    private String sqliteDbUrl;

    @Value("${sqlite-db.file}")
    private String sqliteDbFilePath;

    @Value("${sqlite-db.test-sql}")
    private String sqliteDbTestSql;

    @Value("${sqlite-db.init-db-sql}")
    private String sqliteDbInitDbSqlFile;

    /**
     * 配置sqlite数据源
     */
    @Bean(name = "sqliteDataSource")
    public DataSource sqliteDataSource(){
        // 尝试创建sqlite文件-不存在时创建
        SqliteUtils.initSqliteDbFile(sqliteDbFilePath);
        // 创建数据源
        DataSource dataSource = SqliteBuilder.create().url(sqliteDbUrl).build();
        // 尝试初始化数据库-表不存在时创建
        SqliteUtils.initSqliteDb(dataSource, sqliteDbTestSql, sqliteDbInitDbSqlFile);
        return dataSource;
    }

    /**
     * 动态数据源
     */
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("sqliteDataSource") DataSource dataSource){
        return  new DynamicDataSource(dataSource);
    }

    /**
     * session工厂
     */
    @Bean(name = "sqlSessionFactory")
    public MybatisSqlSessionFactoryBean sqlSessionFactory(
            @Qualifier("dynamicDataSource") DynamicDataSource dataSource) {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

}
