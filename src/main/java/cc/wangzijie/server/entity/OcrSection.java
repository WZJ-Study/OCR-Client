package cc.wangzijie.server.entity;

import cc.wangzijie.utils.DateUtils;
import cc.wangzijie.utils.SnowflakeIdWorker;
import com.benjaminwan.ocrlibrary.OcrResult;
import javafx.scene.shape.Rectangle;
import lombok.Data;

@Data
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
     * 采集区域 - 字段位置-x（以左上点坐标系计算的坐标）
     */
    private Integer x;

    /**
     * 采集区域 - 字段位置-y（以左上点坐标系计算的坐标）
     */
    private Integer y;


    /**
     * 采集区域 - 字段位置-transX（以中点坐标系计算的坐标）
     */
    private Integer transX;

    /**
     * 采集区域 - 字段位置-transY（以中点坐标系计算的坐标）
     */
    private Integer transY;

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

    public void fillByRect(Rectangle rect) {
        this.x = (int) rect.getX();
        this.y = (int) rect.getY();
        this.transX = (int) rect.getTranslateX();
        this.transY = (int) rect.getTranslateY();
        this.width = (int) rect.getWidth();
        this.height = (int) rect.getHeight();
    }

    public String displayPosition() {
        this.position = String.format("(%d;%d;%d;%d)", x, y, width, height);
        return this.position;
    }

    public String displaySection() {
        if (null == this.position) {
            this.position = String.format("(%d;%d;%d;%d)", x, y, width, height);
        }
        return String.format("%s %s", this.name, this.position);
    }

    public OcrSectionResult newResult(String value) {
        OcrSectionResult result = new OcrSectionResult();
        result.setId(SnowflakeIdWorker.generateId());
        result.setSectionId(this.id);
        result.setName(this.name);
        result.setPosition(this.position);
        result.setX(this.x);
        result.setY(this.y);
        result.setTransX(this.transX);
        result.setTransY(this.transY);
        result.setWidth(this.width);
        result.setHeight(this.height);
        result.setType(this.type);
        result.setValue(value);
        result.setCollectTime(value == null ? null : DateUtils.nowStr());
        return result;
    }

    public OcrSectionResult newResult(OcrResult ocrResult, String collectTime) {
        OcrSectionResult result = new OcrSectionResult();
        result.setId(SnowflakeIdWorker.generateId());
        result.setSectionId(this.id);
        result.setName(this.name);
        result.setPosition(this.position);
        result.setX(this.x);
        result.setY(this.y);
        result.setWidth(this.width);
        result.setHeight(this.height);
        result.setType(this.type);
        result.setValue(ocrResult.getStrRes());
        result.setCollectTime(collectTime);
        return result;
    }

}
