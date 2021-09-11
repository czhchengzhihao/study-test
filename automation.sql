/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : automation

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2021-09-11 15:33:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cases_management
-- ----------------------------
DROP TABLE IF EXISTS `cases_management`;
CREATE TABLE `cases_management` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moduleName` varchar(255) DEFAULT NULL,
  `interfaceName` varchar(255) DEFAULT NULL,
  `apiUrl` varchar(255) DEFAULT NULL,
  `casesName` varchar(255) DEFAULT NULL,
  `requestMethod` varchar(255) DEFAULT NULL,
  `theGinseng` varchar(255) DEFAULT NULL,
  `expectedResults` varchar(255) DEFAULT NULL,
  `actualResults` varchar(255) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cases_management
-- ----------------------------

-- ----------------------------
-- Table structure for response_result
-- ----------------------------
DROP TABLE IF EXISTS `response_result`;
CREATE TABLE `response_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `success` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `entity` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of response_result
-- ----------------------------
INSERT INTO `response_result` VALUES ('1', 'aaa', 'aaaa', 'aaaa');
