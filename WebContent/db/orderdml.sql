CREATE SEQUENCE ODNO_SEQ
    MAXVALUE 999999
    NOCACHE;
CREATE SEQUENCE ORDERNO_SEQ
    MAXVALUE 999999
    NOCACHE;

SELECT * FROM ORDERDETAIL OD, ORDERS O, MEMBER M WHERE O.ORDERNO = OD.ORDERNO AND O.MEMBERID = M.MEMBERID AND M.MEMBERID = 'nutellajang' AND ORDERSTATUS = 2;
SELECT * FROM ORDERS;
UPDATE ORDERS SET ORDERSTATUS = 2 WHERE ORDERNO = 29;
SELECT * FROM ORDERDETAIL WHERE ORDERNO = 20;
SELECT * FROM ORDERDETAIL OD, ORDERS O, PRODUCT P, MEMBER M
WHERE OD.ORDERNO = O.ORDERNO AND OD.PRODUCTCODE = P.PRODUCTCODE AND M.MEMBERID = O.MEMBERID AND OD.ORDERNO = 6;
SELECT TO_CHAR(SYSDATE,'YY-MM-DD HH:MI:SS') FROM DUAL;
-- 주문 생성
INSERT INTO ORDERS (ORDERNO, ORDERADDRESS, ORDERTEL, ORDERTOTAL, MEMBERID)
            VALUES (ORDERNO_SEQ.NEXTVAL, '서울시 종로구 육의전빌딩 8층', '010-2020-1111', 30000, 'nutellajang');
-- 주문 상세
INSERT INTO ORDERDETAIL (ODNO, PRODUCTCODE, ORDERNO, ODPRICE, ODCNT)
                VALUES  (ODNO_SEQ.NEXTVAL, 1, 2, 30000, 1);

--주문 상세 보기
SELECT * FROM ORDERDETAIL OD, ORDERS O, MEMBER M WHERE OD.ORDERNO = O.ORDERNO AND M.MEMBERID = O.MEMBERID;
commit;
--ORDERSTATUS 1 == 주문중 2 == 주문완료 3==배송처리중 4 ==배송처리완료
SELECT ORDERNO FROM ORDERS WHERE MEMBERID = 'nutellajang' and orderstatus = 1;
SELECT M.MEMBERID MEMBERID,
M.MEMBERPW MEMBERPW,
M.MEMBERNAME MEMBERNAME,
M.MEMBERTEL MEMBERTEL,
M.MEMBEREMAIL MEMBEREMAIL,
M.MEMBERADDRESS MEMBERADDRESS,
M.MEMBERPOST MEMBERPOST,
M.MEMBERSTATUS MEMBERSTATUS,
M.TOTALSPENT TOTALSPENT,
M.MEMBERPOINT MEMBERPOINT,
M.GRADELEVEL GRADELEVEL,
M.MEMBERRDATE MEMBERRDATE,
M.MEMBERBIRTH MEMBERBIRTH,
O.ORDERNO ORDERNO,
O.ORDERDATE ORDERDATE,
O.ORDERADDRESS ORDERADDRESS,
O.ORDERTEL ORDERTEL,
O.ORDERTOTAL ORDERTOTAL,
O.ORDERSTATUS ORDERSTATUS,
O.MEMBERID MEMBERID_0 FROM MEMBER M, ORDERS O WHERE O.MEMBERID = M.MEMBERID;
SELECT * FROM MEMBER M, ORDERS O WHERE O.MEMBERID = M.MEMBERID AND M.MEMBERID = 'nutellajang' and orderstatus = 1;

SELECT * FROM ORDERDETAIL OD, ORDERS O, PRODUCT P, MEMBER M
				WHERE OD.ORDERNO = O.ORDERNO AND OD.PRODUCTCODE = P.PRODUCTCODE AND M.MEMBERID = O.MEMBERID AND OD.ORDERNO = 6;
                
--ORDER
SELECT * FROM MEMBER M, ORDERS O WHERE O.MEMBERID = M.MEMBERID AND M.MEMBERID = 'nutellajang' AND ORDERSTATUS > 1
--ORDERDETAIL
