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
-- Table structure for table `fir_operator`
--

DROP TABLE IF EXISTS `fir_operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fir_operator` (
  `FIRNo` int NOT NULL,
  `OperatorUser` varchar(50) NOT NULL,
  PRIMARY KEY (`FIRNo`,`OperatorUser`),
  KEY `fir_operator_ibfk_2` (`OperatorUser`),
  CONSTRAINT `fir_operator_ibfk_1` FOREIGN KEY (`FIRNo`) REFERENCES `fir` (`FIRNo`),
  CONSTRAINT `fir_operator_ibfk_2` FOREIGN KEY (`OperatorUser`) REFERENCES `operator` (`OperatorUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fir_operator`
--

LOCK TABLES `fir_operator` WRITE;
/*!40000 ALTER TABLE `fir_operator` DISABLE KEYS */;
INSERT INTO `fir_operator` VALUES (249,'Operator_1'),(273,'Operator_1'),(297,'Operator_1'),(321,'Operator_1'),(345,'Operator_1'),(369,'Operator_1'),(393,'Operator_1'),(417,'Operator_1'),(441,'Operator_1'),(465,'Operator_1'),(258,'Operator_10'),(282,'Operator_10'),(306,'Operator_10'),(330,'Operator_10'),(354,'Operator_10'),(378,'Operator_10'),(402,'Operator_10'),(426,'Operator_10'),(450,'Operator_10'),(259,'Operator_11'),(283,'Operator_11'),(307,'Operator_11'),(331,'Operator_11'),(355,'Operator_11'),(379,'Operator_11'),(403,'Operator_11'),(427,'Operator_11'),(451,'Operator_11'),(260,'Operator_12'),(284,'Operator_12'),(308,'Operator_12'),(332,'Operator_12'),(356,'Operator_12'),(380,'Operator_12'),(404,'Operator_12'),(428,'Operator_12'),(452,'Operator_12'),(261,'Operator_13'),(285,'Operator_13'),(309,'Operator_13'),(333,'Operator_13'),(357,'Operator_13'),(381,'Operator_13'),(405,'Operator_13'),(429,'Operator_13'),(453,'Operator_13'),(262,'Operator_14'),(286,'Operator_14'),(310,'Operator_14'),(334,'Operator_14'),(358,'Operator_14'),(382,'Operator_14'),(406,'Operator_14'),(430,'Operator_14'),(454,'Operator_14'),(263,'Operator_15'),(287,'Operator_15'),(311,'Operator_15'),(335,'Operator_15'),(359,'Operator_15'),(383,'Operator_15'),(407,'Operator_15'),(431,'Operator_15'),(455,'Operator_15'),(264,'Operator_16'),(288,'Operator_16'),(312,'Operator_16'),(336,'Operator_16'),(360,'Operator_16'),(384,'Operator_16'),(408,'Operator_16'),(432,'Operator_16'),(456,'Operator_16'),(265,'Operator_17'),(289,'Operator_17'),(313,'Operator_17'),(337,'Operator_17'),(361,'Operator_17'),(385,'Operator_17'),(409,'Operator_17'),(433,'Operator_17'),(457,'Operator_17'),(266,'Operator_18'),(290,'Operator_18'),(314,'Operator_18'),(338,'Operator_18'),(362,'Operator_18'),(386,'Operator_18'),(410,'Operator_18'),(434,'Operator_18'),(458,'Operator_18'),(267,'Operator_19'),(291,'Operator_19'),(315,'Operator_19'),(339,'Operator_19'),(363,'Operator_19'),(387,'Operator_19'),(411,'Operator_19'),(435,'Operator_19'),(459,'Operator_19'),(250,'Operator_2'),(274,'Operator_2'),(298,'Operator_2'),(322,'Operator_2'),(346,'Operator_2'),(370,'Operator_2'),(394,'Operator_2'),(418,'Operator_2'),(442,'Operator_2'),(466,'Operator_2'),(268,'Operator_20'),(292,'Operator_20'),(316,'Operator_20'),(340,'Operator_20'),(364,'Operator_20'),(388,'Operator_20'),(412,'Operator_20'),(436,'Operator_20'),(460,'Operator_20'),(269,'Operator_21'),(293,'Operator_21'),(317,'Operator_21'),(341,'Operator_21'),(365,'Operator_21'),(389,'Operator_21'),(413,'Operator_21'),(437,'Operator_21'),(461,'Operator_21'),(270,'Operator_22'),(294,'Operator_22'),(318,'Operator_22'),(342,'Operator_22'),(366,'Operator_22'),(390,'Operator_22'),(414,'Operator_22'),(438,'Operator_22'),(462,'Operator_22'),(271,'Operator_23'),(295,'Operator_23'),(319,'Operator_23'),(343,'Operator_23'),(367,'Operator_23'),(391,'Operator_23'),(415,'Operator_23'),(439,'Operator_23'),(463,'Operator_23'),(272,'Operator_24'),(296,'Operator_24'),(320,'Operator_24'),(344,'Operator_24'),(368,'Operator_24'),(392,'Operator_24'),(416,'Operator_24'),(440,'Operator_24'),(464,'Operator_24'),(251,'Operator_3'),(275,'Operator_3'),(299,'Operator_3'),(323,'Operator_3'),(347,'Operator_3'),(371,'Operator_3'),(395,'Operator_3'),(419,'Operator_3'),(443,'Operator_3'),(467,'Operator_3'),(252,'Operator_4'),(276,'Operator_4'),(300,'Operator_4'),(324,'Operator_4'),(348,'Operator_4'),(372,'Operator_4'),(396,'Operator_4'),(420,'Operator_4'),(444,'Operator_4'),(468,'Operator_4'),(253,'Operator_5'),(277,'Operator_5'),(301,'Operator_5'),(325,'Operator_5'),(349,'Operator_5'),(373,'Operator_5'),(397,'Operator_5'),(421,'Operator_5'),(445,'Operator_5'),(469,'Operator_5'),(254,'Operator_6'),(278,'Operator_6'),(302,'Operator_6'),(326,'Operator_6'),(350,'Operator_6'),(374,'Operator_6'),(398,'Operator_6'),(422,'Operator_6'),(446,'Operator_6'),(470,'Operator_6'),(255,'Operator_7'),(279,'Operator_7'),(303,'Operator_7'),(327,'Operator_7'),(351,'Operator_7'),(375,'Operator_7'),(399,'Operator_7'),(423,'Operator_7'),(447,'Operator_7'),(471,'Operator_7'),(256,'Operator_8'),(280,'Operator_8'),(304,'Operator_8'),(328,'Operator_8'),(352,'Operator_8'),(376,'Operator_8'),(400,'Operator_8'),(424,'Operator_8'),(448,'Operator_8'),(257,'Operator_9'),(281,'Operator_9'),(305,'Operator_9'),(329,'Operator_9'),(353,'Operator_9'),(377,'Operator_9'),(401,'Operator_9'),(425,'Operator_9'),(449,'Operator_9');
/*!40000 ALTER TABLE `fir_operator` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-24 23:52:22
