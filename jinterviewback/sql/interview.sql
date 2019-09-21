SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for interview
-- ----------------------------
DROP TABLE IF EXISTS `interview`;
CREATE TABLE `interview`
(
  `interview_id` int(11) NOT NULL auto_increment,
  `user_id`      int(11) not null,
  `company_name` varchar(255),
  `address`      varchar(255),
  `time`         datetime,
  `status`       int(11),
  `suggestion`   INT(11),
  `comment`      varchar(255),
  PRIMARY KEY (`interview_id`),
  index `idx_user_id` (`user_id`),
  index `idx_time` (`time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
