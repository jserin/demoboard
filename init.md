CREATE TABLE board (
boardId NUMBER PRIMARY KEY,
boardTitle VARCHAR2(100) NOT NULL,
boardCnt CLOB NOT NULL,
boardWriter VARCHAR2(50) NOT NULL,
boardPwd NUMBER(10) NOT NULL,
createDate TIMESTAMP DEFAULT sysdate,
modifyDate TIMESTAMP,
groupId NUMBER,
categoryId NUMBER NOT NULL,
boardDel CHAR(1) NOT NULL,
FOREIGN KEY (categoryId) REFERENCES category(categoryId)
);

CREATE TABLE fileTbl (
fileId NUMBER PRIMARY KEY,
fileName VARCHAR2(255),
saveName VARCHAR2(255),
fileSize NUMBER,
createDate TIMESTAMP DEFAULT sysdate,
modifyDate TIMESTAMP,
fileDel CHAR(1) NOT NULL,
boardId NUMBER,
FOREIGN KEY (boardId) REFERENCES board(boardId)
);

CREATE TABLE category (
categoryId NUMBER PRIMARY KEY,
categoryName VARCHAR2(50) NOT NULL
)

CREATE SEQUENCE board_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


CREATE SEQUENCE cmt_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE SEQUENCE file_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

ALTER SEQUENCE board_seq RESTART;

CREATE TABLE cmtTbl (
cmtId NUMBER PRIMARY KEY,
cmtCnt VARCHAR2(255) NOT NULL,
cmtWriter VARCHAR2(50) NOT NULL,
cmtPwd NUMBER(10) NOT NULL,
createDate TIMESTAMP DEFAULT sysdate,
modifyDate TIMESTAMP,
groupId NUMBER,
boardId NUMBER NOT NULL,
cmtDel CHAR(1) NOT NULL
);

select * from board;
select * from cmtTbl;

INSERT INTO category (categoryId, categoryName)
VALUES (1, '공지사항');

INSERT INTO category (categoryId, categoryName)
VALUES (2, '자유게시판');

create user c##serin identified by 123;
grant resource, connect, dba to c##serin;

-- '공지사항' 추가
INSERT INTO category (categoryId, categoryName)
VALUES (1, '공지사항');

-- '자유게시판' 추가
INSERT INTO category (categoryId, categoryName)
VALUES (2, '자유게시판');