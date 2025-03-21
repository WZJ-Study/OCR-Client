package cc.wangzijie.server.config;

import cc.wangzijie.utils.SqliteBuilder;
import cc.wangzijie.utils.SqliteUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SqliteDataSourceConfig {

    private static final String DB_URL = "jdbc:sqlite:sqlite/ocr.db3";

    private static final String DB_FILE = "sqlite/ocr.db3";

    private static final String TEST_SQL = "select * from ocr_section limit 1";

    private static final String INIT_DB_SQL_FILE = "sqlite/init-db.sql";

    /**
     * 配置sqlite数据源
     */
    @Bean(name = "sqliteDataSource")
    public DataSource sqliteDataSource(){
        // 尝试创建sqlite文件-不存在时创建
        SqliteUtils.initSqliteDbFile(DB_FILE);
        // 创建数据源
        DataSource dataSource = SqliteBuilder.create().url(DB_URL).build();
        // 尝试初始化数据库-表不存在时创建
        SqliteUtils.initSqliteDb(dataSource, TEST_SQL, INIT_DB_SQL_FILE);
        return dataSource;
    }



}
