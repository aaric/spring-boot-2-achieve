CREATE TABLE IF NOT EXISTS log (
  id bigserial PRIMARY KEY,
  tag_name varchar(20) NOT NULL,
  group_name varchar(20) NOT NULL DEFAULT 'default',
  title varchar(100) NOT NULL,
  content varchar(200) NOT NULL,
  remark text,
  submit varchar(50) NOT NULL DEFAULT 'system',
  http_url varchar(200) NOT NULL DEFAULT '',
  http_status integer NOT NULL DEFAULT 200,
  http_type varchar(10) NOT NULL DEFAULT '',
  client_connect_start timestamp WITH time ZONE,
  server_process_start timestamp WITH time ZONE NOT NULL,
  server_process_end timestamp WITH time ZONE,
  client_connect_interval integer NOT NULL DEFAULT -1,
  server_process_interval integer NOT NULL DEFAULT -1,
  exception_detail text,
  insert_time timestamp WITH time ZONE NOT NULL DEFAULT current_timestamp
);

COMMENT ON TABLE log IS '日志记录';
COMMENT ON COLUMN log.id IS 'ID';
COMMENT ON COLUMN log.tag_name IS '标签，即系统名称';
COMMENT ON COLUMN log.group_name IS '分组名称，default-操作日志';
COMMENT ON COLUMN log.title IS '标题，即模块名称';
COMMENT ON COLUMN log.content IS '日志内容';
COMMENT ON COLUMN log.remark IS '备注，附加信息';
COMMENT ON COLUMN log.submit IS '提交者，即用户名';
COMMENT ON COLUMN log.http_url IS 'HTTP请求地址';
COMMENT ON COLUMN log.http_status IS 'HTTP请求状态码';
COMMENT ON COLUMN log.http_type IS 'HTTP请求类型';
COMMENT ON COLUMN log.client_connect_start IS '客户端建立连接时间';
COMMENT ON COLUMN log.server_process_start IS '服务端处理业务开始时间';
COMMENT ON COLUMN log.server_process_end IS '服务端处理业务结束时间';
COMMENT ON COLUMN log.client_connect_interval IS '客户端建立连接耗时，单位：ms';
COMMENT ON COLUMN log.server_process_interval IS '服务端处理业务耗时，单位：ms';
COMMENT ON COLUMN log.exception_detail IS '异常信息，方便排除问题';
COMMENT ON COLUMN log.insert_time IS '入库时间';
