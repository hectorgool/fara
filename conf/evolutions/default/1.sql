# --- !Ups

CREATE TABLE `user` (
	`userID` VARCHAR(100) NOT NULL PRIMARY KEY,
	`firstName` VARCHAR(100),
	`lastName` VARCHAR(100),
	`fullName` VARCHAR(100),
	`email` VARCHAR(100),
	`avatarURL` VARCHAR(100)
	);
CREATE TABLE `logininfo` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`providerID` VARCHAR(100) NOT NULL,
	`providerKey` VARCHAR(100) NOT NULL
	);
CREATE TABLE `userlogininfo` (
	`userID` VARCHAR(100) NOT NULL,
	`loginInfoId` BIGINT NOT NULL
	);
CREATE TABLE `passwordinfo` (
	`hasher` VARCHAR(100) NOT NULL,
	`password` VARCHAR(100) NOT NULL,
	`salt` VARCHAR(100),
	`loginInfoId` BIGINT NOT NULL
	);
CREATE TABLE `oauth1info` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`token` VARCHAR(100) NOT NULL,`secret` VARCHAR(100) NOT NULL,
	`loginInfoId` BIGINT NOT NULL
	);
CREATE TABLE `oauth2info` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`accesstoken` VARCHAR(100) NOT NULL,
	`tokentype` VARCHAR(100),
	`expiresin` INTEGER,
	`refreshtoken` VARCHAR(100),
	`logininfoid` BIGINT NOT NULL
	);
CREATE TABLE `openidinfo` (
	`id` VARCHAR(100) NOT NULL PRIMARY KEY,
	`logininfoid` BIGINT NOT NULL
	);
CREATE TABLE `openidattributes` (
	`id` VARCHAR(100) NOT NULL,
	`key` VARCHAR(100) NOT NULL,
	`value` VARCHAR(100) NOT NULL
	);


# --- !Downs

DROP TABLE `openidattributes`;
DROP TABLE `openidinfo`;
DROP TABLE `oauth2info`;
DROP TABLE `oauth1info`;
DROP TABLE `passwordinfo`;
DROP TABLE `userlogininfo`;
DROP TABLE `logininfo`;
DROP TABLE `user`;