CREATE TABLE EventUser
(
	EventID int(11) NOT NULL,
	UserID int(11) NOT NULL,
	Required boolean not null default true,
	CalendarID int(11) NOT NULL,
	PRIMARY KEY (EventID, UserID),
	CONSTRAINT EventUser_Event_FK FOREIGN KEY (EventID) REFERENCES Event(ID) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT EventUser_User_FK FOREIGN KEY (UserID) REFERENCES User(ID) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT EventUser_Calendar_FK FOREIGN KEY (CalendarID) REFERENCES Calendar(ID) ON DELETE CASCADE ON UPDATE CASCADE
);