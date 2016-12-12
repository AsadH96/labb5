CREATE DATABASE IF NOT EXISTS Entertainment;

USE Entertainment;
CREATE TABLE IF NOT EXISTS Person (
	personID 	INT,
	personName 	VARCHAR(30) NOT NULL,
    role		VARCHAR(20),
    nationality VARCHAR(20),
    CONSTRAINT Person_personID_pk PRIMARY KEY (personID)
);    
CREATE TABLE IF NOT EXISTS Genre (
	genreID 	INT,
    category 	VARCHAR(20) NOT NULL,
    CONSTRAINT Genre_genreID_pk PRIMARY KEY (genreID)
);
CREATE TABLE IF NOT EXISTS Movie (
	movieID		INT,
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
	albumID 	INT,
    albumName 	VARCHAR(20) NOT NULL,
    artistID	INT,
    releaseDate INT,
    genreID 	int,
    CONSTRAINT Album_albumID_pk PRIMARY KEY (albumID),
    CONSTRAINT Album_genreID_fk FOREIGN KEY (genreID) REFERENCES Genre(genreID),
    CONSTRAINT Album_artistID_fk FOREIGN KEY (artistID) REFERENCES Person(personID)
);
CREATE TABLE IF NOT EXISTS DBUser (
	userID 		VARCHAR(20),
    pwd 		VARCHAR(20) NOT NULL,
    CONSTRAINT DBUser_userID_pk PRIMARY KEY (userID)
);


CREATE TABLE IF NOT EXISTS Review (
	userID 		VARCHAR(20),
	contentID 	INT NOT NULL AUTO_INCREMENT,
    content 	VARCHAR(300),
    movieID		int,
    albumID		int,
    reviewDate 	DATE,
    CONSTRAINT Review_contentID_pk PRIMARY KEY (contentID,userID),
    CONSTRAINT Review_movieID_fk FOREIGN KEY (movieID) REFERENCES Movie(movieID),
    CONSTRAINT Review_albumID_fk FOREIGN KEY (albumID) REFERENCES Album(albumID),
    CONSTRAINT Review_userID_fk FOREIGN KEY (userID) REFERENCES DBUser(userID)
);

/*ALTER TABLE Album ADD CONSTRAINT Album_ratingID_fk FOREIGN KEY (ratingID) REFERENCES Rating(ratingID)*/

CREATE TABLE IF NOT EXISTS AlbumRating(
	albumID		int,
    userID		varchar(20),
    rating		int,
    CONSTRAINT AlbumRating_ratingAlbumID_pk PRIMARY KEY (albumID,userID),
	CONSTRAINT AlbumRating_albumID_fk FOREIGN KEY (albumID) REFERENCES Album(albumID),
    CONSTRAINT AlbumRating_userID_fk FOREIGN KEY (userID) REFERENCES DBUser(userID)
);

CREATE TABLE IF NOT EXISTS MovieRating(
	rating		int,
    movieID		int,
    userID		varchar(20),
    CONSTRAINT MovieRating_ratingMovieID_pk PRIMARY KEY (movieID,userID),
    CONSTRAINT MovieRating_movieID_fk FOREIGN KEY (movieID) REFERENCES Movie(movieID),
    CONSTRAINT MovieRating_userID_fk FOREIGN KEY (userID) REFERENCES DBUser(userID)
);