CREATE TABLE User
(
	ID int(11) NOT NULL AUTO_INCREMENT,
	Username varchar(255) NOT NULL,
	EMail varchar(255) NOT NULL,
	pass varchar(255) NOT NULL,
	FirstName varchar(255) NOT NULL,
	LastName varchar(255) NOT NULL,
	PhoneNumber varchar(255) DEFAULT NULL,
	SecurityQuestionID int(11) NOT NULL,
	SequrityAnswer varchar(255) NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT User_SecurityQuestion_FK FOREIGN KEY (SecurityQuestionID) REFERENCES SecurityQuestion (ID)
);

INSERT INTO User (Username, EMail, pass, FirstName, LastName, SecurityQuestionID, SequrityAnswer) VALUES
('User1', 'Email1@Mail.de', 'pass1', 'FirstName1', 'LastName1', '1', 'Hund'),
('User2', 'Email2@Mail.de', 'pass2', 'FirstName2', 'LastName2', '2', 'Möp'),
('User3', 'Email3@Mail.de', 'pass3', 'FirstName3', 'LastName3', '3', 'Transformers'),
('User4', 'Email4@Mail.de', 'pass4', 'FirstName4', 'LastName4', '4', 'Secret');