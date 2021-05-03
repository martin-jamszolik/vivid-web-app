CREATE TABLE `users` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(30) NOT NULL DEFAULT '',
  `full_name` varchar(30) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `settings` text
);

CREATE TABLE `company` (
  `c_key` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `c_name` varchar(45) NOT NULL DEFAULT '',
  `c_desc` varchar(100) NOT NULL DEFAULT '',
  `address1` varchar(100) NOT NULL,
  `address2` varchar(100) NOT NULL,
  `contact` varchar(100) NOT NULL,
  `visible` tinyint(4) DEFAULT '1',
  `is_default` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`c_key`)
);



CREATE TABLE `location` (
  `el_key` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(55) DEFAULT NULL,
  `city` varchar(40) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `zip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`el_key`)
);


CREATE TABLE `estimator` (
  `e_key` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `us_key` int(11) NOT NULL DEFAULT '0',
  `e_name` varchar(45) NOT NULL DEFAULT '',
  `e_admin` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `visible` tinyint(3) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`e_key`),
  KEY `FK_Estimator1` (`us_key`),
  CONSTRAINT `FK_Estimator1` FOREIGN KEY (`us_key`) REFERENCES `users` (`id`) ON UPDATE CASCADE
);




CREATE TABLE `project` (
  `pp_key` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `el_key` int(10) unsigned NOT NULL DEFAULT '0',
  `e_key` int(10) unsigned NOT NULL DEFAULT '0',
  `c_key` int(10) unsigned NOT NULL DEFAULT '0',
  `pp_name` varchar(60) DEFAULT NULL,
  `tax` decimal(10,4) DEFAULT '0.0000',
  `pp_status` varchar(20) DEFAULT NULL,
  `pp_date` date DEFAULT NULL,
  `pp_owner` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`pp_key`),
  KEY `FK_1_ProposalProject_1` (`el_key`),
  KEY `FK_1_ProposalProject_2` (`e_key`),
  KEY `FK_1_ProposalProject_3` (`c_key`),
  CONSTRAINT `FK_1_ProposalProject_1` FOREIGN KEY (`el_key`) REFERENCES `location` (`el_key`) ON UPDATE CASCADE,
  CONSTRAINT `FK_1_ProposalProject_2` FOREIGN KEY (`e_key`) REFERENCES `estimator` (`e_key`) ON UPDATE CASCADE,
  CONSTRAINT `FK_1_ProposalProject_3` FOREIGN KEY (`c_key`) REFERENCES `company` (`c_key`) ON UPDATE CASCADE
);


CREATE TABLE `proposal` (
  `pr_key` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pp_key` int(10) unsigned NOT NULL DEFAULT '0',
  `pr_date` datetime DEFAULT NULL,
  `pr_name` varchar(200) DEFAULT NULL,
  `editable` tinyint(1) DEFAULT NULL,
  `p_pr` decimal(10,2) DEFAULT NULL,
  `p_pr_type` tinyint(3) unsigned DEFAULT NULL,
  `pr_status` varchar(20) DEFAULT NULL,
  `authorize` date DEFAULT NULL,
  PRIMARY KEY (`pr_key`),
  KEY `FK_EstProposal_1` (`pp_key`),
  CONSTRAINT `FK_proposal_1` FOREIGN KEY (`pp_key`) REFERENCES `project` (`pp_key`) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE `task` (
  `pst_key` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pr_key` int(10) unsigned NOT NULL,
  `pst_descr` mediumtext,
  `c_cost` decimal(10,2) DEFAULT '0.00',
  `pst_id` varchar(35) DEFAULT NULL,
  `s_name` varchar(800) DEFAULT NULL,
  `t_name` varchar(800) DEFAULT NULL,
  `c_qty` decimal(10,3) DEFAULT '0.000',
  `c_unit` varchar(10) DEFAULT NULL,
  `t_pr` decimal(10,2) DEFAULT NULL,
  `t_pr_type` tinyint(3) unsigned DEFAULT NULL,
  `t_type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`pst_key`),
  KEY `FK_1_proposaltask_1` (`pr_key`),
  CONSTRAINT `FK_1_proposaltask_1` FOREIGN KEY (`pr_key`) REFERENCES `proposal` (`pr_key`) ON DELETE CASCADE ON UPDATE CASCADE
);
