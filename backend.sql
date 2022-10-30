/*
 Navicat Premium Data Transfer

 Source Server         : mysql-8.0.30-winx64
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : backend

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 16/10/2022 11:10:11
*/
CREATE DATABASE IF NOT EXISTS backend;
USE backend;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (7, 19, 2, 1);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `product_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `rate` int NULL DEFAULT NULL,
  `content` char(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 2, 5, 'Good.');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `product_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `warehouse_id` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `warehouse_id`(`warehouse_id`) USING BTREE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`warehouse_id`) REFERENCES `smart_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `name` char(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `category` char(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `location` char(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `product_id` int NOT NULL,
  `imglocation` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 1, 'apple12', 10.00, '11', '1', 10, 0, 'https://imgservice.suning.cn/uimg1/b2c/image/d6LkNg0P9cZP0m8P_4yaog.jpg_800w_800h_4e');
INSERT INTO `product` VALUES (2, 1, 'apple11', 5.00, '22', '1', 100, 0, 'https://imgservice.suning.cn/uimg1/b2c/image/d6LkNg0P9cZP0m8P_4yaog.jpg_800w_800h_4e');

-- ----------------------------
-- Table structure for smart_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `smart_warehouse`;
CREATE TABLE `smart_warehouse`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` char(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `price` int NULL DEFAULT NULL,
  `headquarter_location` char(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of smart_warehouse
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NULL DEFAULT NULL,
  `voucher_id` int NULL DEFAULT NULL,
  `username` char(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `user_email` char(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `password` char(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `address` char(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `admin` tinyint(1) NULL DEFAULT 0 COMMENT 'admin 0:no，1:yes',
  `isban` tinyint(1) NULL DEFAULT 0 COMMENT 'state 0:ban，1:normal',
  `is_ban` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `voucher_id`(`voucher_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 0, 0, 'elian', '814846181@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 'home', 1, 0, b'0');
INSERT INTO `user` VALUES (2, 0, 0, 'cbx', '15197730857@163.com', 'e10adc3949ba59abbe56e057f20f883e', 'home', 1, 0, b'0');
INSERT INTO `user` VALUES (7, 0, 0, 'cbx2', '15197730857@163.com', 'e10adc3949ba59abbe56e057f20f883e', 'home', 0, 0, b'0');
INSERT INTO `user` VALUES (8, 0, 0, 'guo', 'guo@qq.com', '202cb962ac59075b964b07152d234b70', 'guo', 0, 0, b'0');
INSERT INTO `user` VALUES (12, NULL, NULL, 'guoyx', '276600944@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 'brisbane', 0, 0, b'0');
INSERT INTO `user` VALUES (13, NULL, NULL, 'jerry', '276600944@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 'brisbane', 0, 0, b'0');
INSERT INTO `user` VALUES (14, NULL, NULL, 'jerry,guo', '276600944@qq.com,123@qq', '3b5f3aa5689ffed36b60246b54289aef', 'brisbane', 0, 0, b'0');
INSERT INTO `user` VALUES (15, NULL, NULL, 'guo1', 'guo1@qq.copm', 'd41d8cd98f00b204e9800998ecf8427e', 'guo1', 0, 0, b'0');
INSERT INTO `user` VALUES (16, NULL, NULL, '1', '1', 'c4ca4238a0b923820dcc509a6f75849b', '1', 0, 0, b'0');
INSERT INTO `user` VALUES (17, NULL, NULL, '', '', 'd41d8cd98f00b204e9800998ecf8427e', '', 0, 0, b'0');
INSERT INTO `user` VALUES (18, 0, 0, 'gyx', '27@qq.com', '202cb962ac59075b964b07152d234b70', '1', 0, 0, b'0');
INSERT INTO `user` VALUES (19, 0, 0, '12345', '11', '827ccb0eea8a706c4c34a16891f84e7b', '22', 0, 0, b'0');

-- ----------------------------
-- Table structure for voucher
-- ----------------------------
DROP TABLE IF EXISTS `voucher`;
CREATE TABLE `voucher`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `name` char(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `rate` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `voucher_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of voucher
-- ----------------------------
INSERT INTO `voucher` VALUES (1, 0, 'unkown', 0.00, NULL);
INSERT INTO `voucher` VALUES (2, 18, '10%off', 0.00, NULL);
INSERT INTO `voucher` VALUES (3, 18, '10 dollar off', 0.00, NULL);
INSERT INTO `voucher` VALUES (4, 19, '10percent off', 0.00, 0.90);
INSERT INTO `voucher` VALUES (5, 19, '10 dollar off', 10.00, 1.00);

SET FOREIGN_KEY_CHECKS = 1;
