-- 상품정보 테이블
CREATE TABLE tbl_goods_01 (
          goods_cd VARCHAR2(6) NOT NULL PRIMARY KEY, -- 상품코드
          goods_nm VARCHAR2(30), -- 상품명
          goods_price NUMBER(8), -- (판매)단가
          cost NUMBER(8), -- (상품)원가
          in_date DATE -- 입고일자
);

-- 상품정보 샘플 데이터
INSERT INTO tbl_goods_01 VALUES(110001, '라면', 1050, 750, TO_DATE('20190302', 'YYYYMMDD'));
INSERT INTO tbl_goods_01 VALUES(110002, '빵', 1300, 800, TO_DATE('20190302', 'YYYYMMDD'));
INSERT INTO tbl_goods_01 VALUES(110003, '과자', 2000, 1700, TO_DATE('20190302', 'YYYYMMDD'));

INSERT INTO tbl_goods_01 VALUES(110004, '탄산음료', 900, 750, TO_DATE('20190302', 'YYYYMMDD'));
INSERT INTO tbl_goods_01 VALUES(110005, '삼각김밥', 750, 300, TO_DATE('20190302', 'YYYYMMDD'));
INSERT INTO tbl_goods_01 VALUES(110006, '초콜릿', 1500, 1300, TO_DATE('20190302', 'YYYYMMDD'));
INSERT INTO tbl_goods_01 VALUES(110007, '우유', 850, 600, TO_DATE('20190302', 'YYYYMMDD'));

-- 점포정보 테이블
CREATE TABLE store_tbl_003 (
           store_cd VARCHAR2(5) NOT NULL PRIMARY KEY, -- 점포코드
           store_nm VARCHAR2(20), -- 점포명
           stoer_fg VARCHAR2(1) -- 직영구분
);

-- 점포정보 샘플 데이터
INSERT INTO store_tbl_003 VALUES('A001', '이태원점', '0');
INSERT INTO store_tbl_003 VALUES('A002', '한남점', '0');
INSERT INTO store_tbl_003 VALUES('A003', '도원점', '0');

INSERT INTO store_tbl_003 VALUES('B001', '혜화점', '1');

INSERT INTO store_tbl_003 VALUES('C001', '방배점', '1');

INSERT INTO store_tbl_003 VALUES('D001', '사당점', '0');
INSERT INTO store_tbl_003 VALUES('D002', '흑석점', '1');

INSERT INTO store_tbl_003 VALUES('E001', '금호점', '0');

-- 매출정보 테이블
CREATE TABLE sale_tbl_003 (
          sale_no VARCHAR2(4) NOT NULL PRIMARY KEY, -- 판매번호
          sale_ymd DATE NOT NULL, -- 판매일자
          sale_fg VARCHAR2(1) NOT NULL, -- 판매구 분
          store_cd VARCHAR2(5) NOT NULL, -- 점포코드
          goods_cd  VARCHAR2(6), -- 상품코드
          sale_cnt NUMBER(3), -- 판매수량
          pay_type VARCHAR2(2), -- 수취구분
          CONSTRAINT store_cd_fk FOREIGN KEY(store_cd) REFERENCES store_tbl_003(store_cd),
          CONSTRAINT goods_cd_fk FOREIGN KEY(goods_cd) REFERENCES tbl_goods_01(goods_cd)
);

-- 매출정보 샘플 데이터
INSERT INTO sale_tbl_003 VALUES('0001', TO_DATE('20190325', 'YYYYMMDD'), '1', 'A001', '110001', 2, '02');
INSERT INTO sale_tbl_003 VALUES('0002', TO_DATE('20190325', 'YYYYMMDD'), '1', 'B001', '110003', 2, '02');
INSERT INTO sale_tbl_003 VALUES('0003', TO_DATE('20190325', 'YYYYMMDD'), '1', 'D001', '110003', 1, '01');
INSERT INTO sale_tbl_003 VALUES('0004', TO_DATE('20190325', 'YYYYMMDD'), '1', 'A001', '110006', 5, '02');
INSERT INTO sale_tbl_003 VALUES('0005', TO_DATE('20190325', 'YYYYMMDD'), '1', 'C001', '110003', 2, '02');

INSERT INTO sale_tbl_003 VALUES('0006', TO_DATE('20190325', 'YYYYMMDD'), '2', 'C001', '110003', 2, '02');
INSERT INTO sale_tbl_003 VALUES('0007', TO_DATE('20190325', 'YYYYMMDD'), '1', 'A002', '110005', 4, '02');
INSERT INTO sale_tbl_003 VALUES('0008', TO_DATE('20190325', 'YYYYMMDD'), '1', 'A003', '110004', 4, '02');
INSERT INTO sale_tbl_003 VALUES('0009', TO_DATE('20190325', 'YYYYMMDD'), '1', 'B001', '110001', 2, '01');
INSERT INTO sale_tbl_003 VALUES('0010', TO_DATE('20190325', 'YYYYMMDD'), '1', 'A002', '110006', 1, '02');


SELECT TO_CHAR(MAX(sale_no)+1, 'FM0000'), TO_CHAR(SYSDATE, 'YYYYMMDD') FROM sale_tbl_003;


SELECT st.store_nm,
       CASE WHEN sa.pay_type='01' THEN SUM(g.goods_price*sa.sale_cnt) ELSE 0 END AS a,
       CASE WHEN sa.pay_type='02' THEN SUM(g.goods_price*sa.sale_cnt) ELSE 0 END AS b,
       SUM(g.goods_price*sa.sale_cnt) AS SUM
FROM store_tbl_003 st, sale_tbl_003 sa, tbl_goods_01 g
WHERE st.store_cd = sa.store_cd AND sa.goods_cd = g.goods_cd
GROUP BY store_nm, sa.pay_type
ORDER BY SUM DESC

-- 수정된 SQL문(점포별매출현황)
SELECT st.store_nm,
       SUM(CASE WHEN sa.pay_type='01' THEN g.goods_price*sa.sale_cnt ELSE 0 END) AS a,
       SUM(CASE WHEN sa.pay_type='02' THEN g.goods_price*sa.sale_cnt ELSE 0 END) AS b,
       SUM(g.goods_price*sa.sale_cnt) AS SUM
FROM store_tbl_003 st, sale_tbl_003 sa, tbl_goods_01 g
WHERE sa.goods_cd = g.goods_cd AND st.store_cd = sa.store_cd
GROUP BY st.store_nm
ORDER BY SUM DESC