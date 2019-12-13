CREATE TABLE IF NOT EXISTS authority (
  id serial PRIMARY KEY,
  authority_code varchar(50) NOT NULL UNIQUE,
  is_del boolean NOT NULL DEFAULT FALSE,
  insert_time timestamp WITH time ZONE NOT NULL DEFAULT current_timestamp
);

COMMENT ON TABLE authority IS '权限信息';
COMMENT ON COLUMN authority.id IS 'ID';
COMMENT ON COLUMN authority.authority_code IS '权限识别码';
COMMENT ON COLUMN authority.is_del IS '是否删除，默认false';
COMMENT ON COLUMN authority.insert_time IS '入库时间';
