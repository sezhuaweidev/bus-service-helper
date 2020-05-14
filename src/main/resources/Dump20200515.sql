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
INSERT INTO `emp_auth` VALUES (1030161,'abhaduri99@gmail.com','$2a$12$N7LwWMO4AElSV1C3.W3PzeuMtMHl0unJ1C0eBxX0YZcumgRV7ch0u'),(1030171,'abhaduri99.ab@gmail.com','$2a$12$N7LwWMO4AElSV1C3.W3PzeuMtMHl0unJ1C0eBxX0YZcumgRV7ch0u'),(1030172,'riddhi.sohampaul@gmail.com','$2a$12$N7LwWMO4AElSV1C3.W3PzeuMtMHl0unJ1C0eBxX0YZcumgRV7ch0u'),(1030181,'riddhi.sohampaul@gmail.com','$2a$12$N7LwWMO4AElSV1C3.W3PzeuMtMHl0unJ1C0eBxX0YZcumgRV7ch0u'),(1030191,'surya.sahu26@gmail.com','$2a$12$N7LwWMO4AElSV1C3.W3PzeuMtMHl0unJ1C0eBxX0YZcumgRV7ch0u'),(1030192,'abhaduri99@gmail.com','$2a$12$N7LwWMO4AElSV1C3.W3PzeuMtMHl0unJ1C0eBxX0YZcumgRV7ch0u'),(1030193,'riddhi.sohampaul@gmail.com','$2a$12$N7LwWMO4AElSV1C3.W3PzeuMtMHl0unJ1C0eBxX0YZcumgRV7ch0u');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_permission`
--

LOCK TABLES `emp_permission` WRITE;
/*!40000 ALTER TABLE `emp_permission` DISABLE KEYS */;
INSERT INTO `emp_permission` VALUES (1,1030181,'MANAGER'),(2,1030191,'EMPLOYEE'),(3,1030171,'TRANSPORT'),(4,1030161,'MANAGER'),(5,1030192,'EMPLOYEE'),(6,1030193,'EMPLOYEE'),(7,1030172,'TRANSPORT');
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
INSERT INTO `employee_detail` VALUES (1030161,'Manager lvl 2','abhaduri99@gmail.com',1030181,'MANAGER','BBSRSTP'),(1030171,'Transport Desk','abhaduri99.ab@gmail.com',1030172,'TRANSPORT','BBSRSTP'),(1030172,'Transport Desk','abhaduri99.ab@gmail.com',1030171,'TRANSPORT','BBSRSEZ'),(1030181,'Manager lvl 1','riddhi.sohampaul@gmail.com',1030161,'MANAGER','BBSRSTP'),(1030191,'Employee','surya.sahu92@gmail.com',1030181,'EMPLOYEE','BBSRSTP'),(1030192,'Employee','abhaduri99@gmail.com',1030181,'EMPLOYEE','BBSRSTP'),(1030193,'Employee','riddhi.sohampaul@gmail.com',1030181,'EMPLOYEE','BBSRSEZ');
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
INSERT INTO `infy_country` VALUES ('BEL','Belgium','EU'),('CAN','Canada','NA'),('DEU','Germany','EU'),('IND','India','AS'),('ITA','Italy','EU'),('USA','United States','NA');
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
INSERT INTO `infy_dc` VALUES ('BBSRSEZ','Bhubaneswar-SEZ','IND','AS','abhaduri99.ab@gmail.com'),('BBSRSTP','Bhubaneswar-STP','IND','AS','abhaduri99.ab@gmail.com'),('CANCAL','Canada-Calgary','CAN','NA','surya.sahu92@gmail.com'),('CANMONT','Canada-Montread','CAN','NA','surya.sahu92@gmail.com');
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
INSERT INTO `infy_region` VALUES ('AS','Asia'),('EU','Europe'),('NA','North America');
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
  `created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`req_id`),
  UNIQUE KEY `req_id_UNIQUE` (`req_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shuttle_request`
--

LOCK TABLES `shuttle_request` WRITE;
/*!40000 ALTER TABLE `shuttle_request` DISABLE KEYS */;
INSERT INTO `shuttle_request` VALUES ('STLREQ103016114052020F26','STL0800',1030161,1030181,'This is the next timed one.','REJECTED_TRNS','14-05-2020 08:00','BBSRSTP','BBSRSEZ','NA','Approved','No space','2020-05-13 20:48:45'),('STLREQ103016114052020F60','STL1100',1030161,1030181,'This is another timestamp test.','REJECTED_MGR','14-05-2020 11:00','BBSRSTP','BBSRSEZ','NA','Sorry','NA','2020-05-13 20:41:44'),('STLREQ103016114052020F85','STL1100',1030161,1030181,'This is timestamp testing','CANCELLED','14-05-2020 11:00','BBSRSTP','BBSRSEZ','NA','I dont want to go.','NA','2020-05-13 20:39:26'),('STLREQ103016115052020','STL1500',1030161,1030181,'qwertyrfgvcxs','PENDING','15-05-2020 15:00','BBSRSTP','BBSRSEZ','NA','NA','NA','2020-05-14 22:17:10'),('STLREQ103016115052020F26','STL0800',1030161,1030181,'This is the next timed one.','REJECTED_TRNS','15-05-2020 08:00','BBSRSTP','BBSRSEZ','NA','Approved','No space','2020-05-14 20:48:45'),('STLREQ103016115052020F60','STL1100',1030161,1030181,'This is another timestamp test.','REJECTED_MGR','15-05-2020 11:00','BBSRSTP','BBSRSEZ','NA','Sorry','NA','2020-05-14 20:41:44'),('STLREQ103016115052020F85','STL1100',1030161,1030181,'This is timestamp testing','CANCELLED','15-05-2020 11:00','BBSRSTP','BBSRSEZ','NA','I dont want to go.','NA','2020-05-14 20:39:26'),('STLREQ103018114052020F56','STL1100',1030181,1030161,'This is timestamp testing','REJECTED_TRNS','14-05-2020 11:00','BBSRSTP','BBSRSEZ','NA','Go ahead','sorry','2020-05-13 20:43:28'),('STLREQ103018115052020F29','STL1500',1030181,1030161,'Work work work','REJECTED_TRNS','15-05-2020 15:00','BBSRSTP','BBSRSEZ','NA','ok','sorry','2020-05-14 21:32:11'),('STLREQ103018115052020F56','STL1100',1030181,1030161,'This is timestamp testing','REJECTED_TRNS','15-05-2020 11:00','BBSRSTP','BBSRSEZ','NA','Go ahead','sorry','2020-05-14 20:43:28');
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
  `code` char(40) NOT NULL,
  `count` int(11) NOT NULL,
  `for_date` datetime NOT NULL,
  `max_seat` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shuttle_seating`
--

LOCK TABLES `shuttle_seating` WRITE;
/*!40000 ALTER TABLE `shuttle_seating` DISABLE KEYS */;
INSERT INTO `shuttle_seating` VALUES (1,'STL110020200514110000',60,'2020-05-14 11:00:00',60),(2,'STL150020200514150000',0,'2020-05-14 15:00:00',40),(3,'STL080020200514080000',39,'2020-05-14 08:00:00',40),(4,'STL1100',58,'2020-05-15 11:00:00',60),(5,'STL1500',40,'2020-05-15 15:00:00',40),(6,'STL0800',40,'2020-05-15 08:00:00',40);
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

-- Dump completed on 2020-05-15  3:51:39
