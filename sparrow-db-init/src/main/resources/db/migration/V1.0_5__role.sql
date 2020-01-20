CREATE TABLE IF NOT EXISTS role (
  id serial PRIMARY KEY,
  role_name varchar(50) NOT NULL UNIQUE,
  is_del boolean NOT NULL DEFAULT FALSE,
  insert_time timestamp WITH time ZONE NOT NULL DEFAULT current_timestamp
);

COMMENT ON TABLE role IS '权限信息';
COMMENT ON COLUMN role.id IS 'ID';
COMMENT ON COLUMN role.role_name IS '角色名称';
COMMENT ON COLUMN role.is_del IS '是否删除，默认false';
COMMENT ON COLUMN role.insert_time IS '入库时间';

/* 初始化系统支持的角色记录 */
INSERT INTO role VALUES (1, '超级管理员');
