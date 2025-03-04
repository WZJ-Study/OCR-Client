package cc.wangzijie.server.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class SqliteUtils {

    public static void initSqliteDbFile(String filePath) {
        File file = new File(filePath);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                log.error("==== 初始化数据库文件 ==== 初始化数据库文件失败！", e);
            }
        }
    }

    /**
     * https://blog.51cto.com/u_14191/11959090
     *
     * @param connection
     */
    public static void initSqliteDb(Connection connection) {
        boolean requireInit = false;
        try {
            String sql = "select * from ocr_section limit 1";
            connection.prepareStatement(sql).execute();
        } catch (SQLException e) {
            log.error("==== 初始化数据库 ==== 数据库表结构不存在，需要初始化！", e);
            requireInit = true;
        }
        if (requireInit) {
            log.info("==== 初始化数据库 ==== 开始！");
            File initDbFile = new File("classpath:sql/init-db.sql");
            try {
                String initDbSql = FileUtils.readFileToString(initDbFile, "UTF-8");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
