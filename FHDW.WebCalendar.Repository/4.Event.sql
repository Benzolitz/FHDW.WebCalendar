CREATE TABLE Event
(
	ID int(11) NOT NULL AUTO_INCREMENT,
	StartTime DATETIME NOT NULL,
	EndTime DATETIME NOT NULL,
	Location varchar(255) NOT NULL,
	CreatorID int(11) NOT NULL,
	CreationTime DATETIME NOT NULL,
	Message TEXT NOT NULL,
	CalendarID int(11) NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT Event_User_FK FOREIGN KEY (CreatorID) REFERENCES User(ID),
	CONSTRAINT Event_Calendar_FK FOREIGN KEY (CalendarID) REFERENCES Calendar(ID)
);

INSERT INTO Event (StartTime, EndTime, Location, CreatorID, CreationTime, Message, CalendarID) VALUES
('2015-05-21 18:00:00', '2015-05-21 19:00:00', 'Zu Hause1', 1, '2015-05-21 17:00:00', 'Das ist eine Nachricht. Hallo Welt!', 1),
('2015-05-22 18:00:00', '2015-05-22 19:00:00', 'Zu Hause2', 2, '2015-05-21 17:00:00', 'Das ist eine Nachricht. Hallo Welt!', 2),
('2015-05-23 18:00:00', '2015-05-23 19:00:00', 'Zu Hause3', 3, '2015-05-21 17:00:00', 'Das ist eine Nachricht. Hallo Welt!', 3),
('2015-05-24 18:00:00', '2015-05-24 19:00:00', 'Zu Hause4', 4, '2015-05-21 17:00:00', 'Das ist eine Nachricht. Hallo Welt!', 4);