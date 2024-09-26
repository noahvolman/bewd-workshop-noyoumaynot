DROP TABLE IF EXISTS User_Roles;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Movies;

CREATE TABLE Users
(
    userid   INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    salt     VARCHAR(255) NOT NULL
);

CREATE TABLE Roles
(
    roleid INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    isadmin BIT NOT NULL
);

CREATE TABLE User_Roles
(
    userid INT,
    roleid INT,
    PRIMARY KEY (userid, roleid),
    FOREIGN KEY (userid) REFERENCES Users(userid),
    FOREIGN KEY (roleid) REFERENCES Roles(roleid)
);

CREATE TABLE Movies
(
    movieid INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
