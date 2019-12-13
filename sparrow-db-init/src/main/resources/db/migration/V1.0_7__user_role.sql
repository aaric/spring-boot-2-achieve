CREATE TABLE IF NOT EXISTS user_role (
  id serial PRIMARY KEY,
  user_id varchar(50) NOT NULL,
  role_id varchar(50) NOT NULL,
  insert_time timestamp WITH time ZONE NOT NULL DEFAULT current_timestamp
);

COMMENT ON TABLE user_role IS '用户角色信息';
COMMENT ON COLUMN user_role.id IS 'ID';
COMMENT ON COLUMN user_role.user_id IS '用户ID';
COMMENT ON COLUMN user_role.role_id IS '角色ID';
COMMENT ON COLUMN user_role.insert_time IS '入库时间';
