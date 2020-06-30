DROP DATABASE IF EXISTS site32;
CREATE DATABASE site32;
USE site32;
#카테고리
CREATE TABLE cateItem(
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	`name` CHAR(100) NOT NULL UNIQUE
);

INSERT INTO cateItem
SET regDate = NOW(),
`name` = '일상/취미';


INSERT INTO cateItem
SET regDate = NOW(),
`name` = '공부계획';

INSERT INTO cateItem
SET regDate = NOW(),
`name` = '유튜브 링크';

INSERT INTO cateItem
SET regDate = NOW(),
`name` = 'IT/일반';

#카테고리 아이템 4~5개

#1. 일상/취미
#2. 공부계획/유튜브 링크
#3. 공부중 모르는것들\

#케릭터 set 설정
SET NAMES utf8mb4;


SELECT * FROM cateItem;
SELECT * FROM article;

DROP TABLE article;
#게시물 테이블 생성
CREATE TABLE article(
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	cateItemId INT(10) UNSIGNED NOT NULL,
	displayStatus TINYINT(1) UNSIGNED NOT NULL,
	`title` CHAR(100) NOT NULL,
	`body` TEXT NOT NULL
);