/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1-13306
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 127.0.0.1:13306
 Source Schema         : business

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 28/02/2021 20:45:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` bigint(1) NOT NULL COMMENT '购物车id',
  `goods_id` bigint(1) NOT NULL COMMENT '商品id',
  `sku_id` bigint(1) NOT NULL COMMENT 'sku_id',
  `user_id` bigint(1) NOT NULL COMMENT '用户id',
  `is_del` bigint(1) NOT NULL,
  `create_time` bigint(1) NOT NULL,
  `modify_time` bigint(1) NOT NULL,
  `create_user` bigint(1) NOT NULL,
  `modify_user` bigint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cart
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(1) NOT NULL COMMENT '分类id',
  `category_name` varchar(20) NOT NULL COMMENT '分类名称',
  `pid` bigint(1) NOT NULL COMMENT '父级分类；0：顶级',
  `category_level` tinyint(1) NOT NULL COMMENT '分类等级',
  `is_del` tinyint(1) NOT NULL COMMENT '是否删除',
  `create_time` bigint(1) NOT NULL COMMENT '创建时间',
  `modify_time` bigint(1) NOT NULL COMMENT '修改时间',
  `create_user` bigint(1) NOT NULL COMMENT '创建人',
  `modify_user` bigint(1) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(1) NOT NULL COMMENT '商品id',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名层',
  `category_id` bigint(1) NOT NULL COMMENT '商品分类id',
  `title` varchar(20) NOT NULL COMMENT '商品标题',
  `desc` varchar(50) NOT NULL COMMENT '商品描述',
  `pic` varchar(60) NOT NULL COMMENT '商品主图连接',
  `is_on` tinyint(1) NOT NULL COMMENT '是否上架',
  `is_del` tinyint(1) NOT NULL COMMENT '是否删除',
  `create_time` bigint(1) NOT NULL COMMENT '创建时间',
  `modify_time` bigint(1) NOT NULL COMMENT '修改时间',
  `create_user` bigint(1) NOT NULL COMMENT '创建人',
  `modify_user` bigint(1) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(1) NOT NULL COMMENT '主订单id',
  `goods_id` bigint(1) NOT NULL COMMENT '商品名称',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
  `pic` varchar(30) NOT NULL COMMENT '商品图片',
  `total_price` decimal(10,2) NOT NULL COMMENT '订单总价',
  `order_status` varchar(20) NOT NULL COMMENT '订单状态：UNPAID：未付款；UNDELIVERED：待发货；UNRECEIVED：待收货；UNCOMMENT：待评论；COMPLETE：完成',
  `is_del` tinyint(1) NOT NULL,
  `create_time` bigint(1) NOT NULL,
  `modify_time` bigint(1) NOT NULL,
  `create_user` bigint(1) NOT NULL,
  `modify_user` bigint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order_sku
-- ----------------------------
DROP TABLE IF EXISTS `order_sku`;
CREATE TABLE `order_sku` (
  `id` bigint(1) NOT NULL COMMENT '子订单id',
  `order_id` bigint(1) NOT NULL COMMENT '主订单id',
  `sku_price` decimal(10,2) NOT NULL COMMENT 'sku价格（单价）',
  `sku_name` varchar(30) NOT NULL COMMENT 'sku名称',
  `sku_pic` varchar(30) NOT NULL COMMENT 'sku图片链接',
  `number` int(1) NOT NULL COMMENT '购买数量',
  `is_del` tinyint(1) NOT NULL,
  `create_time` bigint(1) NOT NULL,
  `modify_time` bigint(1) NOT NULL,
  `create_user` bigint(1) NOT NULL,
  `modify_user` bigint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_idx` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order_sku
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku` (
  `id` bigint(1) NOT NULL COMMENT 'skuid',
  `goods_id` bigint(1) NOT NULL COMMENT '商品id',
  `sku_name` bigint(1) NOT NULL COMMENT 'sku名称',
  `stock` int(1) NOT NULL COMMENT '库存',
  `pic` varchar(30) NOT NULL COMMENT 'sku图片',
  `price` decimal(10,2) NOT NULL COMMENT 'sku价格',
  `is_del` tinyint(1) NOT NULL COMMENT '是否删除',
  `create_time` bigint(1) NOT NULL,
  `modify_time` bigint(1) NOT NULL,
  `create_user` bigint(1) NOT NULL,
  `modify_user` bigint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `goods_id_idx` (`goods_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sku
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(1) NOT NULL COMMENT '用户id',
  `user_name` varchar(60) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `modile_phone` varchar(30) NOT NULL COMMENT '手机号',
  `email` varchar(60) NOT NULL,
  `is_del` tinyint(1) NOT NULL COMMENT '0：未删除；1:已删除',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `modify_time` bigint(1) NOT NULL COMMENT '修改使劲啊；',
  PRIMARY KEY (`id`),
  KEY `username_isdel_idx` (`user_name`,`is_del`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
