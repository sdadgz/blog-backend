create table post
(
    post_id       bigint auto_increment,
    title         varchar(255) not null,
    content       text         not null,
    user_id       bigint       not null,
    `create`      timestamp    not null,
    last_modified timestamp    not null,
    constraint post_post_id_uindex
        unique (post_id)
);

alter table post
    add primary key (post_id);

INSERT INTO blog_backend.post (title, content, user_id, `create`, last_modified) VALUES ('积好边率一', 'eu eiusmod velit', 2, '2024-07-17 11:16:50', '2024-07-17 14:00:19');
INSERT INTO blog_backend.post (title, content, user_id, `create`, last_modified) VALUES ('示例标题', 'Spring Boot 博客系统代码题：创建一个带有基本用户认证的简单博客系统API，允许用户注册、登录，以及创建、阅读、更新和删除博客文章。技术要求：使用Java 17、Spring Boot 3.x、MySQL作为数据库、ORM框架可以自选(如spring data jpa/jdbc, JOOQ, MyBatis等)、RESTful API设计、实现基本的身份认证机制(不要求必须使用Spring Security, 可以自己实现)。功能要求：1. 用户管理：用户注册、用户登录、获取当前用户信息；2. 博客文章管理：创建新文章、获取所有文章列表、获取单篇文章详情、更新文章、删除文章。用户模型应包含以下必需的字段：user_id (主键)、username (用户名)、password (密码，需要加密存储)、email (邮箱)、created (创建时间)、last_modified (更新时间)。每篇文章应包含以下必需的字段：post_id (主键)、title (标题)、content (内容)、user_id (作者ID，关联到用户表)、created (创建时间)、last_modified (更新时间)。实现基本的错误处理和日志记录、实现基本的权限控制：只有文章作者才能修改或删除自己的文章。API 端点：POST /api/auth/register - 用户注册、POST /api/auth/login - 用户登录、GET /api/auth/me - 获取当前用户信息（需要认证）、POST /api/posts - 创建新文章（需要登录）、GET /api/posts?uid= - 获取某个用户的所有文章列表 支持分页/按创建时间正/倒序、GET /api/posts/{id} - 获取单篇文章详情、PUT /api/posts/{id} - 更新文章（需要登录和权限判断）、DELETE /api/posts/{id} - 删除文章（需要登录和权限判断）。实现要求：实现一个简单的 token 生成机制（也可以使用现成的token生成机制）、在需要认证的接口中，从请求头获取并验证 token、实现一个简单的拦截器或过滤器来处理认证逻辑、注意数据的安全性，如密码加密存储、使用 Docker 部署你的应用，并附上部署说明。提交要求：提交一个 GitHub 仓库，包含完整的代码、提交一个 README.md 文件，描述你的实现思路和注意事项、提交一个 Dockerfile 文件，用于构建和部署你的应用、将上述提交要求提交至 hr@linfo.ai。', 2, '2024-07-17 11:20:04', '2024-07-17 11:20:04');