DROP DATABASE IF EXISTS FOG;
CREATE DATABASE IF NOT EXISTS FOG;
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
    `salt`           binary(16)                                    NOT NULL,
    `secret`         binary(32)                                    NOT NULL,
    CONSTRAINT PK_medarbejder PRIMARY KEY (`Medarbejder_id`, `Email`)

);
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
    `BeslagOgSkruer_Length`      INT,
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
    `assigned_seller` varchar(45),
    CONSTRAINT PK_forespørgsler PRIMARY KEY (`Order_Id`, `kunde`, `assigned_seller`),
    FOREIGN KEY (`Kunde`) REFERENCES kunder (`Email`)
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
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret træ', '480', 5, 'Vindskeder på rejsning', '50.00');

INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret træ', '600', 5, 'Sternbrædder til siderne Carport del', '50.00');

INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret træ', '540', 5, 'Sternbrædder til siderne Skur del (deles)', '50.00');

INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('spær', '0', 5, 'byg-selv spær (skal samles) 8 stk.', '50.00');

INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret Stolpe', '300', 5, 'k. Stolper nedgraves 90 cm. i jord	+ skråstiver',
        '50.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('spærtræ ubh.', '480', 5, 'Remme i sider, sadles ned i stolper Carport del', '50.00');

INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('spærtræ ubh.', '480', 5, 'Remme i sider, sadles ned i stolper Skur del', '50.00');

INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Reglar ubh.', '240', 5, '. Løsholter i siderne af skur', '50.00');

INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Reglar ubh', '360', 5, 'Løsholter i gavle af skur', '50.00');

INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret Bræt', '480', 5, 'Vand bræt på vindskeder', '50.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret Bræt', '240', 5, 'beklædning af gavle 1 på 2', '50.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret Bræt', '210', 5, 'Beklædning af skur 1 på 2 ', '50.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret Bræt', '540', 5, 'til montering oven på tagfodslægte', '50.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('taglægte T1', '540', 5, 'til z på bagside af dør', '50.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('taglægte T1', '540', 5,
        'til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægt', '50.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('taglægte T1', '420', 5, 'toplægte til montering af rygsten lægges i toplægte holde ',
        '50.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret Brædt', '360', 5, 'understernbrædder til for & bag ende', '50.00');


INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret Brædt', '540', 5, 'understernbrædder til siderne', '60.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret brædt', '360', 5, 'oversternbrædder til forenden', '50.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret Brædt', '540', 5, 'oversternbrædder til siderne', '60.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Lægte ubh.', '420', 5, 'til z på bagside af dør', '50.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Reglar ub.', '270', 5, 'løsholter til skur gavle', '40.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Reglar ub.', '240', 5, 'løsholter til skur sider', '35.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('spærtræ ubh.', '600', 5, 'Remme i sider, sadles ned i stolper', '200.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('spærtræ ubh.', '480', 5, 'Remme i sider, sadles ned	i stolper (skur del, deles)',
        '40.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('spærtræ	ubh.', '600', 5, 'Spær, monteres på rem', '40.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret Stolpe', '300', 5, 'Stolper nedgraves 90 cm. i jord', '40.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret brædt', '210', 5, 'til beklædning af skur 1 på 2', '40.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('trykimprægneret Brædt', '360', 5, 'vandbrædt på stern i forende', '40.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Plastmo Ecolite blåtonet', '600', 5, 'tagplader monteres på spær', '40.00');
INSERT INTO FOG.`CartPortMaterialer` (`Carportmateriale_Navn`, `Carportmateriale_length`, `Carportmateriale_antal`,
                                      `materiale_Beskrivelse`, `materiale_Pris`)
VALUES ('Plastmo Ecolite blåtonet', '360', 5, 'tagplader monteres på spær ', '40.00');

INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('universal 190 mm højre', 0, 5, 'Til montering af spær på rem', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('universal 190 mm venstre', 0, 5, 'Til montering af spær på rem', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('Stalddørsgreb', 0, 5, 'til dør i skur', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('T-hængsel 390 mm.', 0, 5, 'til dør i skur', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('vinkelbeslag', 0, 5, 'til montering af løsholter', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('Skruer 200 stk', 0, 5, 'Til montering af Stern, vindskeder, vindkryds & vandbræt', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('beslagskruer 250 stk', 0, 5, 'Til	montering af universalbeslag + toplægte', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('skruer 100 stk', 0, 5, 'e til taglægter', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('bræddebolt', 0, 5, 'Til montering af rem på stolper', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('firkantskiver', 0, 5, 'Til montering af rem på stolper', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('Skruer 200 stk', 0, 5, 'til montering af yderste bræt ved beklædning', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('Skruer 350 stk.', 0, 5, 'til montering af inderste bræt ved beklædning ', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('B&C Dobbelt -s sort ', 0, 5, 'monteres på taglægter 6 rækker af 24 sten på hver side af taget', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('B&C Rygsten sort', 0, 5, 'monteres på toplægte med medfølgende beslag se tagstens-vejledning', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('B&C Toplægte holder', 0, 5, 'monteres på toppen af spæret (til toplægte)', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('B&C rygstensbeslag', 0, 5, 'Til montering af rygsten', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('B&C tagstens bindere & nakkekroge', 0, 5, 'til montering af tagsten, alle ydersten + hver anden fastgøres',
        '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('plastmo bundskruer 200 stk', 0, 5, 'Skruer til tagplader', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('hulbånd 10 mtr', 0, 5, 'Til vindkryds på spær', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)

VALUES ('beslagskruer 250 stk', 0, 5, 'Til montering af universalbeslag + hulbånd', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('bræddebolt', 0, 5, 'Til montering af rem på stolper', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('firkantskiver', 0, 5, 'Til montering af rem på stolper', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('Skruer 400 stk.', 0, 5, 'til montering af yderste beklædning', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('Skruer 300 stk.', 0, 5, 'til montering af inderste beklædning', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('stalddørsgreb', 0, 5, 'Til lås på dør i skur', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('t-hængsel 390 mm', 0, 5, 'Til skurdør', '20.00');
INSERT INTO FOG.`BeslagOgSkruer` (`BeslagOgSkruer_Navn`, `BeslagOgSkruer_Length`, `BeslagOgSkruer_antal`,
                                  `BeslagOgSkruer_Beskrivelse`,
                                  `BeslagOgSkruer_Pris`)
VALUES ('vinkelbeslag', 0, 5, 'Til montering af løsholter i skur', '20.00');

