CREATE TABLE Calendar
(
	ID int(11) NOT NULL AUTO_INCREMENT,
	Name varchar(255) NOT NULL,
	CreatorID int(11) NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT Calendar_User_FK FOREIGN KEY (CreatorID) REFERENCES User(ID) ON DELETE CASCADE ON UPDATE CASCADE
);