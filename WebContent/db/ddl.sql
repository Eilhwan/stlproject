DROP TABLE MEMBER;
DROP TABLE REVIEWBOARD;
DROP TABLE QNABOARD;
DROP TABLE CART;
DROP TABLE ORDERS;
DROP TABLE ORDERDETAIL;

DROP TABLE MEMBERGRADE;
CREATE TABLE MEMBERGRADE(
    GRADELEVEL NUMBER(5) PRIMARY KEY,
    GRADENAME VARCHAR2(10) NOT NULL,
    LOWSPENT NUMBER(10) NOT NULL,
    HISPENT NUMBER(10) NOT NULL,
    DISCOUNT NUMBER(3));
INSERT INTO MEMBERGRADE VALUES(1, 'NORMAL', 0, 50000, 0);
INSERT INTO MEMBERGRADE VALUES(2, 'GOLD', 50001, 100000, 5);
INSERT INTO MEMBERGRADE VALUES(3, 'VIP', 100001, 500000, 10);
INSERT INTO MEMBERGRADE VALUES(4, 'VVIP', 200001, 1000000000, 20);
SELECT * FROM MEMBERGRADE;    


-- MEMBER TABLE   
CREATE TABLE MEMBER(
    MEMBERID VARCHAR2(50) PRIMARY KEY,
    MEMBERPW VARCHAR2(50) NOT NULL,
    MEMBERNAME VARCHAR2(20) NOT NULL,
    MEMBERTEL VARCHAR2(50) UNIQUE,
    MEMBEREMAIL VARCHAR2(50) NOT NULL,
    MEMBERADDRESS VARCHAR2(200) NOT NULL,
    MEMBERPOST VARCHAR2(10),
    MEMBERSTATUS NUMBER(1) DEFAULT 1,
    TOTALSPENT NUMBER(10) DEFAULT 0,
    MEMBERPOINT NUMBER(10) DEFAULT 0,
    GRADELEVEL NUMBER(5) REFERENCES MEMBERGRADE(GRADELEVEL),
    MEMBERRDATE DATE DEFAULT SYSDATE,
    MEMBERBIRTH DATE);
SELECT * FROM MEMBER;
COMMIT;


-- ADMIN 테이블
DROP TABLE ADMIN;
CREATE TABLE ADMIN(
    ADMINID VARCHAR2(50) PRIMARY KEY,
    ADMINPW VARCHAR2(50) UNIQUE);
INSERT INTO ADMIN VALUES('ADMIN', '123');

CREATE TABLE NOTICE(
    NOTICENO NUMBER(7) PRIMARY KEY,
    NOTICENAME VARCHAR2(50) NOT NULL,
    NOTICECONTENT VARCHAR2(1000) NOT NULL,
    NOTICERDATE DATE DEFAULT SYSDATE,
    NOTICEEVENT NUMBER(1),
    ADMINID VARCHAR2(50) REFERENCES ADMIN(ADMINID));

--게시판들

--REVIEW 게시판
CREATE TABLE REVIEWBOARD(
    REVIEWNO NUMBER(7) PRIMARY KEY,
    REVIEWNAME VARCHAR2(50) NOT NULL,
    REVIEWIMG1 VARCHAR2(50),
    REVIEWIMG2 VARCHAR2(50),
    REVIEWCONTENT VARCHAR2(2000) NOT NULL,
    PECODE NUMBER(7) REFERENCES PRODUCTENROLL(PECODE),
    MEMBERID VARCHAR2(50) REFERENCES MEMBER(MEMBERID),
    REVIEWRDATE DATE DEFAULT SYSDATE,
    REVIEWSTATUS NUMBER(1) DEFAULT 1,
    USERIP VARCHAR2(50));
CREATE SEQUENCE RENO_SEQ
    MAXVALUE 9999999
    NOCACHE
    NOCYCLE;
DROP TABLE REVIEWBOARD;
--QNA 게시판
DROP TABLE QNABOARD;
CREATE TABLE QNABOARD(
    QNANO NUMBER(7) PRIMARY KEY,
    MEMBERID VARCHAR2(50),
    QNANAME VARCHAR2(50) NOT NULL,
    QNACONTENT VARCHAR2(2000) NOT NULL,
    QNARDATE DATE DEFAULT SYSDATE,
    QNAANSWER NUMBER(1),
    QNAGROUP NUMBER(7));
CREATE SEQUENCE QNANO_SEQ
    MAXVALUE 9999999
    NOCACHE
    NOCYCLE;

--FAQ
CREATE TABLE FAQCATEGORY(
    FAQCNO NUMBER(2) PRIMARY KEY,
    FAQCNAME VARCHAR2(50));

CREATE TABLE FAQ(
    FAQNO NUMBER(7) PRIMARY KEY,
    FAQQEUSTION VARCHAR2(2000) NOT NULL,
    FAQANSWER VARCHAR2(2000) NOT NULL,
    FAQCNO NUMBER(2) REFERENCES FAQCATEGORY(FAQCNO));

--PRODUCT
DROP TABLE PRODUCT;

CREATE TABLE PRODUCT(
    PRODUCTCODE NUMBER(7) PRIMARY KEY,
    PRODUCTNAME VARCHAR2(50) NOT NULL,
    PRODUCTPRICE VARCHAR2(50) NOT NULL,
    PTYPECODE NUMBER(3) REFERENCES PRODUCT_TYPE(PTYPECODE),
    PRODUCTIMG VARCHAR2(100) NOT NULL,
    PBRANDCODE NUMBER(3) REFERENCES PRODUCT_BRAND(PBRANDCODE) NOT NULL,
    PRODUCTREMAIN NUMBER(7) NOT NULL,
    PRODUCTCONTENT VARCHAR2(2000));
DROP SEQUENCE PCODE_SEQ;
CREATE SEQUENCE PCODE_SEQ
    MAXVALUE 9999999
    NOCACHE
    NOCYCLE;

CREATE TABLE PRODUCT_BRAND(
    PBRANDCODE NUMBER(3) PRIMARY KEY,
    PBRANDNAME VARCHAR2(20) UNIQUE,
    PBRANDCOUNTRY VARCHAR2(20));
CREATE TABLE PRODUCT_TYPE(
    PTYPECODE NUMBER(3) PRIMARY KEY,
    PTYPENAME VARCHAR2(20) UNIQUE);
    
DROP TABLE PRODUCTENROLL;
CREATE TABLE PRODUCTENROLL(
    PECODE NUMBER(7) PRIMARY KEY,
    PRODUCTCODE NUMBER(7) REFERENCES PRODUCT(PRODUCTCODE),
    PENAME VARCHAR2(50) NOT NULL UNIQUE,
    PECONTENT VARCHAR2(2000) NOT NULL,
    PEIMG1 VARCHAR2(100) DEFAULT 'noimg.gif',
    PEIMG2 VARCHAR2(100),
    PEPRICE NUMBER(10),
    PERDATE DATE DEFAULT SYSDATE,
    SELLCNT NUMBER(7) DEFAULT 0,
    PEPOINT NUMBER(7) DEFAULT 0,
    PESTARS NUMBER(2),
    PEDISCOUNT NUMBER(3) DEFAULT 0,
    PESTATUS NUMBER(1) DEFAULT 1);
    
--ORDER
DROP TABLE CART;
CREATE TABLE CART(
    CARTNO NUMBER(7) PRIMARY KEY,
    MEMBERID VARCHAR2(50) REFERENCES MEMBER(MEMBERID),
    PRODUCTEA NUMBER(7) DEFAULT 1,
    PRODUCTCODE NUMBER(7) REFERENCES PRODUCT(PRODUCTCODE),
    CARTPRICE NUMBER(10) );
CREATE TABLE ORDERS(
    ORDERNO NUMBER(7) PRIMARY KEY,
    ORDERDATE VARCHAR2(100) DEFAULT TO_CHAR(SYSDATE,'YY-MM-DD HH:MI:SS'),
    ORDERADDRESS VARCHAR2(200) NOT NULL,
    ORDERTEL VARCHAR2(50) NOT NULL,
    ORDERTOTAL NUMBER(10) NOT NULL,
    ORDERSTATUS NUMBER(1) DEFAULT 1 NOT NULL,
    MEMBERID VARCHAR2(50) REFERENCES MEMBER(MEMBERID));
DROP TABLE ORDERS;
DROP TABLE ORDERDETAIL;
CREATE TABLE ORDERDETAIL(
    ODNO NUMBER(7) PRIMARY KEY,
    PRODUCTCODE NUMBER(7) REFERENCES PRODUCT(PRODUCTCODE),
    ORDERNO NUMBER(7) REFERENCES ORDERS(ORDERNO),
    ODPRICE NUMBER(10) NOT NULL,
    ODCNT NUMBER(7) NOT NULL);
    
    
    
    
    