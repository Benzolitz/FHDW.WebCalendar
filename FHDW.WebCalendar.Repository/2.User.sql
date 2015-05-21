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