package cc.wangzijie.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class FileUtils {

    public static File initFolder(String folderPath) {
        try {
            File file = new File(folderPath);
            if (file.mkdirs()) {
                log.info("==== initFolder ==== 初始化目录：{}", folderPath);
            }
            return file;
        } catch (Exception e) {
            log.error("==== initFolder ==== 初始化目录失败！", e);
        }
        return null;
    }


    public static void delete(File file) {
        if (file == null) {
            return;
        }
        try {
            if (file.delete()) {
                log.info("==== deleteFile ==== 删除文件：{}", file.getPath());
            }
        } catch (Exception e) {
            log.error("==== deleteFile ==== 删除文件失败！", e);
        }
    }

}
