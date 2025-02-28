CREATE TABLE "public"."ocr_section" (
    "id" int8 NOT NULL,
    "name" varchar(255),
    "position" varchar(255),
    "x" int4,
    "y" int4,
    "width" int4,
    "height" int4,
    "type" varchar(255),
    PRIMARY KEY ("id")
)
;

COMMENT ON COLUMN "public"."ocr_section"."id" IS '主键ID';
COMMENT ON COLUMN "public"."ocr_section"."name" IS '字段名称';
COMMENT ON COLUMN "public"."ocr_section"."position" IS '字段位置 (x;y;width;height)';
COMMENT ON COLUMN "public"."ocr_section"."x" IS '字段位置-x';
COMMENT ON COLUMN "public"."ocr_section"."y" IS '字段位置-y';
COMMENT ON COLUMN "public"."ocr_section"."width" IS '字段位置-width';
COMMENT ON COLUMN "public"."ocr_section"."height" IS '字段位置-height';
COMMENT ON COLUMN "public"."ocr_section"."type" IS '字段类型：数字/文本';

COMMENT ON TABLE "public"."ocr_section" IS 'OCR采集区域';









CREATE TABLE "public"."ocr_result" (
    "id" int8 NOT NULL,
    "name" varchar(255),
    "position" varchar(255),
    "x" int4,
    "y" int4,
    "width" int4,
    "height" int4,
    "type" varchar(255),
    "value" text,
    "collect_time" timestamp(6),
    PRIMARY KEY ("id")
)
;

COMMENT ON COLUMN "public"."ocr_result"."id" IS '主键ID';
COMMENT ON COLUMN "public"."ocr_result"."name" IS '字段名称';
COMMENT ON COLUMN "public"."ocr_result"."position" IS '字段位置 (x;y;width;height)';
COMMENT ON COLUMN "public"."ocr_result"."x" IS '字段位置-x';
COMMENT ON COLUMN "public"."ocr_result"."y" IS '字段位置-y';
COMMENT ON COLUMN "public"."ocr_result"."width" IS '字段位置-width';
COMMENT ON COLUMN "public"."ocr_result"."height" IS '字段位置-height';
COMMENT ON COLUMN "public"."ocr_result"."type" IS '字段类型：数字/文本';
COMMENT ON COLUMN "public"."ocr_result"."value" IS '采集结果值';
COMMENT ON COLUMN "public"."ocr_result"."collect_time" IS '采集时间';

COMMENT ON TABLE "public"."ocr_result" IS 'OCR采集结果';



CREATE INDEX "idx_ocrSectionName" ON "public"."ocr_section" USING btree ("name");
COMMENT ON INDEX "public"."idx_ocrSectionName" IS 'ocr识别区域-字段名称';

CREATE INDEX "idx_ocrResultName" ON "public"."ocr_result" USING btree ("name");
COMMENT ON INDEX "public"."idx_ocrResultName" IS 'OCR采集结果-字段名称';
