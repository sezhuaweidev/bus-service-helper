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
  `emp_id` int(11) NOT NULL,
  `emp_name` varchar(80) NOT NULL,
  `emp_email` varchar(100) NOT NULL,
  `emp_manager_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_detail`
--

LOCK TABLES `employee_detail` WRITE;
/*!40000 ALTER TABLE `employee_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_detail` ENABLE KEYS */;
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
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `infy_dc`
--

LOCK TABLES `infy_dc` WRITE;
/*!40000 ALTER TABLE `infy_dc` DISABLE KEYS */;
INSERT INTO `infy_dc` VALUES ('BBSRSEZ','Bhubaneswar-SEZ'),('BBSRSTP','Bhubaneswar-STP');
/*!40000 ALTER TABLE `infy_dc` ENABLE KEYS */;
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
  `status` enum('PENDING','APPROVED','REJECTED') NOT NULL,
  `for_date` varchar(20) NOT NULL,
  PRIMARY KEY (`req_id`),
  UNIQUE KEY `req_id_UNIQUE` (`req_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shuttle_request`
--

LOCK TABLES `shuttle_request` WRITE;
/*!40000 ALTER TABLE `shuttle_request` DISABLE KEYS */;
INSERT INTO `shuttle_request` VALUES ('STLREQ12345607042020','STL0800',123456,343123,'I will sleep for the whole day','APPROVED','07-08-2010');
/*!40000 ALTER TABLE `shuttle_request` ENABLE KEYS */;
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

-- Dump completed on 2020-04-07 13:00:09
