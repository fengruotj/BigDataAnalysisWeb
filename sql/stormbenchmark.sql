/*
Navicat MySQL Data Transfer

Source Server         : 华科root2
Source Server Version : 50525
Source Host           : 211.69.198.200:3306
Source Database       : stormbenchmark

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-03-23 18:48:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES ('1', '谭杰', 'admin', 'admin');

-- ----------------------------
-- Table structure for t_hdfsbytecount
-- ----------------------------
DROP TABLE IF EXISTS `t_hdfsbytecount`;
CREATE TABLE `t_hdfsbytecount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `bytecount` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_hdfsbytecount
-- ----------------------------

-- ----------------------------
-- Table structure for t_spouttuplecount
-- ----------------------------
DROP TABLE IF EXISTS `t_spouttuplecount`;
CREATE TABLE `t_spouttuplecount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `tuplecount` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_spouttuplecount
-- ----------------------------

-- ----------------------------
-- Table structure for t_tuplecount
-- ----------------------------
DROP TABLE IF EXISTS `t_tuplecount`;
CREATE TABLE `t_tuplecount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `tuplecount` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_tuplecount
-- ----------------------------

-- ----------------------------
-- Table structure for t_tuplelatency
-- ----------------------------
DROP TABLE IF EXISTS `t_tuplelatency`;
CREATE TABLE `t_tuplelatency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tuplelatency` bigint(20) NOT NULL,
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_tuplelatency
-- ----------------------------

-- ----------------------------
-- Table structure for t_wordcount
-- ----------------------------
DROP TABLE IF EXISTS `t_wordcount`;
CREATE TABLE `t_wordcount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `word` varchar(255) NOT NULL,
  `count` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_wordcount
-- ----------------------------
