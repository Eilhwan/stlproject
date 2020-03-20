SELECT * FROM REVIEWBOARD;

INSERT INTO REVIEWBOARD (REVIEWNO, REVIEWNAME, REVIEWIMG1, REVIEWIMG2, REVIEWCONTENT, MEMBERID, USERIP, PECODE)
            VALUES      (RENO_SEQ.NEXTVAL, 'REVIEW1', 'reviewimg1','reviewimg2', '내용입니다.', 'nutellajang', '127.120.1.1', 6);

SELECT * 
    FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM REVIEWBOARD ORDER BY REVIEWNO)A) R, MEMBER M, PRODUCTENROLL PE 
    WHERE M.MEMBERID = R.MEMBERID AND R.PECODE = PE.PECODE AND RN BETWEEN 1 AND 3;
COMMIT;

SELECT * FROM productenroll WHERE PENAME LIKE '%'||'다즐'||'%';