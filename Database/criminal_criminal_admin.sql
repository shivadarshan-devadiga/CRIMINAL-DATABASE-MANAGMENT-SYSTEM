-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: criminal
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `criminal_admin`
--

DROP TABLE IF EXISTS `criminal_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criminal_admin` (
  `CriminalNo` int NOT NULL,
  `AdminUser` varchar(50) DEFAULT NULL,
  `CriminalAadhar` char(12) DEFAULT NULL,
  PRIMARY KEY (`CriminalNo`),
  KEY `AdminUser` (`AdminUser`),
  KEY `fk_criminalAadhar` (`CriminalAadhar`),
  CONSTRAINT `criminal_admin_ibfk_1` FOREIGN KEY (`CriminalNo`) REFERENCES `criminal` (`CriminalNo`),
  CONSTRAINT `criminal_admin_ibfk_2` FOREIGN KEY (`AdminUser`) REFERENCES `admin` (`AdminUser`),
  CONSTRAINT `fk_criminalAadhar` FOREIGN KEY (`CriminalAadhar`) REFERENCES `criminal` (`Aadhaar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criminal_admin`
--

LOCK TABLES `criminal_admin` WRITE;
/*!40000 ALTER TABLE `criminal_admin` DISABLE KEYS */;
INSERT INTO `criminal_admin` VALUES (1,'admin_1','123456789022'),(2,'admin_2','234567890123'),(3,'admin_1','345678901244'),(4,'admin_1','456789012345'),(5,'admin_1','567890123456'),(6,'admin_1','678901234567'),(7,'admin_2','789012345678'),(8,'admin_2','890123456789'),(9,'admin_1','901234567890'),(10,'admin_1','012345678901'),(11,'admin_1','123456789013'),(12,'admin_2','234567890124'),(13,'admin_1','345678901235'),(14,'admin_1','456789012346'),(15,'admin_1','567890123457'),(16,'admin_2','678901234568'),(17,'admin_2','789012345679'),(18,'admin_2','890123456780'),(19,'admin_1','901234567891'),(20,'admin_1','012345678902'),(21,'admin_2','123456789014'),(22,'admin_1','234567890125'),(23,'admin_2','345678901236'),(24,'admin_2','456789012347'),(25,'admin_2','567890123458'),(26,'admin_2','678901234569'),(27,'admin_2','789012345680'),(28,'admin_2','890123456781'),(29,'admin_1','901234567892'),(30,'admin_2','012345678903'),(31,'admin_1','123456789015'),(32,'admin_2','234567890126'),(33,'admin_2','345678901237'),(34,'admin_1','456789012348'),(35,'admin_1','567890123459'),(36,'admin_1','678901234570'),(37,'admin_2','789012345681'),(38,'admin_1','890123456782'),(39,'admin_1','901234567893'),(40,'admin_2','012345678904'),(41,'admin_1','123456789016'),(42,'admin_1','234567890127'),(43,'admin_2','345678901238'),(44,'admin_2','456789012349'),(45,'admin_2','567890123460'),(46,'admin_1','678901234571'),(47,'admin_2','789012345682'),(48,'admin_2','890123456783'),(49,'admin_1','901234567894'),(50,'admin_1','012345678905'),(56,'admin_2','123456789076'),(57,'admin_2','998877665544'),(64,'admin_2','123456789101'),(567,'admin_1','123456789012');
/*!40000 ALTER TABLE `criminal_admin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-24 23:52:33
