CREATE SCHEMA `dbdemo`;
CREATE TABLE `dbdemo`.`note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
INSERT INTO `dbdemo`.`note` (`content`) VALUES ('Hello World!');
INSERT INTO `dbdemo`.`note` (`content`) VALUES ('Lorem Ipsum.');
