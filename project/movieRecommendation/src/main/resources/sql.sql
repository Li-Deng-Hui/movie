use movieRecommendation;
drop table t_user;
/*用户表*/
CREATE TABLE t_user
(
    id         bigint(20) NOT NULL AUTO_INCREMENT comment '用户id',
    username   varchar(64)  DEFAULT NULL comment '用户名',
    avatar     varchar(255) DEFAULT NULL comment '用户头像',
    email      varchar(64)  DEFAULT NULL comment '用户邮箱',
    phone      varchar(13)  default null comment '用户电话',
    password   varchar(64)  DEFAULT NULL comment '用户密码',
    status     int(5)     NOT NULL comment '用户状态',
    /*permissions varchar(255) DEFAULT null comment '用户权限',*/
    role       int          default 0 comment '用户角色 0普通用户，1管理员',
    created    datetime     DEFAULT NULL comment '创建时间',
    updateTime timestamp    default CURRENT_TIMESTAMP comment '更新时间',
    lastlogin  datetime     DEFAULT NULL comment '最后一次登录时间',
    PRIMARY KEY (`id`),
    KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO t_user (`id`, `username`, `avatar`, `email`, `password`, `status`, `created`, `lastlogin`)
VALUES ('1', 'markerhub',
        'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',
        NULL, '96e79218965eb72c92a549dd5a330112', '0', '2020-04-20 10:44:01', NULL);
/*电影信息表*/
create table t_movieInformation
(
    movieId         varchar(64) not null primary key comment '电影id',
    movieName       varchar(255) default null comment '电影名',
    movieInfor      text         default null comment '电影信息',
    movieDirector   varchar(30)  default null comment '电影导演',
    movieActor      varchar(255) default null comment '电影演员',
    movieType       varchar(255) default null comment '电影类型',
    movieTag        varchar(255) default null comment '电影标签',
    movieScore      int          default 0 comment '电影评分',
    movieHot        int          default 0 comment '电影热度或观影人数',
    movieCollection int          default 0 comment '电影收藏数',
    movieTime       datetime     default null comment '电影上线时间',
    movieUrl        varchar(255) default null comment '电影链接',
    createTime      datetime     default null comment '创建时间',
    updateTime      timestamp    default CURRENT_TIMESTAMP comment '更新时间'
);
/*电影评论表*/
create table t_movieCriticism
(
    commentId      bigint(20)  NOT NULL AUTO_INCREMENT primary key comment '评论id',
    movieId        varchar(64) not null comment '电影id',
    userId         bigint(20)  not null comment '回复用户id',
    criticism      text        default null comment '电影评论',
    replyComment   int         default 0 comment '是否是回复评论 0不是，1是',
    replyUserId    bigint(20)  default null comment '被回复用户id',
    replyCommentId varchar(64) default null comment '回复评论id',
    replyNumber    int         default 0 comment '回复数',
    thumb          int         default 0 comment '点赞数',
    createTime     datetime    default null comment '创建时间',
    updateTime     timestamp   default CURRENT_TIMESTAMP comment '更新时间',
    CONSTRAINT fk_name FOREIGN KEY (userId) REFERENCES t_user (id) ON DELETE CASCADE,
    CONSTRAINT fk_name1 FOREIGN KEY (replyUserId) REFERENCES t_user (id) ON DELETE CASCADE,
    /* CONSTRAINT fk_name2 FOREIGN KEY(replyCommentId)REFERENCES t_movieCriticism(commentId)  ON DELETE CASCADE,*/
    CONSTRAINT fk_name3 FOREIGN KEY (movieId) REFERENCES t_movieInformation (movieId) ON DELETE CASCADE
);
/*评论回复通知表*/
create table t_movieReplyComment
(
    replyUserId bigint(20) NOT NULL comment '被回复用户id',
    userId      bigint(20) NOT NULL comment '回复用户id',
    commentId   bigint(20) NOT NULL AUTO_INCREMENT comment '评论id',
    createTime  datetime  default null comment '创建时间',
    isdelete    int       default 0 comment '是否删除 0未删除，1删除',
    updateTime  timestamp default CURRENT_TIMESTAMP comment '更新时间',
    CONSTRAINT fk_name_c FOREIGN KEY (userId) REFERENCES t_user (id) ON DELETE CASCADE,
    CONSTRAINT fk_name_c1 FOREIGN KEY (replyUserId) REFERENCES t_user (id) ON DELETE CASCADE,
    CONSTRAINT fk_name_c2 FOREIGN KEY (commentId) REFERENCES t_movieCriticism (commentId) ON DELETE CASCADE

);
/*电影收藏表*/
create table t_movieCollection
(
    userId     bigint(20)  not null comment '用户id',
    movieId    varchar(64) not null comment '电影id',
    createTime datetime  default null comment '创建时间',
    updateTime timestamp default CURRENT_TIMESTAMP comment '更新时间',
    CONSTRAINT fk_name_cll FOREIGN KEY (userId) REFERENCES t_user (id) ON DELETE CASCADE,
    CONSTRAINT fk_name_cll1 FOREIGN KEY (movieId) REFERENCES t_movieInformation (movieId) ON DELETE CASCADE

);