INSERT INTO users (userid, username, password) VALUES (1, 'admin','$2a$10$SPW7hGG2OSTk1SovKFloDeDJMgXAsYigarLoNzGP.aHlaqy.G2/1S');
INSERT INTO users (userid, username, password) VALUES (2, 'user','$2a$10$B3vR0AZmmhJf8eHyYzvXDO6N9s5fDHR2R6jdu2VqaFGwXt.7c4Dzy');

INSERT INTO roles (roleid, name, isadmin) VALUES (1, 'ROLE_ADMIN', 1);
INSERT INTO roles (roleid, name, isadmin) VALUES (2, 'ROLE_USER', 0);

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

