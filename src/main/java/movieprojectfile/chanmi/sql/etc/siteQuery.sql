-- 교과목 정보 명세서
CREATE TABLE course_tbl (
        id VARCHAR2(5) NOT NULL PRIMARY KEY, -- 과목코드
        name VARCHAR2(40), -- 과목명
        credit NUMBER(6), -- 학점
        lecturer NUMBER(6), -- 교수번호
        week NUMBER(2), -- 요일
        start_hour NUMBER(4), -- 시작시간
        end_hour NUMBER(4) -- 종료시간
);


-- 교과목 정보 샘플 데이터
INSERT INTO course_tbl VALUES('10001', '화면구현', 2, 1, 1, 0900, 1100);
INSERT INTO course_tbl VALUES('10002', '프로그래밍언어활용', 2, 2, 2, 0900, 1200);
INSERT INTO course_tbl VALUES('10003', '데이터베이스구현', 3, 4, 3, 0900, 1200);
INSERT INTO course_tbl VALUES('10004', '응용SW기초기술활용', 3, 4, 4, 0900, 1200);

INSERT INTO course_tbl VALUES('20001', '애플리케이션테스트', 2, 5, 1, 1300, 1600);
INSERT INTO course_tbl VALUES('20002', 'UI테스트', 3, 5, 2, 1500, 1800);
INSERT INTO course_tbl VALUES('20003', '애플리케이션배포', 3, 5, 3, 1330, 1630);

INSERT INTO course_tbl VALUES('30001', '데이터입출력', 2, 3, 4, 1330, 1530);
INSERT INTO course_tbl VALUES('30002', '서버프로그램구현', 3, 2, 5, 0900, 1200);

INSERT INTO course_tbl VALUES('40001', '스마트문화앱구현', 3, 2, 5, 1300, 1600);

-- 교수 정보 명세서
CREATE TABLE lecturer_tbl (
          idx NUMBER(6) NOT NULL PRIMARY KEY, -- 교수번호
          name VARCHAR2(10), -- 교수명
          major VARCHAR2(50) -- 전공
);

-- 교수 정보 샘플 데이터
INSERT INTO lecturer_tbl VALUES(1, '김교수', '알고리즘');
INSERT INTO lecturer_tbl VALUES(2, '이교수', '인공지능');
INSERT INTO lecturer_tbl VALUES(3, '박교수', '소프트웨어 공학');
INSERT INTO lecturer_tbl VALUES(4, '최교수', '알고리즘');
INSERT INTO lecturer_tbl VALUES(5, '피교수', '임베디드시스템');
INSERT INTO lecturer_tbl VALUES(6, '깡교수', '스마트멀티미디어');

-- LPAD
SELECT SUBSTR(LPAD(start_hour, 4, '0'), 1, 2) AS H FROM course_tbl

-- 과목코드, 과목명, 학점, 교수명, 요일, 시작시간, 종료시간, 비고
SELECT c.id, c.name, c.credit, l.name, c.week,
       SUBSTR(LPAD(c.start_hour, 4, '0'), 1, 2), SUBSTR(LPAD(c.start_hour, 4, '0'), 3, 2),
       SUBSTR(LPAD(c.end_hour, 4, '0'), 1, 2), SUBSTR(LPAD(c.end_hour, 4, '0'), 3, 2)
FROM course_tbl c, lecturer_tbl l
WHERE l.idx = c.lecturer
ORDER BY c.id

SELECT week FROM course_tbl GROUP BY week ORDER BY week

SELECT c.name, c.credit, l.name, c.week,
       SUBSTR(LPAD(c.start_hour, 4, '0'), 1, 4),
       SUBSTR(LPAD(c.end_hour, 4, '0'), 1, 4)
FROM course_tbl c, lecturer_tbl l
WHERE l.idx = c.lecturer AND c.id='10001'

SELECT idx, name FROM lecturer_tbl