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
-- Table structure for table `fir_admin`
--

DROP TABLE IF EXISTS `fir_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fir_admin` (
  `FIRNo` int NOT NULL,
  `AdminUser` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`FIRNo`),
  KEY `AdminUser` (`AdminUser`),
  CONSTRAINT `fir_admin_ibfk_1` FOREIGN KEY (`FIRNo`) REFERENCES `fir` (`FIRNo`),
  CONSTRAINT `fir_admin_ibfk_2` FOREIGN KEY (`AdminUser`) REFERENCES `admin` (`AdminUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fir_admin`
--

LOCK TABLES `fir_admin` WRITE;
/*!40000 ALTER TABLE `fir_admin` DISABLE KEYS */;
INSERT INTO `fir_admin` VALUES (249,'admin_1'),(250,'admin_1'),(253,'admin_1'),(254,'admin_1'),(257,'admin_1'),(258,'admin_1'),(259,'admin_1'),(260,'admin_1'),(262,'admin_1'),(266,'admin_1'),(267,'admin_1'),(269,'admin_1'),(270,'admin_1'),(271,'admin_1'),(280,'admin_1'),(285,'admin_1'),(289,'admin_1'),(291,'admin_1'),(292,'admin_1'),(295,'admin_1'),(297,'admin_1'),(298,'admin_1'),(302,'admin_1'),(306,'admin_1'),(308,'admin_1'),(309,'admin_1'),(310,'admin_1'),(314,'admin_1'),(315,'admin_1'),(318,'admin_1'),(321,'admin_1'),(325,'admin_1'),(328,'admin_1'),(329,'admin_1'),(332,'admin_1'),(337,'admin_1'),(342,'admin_1'),(343,'admin_1'),(344,'admin_1'),(345,'admin_1'),(346,'admin_1'),(350,'admin_1'),(351,'admin_1'),(352,'admin_1'),(355,'admin_1'),(356,'admin_1'),(359,'admin_1'),(360,'admin_1'),(361,'admin_1'),(363,'admin_1'),(364,'admin_1'),(365,'admin_1'),(366,'admin_1'),(367,'admin_1'),(368,'admin_1'),(369,'admin_1'),(370,'admin_1'),(371,'admin_1'),(372,'admin_1'),(375,'admin_1'),(377,'admin_1'),(379,'admin_1'),(380,'admin_1'),(381,'admin_1'),(382,'admin_1'),(383,'admin_1'),(384,'admin_1'),(387,'admin_1'),(388,'admin_1'),(390,'admin_1'),(392,'admin_1'),(394,'admin_1'),(396,'admin_1'),(398,'admin_1'),(399,'admin_1'),(402,'admin_1'),(404,'admin_1'),(405,'admin_1'),(407,'admin_1'),(408,'admin_1'),(409,'admin_1'),(413,'admin_1'),(414,'admin_1'),(416,'admin_1'),(423,'admin_1'),(424,'admin_1'),(426,'admin_1'),(429,'admin_1'),(432,'admin_1'),(440,'admin_1'),(441,'admin_1'),(443,'admin_1'),(449,'admin_1'),(450,'admin_1'),(453,'admin_1'),(455,'admin_1'),(458,'admin_1'),(459,'admin_1'),(460,'admin_1'),(461,'admin_1'),(462,'admin_1'),(465,'admin_1'),(468,'admin_1'),(469,'admin_1'),(471,'admin_1'),(251,'admin_2'),(252,'admin_2'),(255,'admin_2'),(256,'admin_2'),(261,'admin_2'),(263,'admin_2'),(264,'admin_2'),(265,'admin_2'),(268,'admin_2'),(272,'admin_2'),(273,'admin_2'),(274,'admin_2'),(275,'admin_2'),(276,'admin_2'),(277,'admin_2'),(278,'admin_2'),(279,'admin_2'),(281,'admin_2'),(282,'admin_2'),(283,'admin_2'),(284,'admin_2'),(286,'admin_2'),(287,'admin_2'),(288,'admin_2'),(290,'admin_2'),(293,'admin_2'),(294,'admin_2'),(296,'admin_2'),(299,'admin_2'),(300,'admin_2'),(301,'admin_2'),(303,'admin_2'),(304,'admin_2'),(305,'admin_2'),(307,'admin_2'),(311,'admin_2'),(312,'admin_2'),(313,'admin_2'),(316,'admin_2'),(317,'admin_2'),(319,'admin_2'),(320,'admin_2'),(322,'admin_2'),(323,'admin_2'),(324,'admin_2'),(326,'admin_2'),(327,'admin_2'),(330,'admin_2'),(331,'admin_2'),(333,'admin_2'),(334,'admin_2'),(335,'admin_2'),(336,'admin_2'),(338,'admin_2'),(339,'admin_2'),(340,'admin_2'),(341,'admin_2'),(347,'admin_2'),(348,'admin_2'),(349,'admin_2'),(353,'admin_2'),(354,'admin_2'),(357,'admin_2'),(358,'admin_2'),(362,'admin_2'),(373,'admin_2'),(374,'admin_2'),(376,'admin_2'),(378,'admin_2'),(385,'admin_2'),(386,'admin_2'),(389,'admin_2'),(391,'admin_2'),(393,'admin_2'),(395,'admin_2'),(397,'admin_2'),(400,'admin_2'),(401,'admin_2'),(403,'admin_2'),(406,'admin_2'),(410,'admin_2'),(411,'admin_2'),(412,'admin_2'),(415,'admin_2'),(417,'admin_2'),(418,'admin_2'),(419,'admin_2'),(420,'admin_2'),(421,'admin_2'),(422,'admin_2'),(425,'admin_2'),(427,'admin_2'),(428,'admin_2'),(430,'admin_2'),(431,'admin_2'),(433,'admin_2'),(434,'admin_2'),(435,'admin_2'),(436,'admin_2'),(437,'admin_2'),(438,'admin_2'),(439,'admin_2'),(442,'admin_2'),(444,'admin_2'),(445,'admin_2'),(446,'admin_2'),(447,'admin_2'),(448,'admin_2'),(451,'admin_2'),(452,'admin_2'),(454,'admin_2'),(456,'admin_2'),(457,'admin_2'),(463,'admin_2'),(464,'admin_2'),(466,'admin_2'),(467,'admin_2'),(470,'admin_2');
/*!40000 ALTER TABLE `fir_admin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-24 23:52:25
