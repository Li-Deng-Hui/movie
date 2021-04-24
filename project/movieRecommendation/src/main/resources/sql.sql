use movieRecommendation;
drop table t_user;
/*�û���*/
CREATE TABLE t_user
(
    id         bigint(20) NOT NULL AUTO_INCREMENT comment '�û�id',
    username   varchar(64)  DEFAULT NULL comment '�û���',
    avatar     varchar(255) DEFAULT NULL comment '�û�ͷ��',
    email      varchar(64)  DEFAULT NULL comment '�û�����',
    phone      varchar(13)  default null comment '�û��绰',
    password   varchar(64)  DEFAULT NULL comment '�û�����',
    status     int(5)     NOT NULL comment '�û�״̬',
    /*permissions varchar(255) DEFAULT null comment '�û�Ȩ��',*/
    role       int          default 0 comment '�û���ɫ 0��ͨ�û���1����Ա',
    created    datetime     DEFAULT NULL comment '����ʱ��',
    updateTime timestamp    default CURRENT_TIMESTAMP comment '����ʱ��',
    lastlogin  datetime     DEFAULT NULL comment '���һ�ε�¼ʱ��',
    PRIMARY KEY (`id`),
    KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO t_user (`id`, `username`, `avatar`, `email`, `password`, `status`, `created`, `lastlogin`)
VALUES ('1', 'markerhub',
        'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',
        NULL, '96e79218965eb72c92a549dd5a330112', '0', '2020-04-20 10:44:01', NULL);
/*��Ӱ��Ϣ��*/
create table t_movieInformation
(
    movieId         varchar(64) not null primary key comment '��Ӱid',
    movieName       varchar(255) default null comment '��Ӱ��',
    movieInfor      text         default null comment '��Ӱ��Ϣ',
    movieDirector   varchar(30)  default null comment '��Ӱ����',
    movieActor      varchar(255) default null comment '��Ӱ��Ա',
    movieType       varchar(255) default null comment '��Ӱ����',
    movieTag        varchar(255) default null comment '��Ӱ��ǩ',
    movieScore      int          default 0 comment '��Ӱ����',
    movieHot        int          default 0 comment '��Ӱ�ȶȻ��Ӱ����',
    movieCollection int          default 0 comment '��Ӱ�ղ���',
    movieTime       datetime     default null comment '��Ӱ����ʱ��',
    movieUrl        varchar(255) default null comment '��Ӱ����',
    createTime      datetime     default null comment '����ʱ��',
    updateTime      timestamp    default CURRENT_TIMESTAMP comment '����ʱ��'
);
/*��Ӱ���۱�*/
create table t_movieCriticism
(
    commentId      bigint(20)  NOT NULL AUTO_INCREMENT primary key comment '����id',
    movieId        varchar(64) not null comment '��Ӱid',
    userId         bigint(20)  not null comment '�ظ��û�id',
    criticism      text        default null comment '��Ӱ����',
    replyComment   int         default 0 comment '�Ƿ��ǻظ����� 0���ǣ�1��',
    replyUserId    bigint(20)  default null comment '���ظ��û�id',
    replyCommentId varchar(64) default null comment '�ظ�����id',
    replyNumber    int         default 0 comment '�ظ���',
    thumb          int         default 0 comment '������',
    createTime     datetime    default null comment '����ʱ��',
    updateTime     timestamp   default CURRENT_TIMESTAMP comment '����ʱ��',
    CONSTRAINT fk_name FOREIGN KEY (userId) REFERENCES t_user (id) ON DELETE CASCADE,
    CONSTRAINT fk_name1 FOREIGN KEY (replyUserId) REFERENCES t_user (id) ON DELETE CASCADE,
    /* CONSTRAINT fk_name2 FOREIGN KEY(replyCommentId)REFERENCES t_movieCriticism(commentId)  ON DELETE CASCADE,*/
    CONSTRAINT fk_name3 FOREIGN KEY (movieId) REFERENCES t_movieInformation (movieId) ON DELETE CASCADE
);
/*���ۻظ�֪ͨ��*/
create table t_movieReplyComment
(
    replyUserId bigint(20) NOT NULL comment '���ظ��û�id',
    userId      bigint(20) NOT NULL comment '�ظ��û�id',
    commentId   bigint(20) NOT NULL AUTO_INCREMENT comment '����id',
    createTime  datetime  default null comment '����ʱ��',
    isdelete    int       default 0 comment '�Ƿ�ɾ�� 0δɾ����1ɾ��',
    updateTime  timestamp default CURRENT_TIMESTAMP comment '����ʱ��',
    CONSTRAINT fk_name_c FOREIGN KEY (userId) REFERENCES t_user (id) ON DELETE CASCADE,
    CONSTRAINT fk_name_c1 FOREIGN KEY (replyUserId) REFERENCES t_user (id) ON DELETE CASCADE,
    CONSTRAINT fk_name_c2 FOREIGN KEY (commentId) REFERENCES t_movieCriticism (commentId) ON DELETE CASCADE

);
/*��Ӱ�ղر�*/
create table t_movieCollection
(
    userId     bigint(20)  not null comment '�û�id',
    movieId    varchar(64) not null comment '��Ӱid',
    createTime datetime  default null comment '����ʱ��',
    updateTime timestamp default CURRENT_TIMESTAMP comment '����ʱ��',
    CONSTRAINT fk_name_cll FOREIGN KEY (userId) REFERENCES t_user (id) ON DELETE CASCADE,
    CONSTRAINT fk_name_cll1 FOREIGN KEY (movieId) REFERENCES t_movieInformation (movieId) ON DELETE CASCADE

);