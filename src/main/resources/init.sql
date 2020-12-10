DROP DATABASE IF EXISTS `FOG`;
CREATE DATABASE IF NOT EXISTS `FOG`;
USE FOG;

DROP USER IF exists 'FOG'@'localhost';
CREATE USER 'FOG'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON FOG.* TO 'FOG'@'localhost';

CREATE TABLE VERSION (
`version` int default 1
);
CREATE TABLE kunder (
`Kunde_Id` INT auto_increment,
`Email` VARCHAR(45) NOT NULL,
`Post_Nummer` int,
`City` VARCHAR(45),
`Adresse` VARCHAR(45),
`TlfNr` int,
`Kunde_Skabelse` DATETIME ON UPDATE CURRENT_TIMESTAMP,
 CONSTRAINT PK_kunder PRIMARY KEY (`Kunde_Id`,`Email`)
);
CREATE TABLE medarbejder (
`Medarbejder_id` INT auto_increment,
`Email` VARCHAR(45) NOT NULL,
`Role` ENUM("SALESMAN", "ADMIN") DEFAULT "SALESMAN" NOT NULL,
`salt` binary(16) NOT NULL,
`secret` binary(32) NOT NULL,
 PRIMARY KEY (`Medarbejder_id`)
);
CREATE TABLE Carporte (
`CarportI_id` INT auto_increment,
`Produckt_Nr` INT,
`Produkt_Navn` VARCHAR(45),
 PRIMARY KEY (`CarportI_id`)
);

CREATE TABLE CartPortMaterialer( /*dette inkl træ og tagplader*/
`Carportmateriale_Id`INT auto_Increment,
`Carportmateriale_Navn` VARCHAR(45) NOT NULL,
`materiale_Beskrivelse` VARCHAR(45) NOT NULL,
`materiale_Pris` double not null,
PRIMARY KEY (`Carportmateriale_Id`)
);
CREATE TABLE BeslagOgSkruer( /* beslag&skruer */
`BeslagOgSkruer_Id`INT auto_Increment,
`BeslagOgSkruer_Navn` VARCHAR(45) NOT NULL,
`BeslagOgSkruer_Beskrivelse` VARCHAR(45) NOT NULL,																						
`BeslagOgSkruer_Pris` double not null,
PRIMARY KEY (`BeslagOgSkruer_Id`)
);

CREATE TABLE forespørgsler(
`Order_Id` INT auto_increment NOT NULL,
`kunde` INT NOT NULL,
`Carport_Bredde`INT NOT NULL,
`Carport_Længde` int NOT NULL,
`Tag_Type` VARCHAR(45) NOT NULL,
`has_redskabesrum` bit NOT NULL,
`Redskabsrum_Bredde` INT,
`Redskabsrum_Længde` INT,
PRIMARY KEY (`Order_Id`),
FOREIGN KEY (Kunde) REFERENCES kunder(`Kunde_Id`)

);

CREATE TABLE forespørgsel_Indhold(
`forespørgsel_Indhold_id` INT,
`Order_Id` INT NOT NULL,
`Carportmateriale`INT not null,
`BeslagOgSkruer` INT not null,
`antal` int not null,
`sum` double not null,
CONSTRAINT PK_forespørgsel_Indhold PRIMARY KEY  (`Order_Id`,`Carportmateriale`,`BeslagOgSkruer`),
FOREIGN KEY (`Carportmateriale`) REFERENCES CartPortMaterialer(`Carportmateriale_Id`),
FOREIGN KEY (BeslagOgSkruer) REFERENCES BeslagOgSkruer(`BeslagOgSkruer_Id`),
FOREIGN KEY (Order_Id) REFERENCES forespørgsler(`Order_Id`)
);

