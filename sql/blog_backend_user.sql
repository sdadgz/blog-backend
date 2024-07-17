create table user
(
    user_id       bigint auto_increment,
    username      varchar(255) not null,
    password      varchar(255) not null,
    email         varchar(255) null,
    `create`      timestamp    not null,
    last_modified timestamp    not null,
    constraint user_user_id_uindex
        unique (user_id),
    constraint user_username_uindex
        unique (username)
);

alter table user
    add primary key (user_id);

INSERT INTO blog_backend.user (username, password, email, `create`, last_modified) VALUES ('蒋涛', '$2a$10$uznH2gz3bKOhS64u78tqAujDEueK.CU5owe3Gb710HiTygbwySC8O', null, '2024-07-16 15:53:41', '2024-07-16 15:53:41');
INSERT INTO blog_backend.user (username, password, email, `create`, last_modified) VALUES ('sdadgz', '$2a$10$A7sbjS/jGrAQfeRXfLYqPeBZu1rRG8eCuaMs./yTPazSNB0qNZE4C', null, '2024-07-17 10:54:58', '2024-07-17 10:54:58');
INSERT INTO blog_backend.user (username, password, email, `create`, last_modified) VALUES ('武伟', '$2a$10$UGFJwpCA.TwCO/nhfIUYneQ6dbkXy0gVrZVMFwAEuH89QzIEHEnVq', null, '2024-07-17 14:01:27', '2024-07-17 14:01:27');
INSERT INTO blog_backend.user (username, password, email, `create`, last_modified) VALUES ('白杰', '$2a$10$SDjS7HbGFsKz8qZaWacDTe32G81l6mHdV90VbFPsC6p3HkqDnd572', null, '2024-07-17 15:14:24', '2024-07-17 15:14:24');
INSERT INTO blog_backend.user (username, password, email, `create`, last_modified) VALUES ('陆明', '$2a$10$1B0PxrPBNsEbeBJfcecWluPbu63iPCpGvDtuVHygGsXY7/73zRVQO', null, '2024-07-17 15:17:40', '2024-07-17 15:17:40');
INSERT INTO blog_backend.user (username, password, email, `create`, last_modified) VALUES ('雷敏', '$2a$10$gTLIk7LAsm8eToNPRcgUVulC8VdBXb0yaO0oktnzqO8SHJVx4Dzoy', null, '2024-07-17 15:29:45', '2024-07-17 15:29:45');