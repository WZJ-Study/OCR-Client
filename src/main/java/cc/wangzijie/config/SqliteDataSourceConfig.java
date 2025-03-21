package cc.wangzijie.config;

import cc.wangzijie.utils.SqliteBuilder;
import cc.wangzijie.utils.SqliteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class SqliteDataSourceConfig {

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



}
