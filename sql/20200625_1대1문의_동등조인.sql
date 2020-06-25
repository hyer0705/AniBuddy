-- 사용자 번호 = 등록자 번호 동등조인
SELECT * FROM (
    SELECT rownum rnum, O.* FROM(
        SELECT u.user_id, o.*
        FROM user_tb u, oneonone o
        WHERE 1=1
            AND u.user_no = o.user_no
        ORDER BY oneonone_no DESC
    ) O
) One
WHERE rnum BETWEEN 1 AND 10;

SELECT * FROM (
	    SELECT rownum rnum, O.* FROM(
	        SELECT u.user_id, o.*
	        FROM user_tb u, oneonone o
	        WHERE 1=1
--	            AND title LIKE '%'||'사이트'||'%'
	            AND u.user_no(+) = o.user_no
	        START WITH reply_no IS NULL
	        CONNECT BY reply_no = PRIOR oneonone_no
	        ORDER SIBLINGS BY oneonone_no DESC
	    ) O
	) One
WHERE rnum BETWEEN 1 AND 10;

SELECT * FROM user_tb;