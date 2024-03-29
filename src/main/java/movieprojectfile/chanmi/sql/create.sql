
-- 회원 테이블
CREATE TABLE Member (
    Member_PK CHAR(100) PRIMARY KEY,
    ID VARCHAR2(500) NOT NULL UNIQUE, -- 로그인 할 때 사용하는 ID값.
    NAME VARCHAR2(1000) NOT NULL, -- 로그인 후 사용하는 닉네임 중복O
    PW VARCHAR2(1000) NOT NULL -- 비밀번호
);
--ALTER TABLE Member ADD discountPrice NUMBER DEFAULT 0;

-- 영화관
CREATE TABLE Cinema (
    cinema_Name VARCHAR2(1500) PRIMARY KEY, -- 영화관 명
    address VARCHAR2(1500) -- 영화관 주소
);

-- 관
CREATE TABLE Theater (
    Theater_PK VARCHAR2(1500) PRIMARY KEY,
    cinema_Name VARCHAR2(1500),
    theater_Name VARCHAR2(1500) NOT NULL,
    column_Number INT, -- ABCD ,,,
    row_Number INT, -- 1234,,,
    CONSTRAINT T_C_FK FOREIGN KEY(cinema_Name) REFERENCES Cinema(cinema_Name) ON UPDATE CASCADE ON DELETE CASCADE
);

-- 좌석
CREATE TABLE site (
    Site_PK VARCHAR2(1500) PRIMARY KEY,
    Theater_PK VARCHAR2(1500),
    site_Name VARCHAR2(5),
    CONSTRAINT S_T_FK FOREIGN KEY(Theater_PK) REFERENCES Theater(Theater_PK) ON UPDATE CASCADE ON DELETE CASCADE
);

-- 영화
CREATE TABLE Movie (
    movie_Name VARCHAR2(1500) PRIMARY KEY,
    movie_img VARCHAR2(300),
    movie_Run_Time NUMBER
);

-- 상영영화
CREATE TABLE Open_Movie (
    Open_Movie_PK VARCHAR2(1500) PRIMARY KEY,
    movie_Name	 VARCHAR2(1500),
    Theater_PK VARCHAR2(1500),
    openMovieStartTime TIMESTAMP,
    CONSTRAINT O_T_P FOREIGN KEY(Theater_PK) REFERENCES Theater(Theater_PK) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT O_M_P FOREIGN KEY(movie_Name) REFERENCES Movie(movie_Name) ON UPDATE CASCADE ON DELETE CASCADE
);

-- 영화 예매
CREATE TABLE Reserve_Movie (
    Member_PK VARCHAR2(1500),
    Open_Movie_PK VARCHAR2(1500),
    Site_PK VARCHAR2(1500),
    Reserve_Movie_PK VARCHAR2(1500) PRIMARY KEY,
    CONSTRAINT M_O_P FOREIGN KEY(Open_Movie_PK) REFERENCES Open_Movie(Open_Movie_PK) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT M_M_P FOREIGN KEY(Member_PK) REFERENCES Member(Member_PK) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT M_S_P FOREIGN KEY(Site_PK) REFERENCES Site(Site_PK) ON UPDATE CASCADE ON DELETE CASCADE
);

-- 참고
-- https://tcpschool.com/mysql/mysql_constraint_foreignKey

DROP TABLE RESERVE_MOVIE;
DROP TABLE OPEN_MOVIE;
DROP TABLE MOVIE;
DROP TABLE SITE;
DROP TABLE THEATER;
DROP TABLE CINEMA;
DROP TABLE MEMBER;

SELECT * FROM MEMBER;
SELECT * FROM MOVIE;
SELECT * FROM CINEMA;
SELECT * FROM THEATER;

SELECT RESERVE_MOVIE;
SELECT OPEN_MOVIE;
