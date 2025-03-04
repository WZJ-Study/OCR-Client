package cc.wangzijie.server.entity;

import cc.wangzijie.ocr.utils.DateFormat;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ocr_section_result")
public class OcrSectionResult {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 采集区域ID
     */
    private Long sectionId;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段位置 (x;y;width;height)
     */
    private String position;


    /**
     * 字段位置-x（以左上点坐标系计算的坐标）
     */
    private Integer x;

    /**
     * 字段位置-y（以左上点坐标系计算的坐标）
     */
    private Integer y;


    /**
     * 字段位置-transX（以中点坐标系计算的坐标）
     */
    private Integer transX;

    /**
     * 字段位置-transY（以中点坐标系计算的坐标）
     */
    private Integer transY;

    /**
     * 字段位置-width
     */
    private Integer width;

    /**
     * 字段位置-height
     */
    private Integer height;

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
    @JsonFormat(pattern = DateFormat.YYYY_MM_DD_HH_MM_SS, timezone=DateFormat.TIMEZONE)
    private Date collectTime;

}
