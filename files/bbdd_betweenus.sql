DROP DATABASE IF EXISTS BetweenUs;
CREATE DATABASE BetweenUs;
USE BetweenUs;

DROP TABLE IF EXISTS User CASCADE;
CREATE TABLE User (
    username VARCHAR(255),
    email    VARCHAR(255),
    password    VARCHAR(255),
    partides_guanyades INT,
    partides_jugades INT,
    PRIMARY KEY (username, email)
);

DROP TABLE IF EXISTS Game CASCADE;
CREATE TABLE Game (
    gameName VARCHAR(255),
    players INT,
    impostors INT,
    playerColor VARCHAR(255),
    map    VARCHAR(255),
    creator VARCHAR(255),
    PRIMARY KEY (gameName),
    FOREIGN KEY (creator) REFERENCES User(username)
);

DROP TABLE IF EXISTS PlayerCharacter CASCADE;
CREATE TABLE PlayerCharacter (
    color VARCHAR(255),
    xCoordinate INT,
    yCoordinate INT,
    gameName VARCHAR(255),
    PRIMARY KEY (color, gameName),
    FOREIGN KEY (gameName) REFERENCES Game(gameName)
);

DROP TABLE IF EXISTS NpcPlayer CASCADE;
CREATE TABLE NpcPlayer (
    color VARCHAR(255),
    xCoordinate INT,
    yCoordinate INT,
    gameName VARCHAR(255),
    PRIMARY KEY (color, gameName),
    FOREIGN KEY (gameName) REFERENCES Game(gameName)
);
DROP TABLE IF EXISTS PlayerStatistics CASCADE;
CREATE TABLE PlayerStatistics (
    username VARCHAR(255),
    game INT,
    percentage FLOAT,
    foreign key (username) REFERENCES User(username)
);