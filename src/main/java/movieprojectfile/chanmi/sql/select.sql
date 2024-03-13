-- 전체 회원 조회
-- 회원 아이디, 회원 닉네임
SELECT ID, NAME FROM Member;

-- 특정 회원 조회
-- 회원 아이디, 회워 닉네임
SELECT ID, NAME FROM Member WHERE Member_PK = 'vKymuJ1YzngiMCvOwgD2vByuiMPe4PuEpq6yqT9ONMPF6mnJNmiFjBK4WO7vLkhQ4c1WjEick3NlpIHJcQ7Ucdtf4Ayqlty7ClOA';

-- 회원의 예매 내역 조회
-- 회원 아이디, 회원 닉네임, 예매한 영화 시작 시간, 영화 이름
SELECT me.ID, me.NAME, o.openMovieStartTime, mo.movieName
FROM Member me, Movie mo, Open_Movie o, Reserve_Movie r, Theater t, Cinema c
WHERE r.MEMBER_PK = me.Member_PK AND r.OpenMovie_PK = o.OpenMovie_PK
AND o.Movie_PK = mo.Movie_PK AND o.Theater_PK = t.Theater_PK
AND t.Cinema_PK = c.Cinema_PK

--     서브쿼리
--     예매한 수가 30 이상인 회원은 discountPrice를 10000원으로 변경할 것
UPDATE Member me SET me.discountPrice = 10000 WHERE me.Member_PK =
(SELECT mem.Member_PK FROM Reserve_Movie r, Member mem WHERE r.Member_PK = mem.Member_PK GROUP BY mem.ID HAVING COUNT(r.Member_PK)>=30)

-- 좌석 출력
OPEN_MOVIE o
Site s
THEATER t
RESERVE_MOVIE r
SELECT o.OPEN_MOVIE_PK, t.THEATER_PK, s.SITE_PK, r.MEMBER_PK
FROM OPEN_MOVIE o LEFT JOIN THEATER t
ON o.THEATER_PK = t.THEATER_PK
LEFT JOIN SITE s
ON t.THEATER_PK = s.THEATER_PK
LEFT JOIN RESERVE_MOVIE r
ON s.SITE_PK = r.SITE_PK
WHERE o.OPEN_MOVIE_PK='초능력 대결전 ~날아라 수제김밥~ChAnMI1관12131300'

SELECT s.site_Name FROM Site s LEFT OUTER JOIN Reserve_Movie r ON s.site_PK = r.site_PK WHERE r.Member_PK IS NULL
AND r.Open_Movie_PK = '초능력 대결전 ~날아라 수제김밥~ChAnMI1관12131500';

SELECT s.site_name
FROM OPEN_MOVIE o LEFT JOIN THEATER t
ON o.THEATER_PK = t.THEATER_PK
LEFT JOIN SITE s
ON t.THEATER_PK = s.THEATER_PK
LEFT JOIN RESERVE_MOVIE r
ON s.SITE_PK = r.SITE_PK
WHERE o.OPEN_MOVIE_PK='초능력 대결전 ~날아라 수제김밥~ChAnMI1관12131500' AND r.MEMBER_PK IS NULL