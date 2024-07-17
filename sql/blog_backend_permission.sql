create table permission
(
    id          bigint auto_increment,
    name        varchar(255)         not null,
    description varchar(255)         null,
    type        varchar(255)         not null,
    status      tinyint(1) default 1 not null,
    constraint permission_id_uindex
        unique (id),
    constraint permission_name_uindex
        unique (name)
);

alter table permission
    add primary key (id);

