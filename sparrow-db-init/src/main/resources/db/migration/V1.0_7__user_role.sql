CREATE TABLE IF NOT EXISTS user_role (
  id serial PRIMARY KEY,
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  insert_time timestamp WITH time ZONE NOT NULL DEFAULT current_timestamp
);

COMMENT ON TABLE user_role IS '用户角色信息';
COMMENT ON COLUMN user_role.id IS 'ID';
COMMENT ON COLUMN user_role.user_id IS '用户ID';
COMMENT ON COLUMN user_role.role_id IS '角色ID';
COMMENT ON COLUMN user_role.insert_time IS '入库时间';

/* 初始化系统支持的超级管理员角色权限信息 */
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
