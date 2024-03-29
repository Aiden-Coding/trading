CREATE TABLE `quartz_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'SpringBean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '执行参数',
  `cron_expres` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `state` int(1) DEFAULT NULL COMMENT '任务状态：1正常，2暂停，3删除',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务列表';

CREATE TABLE `quartz_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` int(11) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'SpringBean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '执行参数',
  `state` tinyint(4) NOT NULL COMMENT '任务状态：1成功，2失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务日志';

INSERT INTO `quartz_job` (`id`, `bean_name`, `params`, `cron_expres`, `state`, `remark`, `create_time`) VALUES (1, 'printJob', 'printJob-params', '0 /2 * * * ? *', 2, '打印信息任务', '2023-07-26 13:17:17');
INSERT INTO `quartz_job` (`id`, `bean_name`, `params`, `cron_expres`, `state`, `remark`, `create_time`) VALUES (2, 'timerJob', 'timerJob-params-update', '0 /3 * * * ? *', 2, '时间输出任务', '2023-07-26 14:17:17');


#
# In your Quartz properties file, you'll need to set
# org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.StdJDBCDelegate
#
#
# By: Ron Cordell - roncordell
#  I didn't see this anywhere, so I thought I'd post it here. This is the script from Quartz to create the tables in a MySQL database, modified to use INNODB instead of MYISAM.

drop table if exists qrtz_fired_triggers;
drop table if exists qrtz_paused_trigger_grps;
drop table if exists qrtz_scheduler_state;
drop table if exists qrtz_locks;
drop table if exists qrtz_simple_triggers;
drop table if exists qrtz_simprop_triggers;
drop table if exists qrtz_cron_triggers;
drop table if exists qrtz_blob_triggers;
drop table if exists qrtz_triggers;
drop table if exists qrtz_job_details;
drop table if exists qrtz_calendars;

create table qrtz_job_details(
sched_name varchar(120) not null,
job_name varchar(190) not null,
job_group varchar(190) not null,
description varchar(250) null,
job_class_name varchar(250) not null,
is_durable varchar(1) not null,
is_nonconcurrent varchar(1) not null,
is_update_data varchar(1) not null,
requests_recovery varchar(1) not null,
job_data blob null,
primary key (sched_name,job_name,job_group))
engine=innodb;

create table qrtz_triggers (
sched_name varchar(120) not null,
trigger_name varchar(190) not null,
trigger_group varchar(190) not null,
job_name varchar(190) not null,
job_group varchar(190) not null,
description varchar(250) null,
next_fire_time bigint(13) null,
prev_fire_time bigint(13) null,
priority integer null,
trigger_state varchar(16) not null,
trigger_type varchar(8) not null,
start_time bigint(13) not null,
end_time bigint(13) null,
calendar_name varchar(190) null,
misfire_instr smallint(2) null,
job_data blob null,
primary key (sched_name,trigger_name,trigger_group),
foreign key (sched_name,job_name,job_group)
references qrtz_job_details(sched_name,job_name,job_group))
engine=innodb;

create table qrtz_simple_triggers (
sched_name varchar(120) not null,
trigger_name varchar(190) not null,
trigger_group varchar(190) not null,
repeat_count bigint(7) not null,
repeat_interval bigint(12) not null,
times_triggered bigint(10) not null,
primary key (sched_name,trigger_name,trigger_group),
foreign key (sched_name,trigger_name,trigger_group)
references qrtz_triggers(sched_name,trigger_name,trigger_group))
engine=innodb;

create table qrtz_cron_triggers (
sched_name varchar(120) not null,
trigger_name varchar(190) not null,
trigger_group varchar(190) not null,
cron_expression varchar(120) not null,
time_zone_id varchar(80),
primary key (sched_name,trigger_name,trigger_group),
foreign key (sched_name,trigger_name,trigger_group)
references qrtz_triggers(sched_name,trigger_name,trigger_group))
engine=innodb;

create table qrtz_simprop_triggers
  (
    sched_name varchar(120) not null,
    trigger_name varchar(190) not null,
    trigger_group varchar(190) not null,
    str_prop_1 varchar(512) null,
    str_prop_2 varchar(512) null,
    str_prop_3 varchar(512) null,
    int_prop_1 int null,
    int_prop_2 int null,
    long_prop_1 bigint null,
    long_prop_2 bigint null,
    dec_prop_1 numeric(13,4) null,
    dec_prop_2 numeric(13,4) null,
    bool_prop_1 varchar(1) null,
    bool_prop_2 varchar(1) null,
    primary key (sched_name,trigger_name,trigger_group),
    foreign key (sched_name,trigger_name,trigger_group)
    references qrtz_triggers(sched_name,trigger_name,trigger_group))
engine=innodb;

create table qrtz_blob_triggers (
sched_name varchar(120) not null,
trigger_name varchar(190) not null,
trigger_group varchar(190) not null,
blob_data blob null,
primary key (sched_name,trigger_name,trigger_group),
index (sched_name,trigger_name, trigger_group),
foreign key (sched_name,trigger_name,trigger_group)
references qrtz_triggers(sched_name,trigger_name,trigger_group))
engine=innodb;

create table qrtz_calendars (
sched_name varchar(120) not null,
calendar_name varchar(190) not null,
calendar blob not null,
primary key (sched_name,calendar_name))
engine=innodb;

create table qrtz_paused_trigger_grps (
sched_name varchar(120) not null,
trigger_group varchar(190) not null,
primary key (sched_name,trigger_group))
engine=innodb;

create table qrtz_fired_triggers (
sched_name varchar(120) not null,
entry_id varchar(95) not null,
trigger_name varchar(190) not null,
trigger_group varchar(190) not null,
instance_name varchar(190) not null,
fired_time bigint(13) not null,
sched_time bigint(13) not null,
priority integer not null,
state varchar(16) not null,
job_name varchar(190) null,
job_group varchar(190) null,
is_nonconcurrent varchar(1) null,
requests_recovery varchar(1) null,
primary key (sched_name,entry_id))
engine=innodb;

create table qrtz_scheduler_state (
sched_name varchar(120) not null,
instance_name varchar(190) not null,
last_checkin_time bigint(13) not null,
checkin_interval bigint(13) not null,
primary key (sched_name,instance_name))
engine=innodb;

create table qrtz_locks (
sched_name varchar(120) not null,
lock_name varchar(40) not null,
primary key (sched_name,lock_name))
engine=innodb;

create index idx_qrtz_j_req_recovery on qrtz_job_details(sched_name,requests_recovery);
create index idx_qrtz_j_grp on qrtz_job_details(sched_name,job_group);

create index idx_qrtz_t_j on qrtz_triggers(sched_name,job_name,job_group);
create index idx_qrtz_t_jg on qrtz_triggers(sched_name,job_group);
create index idx_qrtz_t_c on qrtz_triggers(sched_name,calendar_name);
create index idx_qrtz_t_g on qrtz_triggers(sched_name,trigger_group);
create index idx_qrtz_t_state on qrtz_triggers(sched_name,trigger_state);
create index idx_qrtz_t_n_state on qrtz_triggers(sched_name,trigger_name,trigger_group,trigger_state);
create index idx_qrtz_t_n_g_state on qrtz_triggers(sched_name,trigger_group,trigger_state);
create index idx_qrtz_t_next_fire_time on qrtz_triggers(sched_name,next_fire_time);
create index idx_qrtz_t_nft_st on qrtz_triggers(sched_name,trigger_state,next_fire_time);
create index idx_qrtz_t_nft_misfire on qrtz_triggers(sched_name,misfire_instr,next_fire_time);
create index idx_qrtz_t_nft_st_misfire on qrtz_triggers(sched_name,misfire_instr,next_fire_time,trigger_state);
create index idx_qrtz_t_nft_st_misfire_grp on qrtz_triggers(sched_name,misfire_instr,next_fire_time,trigger_group,trigger_state);

create index idx_qrtz_ft_trig_inst_name on qrtz_fired_triggers(sched_name,instance_name);
create index idx_qrtz_ft_inst_job_req_rcvry on qrtz_fired_triggers(sched_name,instance_name,requests_recovery);
create index idx_qrtz_ft_j_g on qrtz_fired_triggers(sched_name,job_name,job_group);
create index idx_qrtz_ft_jg on qrtz_fired_triggers(sched_name,job_group);
create index idx_qrtz_ft_t_g on qrtz_fired_triggers(sched_name,trigger_name,trigger_group);
create index idx_qrtz_ft_tg on qrtz_fired_triggers(sched_name,trigger_group);

commit;

