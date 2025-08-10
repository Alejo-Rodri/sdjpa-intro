alter table book change id id bigint auto_increment;
alter table author add primary key (id);
alter table author change id id bigint auto_increment;