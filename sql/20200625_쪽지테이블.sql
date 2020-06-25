-- 쪽지 생성, 수정, 삭제 , 조회 쿼리문
DROP TABLE POST_MESSAGE;
CREATE TABLE POST_MESSAGE
(
    PM_NO              NUMBER            NOT NULL, 
    PM_SENDER_ID       NUMBER            NOT NULL, 
    TITLE              VARCHAR2(500)     NOT NULL, 
    CONTENT            VARCHAR2(2000)    NULL, 
    SEND_DATE          DATE              NOT NULL, 
    PM_RECIPIENT_ID    NUMBER            NOT NULL, 
    CONSTRAINT POST_MESSAGE_PK PRIMARY KEY (PM_NO)
);

COMMENT ON TABLE POST_MESSAGE IS '쪽지 테이블';

COMMENT ON COLUMN POST_MESSAGE.PM_NO IS '쪽지 번호';

COMMENT ON COLUMN POST_MESSAGE.PM_SENDER_ID IS '쪽지 발신자';

COMMENT ON COLUMN POST_MESSAGE.TITLE IS '쪽지 제목';

COMMENT ON COLUMN POST_MESSAGE.CONTENT IS '쪽지 내용';

COMMENT ON COLUMN POST_MESSAGE.SEND_DATE IS '쪽지 발신일';

COMMENT ON COLUMN POST_MESSAGE.PM_RECIPIENT_ID IS '쪽지 수신자';

ALTER TABLE POST_MESSAGE
    ADD CONSTRAINT FK_POST_MESSAGE_PM_SENDER_ID_U FOREIGN KEY (PM_SENDER_ID)
        REFERENCES USER_TB (USER_NO);

ALTER TABLE POST_MESSAGE
    ADD CONSTRAINT FK_POST_MESSAGE_PM_RECIPIENT_I FOREIGN KEY (PM_RECIPIENT_ID)
        REFERENCES USER_TB (USER_NO);

ALTER TABLE post_message
    ADD(is_check VARCHAR2(10));

DROP SEQUENCE post_message_seq;        
CREATE SEQUENCE post_message_seq;


-- 사용자 테이블 조회
SELECT * FROM USER_TB
ORDER BY user_no;


-- 더미데이터 삽입
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목1',	'쪽지내용1',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목2',	'쪽지내용2',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목3',	'쪽지내용3',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목4',	'쪽지내용4',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목5',	'쪽지내용5',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목6',	'쪽지내용6',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목7',	'쪽지내용7',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목8',	'쪽지내용8',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목9',	'쪽지내용9',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목10',	'쪽지내용10',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목11',	'쪽지내용11',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목12',	'쪽지내용12',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목13',	'쪽지내용13',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목14',	'쪽지내용14',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목15',	'쪽지내용15',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목16',	'쪽지내용16',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목17',	'쪽지내용17',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목18',	'쪽지내용18',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목19',	'쪽지내용19',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목20',	'쪽지내용20',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목21',	'쪽지내용21',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목22',	'쪽지내용22',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목23',	'쪽지내용23',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목24',	'쪽지내용24',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목25',	'쪽지내용25',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목26',	'쪽지내용26',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목27',	'쪽지내용27',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목28',	'쪽지내용28',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목29',	'쪽지내용29',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목30',	'쪽지내용30',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목31',	'쪽지내용31',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목32',	'쪽지내용32',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목33',	'쪽지내용33',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목34',	'쪽지내용34',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목35',	'쪽지내용35',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목36',	'쪽지내용36',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목37',	'쪽지내용37',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목38',	'쪽지내용38',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목39',	'쪽지내용39',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목40',	'쪽지내용40',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'쪽지제목41',	'쪽지내용41',	sysdate,	57,	'unchecked');


INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	57,	'쪽지제목42',	'쪽지내용42',	sysdate,	58, 'unchecked');

-- 쪽지 테이블 전체 조회 - 보낸 메시지
SELECT * FROM post_message
WHERE pm_sender_id = 58
ORDER BY pm_no DESC;

-- 쪽지 테이블 전체 조회 - 받은 메시지
SELECT * FROM post_message
WHERE pm_recipient_id = 58
ORDER BY pm_no DESC;

--commit;
-- rollback;
--DELETE FROM post_message;

SELECT * FROM user_tb
ORDER BY user_no DESC;

-- 쪽지 테스트용 회원 - 발신자, 수신자 
SELECT * FROM user_tb
WHERE user_no > 58;

-- 테스트용 더미 데이터
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목1',	'쪽지내용1',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목2',	'쪽지내용2',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목3',	'쪽지내용3',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목4',	'쪽지내용4',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목5',	'쪽지내용5',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목6',	'쪽지내용6',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목7',	'쪽지내용7',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목8',	'쪽지내용8',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목9',	'쪽지내용9',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목10',	'쪽지내용10',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목11',	'쪽지내용11',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목12',	'쪽지내용12',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목13',	'쪽지내용13',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목14',	'쪽지내용14',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목15',	'쪽지내용15',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목16',	'쪽지내용16',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목17',	'쪽지내용17',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목18',	'쪽지내용18',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목19',	'쪽지내용19',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목20',	'쪽지내용20',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목21',	'쪽지내용21',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목22',	'쪽지내용22',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목23',	'쪽지내용23',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목24',	'쪽지내용24',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목25',	'쪽지내용25',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목26',	'쪽지내용26',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목27',	'쪽지내용27',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목28',	'쪽지내용28',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목29',	'쪽지내용29',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목30',	'쪽지내용30',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목31',	'쪽지내용31',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목32',	'쪽지내용32',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목33',	'쪽지내용33',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목34',	'쪽지내용34',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'쪽지제목35',	'쪽지내용35',	sysdate,	60,	'unchecked');

SELECT * FROM post_message
--SELECT count(*) FROM post_message
WHERE pm_recipient_id = 60
ORDER BY pm_no DESC;

-- commit;
SELECT * FROM (
    SELECT rownum rnum, P.* FROM (
        SELECT * FROM post_message
        WHERE pm_recipient_id = 60
        ORDER BY pm_no DESC
    ) P
) PM
WHERE rnum BETWEEN 1 AND 10;

SELECT * FROM post_message
WHERE pm_no = 119;

SELECT * FROM user_tb
WHERE user_no = 59;

-- 쪽지 is_check update
UPDATE post_message
SET
    is_check = 'checked'
WHERE pm_no = 119;
-- rollback;

INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) 
VALUES (	post_message_seq.nextval,	59,	'쪽지제목35',	'쪽지내용35',	sysdate,	60,	'unchecked');

SELECT * FROM post_message
WHERE pm_no = 134;

--DELETE FROM post_message
--WHERE pm_no = 134;

--rollback;

SELECT count(*) FROM post_message
WHERE pm_sender_id = 59
ORDER BY pm_no DESC;

SELECT * FROM user_tb
WHERE user_no = 59;


SELECT * FROM (
	    SELECT rownum rnum, P.* FROM (
	        SELECT * FROM post_message
	        WHERE pm_sender_id = 59
	        ORDER BY pm_no DESC
	    ) P
	) PM
WHERE rnum BETWEEN 1 AND 10;
