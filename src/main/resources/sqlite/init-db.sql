DROP TABLE IF EXISTS "ocr_section";
DROP TABLE IF EXISTS "ocr_section_result";
CREATE TABLE "ocr_section" (
    "id" integer NOT NULL,
    "name" text,
    "position" text,
    "x" integer,
    "y" integer,
    "trans_x" integer,
    "trans_y" integer,
    "width" integer,
    "height" integer,
    "type" text,
    PRIMARY KEY ("id")
);
CREATE TABLE "ocr_section_result" (
    "id" integer NOT NULL,
    "section_id" integer,
    "name" text,
    "position" text,
    "x" integer,
    "y" integer,
    "trans_x" integer,
    "trans_y" integer,
    "width" integer,
    "height" integer,
    "type" text,
    "value" text,
    "collect_time" text,
    PRIMARY KEY ("id")
);
