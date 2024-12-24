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
-- Table structure for table `operator`
--

DROP TABLE IF EXISTS `operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operator` (
  `OperatorUser` varchar(50) NOT NULL,
  `OperatorName` varchar(100) NOT NULL,
  `OperatorPass` varchar(255) NOT NULL,
  `AdminUser` varchar(50) NOT NULL,
  PRIMARY KEY (`OperatorUser`),
  KEY `AdminUser` (`AdminUser`),
  CONSTRAINT `operator_ibfk_1` FOREIGN KEY (`AdminUser`) REFERENCES `login` (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operator`
--

LOCK TABLES `operator` WRITE;
/*!40000 ALTER TABLE `operator` DISABLE KEYS */;
INSERT INTO `operator` VALUES ('operator_1','Operator One','pass@op1','admin_1'),('operator_10','Operator Ten','password','admin_1'),('operator_11','Operator Eleven','pass@op11','admin_1'),('operator_12','Operator Twelve','pass@op12','admin_1'),('operator_13','Operator Thirteen','pass@op13','admin_1'),('operator_14','Operator Fourteen','pass@op14','admin_2'),('operator_15','Operator Fifteen','pass@op15','admin_2'),('operator_16','Operator Sixteen','pass@op16','admin_2'),('operator_17','Operator Seventeen','pass@op17','admin_2'),('operator_18','Operator Eighteen','pass@op18','admin_2'),('operator_19','Operator Nineteen','pass@op19','admin_2'),('operator_2','Operator Two','pass@op2','admin_1'),('operator_20','Operator Twenty','pass@op20','admin_2'),('operator_21','Operator Twenty-One','pass@op21','admin_2'),('operator_22','Operator Twenty-Two','pass@op22','admin_2'),('operator_23','Operator Twenty-Three','pass@op23','admin_2'),('operator_24','Operator Twenty-Four','pass@op24','admin_2'),('operator_3','Operator Three','pass@op3','admin_1'),('operator_4','Operator Four','pass@op4','admin_1'),('operator_5','Operator Five','pass@op5','admin_1'),('operator_6','Operator Six','pass@op6','admin_1'),('operator_7','Operator Seven','pass@op7','admin_1'),('operator_8','Operator Eight','pass@op8','admin_1'),('operator_9','Operator Nine','pass@op9','admin_1');
/*!40000 ALTER TABLE `operator` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-24 23:52:27
