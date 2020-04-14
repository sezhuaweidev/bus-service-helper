CREATE DATABASE  IF NOT EXISTS `infy_employees` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `infy_employees`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: infy_employees
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee_detail`
--

DROP TABLE IF EXISTS `employee_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_detail` (
  `emp_id` INT(11) NOT NULL,
  `emp_name` VARCHAR(80) NOT NULL,
  `emp_email` VARCHAR(100) NOT NULL,
  `emp_manager_id` INT(11) DEFAULT NULL,
  `emp_type` VARCHAR(80) NOT NULL,
  `emp_dc` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_detail`
--

LOCK TABLES `employee_detail` WRITE;
/*!40000 ALTER TABLE `employee_detail` DISABLE KEYS */;
INSERT INTO `employee_detail` VALUES (1030181,'Soham Paul','riddhi.sohampaul@gmail.com',NULL,'MANAGER','BBSRSEZ'),(1030191,'Surya Sahu','surya.sahu92@gmail.com',1030181,'EMPLOYEE','BBSRSEZ'),(1030171,'Satya Bhai','satyabrata2004@gmail.com',NULL,'TRANSPORT','BBSRSEZ');
/*!40000 ALTER TABLE `employee_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `infy_country`
--

DROP TABLE IF EXISTS `infy_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `infy_country` (
  `code` CHAR(4) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `continent` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `infy_country`
--

LOCK TABLES `infy_country` WRITE;
/*!40000 ALTER TABLE `infy_country` DISABLE KEYS */;
INSERT INTO `infy_country` VALUES ('ABW','Aruba','NA'),('AFG','Afghanistan','AS'),('AGO','Angola','AF'),('AIA','Anguilla','NA'),('ALB','Albania','EU'),('AND','Andorra','EU'),('ANT','Netherlands Antilles','NA'),('ARE','United Arab Emirates','AS'),('ARG','Argentina','SA'),('ARM','Armenia','AS'),('ASM','American Samoa','OC'),('ATA','Antartica','AN'),('ATF','French Southern territories','AN'),('ATG','Antigua and Barbuda','NA'),('AUS','Australia','OC'),('AUT','Austria','EU'),('AZE','Azerbaijan','AS'),('BDI','Burundi','AF'),('BEL','Belgium','EU'),('BEN','Benin','AF'),('BFA','Burkina Faso','AF'),('BGD','Bangladesh','AS'),('BGR','Bulgaria','EU'),('BHR','Bahrain','AS'),('BHS','Bahamas','NA'),('BIH','Bosnia and Herzegovina','EU'),('BLR','Belarus','EU'),('BLZ','Belize','NA'),('BMU','Bermuda','NA'),('BOL','Bolivia','SA'),('BRA','Brazil','SA'),('BRB','Barbados','NA'),('BRN','Brunei','AS'),('BTN','Bhutan','AS'),('BVT','Bouvet Island','AN'),('BWA','Botswana','AF'),('CAF','Central African Republic','AF'),('CAN','Canada','NA'),('CCK','Cocos (Keeling) Islands','OC'),('CHE','Switzerland','EU'),('CHL','Chile','SA'),('CHN','China','AS'),('CIV','Côte d’Ivoire','AF'),('CMR','Cameroon','AF'),('COD','Congo, The Democratic Republic of the','AF'),('COG','Congo','AF'),('COK','Cook Islands','OC'),('COL','Colombia','SA'),('COM','Comoros','AF'),('CPV','Cape Verde','AF'),('CRI','Costa Rica','NA'),('CUB','Cuba','NA'),('CXR','Christmas Island','OC'),('CYM','Cayman Islands','NA'),('CYP','Cyprus','AS'),('CZE','Czech Republic','EU'),('DEU','Germany','EU'),('DJI','Djibouti','AF'),('DMA','Dominica','NA'),('DNK','Denmark','EU'),('DOM','Dominican Republic','NA'),('DZA','Algeria','AF'),('ECU','Ecuador','SA'),('EGY','Egypt','AF'),('ERI','Eritrea','AF'),('ESH','Western Sahara','AF'),('ESP','Spain','EU'),('EST','Estonia','EU'),('ETH','Ethiopia','AF'),('FIN','Finland','EU'),('FJI','Fiji Islands','OC'),('FLK','Falkland Islands','SA'),('FRA','France','EU'),('FRO','Faroe Islands','EU'),('FSM','Micronesia, Federated States of','OC'),('GAB','Gabon','AF'),('GBR','United Kingdom','EU'),('GEO','Georgia','AS'),('GHA','Ghana','AF'),('GIB','Gibraltar','EU'),('GIN','Guinea','AF'),('GLP','Guadeloupe','NA'),('GMB','Gambia','AF'),('GNB','Guinea-Bissau','AF'),('GNQ','Equatorial Guinea','AF'),('GRC','Greece','EU'),('GRD','Grenada','NA'),('GRL','Greenland','NA'),('GTM','Guatemala','NA'),('GUF','French Guiana','SA'),('GUM','Guam','OC'),('GUY','Guyana','SA'),('HKG','Hong Kong','AS'),('HMD','Heard Island and McDonald Islands','AN'),('HND','Honduras','NA'),('HRV','Croatia','EU'),('HTI','Haiti','NA'),('HUN','Hungary','EU'),('IDN','Indonesia','AS'),('IND','India','AS'),('IOT','British Indian Ocean Territory','AF'),('IRL','Ireland','EU'),('IRN','Iran','AS'),('IRQ','Iraq','AS'),('ISL','Iceland','EU'),('ISR','Israel','AS'),('ITA','Italy','EU'),('JAM','Jamaica','NA'),('JOR','Jordan','AS'),('JPN','Japan','AS'),('KAZ','Kazakstan','AS'),('KEN','Kenya','AF'),('KGZ','Kyrgyzstan','AS'),('KHM','Cambodia','AS'),('KIR','Kiribati','OC'),('KNA','Saint Kitts and Nevis','NA'),('KOR','South Korea','AS'),('KWT','Kuwait','AS'),('LAO','Laos','AS'),('LBN','Lebanon','AS'),('LBR','Liberia','AF'),('LBY','Libyan Arab Jamahiriya','AF'),('LCA','Saint Lucia','NA'),('LIE','Liechtenstein','EU'),('LKA','Sri Lanka','AS'),('LSO','Lesotho','AF'),('LTU','Lithuania','EU'),('LUX','Luxembourg','EU'),('LVA','Latvia','EU'),('MAC','Macao','AS'),('MAR','Morocco','AF'),('MCO','Monaco','EU'),('MDA','Moldova','EU'),('MDG','Madagascar','AF'),('MDV','Maldives','AS'),('MEX','Mexico','NA'),('MHL','Marshall Islands','OC'),('MKD','Macedonia','EU'),('MLI','Mali','AF'),('MLT','Malta','EU'),('MMR','Myanmar','AS'),('MNG','Mongolia','AS'),('MNP','Northern Mariana Islands','OC'),('MOZ','Mozambique','AF'),('MRT','Mauritania','AF'),('MSR','Montserrat','NA'),('MTQ','Martinique','NA'),('MUS','Mauritius','AF'),('MWI','Malawi','AF'),('MYS','Malaysia','AS'),('MYT','Mayotte','AF'),('NAM','Namibia','AF'),('NCL','New Caledonia','OC'),('NER','Niger','AF'),('NFK','Norfolk Island','OC'),('NGA','Nigeria','AF'),('NIC','Nicaragua','NA'),('NIU','Niue','OC'),('NLD','Netherlands','EU'),('NOR','Norway','EU'),('NPL','Nepal','AS'),('NRU','Nauru','OC'),('NZL','New Zealand','OC'),('OMN','Oman','AS'),('PAK','Pakistan','AS'),('PAN','Panama','NA'),('PCN','Pitcairn','OC'),('PER','Peru','SA'),('PHL','Philippines','AS'),('PLW','Palau','OC'),('PNG','Papua New Guinea','OC'),('POL','Poland','EU'),('PRI','Puerto Rico','NA'),('PRK','North Korea','AS'),('PRT','Portugal','EU'),('PRY','Paraguay','SA'),('PSE','Palestine','AS'),('PYF','French Polynesia','OC'),('QAT','Qatar','AS'),('REU','Réunion','AF'),('ROM','Romania','EU'),('RUS','Russian Federation','EU'),('RWA','Rwanda','AF'),('SAU','Saudi Arabia','AS'),('SDN','Sudan','AF'),('SEN','Senegal','AF'),('SGP','Singapore','AS'),('SGS','South Georgia and the South Sandwich Islands','AN'),('SHN','Saint Helena','AF'),('SJM','Svalbard and Jan Mayen','EU'),('SLB','Solomon Islands','OC'),('SLE','Sierra Leone','AF'),('SLV','El Salvador','NA'),('SMR','San Marino','EU'),('SOM','Somalia','AF'),('SPM','Saint Pierre and Miquelon','NA'),('STP','Sao Tome and Principe','AF'),('SUR','Suriname','SA'),('SVK','Slovakia','EU'),('SVN','Slovenia','EU'),('SWE','Sweden','EU'),('SWZ','Swaziland','AF'),('SYC','Seychelles','AF'),('SYR','Syria','AS'),('TCA','Turks and Caicos Islands','NA'),('TCD','Chad','AF'),('TGO','Togo','AF'),('THA','Thailand','AS'),('TJK','Tajikistan','AS'),('TKL','Tokelau','OC'),('TKM','Turkmenistan','AS'),('TMP','East Timor','AS'),('TON','Tonga','OC'),('TTO','Trinidad and Tobago','NA'),('TUN','Tunisia','AF'),('TUR','Turkey','AS'),('TUV','Tuvalu','OC'),('TWN','Taiwan','AS'),('TZA','Tanzania','AF'),('UGA','Uganda','AF'),('UKR','Ukraine','EU'),('UMI','United States Minor Outlying Islands','OC'),('URY','Uruguay','SA'),('USA','United States','NA'),('UZB','Uzbekistan','AS'),('VAT','Holy See (Vatican City State)','EU'),('VCT','Saint Vincent and the Grenadines','NA'),('VEN','Venezuela','SA'),('VGB','Virgin Islands, British','NA'),('VIR','Virgin Islands, U.S.','NA'),('VNM','Vietnam','AS'),('VUT','Vanuatu','OC'),('WLF','Wallis and Futuna','OC'),('WSM','Samoa','OC'),('YEM','Yemen','AS'),('YUG','Yugoslavia','EU'),('ZAF','South Africa','AF'),('ZMB','Zambia','AF'),('ZWE','Zimbabwe','AF');
/*!40000 ALTER TABLE `infy_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `infy_dc`
--

DROP TABLE IF EXISTS `infy_dc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `infy_dc` (
  `code` VARCHAR(20) NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  `country` CHAR(4) NOT NULL,
  `continent` CHAR(4) NOT NULL,
  `travel_desk_mail` CHAR(60) NOT NULL,
  PRIMARY KEY (`code`,`travel_desk_mail`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `infy_dc`
--

LOCK TABLES `infy_dc` WRITE;
/*!40000 ALTER TABLE `infy_dc` DISABLE KEYS */;
INSERT INTO `infy_dc` VALUES ('BBSRSEZ','Bhubaneswar-SEZ','IND','AS','satyabrat2004@gmail.com'),('BBSRSTP','Bhubaneswar-STP','IND','AS','satyabrat2004@gmail.com'),('CANCAL','Canada-Calgary','CAN','NA',''),('CANMONT','Canada-Montread','CAN','NA','');
/*!40000 ALTER TABLE `infy_dc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `infy_region`
--

DROP TABLE IF EXISTS `infy_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `infy_region` (
  `code` CHAR(4) NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `infy_region`
--

LOCK TABLES `infy_region` WRITE;
/*!40000 ALTER TABLE `infy_region` DISABLE KEYS */;
INSERT INTO `infy_region` VALUES ('AF','Africa'),('AN','Antarctica'),('AS','Asia'),('EU','Europe'),('NA','North America'),('OC','Oceania'),('SA','South America');
/*!40000 ALTER TABLE `infy_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shuttle_request`
--

DROP TABLE IF EXISTS `shuttle_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shuttle_request` (
  `req_id` VARCHAR(40) NOT NULL,
  `shuttle_id` VARCHAR(10) NOT NULL,
  `requester` INT(11) NOT NULL,
  `approver` INT(11) NOT NULL,
  `reason` VARCHAR(100) NOT NULL,
  `status` ENUM('PENDING','APPROVED','APPROVED_MGR','APPROVED_TRNS','REJECTED','REJECTED_MGR','REJECTED_TRNS') NOT NULL,
  `for_date` VARCHAR(20) NOT NULL,
  `dc_from` CHAR(20) NOT NULL,
  `dc_to` CHAR(20) NOT NULL,
  PRIMARY KEY (`req_id`),
  UNIQUE KEY `req_id_UNIQUE` (`req_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shuttle_request`
--

LOCK TABLES `shuttle_request` WRITE;
/*!40000 ALTER TABLE `shuttle_request` DISABLE KEYS */;
INSERT INTO `shuttle_request` VALUES ('STLREQ103018108042020','STL0800',1030181,1030191,'stufffff will get done','APPROVED','08-04-2020','BBSRSTP','BBSRSEZ'),('STLREQ103019108042020','STL1100',1030191,1030181,'Well this works I guess','PENDING','08-04-2020','BBSRSTP','BBSRSEZ');
/*!40000 ALTER TABLE `shuttle_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shuttle_timing`
--

DROP TABLE IF EXISTS `shuttle_timing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shuttle_timing` (
  `code` VARCHAR(40) NOT NULL,
  `start_time` TIME(2) NOT NULL,
  `return_time1` TIME(2) NOT NULL,
  `return_time2` TIME(2) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shuttle_timing`
--

LOCK TABLES `shuttle_timing` WRITE;
/*!40000 ALTER TABLE `shuttle_timing` DISABLE KEYS */;
INSERT INTO `shuttle_timing` VALUES ('STL0800','08:00:00.00','16:00:00.00','18:15:00.00'),('STL1100','11:00:00.00','16:00:00.00','19:15:00.00');
/*!40000 ALTER TABLE `shuttle_timing` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-08 16:41:27
