CREATE TABLE IF NOT EXISTS role_authority (
  id serial PRIMARY KEY,
  role_id varchar(50) NOT NULL,
  authority_id varchar(50) NOT NULL,
  insert_time timestamp WITH time ZONE NOT NULL DEFAULT current_timestamp
);

COMMENT ON TABLE role_authority IS '角色权限信息';
COMMENT ON COLUMN role_authority.id IS 'ID';
COMMENT ON COLUMN role_authority.role_id IS '角色ID';
COMMENT ON COLUMN role_authority.authority_id IS '权限ID';
COMMENT ON COLUMN role_authority.insert_time IS '入库时间';
