CREATE TABLE IF NOT EXISTS user_info (
  id serial PRIMARY KEY,
  username varchar(50) NOT NULL UNIQUE,
  secret varchar(100) NOT NULL,
  secret_salt varchar(10) NOT NULL,
  is_lock boolean NOT NULL DEFAULT false,
  is_del boolean NOT NULL DEFAULT false,
  insert_time timestamp WITH time ZONE NOT NULL DEFAULT current_timestamp
);

COMMENT ON TABLE user_info IS '用户信息';
COMMENT ON COLUMN user_info.id IS 'ID';
COMMENT ON COLUMN user_info.username IS '用户名';
COMMENT ON COLUMN user_info.secret IS '密码';
COMMENT ON COLUMN user_info.secret_salt IS '密码盐';
COMMENT ON COLUMN user_info.is_lock IS '是否锁定，默认false';
COMMENT ON COLUMN user_info.is_del IS '是否删除，默认false';
COMMENT ON COLUMN user_info.insert_time IS '入库时间';

INSERT INTO user_info VALUES (1, 'root', '$2a$10$I8l1l4U7LyYVQGQDNbUH1eiQDG.n0SS6yuEcRl9SXVkTfBYhtc4sK', 'somecode');
