package cc.wangzijie.server.entity;

import lombok.Data;

@Data
public class OcrSectionResultDisplayVO {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段位置 (x;y;width;height)
     */
    private String position;

    /**
     * 字段类型：数字/文本
     */
    private String type;

    /**
     * 采集结果值
     */
    private String value;

    /**
     * 采集时间
     */
    private String collectTime;

}
