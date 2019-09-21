SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
  `user_id`            int(11) NOT NULL auto_increment,
  `name`               varchar(255),
  `encrypted_password` varchar(255),
  `mobile`             varchar(255),
  PRIMARY KEY (`user_id`),
  unique `idx_mobile` (`mobile`),
  index `idx_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
