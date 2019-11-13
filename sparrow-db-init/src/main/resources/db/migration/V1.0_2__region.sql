CREATE TABLE IF NOT EXISTS region (
  id serial PRIMARY KEY,
  region_code varchar(20),
  region_name varchar(100)
);

COMMENT ON TABLE region IS '行政区信息';
COMMENT ON COLUMN region.id IS 'ID';
COMMENT ON COLUMN region.region_code IS '行政区代码';
COMMENT ON COLUMN region.region_name IS '行政区名称';
