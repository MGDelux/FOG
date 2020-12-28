DROP DATABASE IF EXISTS `FOG`;
CREATE DATABASE IF NOT EXISTS `FOG`;
USE FOG;

DROP USER IF exists 'FOG'@'localhost';
CREATE USER 'FOG'@'localhost' IDENTIFIED BY 'xY1xf45c1#';
GRANT ALL PRIVILEGES ON FOG.* TO 'FOG'@'localhost';


CREATE TABLE VERSION
(
    `version` int default 1
);
CREATE TABLE kunder
(
    `Kunde_Id`       INT auto_increment,
    `Email`          VARCHAR(45) NOT NULL,
    `Post_Nummer`    int,
    `City`           VARCHAR(45),
    `Adresse`        VARCHAR(45),
    `TlfNr`          int,
    `Kunde_Skabelse` DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`Kunde_Id`),
    UNIQUE (`Email`)
);
CREATE TABLE medarbejder
(
    `Medarbejder_id` INT auto_increment,
    `Email`          VARCHAR(45)                                   NOT NULL,
    `Role`           ENUM ("SALESMAN", "ADMIN") DEFAULT "SALESMAN" NOT NULL,
    `salt`           binary(16),
    `secret`         binary(32),
    CONSTRAINT PK_medarbejder PRIMARY KEY (`Medarbejder_id`),
    UNIQUE (`Email`)

);
INSERT INTO `fog`.`medarbejder` (`Medarbejder_id`, `Email`)
VALUES (1, "Ikke_Tildelt");
CREATE TABLE Carporte
(
    `CarportI_id`  INT auto_increment,
    `Produckt_Nr`  INT,
    `Produkt_Navn` VARCHAR(45),
    PRIMARY KEY (`CarportI_id`)
);

CREATE TABLE CartPortMaterialer
( /*dette inkl træ og tagplader*/
    `Carportmateriale_Id`     INT auto_Increment,
    `Carportmateriale_Navn`   VARCHAR(45)  NOT NULL,
    `Carportmateriale_Length` INT,
    `Carportmateriale_antal`  INT,
    `materiale_Beskrivelse`   VARCHAR(256) NOT NULL,
    `materiale_Pris`          double       not null,
    PRIMARY KEY (`Carportmateriale_Id`)
);
CREATE TABLE BeslagOgSkruer
( /* beslag&skruer */
    `BeslagOgSkruer_Id`          INT auto_Increment,
    `BeslagOgSkruer_Navn`        VARCHAR(45)  NOT NULL,
    `BeslagOgSkruer_antal`       INT,
    `BeslagOgSkruer_Beskrivelse` VARCHAR(256) NOT NULL,
    `BeslagOgSkruer_Pris`        double       not null,
    PRIMARY KEY (`BeslagOgSkruer_Id`)
);

CREATE TABLE forespørgsler
(
    `Order_Id`        INT auto_increment NOT NULL,
    `kunde`           varchar(45),
    `Carport_Bredde`  INT,
    `Carport_Længde`  INT,
    `Tag_Type`        VARCHAR(45),
    `has_shed`        bit,
    `shed_width`      INT,
    `shed_length`     INT,
    `shed_height`     INT,
    `assigned_seller` varchar(45) default "Ikke_Tildelt",
    CONSTRAINT PK_forespørgsler PRIMARY KEY (`Order_Id`, `kunde`, `assigned_seller`),
    FOREIGN KEY (`Kunde`) REFERENCES kunder (`Email`),
    FOREIGN KEY (`assigned_seller`) REFERENCES medarbejder (`Email`)
);

CREATE TABLE forespørgsel_Indhold
(
    `forespørgsel_Indhold_id` INT,
    `Order_Id`                INT    NOT NULL,
    `Carportmateriale`        INT    not null,
    `BeslagOgSkruer`          INT    not null,
    `antal`                   int    not null,
    `sum`                     double not null,
    CONSTRAINT PK_forespørgsel_Indhold PRIMARY KEY (`Order_Id`, `Carportmateriale`, `BeslagOgSkruer`),
    FOREIGN KEY (`Carportmateriale`) REFERENCES CartPortMaterialer (`Carportmateriale_Id`),
    FOREIGN KEY (BeslagOgSkruer) REFERENCES BeslagOgSkruer (`BeslagOgSkruer_Id`),
    FOREIGN KEY (Order_Id) REFERENCES forespørgsler (`Order_Id`)
);
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Brædt', '480', 5, 'Vindskeder på rejsning', '50.00');

INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Bræt', '600', 5, 'Sternbrædder til siderne Carport del', '50.00');

INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Bræt', '540', 5, 'Sternbrædder til siderne Skur del (deles)', '50.00');

INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('fædigskåret (byg-selv spær)', '0', 5, 'byg-selv spær (skal samles) 8 stk.', '50.00');

INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('97x97 mm. trykimp. Stolpe læmgde 300', '300', 5, 'k. Stolper nedgraves 90 cm. i jord	+ skråstiver',
        '50.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('45x195 spærtræ ubh. læmgde 480', '480', 5, 'Remme i sider, sadles ned i stolper Carport del', '50.00');

INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('45x195 spærtræ ubh. læmgde 480', '480', 5, 'Remme i sider, sadles ned i stolper Skur del', '50.00');

INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('45x95 Reglar ubh.læmgde 240', '240', 5, '. Løsholter i siderne af skur', '50.00');

INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('45x95 Reglar ubh læmgde 360', '360', 5, 'Løsholter i gavle af skur', '50.00');

INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Bræt', '480', 5, 'Vand bræt på vindskeder', '50.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Bræt', '240', 5, 'beklædning af gavle 1 på 2', '50.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Bræt', '210', 5, 'Beklædning af skur 1 på 2 ', '50.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Bræt', '540', 5, 'til montering oven på tagfodslægte', '50.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('38x73 mm. taglægte T1 læmgde 540', '540', 5, 'til z på bagside af dør', '50.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('38x73 mm. taglægte T1 læmgde 540', '540', 5,
        'til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægt', '50.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('38x73 mm. taglægte T1 læmgde 420', '420', 5, 'toplægte til montering af rygsten lægges i toplægte holde ',
        '50.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES (' Bræt', '360', 5, 'understernbrædder til for & bag ende', '50.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Bræt', '240', 5, 'understernbrædder til siderne', '60.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Bræt', '360', 5, 'oversternbrædder til forenden', '50.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Bræt', '240', 5, 'oversternbrædder til siderne', '60.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('38x73 mm. Lægte ubh. længde 420', '420', 5, 'til z på bagside af dør', '50.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('45x95 mm. Reglar ub. længde 270', '270', 5, 'løsholter til skur gavle', '40.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('45x95 mm. Reglar ub. længde 240', '240', 5, 'løsholter til skur sider', '35.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('45x195 mm. spærtræ ubh. længde 600', '600', 5, 'Remme i sider, sadles ned i stolper', '200.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('45x195 mm. spærtræ ubh. længde 480', '480', 5, 'Remme i sider, sadles ned	i stolper (skur del, deles)',
        '40.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('45x195 mm. spærtræ	ubh. længde 600', '600', 5, 'Spær, monteres på rem', '40.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('97x97 mm. trykimp. Stolpe længde 300', '300', 5, 'Stolper nedgraves 90 cm. i jord', '40.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES (' Bræt', '210', 5, 'til beklædning af skur 1 på 2', '40.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Bræt', '360', 5, 'vandbrædt på stern i forende', '40.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Plastmo	Ecolite	blåtonet længde 600', '600', 5, 'tagplader monteres på spær', '40.00');
INSERT INTO `fog`.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                        `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Plastmo	Ecolite	blåtonet længde 360', '360', 5, 'tagplader monteres på spær ', '40.00');

INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('universal 190 mm højre', 5, 'Til montering af spær på rem', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('universal 190 mm venstre', 5, 'Til montering af spær på rem', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('Stalddørsgreb 50x75', 5, 'til dør i skur', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('T-hængsel 390 mm.', 5, 'til dør i skur', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('vinkelbeslag', 5, 'til montering af løsholter', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('4,5 x 60 mm. Skruer 200 stk', 5, 'Til montering af Stern, vindskeder, vindkryds & vandbræt', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('5,0 x 40 mm. beslagskruer 250 stk', 5, 'Til	montering af universalbeslag + toplægte', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('5,0 x 100 mm. skruer 100	stk', 5, 'e til taglægter', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('bræddebolt 10 x 120 mm.', 5, 'Til montering af rem på stolper', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('firkantskiver 40x40x11mm', 5, 'Til montering af rem på stolper', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('4,5 x 70 mm. Skruer 200 stk', 5, 'til montering af yderste bræt ved beklædning', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('4,5 x 50 mm. Skruer 350 stk.', 5, 'til montering af inderste bræt ved beklædning ', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('B & C Dobbelt -s sort ', 5, 'monteres på taglægter 6 rækker af 24 sten på hver side af taget', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('B & C Rygsten sort', 5, 'monteres på toplægte med medfølgende beslag se tagstens-vejledning', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('B & C Toplægte holder', 5, 'monteres på toppen af spæret (til toplægte)', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('B & C rygstensbeslag', 5, 'Til montering af rygsten', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('B & C tagstens bindere & nakkekroge', 5, 'til montering af tagsten, alle ydersten + hver anden fastgøres',
        '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('plastmo bundskruer 200 stk', 5, 'Skruer til tagplader', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('hulbånd 1x20 mm. 10 mtr', 5, 'Til vindkryds på spær', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('universal 190 mm venstre', 5, 'Til montering af spær på rem', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('4,5 x 60 mm. skruer 200 stk.', 5, 'Til montering af stern&vandbrædt', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('4,0 x 50 mm. beslagskruer 250	stk', 5, 'Til montering af universalbeslag + hulbånd', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('bræddebolt 10 x 120 mm.', 5, 'Til montering af rem på stolper', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('firkantskiver 40x40x11mm', 5, 'Til montering af rem på stolper', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('4,5 x 70 mm. Skruer 400 stk.', 5, 'til montering af yderste beklædning', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('4,5 x 50 mm. Skruer 300 stk. ', 5, 'til montering af inderste beklædning', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('stalddørsgreb 50x75', 5, 'Til lås på dør i skur', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('t-hængsel 390 mm', 5, 'Til skurdør', '20.00');
INSERT INTO `fog`.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_antal`, `BeslagOgSkruer_Beskrivelse`,
                                    `BeslagOgSkruer_Pris`)
VALUES ('vinkelbeslag 35', 5, 'Til montering af løsholter i skur', '20.00');

