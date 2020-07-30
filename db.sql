#케릭터 set 설정
SET NAMES utf8mb4;

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

#멤버 테이블 생성
CREATE TABLE `member`(
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(100) NOT NULL UNIQUE,
	loginPw CHAR(200) NOT NULL,
	`name` CHAR(100) NOT NULL,
	`nickname` CHAR(100) NOT NULL UNIQUE,
	email CHAR(100) NOT NULL UNIQUE,
	`level` INT(10) NOT NULL,
	mailAuth TINYINT(1) NOT NULL 
);

CREATE TABLE articleReply(
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	articleId CHAR(50) NOT NULL,
	memberId CHAR(100) NOT NULL,
	`nickname` CHAR(70) NOT NULL,
	`body` CHAR(250) NOT NULL

);

ALTER TABLE `member`
ADD email CHAR(250) NOT NULL;

INSERT INTO `member`(
	regDate = NOW(),
	loginId = 'danny5603',
	loginPw = '1234',
	`name` = 'danny',
	`nickname` = 'danny',
	email = 'dannykim5603@asdf'
);

ALTER TABLE `member` MODIFY mailAuth TINYINT(1) NOT NULL;
ALTER TABLE `member` ADD mailAuthCode CHAR(100) NOT NULL AFTER mailAuth;

UPDATE article SET hit = hit + 1 WHERE id = 16;

UPDATE article
 SET title = 'test1title',
 `body`= 'test1body',
 updateDate = NOW(),
 cateItemId = 1,
 displayStatus = 1
 WHERE id= 16;
 
 SELECT * 
 FROM `member`
  WHERE 1
  AND loginId = 'dannykim5603'
  AND loginPw = '11';
 
 
SELECT * FROM cateItem;
SELECT * FROM article;
SELECT * FROM `member`;
SELECT * FROM articleReply;
SELECT * FROM attr;


DESC articleReply;
DESC `member`;
 
 SELECT * FROM articleReply; 
 
 
ALTER TABLE MEMBER MODIFY `level` INT(1) DEFAULT 1 NOT NULL;
ALTER TABLE `member` MODIFY mailAuth TINYINT(1) DEFAULT 0 NOT NULL;
ALTER TABLE `member` MODIFY mailAuthCode CHAR(100) DEFAULT 0 NOT NULL;

SELECT *
FROM `member`;

SELECT *, member.name AS extra__writer FROM article AS aritcle
INNER JOIN MEMBER AS `member` ON memberId = member.id
WHERE 1 AND displayStatus = 1 AND id = 4;

UPDATE MEMBER 
SET email = 'dannykim5603@gmail.com' 
, nickname = 'danny' 
, loginPw = 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'
 WHERE id = 16;
 
# 부가정보테이블 
# 댓글 테이블 추가
DROP TABLE IF EXISTS attr;
CREATE TABLE attr (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDat`site32``attr`e DATETIME NOT NULL,
    `name` CHAR(100) NOT NULL UNIQUE,
    `value` TEXT NOT NULL
);


# attr 테이블에서 name 을 4가지 칼럼으로 나누기
ALTER TABLE `attr` DROP COLUMN `name`,
ADD COLUMN `relTypeCode` CHAR(20) NOT NULL AFTER `updateDate`,
ADD COLUMN `relId` INT(10) UNSIGNED NOT NULL AFTER `relTypeCode`,
ADD COLUMN `typeCode` CHAR(30) NOT NULL AFTER `relId`,
ADD COLUMN `type2Code` CHAR(30) NOT NULL AFTER `typeCode`,
CHANGE `value` `value` TEXT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `type2Code`,
DROP INDEX `name`; 

# attr 유니크 인덱스 걸기
## 중복변수 생성금지
## 변수찾는 속도 최적화
ALTER TABLE `attr` ADD UNIQUE INDEX (`relTypeCode`, `relId`, `typeCode`, `type2Code`); 

## 특정 조건을 만족하는 회원 또는 게시물(기타 데이터)를 빠르게 찾기 위해서
ALTER TABLE `attr` ADD INDEX (`relTypeCode`, `typeCode`, `type2Code`);  


# updateDate 칼럼 추가
ALTER TABLE `cateItem` ADD COLUMN `updateDate` DATETIME NOT NULL AFTER `regDate`; 
 
SELECT * FROM `member` WHERE email ='admin@admin.com' AND `name` = 'admin' AND loginId = 'admin';
SELECT * FROM MEMBER WHERE email = 'admin@admin.com' AND `name` = 'admin';

ALTER TABLE `article` ADD INDEX (`displayStatus`,`cateItemId`);
ALTER TABLE `articleReply` ADD INDEX(`articleId`);
ALTER TABLE `member` ADD INDEX(`loginId`,`loginPw`);

