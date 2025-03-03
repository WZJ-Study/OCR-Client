package cc.wangzijie.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ocr_section")
public class OcrSection {

    /**
     * 主键ID
     */
    private Long id;

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

    public String displayPosition() {
        this.position = String.format("(%d;%d;%d;%d)", x, y, width, height);
        return this.position;
    }

    public OcrResult newResult(String value) {
        OcrResult result = new OcrResult();
        result.setId(IdWorker.getId());
        result.setSectionId(this.id);
        result.setName(this.name);
        result.setPosition(this.position);
        result.setX(this.x);
        result.setY(this.y);
        result.setWidth(this.width);
        result.setHeight(this.height);
        result.setType(this.type);
        result.setValue(value);
        result.setCollectTime(value == null ? null : new Date());
        return result;
    }
}
