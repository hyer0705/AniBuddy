-- ���� ����, ����, ���� , ��ȸ ������
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

COMMENT ON TABLE POST_MESSAGE IS '���� ���̺�';

COMMENT ON COLUMN POST_MESSAGE.PM_NO IS '���� ��ȣ';

COMMENT ON COLUMN POST_MESSAGE.PM_SENDER_ID IS '���� �߽���';

COMMENT ON COLUMN POST_MESSAGE.TITLE IS '���� ����';

COMMENT ON COLUMN POST_MESSAGE.CONTENT IS '���� ����';

COMMENT ON COLUMN POST_MESSAGE.SEND_DATE IS '���� �߽���';

COMMENT ON COLUMN POST_MESSAGE.PM_RECIPIENT_ID IS '���� ������';

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


-- ����� ���̺� ��ȸ
SELECT * FROM USER_TB
ORDER BY user_no;


-- ���̵����� ����
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������1',	'��������1',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������2',	'��������2',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������3',	'��������3',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������4',	'��������4',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������5',	'��������5',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������6',	'��������6',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������7',	'��������7',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������8',	'��������8',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������9',	'��������9',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������10',	'��������10',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������11',	'��������11',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������12',	'��������12',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������13',	'��������13',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������14',	'��������14',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������15',	'��������15',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������16',	'��������16',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������17',	'��������17',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������18',	'��������18',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������19',	'��������19',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������20',	'��������20',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������21',	'��������21',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������22',	'��������22',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������23',	'��������23',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������24',	'��������24',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������25',	'��������25',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������26',	'��������26',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������27',	'��������27',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������28',	'��������28',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������29',	'��������29',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������30',	'��������30',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������31',	'��������31',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������32',	'��������32',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������33',	'��������33',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������34',	'��������34',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������35',	'��������35',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������36',	'��������36',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������37',	'��������37',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������38',	'��������38',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������39',	'��������39',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������40',	'��������40',	sysdate,	57,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	58,	'��������41',	'��������41',	sysdate,	57,	'unchecked');


INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	57,	'��������42',	'��������42',	sysdate,	58, 'unchecked');

-- ���� ���̺� ��ü ��ȸ - ���� �޽���
SELECT * FROM post_message
WHERE pm_sender_id = 58
ORDER BY pm_no DESC;

-- ���� ���̺� ��ü ��ȸ - ���� �޽���
SELECT * FROM post_message
WHERE pm_recipient_id = 58
ORDER BY pm_no DESC;

--commit;
-- rollback;
--DELETE FROM post_message;

SELECT * FROM user_tb
ORDER BY user_no DESC;

-- ���� �׽�Ʈ�� ȸ�� - �߽���, ������ 
SELECT * FROM user_tb
WHERE user_no > 58;

-- �׽�Ʈ�� ���� ������
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������1',	'��������1',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������2',	'��������2',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������3',	'��������3',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������4',	'��������4',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������5',	'��������5',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������6',	'��������6',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������7',	'��������7',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������8',	'��������8',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������9',	'��������9',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������10',	'��������10',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������11',	'��������11',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������12',	'��������12',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������13',	'��������13',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������14',	'��������14',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������15',	'��������15',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������16',	'��������16',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������17',	'��������17',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������18',	'��������18',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������19',	'��������19',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������20',	'��������20',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������21',	'��������21',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������22',	'��������22',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������23',	'��������23',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������24',	'��������24',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������25',	'��������25',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������26',	'��������26',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������27',	'��������27',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������28',	'��������28',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������29',	'��������29',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������30',	'��������30',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������31',	'��������31',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������32',	'��������32',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������33',	'��������33',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������34',	'��������34',	sysdate,	60,	'unchecked');
INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) VALUES (	post_message_seq.nextval,	59,	'��������35',	'��������35',	sysdate,	60,	'unchecked');

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

-- ���� is_check update
UPDATE post_message
SET
    is_check = 'checked'
WHERE pm_no = 119;
-- rollback;

INSERT INTO post_message(pm_no, pm_sender_id, title, content, send_date, pm_recipient_id, is_check) 
VALUES (	post_message_seq.nextval,	59,	'��������35',	'��������35',	sysdate,	60,	'unchecked');

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
