SELECT * FROM MEMBER;
--SIGNUP
INSERT INTO MEMBER ( MEMBERID, MEMBERPW, MEMBERNAME, MEMBERTEL, MEMBEREMAIL, MEMBERADDRESS,
                     MEMBERPOST, MEMBERBIRTH, GRADELEVEL)
            VALUES(  'MEMBER2', '1234', 'STELLA', '010-1212-1211', 'STELLAJANG@naver.com', '스텔라네집',
                     '110-111', TO_DATE('1991-10-21', 'YY-MM-DD'), 1);
--가입 ID 체크
SELECT MEMBERID FROM MEMBER WHERE MEMBERID = 'MEMBER1';

-- SESSION에 저장할 정보
SELECT * FROM MEMBER M, MEMBERGRADE MG WHERE MEMBERID = 'MEMBER2' AND M.GRADELEVEL = MG.GRADELEVEL;
-- 로그인
SELECT * FROM MEMBER WHERE MEMBERID = 'MEMBER2' AND MEMBERPW='1234' AND MEMBERSTATUS = 1;
--아이디 삭제 (사용자)
UPDATE MEMBER SET MEMBERSTATUS = 0 WHERE MEMBERID='MEMBER1';

SELECT * FROM MEMBER WHERE MEMBERID = 'MEMBER2' AND MEMBERPW= '1234' AND MEMBERSTATUS = 1