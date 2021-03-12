DROP TABLE IF EXISTS security_role;
CREATE TABLE `security_role` (
  `key` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色key',
  `name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifyTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  PRIMARY KEY (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

DROP TABLE IF EXISTS security_role_route;
CREATE TABLE `security_role_route` (
  `roleKey` bigint(20) NOT NULL COMMENT '角色ID',
  `routeId` bigint(20) NOT NULL COMMENT '页面ID',
  PRIMARY KEY (`roleKey`,`routeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色页面表';

DROP TABLE IF EXISTS security_route;
CREATE TABLE `security_route` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页面ID',
  `parentId` bigint(20) NOT NULL DEFAULT 0,
  `path` varchar(255) DEFAULT NULL COMMENT '页面路径',
  `name` varchar(255) DEFAULT NULL COMMENT '页面名称',
  `component` varchar(255) DEFAULT NULL COMMENT '页面组件',
  `title` varchar(255) DEFAULT NULL COMMENT '页面meta标题',
  `icon` varchar(255) DEFAULT NULL COMMENT '页面meta图标',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifyTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='页面表';

DROP TABLE IF EXISTS security_user;
CREATE TABLE `security_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(64) DEFAULT NULL COMMENT '登陆用户',
  `password` varchar(64) DEFAULT NULL COMMENT '登陆密码',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `introduction` varchar(500) DEFAULT NULL COMMENT '个人简介',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifyTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS security_user_role;
CREATE TABLE `security_user_role` (
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `roleKey` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`userId`,`roleKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';


INSERT INTO security_role(`key`,name,description,createTime,modifyTime,status) VALUES(2,'超级管理员','超级管理员','2021-03-11 07:15:44','2021-03-11 07:15:44',1),(3,'公司管理员','公司管理员','2021-03-11 07:16:28','2021-03-11 07:16:28',1),(4,'普通用户','普通用户','2021-03-11 07:16:48','2021-03-11 07:52:10',1);
INSERT INTO security_role_route(roleKey,routeId) VALUES(2,2),(2,3),(3,2);
INSERT INTO security_route(id,path,name,component,title,icon,createTime,modifyTime,status,parentId) VALUES(2,'/login','login','views/login/index','登陆界面',NULL,'2021-03-11 07:19:54','2021-03-11 07:19:54',1,0),(3,'/permission','permission','layout/Layout','权限控制','lock','2021-03-11 07:21:18','2021-03-11 07:21:18',1,0),(4,'page','PagePermission','views/permission/page','Page Permission',NULL,'2021-03-11 07:22:06','2021-03-11 07:23:28',1,3),(5,'directive','DirectivePermission','views/permission/directive','Directive Permission',NULL,'2021-03-11 07:24:07','2021-03-11 07:24:07',1,3),(6,'role','RolePermission','views/permission/role','Role Permission',NULL,'2021-03-11 07:24:37','2021-03-11 07:24:37',1,3);
INSERT INTO security_user(id,username,password,avatar,email,introduction,createTime,modifyTime,status,name) VALUES(1,'admin','$2a$10$XOS5dVaMv4DJHewoWvIoNOWq7OXMv2oYkEaFcP6T9p3ben42cnsm2','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','772333621@qq.com','技术宅男','2021-03-11 06:37:30','2021-03-11 08:25:57',1,'name_admin');
INSERT INTO security_user_role(userId,roleKey) VALUES(2,2);




