CREATE DATABASE if not exists scrum_db;
CREATE TABLE if not exists `scrum_tool_tbl`  (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `projectName` varchar(64)  NOT NULL,
   `number` int(11)  NOT NULL,
   PRIMARY KEY (`id`)
);
