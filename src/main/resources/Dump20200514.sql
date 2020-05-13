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
-- Table structure for table `core_permission`
--

DROP TABLE IF EXISTS `core_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_permission` (
  `id` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_permission`
--

LOCK TABLES `core_permission` WRITE;
/*!40000 ALTER TABLE `core_permission` DISABLE KEYS */;
INSERT INTO `core_permission` VALUES ('EMP','Employee'),('MGR','Manager');
/*!40000 ALTER TABLE `core_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_auth`
--

DROP TABLE IF EXISTS `emp_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emp_auth` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_auth`
--

LOCK TABLES `emp_auth` WRITE;
/*!40000 ALTER TABLE `emp_auth` DISABLE KEYS */;
INSERT INTO `emp_auth` VALUES (1030161,'surya.sahu26@gmail.com','$2a$12$N7LwWMO4AElSV1C3.W3PzeuMtMHl0unJ1C0eBxX0YZcumgRV7ch0u'),(1030171,'satyabrata2004@gmail.com','$2a$12$N7LwWMO4AElSV1C3.W3PzeuMtMHl0unJ1C0eBxX0YZcumgRV7ch0u'),(1030181,'riddhi.sohampaul@gmail.com','$2a$12$N7LwWMO4AElSV1C3.W3PzeuMtMHl0unJ1C0eBxX0YZcumgRV7ch0u'),(1030191,'surya.sahu26@gmail.com','$2a$12$N7LwWMO4AElSV1C3.W3PzeuMtMHl0unJ1C0eBxX0YZcumgRV7ch0u');
/*!40000 ALTER TABLE `emp_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_permission`
--

DROP TABLE IF EXISTS `emp_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emp_permission` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) NOT NULL,
  `emp_permission_id` varchar(20) NOT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_permission`
--

LOCK TABLES `emp_permission` WRITE;
/*!40000 ALTER TABLE `emp_permission` DISABLE KEYS */;
INSERT INTO `emp_permission` VALUES (1,1030181,'MANAGER'),(2,1030191,'EMPLOYEE'),(3,1030171,'TRANSPORT'),(4,1030161,'MANAGER');
/*!40000 ALTER TABLE `emp_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_detail`
--

DROP TABLE IF EXISTS `employee_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_detail` (
  `emp_id` int(11) NOT NULL,
  `emp_name` varchar(80) NOT NULL,
  `emp_email` varchar(100) NOT NULL,
  `emp_manager_id` int(11) DEFAULT NULL,
  `emp_type` varchar(80) NOT NULL,
  `emp_dc` varchar(80) NOT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_detail`
--

LOCK TABLES `employee_detail` WRITE;
/*!40000 ALTER TABLE `employee_detail` DISABLE KEYS */;
INSERT INTO `employee_detail` VALUES (1030161,'Manager lvl 2','surya.sahu92@gmail.com',1030181,'MANAGER','BBSRSTP'),(1030171,'Transport Desk','satyabrata2004@gmail.com',NULL,'TRANSPORT','BBSRSTP'),(1030181,'Manager lvl 1','riddhi.sohampaul@gmail.com',1030161,'MANAGER','BBSRSTP'),(1030191,'Employee','surya.sahu92@gmail.com',1030181,'EMPLOYEE','BBSRSTP');
/*!40000 ALTER TABLE `employee_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `infy_country`
--

DROP TABLE IF EXISTS `infy_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `infy_country` (
  `code` char(4) NOT NULL,
  `name` varchar(50) NOT NULL,
  `continent` varchar(20) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `code` varchar(20) NOT NULL,
  `value` varchar(45) NOT NULL,
  `country` char(4) NOT NULL,
  `continent` char(4) NOT NULL,
  `travel_desk_mail` char(60) NOT NULL,
  PRIMARY KEY (`code`,`travel_desk_mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `code` char(4) NOT NULL,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `req_id` varchar(40) NOT NULL,
  `shuttle_id` varchar(10) NOT NULL,
  `requester` int(11) NOT NULL,
  `approver` int(11) NOT NULL,
  `reason` varchar(100) NOT NULL,
  `status` enum('PENDING','APPROVED','APPROVED_MGR','APPROVED_TRNS','REJECTED','REJECTED_MGR','REJECTED_TRNS','CANCELLED') NOT NULL,
  `for_date` varchar(20) NOT NULL,
  `dc_from` char(20) NOT NULL,
  `dc_to` char(20) NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `mng_remark` varchar(45) DEFAULT NULL,
  `trns_remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`req_id`),
  UNIQUE KEY `req_id_UNIQUE` (`req_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shuttle_request`
--

LOCK TABLES `shuttle_request` WRITE;
/*!40000 ALTER TABLE `shuttle_request` DISABLE KEYS */;
INSERT INTO `shuttle_request` VALUES ('STLREQ103016114052020','STL1100',1030161,1030181,'imhunybtvrdcesd','PENDING','14-05-2020 11:00','BBSRSTP','BBSRSEZ','NA','NA','NA'),('STLREQ103016114052020F12','STL1500',1030161,1030181,'xdtcfvhbjnkm','CANCELLED','14-05-2020 15:00','BBSRSTP','BBSRSEZ','NA','No i wont go','NA'),('STLREQ103016114052020F2','STL0800',1030161,1030181,'I will go to SEZ today.','REJECTED_TRNS','14-05-2020 08:00','BBSRSTP','BBSRSEZ','NA','Go ahead','NA'),('STLREQ103016114052020F53','STL0800',1030161,1030181,'I will go to SEZ today.','REJECTED_TRNS','14-05-2020 08:00','BBSRSTP','BBSRSEZ','NA','Go ahead','no'),('STLREQ103018114052020','STL0800',1030181,1030161,'xdtcfyvhbjmlk','APPROVED_MGR','14-05-2020 08:00','BBSRSTP','BBSRSEZ','NA','go ahead','NA'),('STLREQ1030181140520202F0','STL1500',1030181,1030161,'Sorry wrong data entered previously','CANCELLED','12-05-2020 15:00','BBSRSTP','BBSRSEZ','NA','NA','NA'),('STLREQ1030181140520203F95','STL1500',1030181,1030161,'Sorry wrong data entered previously','CANCELLED','11-05-2020 15:00','BBSRSTP','BBSRSEZ','NA','abcd','NA'),('STLREQ103018114052020F13','STL1500',1030181,1030161,'Sorry wrong data entered previously','CANCELLED','14-05-2020 15:00','BBSRSTP','BBSRSEZ','NA','no','NA'),('STLREQ103018114052020F93','STL1100',1030181,1030161,'I will go to SEZ today.','CANCELLED','13-05-2020 11:00','BBSRSTP','BBSRSEZ','NA','No I don\'t want to go','NA'),('STLREQ103018114052020F94','STL1100',1030181,1030161,'I will go to SEZ today.','CANCELLED','13-05-2020 11:00','BBSRSTP','BBSRSEZ','NA','No I don\'t want to go','NA'),('STLREQ103019114052020','STL1500',1030191,1030181,'xdcfvgjbhkm','PENDING','14-05-2020 15:00','BBSRSTP','BBSRSEZ','NA','NA','NA'),('STLREQ1030191140520202F37','STL0800',1030191,1030181,'ctfyvgubhinjomk,l','REJECTED_MGR','14-05-2020 08:00','BBSRSTP','BBSRSEZ','NA','sssss','NA'),('STLREQ1030191140520203','STL0800',1030191,1030181,'ctfyvgubhinjomk,l','APPROVED_MGR','13-05-2020 08:00','BBSRSTP','BBSRSEZ','NA','NA','NA'),('STLREQ1030191140520204','STL0800',1030191,1030181,'ctfyvgubhinjomk,l','APPROVED_MGR','12-05-2020 08:00','BBSRSTP','BBSRSEZ','NA','NA','NA'),('STLREQ103019114052020F3F6','STL0800',1030191,1030181,'I will go to SEZ today.','REJECTED_MGR','13-05-2020 08:00','BBSRSTP','BBSRSEZ','NA','leave','NA');
/*!40000 ALTER TABLE `shuttle_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shuttle_seating`
--

DROP TABLE IF EXISTS `shuttle_seating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shuttle_seating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` char(8) NOT NULL,
  `count` int(11) NOT NULL,
  `for_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shuttle_seating`
--

LOCK TABLES `shuttle_seating` WRITE;
/*!40000 ALTER TABLE `shuttle_seating` DISABLE KEYS */;
INSERT INTO `shuttle_seating` VALUES (1,'STL1100',60,'2020-05-14 11:00:00'),(2,'STL1500',60,'2020-05-14 15:00:00'),(3,'STL0800',400,'2020-05-14 08:00:00');
/*!40000 ALTER TABLE `shuttle_seating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shuttle_timing`
--

DROP TABLE IF EXISTS `shuttle_timing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shuttle_timing` (
  `code` varchar(40) NOT NULL,
  `start_time` time(2) NOT NULL,
  `return_time1` time(2) NOT NULL,
  `return_time2` time(2) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shuttle_timing`
--

LOCK TABLES `shuttle_timing` WRITE;
/*!40000 ALTER TABLE `shuttle_timing` DISABLE KEYS */;
INSERT INTO `shuttle_timing` VALUES ('STL0800','08:00:00.00','16:00:00.00','18:15:00.00'),('STL1100','11:00:00.00','16:00:00.00','19:15:00.00'),('STL1500','15:00:00.00','16:00:00.00','19:15:00.00');
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

-- Dump completed on 2020-05-14  5:00:14
