CREATE TABLE Calendar
(
	ID int(11) NOT NULL AUTO_INCREMENT,
	Name varchar(255) NOT NULL,
	CreatorID int(11) NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT Calendar_User_FK FOREIGN KEY (CreatorID) REFERENCES User(ID)
);

INSERT INTO Calendar (Name, CreatorID) VALUES
('Kalendar1', 1),
('Kalendar2', 2),
('Kalendar3', 3),
('Kalendar4', 4);