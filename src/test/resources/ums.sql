CREATE DATABASE `ums` DEFAULT CHARACTER SET utf8mb4 ;
USE `ums`;

CREATE TABLE `user`(
    `userId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username` VARCHAR(20) NOT NULL COMMENT '用户名',
    `name` VARCHAR(10) NOT NULL COMMENT '姓名',
    `password` VARCHAR(500) NOT NULL COMMENT '密码',
    `userStatusType` INT(11) NOT NULL DEFAULT '1' COMMENT '用户状态类型',
    `loginTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登陆时间',
    `preLoginTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '前一次登陆时间',
    `loginCount` INT(11) NOT NULL DEFAULT '0' COMMENT '登录次数',
    `createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`userId`),
    UNIQUE (`username`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COMMENT='用户表';
CREATE INDEX user_username_idx ON user (`username`);
CREATE INDEX user_updateTime_idx ON user (`updateTime`);

CREATE TABLE `role` (
  `roleId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `roleName` VARCHAR(64) NOT NULL COMMENT '角色名称',
  `description` VARCHAR(200) NOT NULL COMMENT '角色描述',
  `createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`roleId`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COMMENT='角色表';
CREATE INDEX role_updateTime_idx ON role (`updateTime`);

CREATE TABLE `permission_group` (
  `permissionGroupId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '权限组id',
  `permissionGroupName` VARCHAR(64) NOT NULL COMMENT '权限组名称',
  `description` VARCHAR(200) NOT NULL COMMENT '权限组描述',
  `createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`permissionGroupId`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COMMENT='权限组表';
CREATE INDEX permission_group_updateTime_idx ON permission_group (`updateTime`);

CREATE TABLE `permission` (
  `permissionId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permissionName` VARCHAR(64) NOT NULL COMMENT '权限名称',
  `permissionGroupId` INT(11) NOT NULL COMMENT '权限组id',
  `path` VARCHAR(100) NOT NULL COMMENT '权限地址',
  `description` VARCHAR(200) NOT NULL COMMENT '权限描述',
  `createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`permissionId`),
  UNIQUE (`path`),
  FOREIGN KEY (permissionGroupId) REFERENCES permission_group(permissionGroupId)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COMMENT='权限表';
CREATE INDEX permission_updateTime_idx ON permission (`updateTime`);

CREATE TABLE `user_role` (
    `userId` INT(11) NOT NULL COMMENT '用户id',
    `roleId` INT(11) NOT NULL COMMENT '角色id',
    `createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`userId` , `roleId`),
    FOREIGN KEY (userId) REFERENCES user(userId),
    FOREIGN KEY (roleId) REFERENCES role(roleId)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COMMENT='用户角色表';
CREATE INDEX user_role_updateTime_idx ON user_role (`updateTime`);

CREATE TABLE `role_permission` (
    `roleId` INT(11) NOT NULL COMMENT '角色id',
    `permissionId` INT(11) NOT NULL COMMENT '权限id',
    `createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`roleId` , `permissionId`),
    FOREIGN KEY (roleId) REFERENCES role(roleId),
    FOREIGN KEY (permissionId) REFERENCES permission(permissionId)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COMMENT='角色权限表';
CREATE INDEX role_permission_updateTime_idx ON role_permission (`updateTime`);


INSERT INTO permission_group (`permissionGroupName`, `description`) VALUES ('权限组', '');
INSERT INTO permission_group (`permissionGroupName`, `description`) VALUES ('权限', '');
INSERT INTO permission_group (`permissionGroupName`, `description`) VALUES ('角色', '');
INSERT INTO permission_group (`permissionGroupName`, `description`) VALUES ('用户', '');

INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('创建权限组', 1, '/api/permissionGroup/createPermissionGroup', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('删除权限组', 1, '/api/permissionGroup/deletePermissionGroup', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('修改权限组', 1, '/api/permissionGroup/updatePermissionGroup', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('读取权限组', 1, '/api/permissionGroup/readPermissionGroup', '');

INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('创建权限', 2, '/api/permission/createPermission', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('删除权限', 2, '/api/permission/deletePermission', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('修改权限', 2, '/api/permission/updatePermission', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('读取权限', 2, '/api/permission/readPermission', '');

INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('创建角色', 3, '/api/role/createRole', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('删除角色', 3, '/api/role/deleteRole', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('修改角色', 3, '/api/role/updateRole', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('读取角色', 3, '/api/role/readRole', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('分配角色权限', 3, '/api/role/assignRolePermission', '');

INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('创建用户', 4, '/api/user/createUser', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('删除用户', 4, '/api/user/deleteUser', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('修改用户', 4, '/api/user/updateUser', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('读取用户', 4, '/api/user/readUser', '');
INSERT INTO permission (`permissionName`, `permissionGroupId`, `path`, `description`) VALUES ('分配用户角色', 4, '/api/user/assignUserRole', '');

INSERT INTO role (`roleName`, `description`) VALUES ('管理员角色', '');

INSERT INTO user (`username`, `name`, `password`) VALUES ('admin', '管理员', '531b937a336529891035dd898cc836c0');

INSERT INTO user_role (`userId`, `roleId`) VALUES (1, 1);

INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 1);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 2);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 3);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 4);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 5);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 6);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 7);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 8);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 9);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 10);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 11);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 12);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 13);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 14);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 15);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 16);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 17);
INSERT INTO role_permission (`roleId`, `permissionId`) VALUES (1, 18);



