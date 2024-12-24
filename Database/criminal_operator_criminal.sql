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
-- Table structure for table `operator_criminal`
--

DROP TABLE IF EXISTS `operator_criminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operator_criminal` (
  `CriminalNo` int NOT NULL,
  `OperatorUser` varchar(50) DEFAULT NULL,
  `CriminalAadhar` char(12) DEFAULT NULL,
  PRIMARY KEY (`CriminalNo`),
  KEY `operator_criminal_ibfk_2` (`OperatorUser`),
  KEY `fk_CriminalAadhar1` (`CriminalAadhar`),
  CONSTRAINT `fk_CriminalAadhar1` FOREIGN KEY (`CriminalAadhar`) REFERENCES `criminal` (`Aadhaar`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_CriminalNo` FOREIGN KEY (`CriminalNo`) REFERENCES `criminal` (`CriminalNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operator_criminal`
--

LOCK TABLES `operator_criminal` WRITE;
/*!40000 ALTER TABLE `operator_criminal` DISABLE KEYS */;
INSERT INTO `operator_criminal` VALUES (1,'operator_4','123456789022'),(2,'operator_4','234567890123'),(3,'operator_16','345678901244'),(4,'operator_5','456789012345'),(5,'operator_7','567890123456'),(6,'operator_13','678901234567'),(7,'operator_10','789012345678'),(8,'operator_1','890123456789'),(9,'operator_6','901234567890'),(10,'operator_17','012345678901'),(11,'operator_11','123456789013'),(12,'operator_4','234567890124'),(13,'operator_10','345678901235'),(14,'operator_15','456789012346'),(15,'operator_19','567890123457'),(16,'operator_7','678901234568'),(17,'operator_17','789012345679'),(18,'operator_22','890123456780'),(19,'operator_3','901234567891'),(20,'operator_10','012345678902'),(21,'operator_6','123456789014'),(22,'operator_8','234567890125'),(23,'operator_6','345678901236'),(24,'operator_10','456789012347'),(25,'operator_13','567890123458'),(26,'operator_22','678901234569'),(27,'operator_4','789012345680'),(28,'operator_10','890123456781'),(29,'operator_22','901234567892'),(30,'operator_21','012345678903'),(31,'operator_21','123456789015'),(32,'operator_1','234567890126'),(33,'operator_22','345678901237'),(34,'operator_4','456789012348'),(35,'operator_15','567890123459'),(36,'operator_12','678901234570'),(37,'operator_17','789012345681'),(38,'operator_9','890123456782'),(39,'operator_23','901234567893'),(40,'operator_1','012345678904'),(41,'operator_14','123456789016'),(42,'operator_7','234567890127'),(43,'operator_19','345678901238'),(44,'operator_14','456789012349'),(45,'operator_2','567890123460'),(46,'operator_19','678901234571'),(47,'operator_13','789012345682'),(48,'operator_16','890123456783'),(49,'operator_23','901234567894'),(50,'operator_17','012345678905'),(56,'operator_1','123456789076'),(57,'operator_1','998877665544'),(64,'operator_11','123456789101'),(567,'operator_20','123456789012');
/*!40000 ALTER TABLE `operator_criminal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-24 23:52:31
