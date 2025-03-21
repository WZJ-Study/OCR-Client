package cc.wangzijie.server.entity;

import lombok.Data;

@Data
public class OcrSectionResult {


    /**
     * checkBox选中情况
     */
    private Boolean checkedFlag = false;

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
    private String collectTime;

    public OcrSection toSection() {
        OcrSection section = new OcrSection();
        section.setId(this.sectionId);
        section.setName(this.name);
        section.setType(this.type);
        return section;
    }

    public OcrSectionResultDisplayVO toDisplay() {
        OcrSectionResultDisplayVO vo = new OcrSectionResultDisplayVO();
        vo.setName(this.name);
        vo.setPosition(this.position);
        vo.setType(this.type);
        vo.setValue(this.value);
        vo.setCollectTime(this.collectTime);
        return vo;
    }
}
