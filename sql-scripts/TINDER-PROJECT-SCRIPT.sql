DROP SCHEMA IF EXISTS `tinder_database_schema`;

CREATE SCHEMA `tinder_database_schema`;

use `tinder_database_schema`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `userentity`;

CREATE TABLE `userentity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(128) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `lastname` varchar(40) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `sexualorientation` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `hobbies` varchar(100) DEFAULT NULL,
  
 
  PRIMARY KEY (`id`)
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user_buffer`;

CREATE TABLE `user_buffer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(128) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `lastname` varchar(40) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `sexualorientation` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `hobbies` varchar(100) DEFAULT NULL,
 
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `matcher`;

CREATE TABLE `matcher`(
	`user_id` int(11) NOT NULL,
    `userbuffer_id` int NOT NULL,
     PRIMARY KEY (`user_id`,`userbuffer_id`),
   
    FOREIGN KEY(`user_id`) REFERENCES `userentity` (`id`) 
    ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY(`userbuffer_id`) REFERENCES `user_buffer` (`id`) 
    ON DELETE NO ACTION ON UPDATE NO ACTION
    
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
