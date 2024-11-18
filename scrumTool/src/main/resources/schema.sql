CREATE DATABASE if not exists scrum_db;
CREATE TABLE if not exists `scrum_tool_tbl`  (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `projectName` varchar(64)  NOT NULL,
   `number` int(11)  NOT NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE if not exists `scrum_user_tbl`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(64)  NOT NULL,
    `email` varchar(64)   NOT NULL,
    `password` varchar(64)   NOT NULL,
    PRIMARY KEY (`id`)
    );
