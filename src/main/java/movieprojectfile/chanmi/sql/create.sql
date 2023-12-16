
-- 회원 테이블
CREATE TABLE Member (
    Member_PK CHAR(100) PRIMARY KEY,
    ID VARCHAR2(500) NOT NULL UNIQUE, -- 로그인 할 때 사용하는 ID값.
    NAME VARCHAR2(1000) NOT NULL, -- 로그인 후 사용하는 닉네임 중복O
    PW VARCHAR2(1000) NOT NULL -- 비밀번호
);
ALTER TABLE Member ADD discountPrice NUMBER DEFAULT 0;

-- 영화관
CREATE TABLE Cinema (
    Cinema_PK CHAR(100) PRIMARY KEY,
    cinemaName VARCHAR2(1500), -- 영화관 명
    address VARCHAR2(1500) -- 영화관 주소
);

-- 관
CREATE TABLE Theater (
    Theater_PK CHAR(100) PRIMARY KEY,
    Cinema_PK CHAR(100),
    theaterName VARCHAR2(1500) NOT NULL,
    CONSTRAINT T_C_FK FOREIGN KEY(Cinema_PK) REFERENCES Cinema(Cinema_PK) ON UPDATE CASCADE ON DELETE CASCADE
);

-- 영화
CREATE TABLE Movie (
    Movie_PK	 CHAR(100) PRIMARY KEY,
    movieName VARCHAR2(1500),
    movieRuntime NUMBER
);

-- 상영영화
CREATE TABLE Open_Movie (
    OpenMovie_PK CHAR(100) PRIMARY KEY,
    Movie_PK	 CHAR(100),
    Theater_PK CHAR(100),
    openMovieStartTime TIMESTAMP,
    CONSTRAINT O_T_P FOREIGN KEY(Theater_PK) REFERENCES Theater(Theater_PK) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT O_M_P FOREIGN KEY(Movie_PK) REFERENCES Movie(Movie_PK) ON UPDATE CASCADE ON DELETE CASCADE
);

-- 영화 예매
CREATE TABLE Reserve_Movie (
    ReserveMovie_PK CHAR(100) PRIMARY KEY,
    OpenMovie_PK CHAR(100),
    Member_PK CHAR(100),
    CONSTRAINT M_O_P FOREIGN KEY(OpenMovie_PK) REFERENCES Open_Movie(OpenMovie_PK) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT M_M_P FOREIGN KEY(Member_PK) REFERENCES Member(Member_PK) ON UPDATE CASCADE ON DELETE CASCADE
);

-- 참고
-- https://tcpschool.com/mysql/mysql_constraint_foreignKey

DROP TABLE RESERVE_MOVIE;
DROP TABLE OPEN_MOVIE;
DROP TABLE MOVIE;
DROP TABLE THEATER;
DROP TABLE CINEMA;
DROP TABLE MEMBER;

SELECT * FROM MEMBER;
SELECT * FROM MOVIE;
SELECT * FROM CINEMA;
SELECT * FROM THEATER;

SELECT RESERVE_MOVIE;
SELECT OPEN_MOVIE;
