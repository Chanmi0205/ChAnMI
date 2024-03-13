
INSERT INTO Cinema VALUES(
'ChAnMI',
'찬미나라 찬미시 찬미동'
);

INSERT INTO Theater VALUES(
'ChAnMI1관',
'ChAnMI',
'1관',
18,
8
);

INSERT INTO Theater VALUES(
'ChAnMI2관',
'ChAnMI',
'2관',
18,
8
);

INSERT INTO Theater VALUES(
'ChAnMI3관',
'ChAnMI',
'3관',
18,
8
);

INSERT INTO Theater VALUES(
'ChAnMI4관',
'ChAnMI',
'4관',
16,
6
);

-- 초능력 대결전
-- https://i.namu.wiki/i/z3Gk7JEr5iasPH0ci2R4jJBej572W6hR53p_hyBKC8C2YZdE9P5MAHZ4FnyBcEs8w_TxrBXRhLDlLBmWO42Rk7Lc6jGSM64DlIJdBbaDu4vB1aAHUVWPD2it-KQkv8lL2Cdu22AbdfO_o2gD7a7HeA.webp
-- 어른제국의 역습
-- https://i.namu.wiki/i/goKVIIba5U1NP1THu1grB1KJInlR8MmbAfjzHUmNj3-fYruV5d30vnoATzJQNseoQeaozbXYvZXHv4E_7CFArp3aisgv_ig9XTBcBHdYQQKh1ZzzynZ3OlfE8OxvkCu2AFxzc4X7wp5aSi163RFhiw.webp
-- 석양의 떡잎마을방범대
-- https://i.namu.wiki/i/x-K4hgpeD16Ojg8yTo-1Px5X4izZxCd3c7zqTFoHDPAJbk6h_kEa0mU26sMn9rA0Ijj-KND5Kua9eI3o39h2W3hqa7wuaGOe90p-dT-cp93xZc7FS3umkSGx93CFChyKoSBC5a0Fzy3RGRNaX-szyg.webp
-- 장엄한 전설의 전투
-- https://i.namu.wiki/i/k-nKomW83vyrP4ie6nVamhyOvVjTytROLHRDfOfo5itLsUH9U7AQXbMVpDSvgEG2ytC7josC8Xbaui6INMzX4-JWjxgHswxOtQ8mIr9l4v4VQm0k7GgvS681XpPzQ7vXBno3oYNmSUFTuJzbPdarxw.webp


INSERT INTO Movie VALUES(
'초능력 대결전 ~날아라 수제김밥~',
'https://i.namu.wiki/i/z3Gk7JEr5iasPH0ci2R4jJBej572W6hR53p_hyBKC8C2YZdE9P5MAHZ4FnyBcEs8w_TxrBXRhLDlLBmWO42Rk7Lc6jGSM64DlIJdBbaDu4vB1aAHUVWPD2it-KQkv8lL2Cdu22AbdfO_o2gD7a7HeA.webp',
94
);

INSERT INTO Movie VALUES(
'폭풍을 부르는 모레츠! 오토나 제국의 역습',
'https://i.namu.wiki/i/goKVIIba5U1NP1THu1grB1KJInlR8MmbAfjzHUmNj3-fYruV5d30vnoATzJQNseoQeaozbXYvZXHv4E_7CFArp3aisgv_ig9XTBcBHdYQQKh1ZzzynZ3OlfE8OxvkCu2AFxzc4X7wp5aSi163RFhiw.webp',
89
);

INSERT INTO Movie VALUES(
'폭풍을 부르는 석양의 떡잎마을 방범대',
'https://i.namu.wiki/i/x-K4hgpeD16Ojg8yTo-1Px5X4izZxCd3c7zqTFoHDPAJbk6h_kEa0mU26sMn9rA0Ijj-KND5Kua9eI3o39h2W3hqa7wuaGOe90p-dT-cp93xZc7FS3umkSGx93CFChyKoSBC5a0Fzy3RGRNaX-szyg.webp',
95
);

INSERT INTO Movie VALUES(
'태풍을 부르는 장엄한 전설의 전투',
'https://i.namu.wiki/i/k-nKomW83vyrP4ie6nVamhyOvVjTytROLHRDfOfo5itLsUH9U7AQXbMVpDSvgEG2ytC7josC8Xbaui6INMzX4-JWjxgHswxOtQ8mIr9l4v4VQm0k7GgvS681XpPzQ7vXBno3oYNmSUFTuJzbPdarxw.webp',
95
);


INSERT INTO Open_Movie VALUES(
'초능력 대결전 ~날아라 수제김밥~ChAnMI1관12131300',
'초능력 대결전 ~날아라 수제김밥~',
'ChAnMI1관',
to_timestamp('2024-12-13 13:00', 'YYYY-MM-DD HH24:MI')
);

INSERT INTO Open_Movie VALUES(
'초능력 대결전 ~날아라 수제김밥~ChAnMI1관12131500',
'초능력 대결전 ~날아라 수제김밥~',
'ChAnMI1관',
to_timestamp('2024-12-13 15:00', 'YYYY-MM-DD HH24:MI')
);

INSERT INTO Open_Movie VALUES(
'초능력 대결전 ~날아라 수제김밥~ChAnMI1관12131700',
'초능력 대결전 ~날아라 수제김밥~',
'ChAnMI1관',
to_timestamp('2024-12-13 17:00:00', 'YYYY-MM-DD HH24:MI')
);

INSERT INTO Open_Movie VALUES(
'초능력 대결전 ~날아라 수제김밥~ChAnMI1관12131900',
'초능력 대결전 ~날아라 수제김밥~',
'ChAnMI1관',
to_timestamp('2024-12-13 19:00:00', 'YYYY-MM-DD HH24:MI')
);

