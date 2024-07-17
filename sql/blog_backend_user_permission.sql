create table user_permission
(
    id            bigint auto_increment,
    user_id       bigint not null,
    permission_id bigint null,
    constraint user_permission_id_uindex
        unique (id)
);

alter table user_permission
    add primary key (id);

