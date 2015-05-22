CREATE TABLE Event
(
	ID int(11) NOT NULL AUTO_INCREMENT,
	StartTime DATETIME NOT NULL,
	EndTime DATETIME NOT NULL,
	Title varchar(255) NOT NULL,
	Location varchar(255) NOT NULL,
	CreatorID int(11) NOT NULL,
	CreationTime DATETIME NOT NULL,
	Message TEXT NOT NULL,
	CalendarID int(11) NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT Event_User_FK FOREIGN KEY (CreatorID) REFERENCES User(ID),
	CONSTRAINT Event_Calendar_FK FOREIGN KEY (CalendarID) REFERENCES Calendar(ID)
);