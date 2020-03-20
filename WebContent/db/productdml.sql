SELECT * FROM PRODUCT;
SELECT * FROM PRODUCT_BRAND;
SELECT * FROM PRODUCT_TYPE;
CREATE SEQUENCE BRAND_SEQ
    NOCACHE
    NOCYCLE;
INSERT INTO PRODUCT_TYPE VALUES(1, '홍차');
INSERT INTO PRODUCT_TYPE VALUES(4, '백차');
INSERT INTO PRODUCT_TYPE VALUES(3, '청차');
INSERT INTO PRODUCT_TYPE VALUES(2, '녹차');
INSERT INTO PRODUCT_TYPE VALUES(5, '우롱차');
INSERT INTO PRODUCT_TYPE VALUES(6, '보이차');
INSERT INTO PRODUCT_TYPE VALUES(7, '허브티');
DELETE FROM PRODUCT_BRAND;
INSERT INTO PRODUCT_BRAND VALUES(1, 'POTNAM&MASON', '영국');
INSERT INTO PRODUCT_BRAND VALUES(2, 'AMHAD', '영국');
INSERT INTO PRODUCT_BRAND VALUES(3, 'TWG', '싱가폴');
INSERT INTO PRODUCT_BRAND VALUES(4, 'TWINING', '영국');
INSERT INTO PRODUCT_BRAND VALUES(5, 'LIPTON', '미국');



INSERT INTO PRODUCT (PRODUCTCODE, PRODUCTNAME, PRODUCTPRICE, PTYPECODE, PRODUCTIMG, PBRANDCODE, PRODUCTREMAIN, PRODUCTCONTENT)
                VALUES(PCODE_SEQ.NEXTVAL, '포트넘앤메이슨: 다즐링', 30000, 1, 'potnamdarjeeling.jpg', 1, 10, '맛있는 TEA 환상의 TEA');

SELECT RN, PRODUCTCODE, PRODUCTNAME, PRODUCTPRICE, PTYPENAME, t.ptypecode, PRODUCTIMG, PBRANDNAME, PRODUCTREMAIN, PRODUCTCONTENT
FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCT ORDER BY PRODUCTCODE) A) P, PRODUCT_BRAND B, PRODUCT_TYPE T 
WHERE RN BETWEEN 1 AND 3 AND B.PBRANDCODE = P.PBRANDCODE AND P.PTYPECODE = T.PTYPECODE;              
SELECT RN, PRODUCTCODE, PRODUCTNAME, PRODUCTPRICE, PTYPENAME, T.PTYPECODE, PRODUCTIMG, PBRANDNAME, PRODUCTREMAIN, PRODUCTCONTENT, p.pbrandcode
				FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCT ORDER BY PRODUCTCODE) A) P, PRODUCT_BRAND B, PRODUCT_TYPE T 
                 WHERE RN BETWEEN 1 AND 3 AND B.PBRANDCODE = P.PBRANDCODE AND P.PTYPECODE = T.PTYPECODE;
INSERT INTO PRODUCT (PRODUCTCODE, PRODUCTNAME, PRODUCTPRICE, PTYPECODE, PRODUCTIMG, PBRANDCODE, PRODUCTREMAIN, PRODUCTCONTENT)
				  VALUES(PCODE_SEQ.NEXTVAL, '아마드티:얼그레이', 7000, 1, 'AMHADTEA.JPG', 2, 10, '아마드티');
SELECT * FROM PRODUCT P, PRODUCT_BRAND B, PRODUCT_TYPE T
    WHERE P.PTYPECODE = t.ptypecode AND P.PBRANDCODE = B.PBRANDCODE AND PRODUCTCODE = 1;
SELECT * FROM PRODUCT_BRAND;    
select * from product_type;
COMMIT;

SELECT COUNT(*) FROM PRODUCT_BRAND WHERE PBRANDNAME= '오설록';
-- 리스트 출력
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCT ORDER BY ) A WHERE PTYPECODE LIKE '1' AND RN BETWEEN 1 AND 3;

SELECT * FROM PRODUCTENROLL;

UPDATE PRODUCT SET PRODUCTREMAIN = PRODUCTREMAIN - 1 WHERE PRODUCTCODE = 1;
