CREATE TABLE actors
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255)
);
CREATE TABLE movie_actors
(
    movies_id BIGINT(20) NOT NULL,
    actors_id BIGINT(20) NOT NULL
);
CREATE TABLE movies
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    categoryName VARCHAR(255),
    description VARCHAR(255),
    languageName VARCHAR(255),
    length SMALLINT(6),
    rating FLOAT,
    releaseYear SMALLINT(6),
    title VARCHAR(255)
);
CREATE TABLE search_logs
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    facebookId VARCHAR(255),
    resultCount INT(11) NOT NULL,
    time DATETIME
);
CREATE TABLE search_logs_movies
(
    SearchLog_id BIGINT(20) NOT NULL,
    foundMovies_id BIGINT(20) NOT NULL
);
CREATE TABLE searchlog_fieldstosearch
(
    SearchLog_id BIGINT(20) NOT NULL,
    fieldsToSearch VARCHAR(255)
);
ALTER TABLE movie_actors ADD FOREIGN KEY (movies_id) REFERENCES movies (id);
ALTER TABLE movie_actors ADD FOREIGN KEY (actors_id) REFERENCES actors (id);
CREATE INDEX FK_lsnj4a0uk67id8b598dyrshi9 ON movie_actors (actors_id);
CREATE INDEX FK_sl5epdttsw1ix77dgif94wqwt ON movie_actors (movies_id);
ALTER TABLE search_logs_movies ADD FOREIGN KEY (SearchLog_id) REFERENCES search_logs (id);
ALTER TABLE search_logs_movies ADD FOREIGN KEY (foundMovies_id) REFERENCES movies (id);
CREATE INDEX FK_o2ua1yee3um68b8rrrkbee9wo ON search_logs_movies (SearchLog_id);
ALTER TABLE searchlog_fieldstosearch ADD FOREIGN KEY (SearchLog_id) REFERENCES search_logs (id);
CREATE INDEX FK_oq5nsln3av2ftk1dbnimslnv1 ON searchlog_fieldstosearch (SearchLog_id);