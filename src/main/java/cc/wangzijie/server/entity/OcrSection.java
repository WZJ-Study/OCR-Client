package cc.wangzijie.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ocr_section")
public class OcrSection {

    /**
     * 采集区域 - 字段名称
     */
    private String name;

    /**
     * 采集区域 - 字段位置 (x;y;width;height)
     */
    private String position;

    /**
     * 采集区域 - 字段位置-x
     */
    private Integer x;

    /**
     * 采集区域 - 字段位置-y
     */
    private Integer y;

    /**
     * 采集区域 - 字段位置-width
     */
    private Integer width;

    /**
     * 采集区域 - 字段位置-height
     */
    private Integer height;

    /**
     * 采集区域 - 字段类型：数字/文本
     */
    private String type;

}
