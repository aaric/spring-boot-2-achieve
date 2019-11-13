CREATE TABLE IF NOT EXISTS log (
  id bigserial PRIMARY KEY,
  tag varchar(20),
  title varchar(200),
  content text
);

COMMENT ON TABLE log IS '日志记录';
COMMENT ON COLUMN log.id IS 'ID';
COMMENT ON COLUMN log.tag IS '标签，即系统名称';
COMMENT ON COLUMN log.title IS '标题，即模块名称';
COMMENT ON COLUMN log.content IS '日志内容';
