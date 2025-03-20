package cc.wangzijie.server.utils;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class SqliteUtils {

    /**
     * 初始化数据库
     *
     * @param dataSource 数据源
     */
    public static void initSqliteDb(DataSource dataSource, String testSql, String initDbSqlFile) {
        try (Connection connection = dataSource.getConnection()) {
            if (!testDb(connection, testSql)) {
                log.info("==== 初始化数据库 ==== 数据库表结构初始化！执行Sql文件：{}", initDbSqlFile);
                String initDbSql = parseSql(initDbSqlFile);
                log.info("==== 初始化数据库 ==== 数据库表结构初始化！执行Sql文件内容：{}", initDbSql);
                doInitSqliteDb(connection, testSql, initDbSql.split(";"));
                // 再次检查
                if (testDb(connection, testSql)) {
                    log.info("==== 初始化数据库 ==== 数据库表结构初始化成功！");
                } else {
                    log.error("==== 初始化数据库 ==== 数据库表结构初始化失败！");
                }
            }
        } catch (Exception e) {
            log.error("==== 初始化数据库 ==== 数据库初始化失败！", e);
        }
    }

    protected static String parseSql(String initDbSqlFile) {
        try (InputStream is = SqliteUtils.class.getClassLoader().getResourceAsStream(initDbSqlFile)) {
            if (is != null) {
                return IOUtils.toString(is, StandardCharsets.UTF_8);
            }
            return "";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("all")
    public static void doInitSqliteDb(Connection connection, String testSql, String[] initDbSqls) {
        try {
            connection.setAutoCommit(false);
            for (String sql : initDbSqls) {
                connection.createStatement().executeUpdate(sql);
            }
            connection.commit();
        } catch (Exception e) {
            log.error("==== 初始化数据库 ==== 数据库表结构初始化失败！", e);
        }
    }

    @SuppressWarnings("all")
    public static boolean testDb(Connection connection, String testSql) {
        try {
            connection.createStatement().executeQuery(testSql);
            log.info("==== 检查数据库 ==== 数据库表结构OK！");
            return true;
        } catch (SQLException e) {
            log.error("==== 检查数据库 ==== 数据库表结构不存在！ {}", e.getMessage());
        }
        return false;
    }

    @SuppressWarnings("all")
    public static boolean initSqliteDbFile(String filePath) {
        File file = new File(filePath);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                return true;
            } catch (Exception e) {
                log.error("==== 初始化数据库文件 ==== 初始化数据库文件失败！", e);
            }
        }
        return false;
    }


}
