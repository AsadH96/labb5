CREATE DATABASE IF NOT EXISTS Entertainment;

USE Entertainment;

CREATE TABLE IF NOT EXISTS Person (
	personID 	INT NOT NULL,
	personName 	VARCHAR(30) NOT NULL,
    role		VARCHAR(20),
    nationality VARCHAR(20),
    CONSTRAINT Person_personID_pk PRIMARY KEY (personID)
);    

CREATE TABLE IF NOT EXISTS Genre (
	genreID 	INT NOT NULL,
    category 	VARCHAR(20) NOT NULL,
    CONSTRAINT Genre_genreID_pk PRIMARY KEY (genreID)
);

CREATE TABLE IF NOT EXISTS Movie (
	movieID		INT NOT NULL,
	title 		VARCHAR(30) NOT NULL,
    yearMovie 	INT NOT NULL,
    directorID 	INT,
    genreID 	INT,
    grade 		INT,
    CONSTRAINT Movie_movieID_pk PRIMARY KEY (movieID),
    CONSTRAINT Movie_directorID_fk FOREIGN KEY (directorID) REFERENCES Person(personID),
    CONSTRAINT Movie_genreID_fk FOREIGN KEY (genreID) REFERENCES Genre(genreID)
);

CREATE TABLE IF NOT EXISTS Album (
	albumID 	INT NOT NULL,
    albumName 	VARCHAR(20) NOT NULL,
    artistID	INT,
    releaseDate INT,
    genreID		INT,
    CONSTRAINT MusicAlbum_albumID_pk PRIMARY KEY (albumID),
    CONSTRAINT MusicAlbum_artistID_fk FOREIGN KEY (artistID) REFERENCES Person(personID),
    CONSTRAINT MusicAlbum_genreID_fk FOREIGN KEY (genreID) REFERENCES Genre(genreID)
);

CREATE TABLE IF NOT EXISTS DBUser (
	userID 		VARCHAR(20),
    pwd 		VARCHAR(20) NOT NULL,
    CONSTRAINT DBUser_userID_pk PRIMARY KEY (userID)
);

CREATE TABLE IF NOT EXISTS Review (
	userID 		VARCHAR(20),
    contentID 	INT,
    content 	VARCHAR(300),
    reviewDate 	DATE,
    CONSTRAINT Review_userIDfilmIDreviewDate_pk PRIMARY KEY (userID, contentID, reviewDate),
    CONSTRAINT Review_contentID_fk FOREIGN KEY (contentID) REFERENCES Movie(movieID),
    CONSTRAINT Review_userID_fk FOREIGN KEY (userID) REFERENCES DBUser(userID)
);

CREATE TABLE IF NOT EXISTS AlbumRating (
	albumID		INT NOT NULL,
    userID		VARCHAR(20),
	rating		INT NOT NULL,
    CONSTRAINT AlbumRating_albumIDuserID_pk PRIMARY KEY (albumID, userID),
    CONSTRAINT AlbumRating_albumID_fk FOREIGN KEY (albumID) REFERENCES Album(albumID),
	CONSTRAINT AlbumRating_userID_fk FOREIGN KEY (userID) REFERENCES DBUser(userID)
);

CREATE TABLE IF NOT EXISTS MovieRating (
	movieID		INT NOT NULL,
	userID		VARCHAR(20),
	rating		INT NOT NULL,
    CONSTRAINT MovieRating_movieIDuserID_pk PRIMARY KEY (movieID, userID),
    CONSTRAINT MovieRating_movieID_fk FOREIGN KEY (movieID) REFERENCES Movie(movieID),
	CONSTRAINT MovieRating_userID_fk FOREIGN KEY (userID) REFERENCES DBUser(userID)
);