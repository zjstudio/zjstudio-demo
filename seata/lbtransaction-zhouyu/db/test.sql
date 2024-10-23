/*
 Navicat Premium Dump SQL

 Source Server         : mysql-docker-8.0.38
 Source Server Type    : MySQL
 Source Server Version : 80038 (8.0.38)
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80038 (8.0.38)
 File Encoding         : 65001

 Date: 16/10/2024 19:37:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test` (
  `name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_test
-- ----------------------------
BEGIN;
INSERT INTO `t_test` (`name`, `create_time`) VALUES ('server1', '2024-10-16 19:13:02');
INSERT INTO `t_test` (`name`, `create_time`) VALUES ('server2', '2024-10-16 19:13:03');
INSERT INTO `t_test` (`name`, `create_time`) VALUES ('server1', '2024-10-16 19:24:44');
INSERT INTO `t_test` (`name`, `create_time`) VALUES ('server2', '2024-10-16 19:24:44');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
