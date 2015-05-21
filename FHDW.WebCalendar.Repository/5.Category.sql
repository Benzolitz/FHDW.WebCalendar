CREATE TABLE Category
(
	ID int(11) NOT NULL AUTO_INCREMENT,
	Name varchar(255) NOT NULL,
	EventID int(11) NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT Category_Event_FK FOREIGN KEY (EventID) REFERENCES Event(ID)
);

INSERT INTO Category (Name, EventID) VALUES
('Kategorie1', 1),
('Kategorie2', 2),
('Kategorie3', 3),
('Kategorie4', 4);