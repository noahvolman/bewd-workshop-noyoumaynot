INSERT INTO users (userid, username, password, salt) VALUES (1, 'admin','7986082e9d89be150d9c8748ba9f16a1de0571354fbb9163cd8fc194c1fa263044dd75b31831155e234b37f85477de359f879eb556b2244d0288f1f1f02c489f', 'c788a0ffb54ededb5b8069f487208c8f');
INSERT INTO users (userid, username, password, salt) VALUES (2, 'user','a8af5d239b15eda459737fa9ac789e2dc750bd765f1dc48a194d76fd8389083f85e3ee0d814f07fb82b440f07934ba2fbd85bc781e04ee086226de19686eca68', 'dd1308adadd4b0298e57b3bf6800c2d9');

INSERT INTO roles (roleid, name, isadmin) VALUES (1, 'admin', 1);
INSERT INTO roles (roleid, name, isadmin) VALUES (2, 'user', 0);

INSERT INTO user_roles (userid, roleid) VALUES (1, 1);
INSERT INTO user_roles (userid, roleid) VALUES (2, 2);

INSERT INTO movies (movieid, name) VALUES (1, 'Dune 2');
INSERT INTO movies (movieid, name) VALUES (2, 'The Matrix 4');
INSERT INTO movies (movieid, name) VALUES (3, 'Twisters');
INSERT INTO movies (movieid, name) VALUES (4, 'Love Actually');
INSERT INTO movies (movieid, name) VALUES (5, 'The Godfather');
INSERT INTO movies (movieid, name) VALUES (6, 'The Shawshank Redemption');
INSERT INTO movies (movieid, name) VALUES (7, 'The Dark Knight');
INSERT INTO movies (movieid, name) VALUES (8, 'The Lord of the Rings: The Return of the King');
INSERT INTO movies (movieid, name) VALUES (9, 'The Lord of the Rings: The Fellowship of the Ring');
INSERT INTO movies (movieid, name) VALUES (10, 'The Lord of the Rings: The Two Towers');

