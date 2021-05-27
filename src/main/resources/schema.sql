DROP TABLE IF EXISTS `dubbo_invoke`;

CREATE TABLE `dubbo_invoke` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `invoke_date` date NOT NULL,
  `service` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `consumer` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT '',
  `invoke_time` bigint(20) DEFAULT NULL,
  `success` int(11) DEFAULT NULL,
  `failure` int(11) DEFAULT NULL,
  `elapsed` int(11) DEFAULT NULL,
  `concurrent` int(11) DEFAULT NULL,
  `max_elapsed` int(11) DEFAULT NULL,
  `max_concurrent` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `index_service` (`service`),
  KEY `index_method` (`method`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;