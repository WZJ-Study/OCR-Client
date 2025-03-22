
drop table if exists "public"."ocr_section";
drop table if exists "public"."ocr_section_result";

CREATE TABLE "public"."ocr_section" (
    "id" int8 NOT NULL,
    "name" varchar(255),
    "position" varchar(255),
    "x" int4,
    "y" int4,
    "trans_x" int4,
    "trans_y" int4,
    "width" int4,
    "height" int4,
    "type" varchar(255),
    PRIMARY KEY ("id")
)
;


COMMENT ON COLUMN "public"."ocr_section"."id" IS '主键ID';
COMMENT ON COLUMN "public"."ocr_section"."name" IS '字段名称';
COMMENT ON COLUMN "public"."ocr_section"."position" IS '字段位置 (x;y;width;height)';
COMMENT ON COLUMN "public"."ocr_section"."x" IS '字段位置-x（以左上点坐标系计算的坐标）';
COMMENT ON COLUMN "public"."ocr_section"."y" IS '字段位置-y（以左上点坐标系计算的坐标）';
COMMENT ON COLUMN "public"."ocr_section"."trans_x" IS '字段位置-transX（以中点坐标系计算的坐标）';
COMMENT ON COLUMN "public"."ocr_section"."trans_y" IS '字段位置-transY（以中点坐标系计算的坐标）';
COMMENT ON COLUMN "public"."ocr_section"."width" IS '字段位置-width';
COMMENT ON COLUMN "public"."ocr_section"."height" IS '字段位置-height';
COMMENT ON COLUMN "public"."ocr_section"."type" IS '字段类型：数字/文本';

COMMENT ON TABLE "public"."ocr_section" IS 'OCR采集区域';








CREATE TABLE "public"."ocr_section_result" (
    "id" int8 NOT NULL,
    "section_id" int8,
    "name" varchar(255),
    "position" varchar(255),
    "x" int4,
    "y" int4,
    "trans_x" int4,
    "trans_y" int4,
    "width" int4,
    "height" int4,
    "type" varchar(255),
    "value" text,
    "collect_time" varchar(255),
    PRIMARY KEY ("id")
)
;

COMMENT ON COLUMN "public"."ocr_section_result"."id" IS '主键ID';
COMMENT ON COLUMN "public"."ocr_section_result"."section_id" IS '识别区域ID';
COMMENT ON COLUMN "public"."ocr_section_result"."name" IS '字段名称';
COMMENT ON COLUMN "public"."ocr_section_result"."position" IS '字段位置 (x;y;width;height)';
COMMENT ON COLUMN "public"."ocr_section_result"."x" IS '字段位置-x（以左上点坐标系计算的坐标）';
COMMENT ON COLUMN "public"."ocr_section_result"."y" IS '字段位置-y（以左上点坐标系计算的坐标）';
COMMENT ON COLUMN "public"."ocr_section_result"."trans_x" IS '字段位置-transX（以中点坐标系计算的坐标）';
COMMENT ON COLUMN "public"."ocr_section_result"."trans_y" IS '字段位置-transY（以中点坐标系计算的坐标）';
COMMENT ON COLUMN "public"."ocr_section_result"."width" IS '字段位置-width';
COMMENT ON COLUMN "public"."ocr_section_result"."height" IS '字段位置-height';
COMMENT ON COLUMN "public"."ocr_section_result"."type" IS '字段类型：数字/文本';
COMMENT ON COLUMN "public"."ocr_section_result"."value" IS '采集结果值';
COMMENT ON COLUMN "public"."ocr_section_result"."collect_time" IS '采集时间';

COMMENT ON TABLE "public"."ocr_section_result" IS 'OCR采集结果';



CREATE INDEX "idx_ocrSectionName" ON "public"."ocr_section" USING btree ("name");
COMMENT ON INDEX "public"."idx_ocrSectionName" IS 'ocr识别区域-字段名称';

CREATE INDEX "idx_ocrSectionResultName" ON "public"."ocr_section_result" USING btree ("name");
COMMENT ON INDEX "public"."idx_ocrSectionResultName" IS 'OCR采集结果-字段名称';
