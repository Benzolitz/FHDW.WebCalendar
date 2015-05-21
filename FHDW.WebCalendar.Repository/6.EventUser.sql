CREATE TABLE EventUser
(
	EventID int(11) NOT NULL,
	UserID int(11) NOT NULL,
	Required boolean not null default true,
	PRIMARY KEY (EventID, UserID),
	CONSTRAINT EventUser_Event_FK FOREIGN KEY (EventID) REFERENCES Event(ID),
	CONSTRAINT EventUser_User_FK FOREIGN KEY (UserID) REFERENCES User(ID)
);

INSERT INTO EventUser (EventID, UserID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);