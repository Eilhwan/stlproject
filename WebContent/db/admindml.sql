SELECT * FROM ADMIN WHERE ADMINID = 'ADMIN' AND ADMINPW = '123';
SELECT * FROM ADMIN;
commit;
--관리자 등록
INSERT INTO ADMIN VALUES('관리자군', '1234a');
update admin set adminpw = '123' where adminid = 'ADMIN';