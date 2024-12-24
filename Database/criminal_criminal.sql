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
-- Table structure for table `criminal`
--

DROP TABLE IF EXISTS `criminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criminal` (
  `CriminalNo` int NOT NULL AUTO_INCREMENT,
  `Aadhaar` varchar(12) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Aliase` varchar(100) DEFAULT NULL,
  `Gender` enum('Male','Female','Other') DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CriminalNo`),
  UNIQUE KEY `idx_aadhaar` (`Aadhaar`)
) ENGINE=InnoDB AUTO_INCREMENT=568 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criminal`
--

LOCK TABLES `criminal` WRITE;
/*!40000 ALTER TABLE `criminal` DISABLE KEYS */;
INSERT INTO `criminal` VALUES (1,'123456789022','John Doe','Johnny','Male','123 Main St, City, Country'),(2,'234567890123','Jane Smith','Janey','Female','234 Elm St, City, Country'),(3,'345678901244','Mike Johnson E','Mikey E','Female','345 Oak St, City, Country East'),(4,'456789012345','Sarah Brown','Sara','Female','456 Pine St, City, Country'),(5,'567890123456','David Williams','Dave','Male','567 Maple St, City, Country'),(6,'678901234567','Emily Jones','Em','Female','678 Cedar St, City, Country'),(7,'789012345678','Chris Davis','Chris','Male','789 Birch St, City, Country'),(8,'890123456789','Jessica Miller','Jess','Female','890 Spruce St, City, Country'),(9,'901234567890','Daniel Garcia','Danny','Male','901 Walnut St, City, Country'),(10,'012345678901','Amanda Martinez','Mandy','Female','012 Chestnut St, City, Country'),(11,'123456789013','Matthew Wilson','Matt','Male','123 Maple St, City, Country'),(12,'234567890124','Olivia Anderson','Liv','Female','234 Oak St, City, Country'),(13,'345678901235','William Thomas','Will','Male','345 Pine St, City, Country'),(14,'456789012346','Sophia Taylor','Sophie','Female','456 Birch St, City, Country'),(15,'567890123457','James White','Jimmy','Male','567 Cedar St, City, Country'),(16,'678901234568','Isabella Harris','Bella','Female','678 Walnut St, City, Country'),(17,'789012345679','Ethan Clark','E','Male','789 Chestnut St, City, Country'),(18,'890123456780','Mia Lewis','M','Female','890 Spruce St, City, Country'),(19,'901234567891','Alexander Robinson','Alex','Male','901 Elm St, City, Country'),(20,'012345678902','Charlotte Hall','Charlie','Female','012 Main St, City, Country'),(21,'123456789014','Benjamin Young','Ben','Male','123 Maple St, City, Country'),(22,'234567890125','Amelia King','Amy','Female','234 Oak St, City, Country'),(23,'345678901236','Lucas Wright','Luke','Male','345 Pine St, City, Country'),(24,'456789012347','Harper Scott','Har','Female','456 Birch St, City, Country'),(25,'567890123458','Henry Green','Hank','Male','567 Cedar St, City, Country'),(26,'678901234569','Evelyn Adams','Evie','Female','678 Walnut St, City, Country'),(27,'789012345680','Jacob Baker','Jake','Male','789 Chestnut St, City, Country'),(28,'890123456781','Ella Gonzalez','Ellie','Female','890 Spruce St, City, Country'),(29,'901234567892','Jackson Nelson','Jack','Male','901 Elm St, City, Country'),(30,'012345678903','Charlotte Hall','Charlie','Female','012 Main St, City, Country'),(31,'123456789015','Carter Mitchell','Cart','Male','123 Maple St, City, Country'),(32,'234567890126','Scarlett Perez','Scar','Female','234 Oak St, City, Country'),(33,'345678901237','Luke Roberts','Rob','Male','345 Pine St, City, Country'),(34,'456789012348','Chloe Turner','Chlo','Female','456 Birch St, City, Country'),(35,'567890123459','Mason Phillips','Mase','Male','567 Cedar St, City, Country'),(36,'678901234570','Luna Campbell','Lu','Female','678 Walnut St, City, Country'),(37,'789012345681','Oliver Parker','Ollie','Male','789 Chestnut St, City, Country'),(38,'890123456782','Sofia Evans','Sof','Female','890 Spruce St, City, Country'),(39,'901234567893','Jackson Edwards','Jax','Male','901 Elm St, City, Country'),(40,'012345678904','Grace Collins','Gracie','Female','012 Main St, City, Country'),(41,'123456789016','Caleb Stewart','Cale','Male','123 Maple St, City, Country'),(42,'234567890127','Victoria Sanchez','Vicky','Female','234 Oak St, City, Country'),(43,'345678901238','Elijah Morris','Eli','Male','345 Pine St, City, Country'),(44,'456789012349','Zoe Rogers','Zo','Female','456 Birch St, City, Country'),(45,'567890123460','Gabriel Reed','Gabe','Male','567 Cedar St, City, Country'),(46,'678901234571','Audrey Cook','Aud','Female','678 Walnut St, City, Country'),(47,'789012345682','Nathan Morgan','Nate','Male','789 Chestnut St, City, Country'),(48,'890123456783','Lily Bell','Lil','Female','890 Spruce St, City, Country'),(49,'901234567894','Christopher Bailey','Chris','Male','901 Elm St, City, Country'),(50,'012345678905','Madison Rivera','Maddie','Female','012 Main St, City, Country'),(56,'123456789076','abb','','Male','1234'),(57,'998877665544','ert','','Female','56789'),(64,'123456789101','name','dakd','Male','djemdl'),(567,'123456789012','jagga','','Male','1234');
/*!40000 ALTER TABLE `criminal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-24 23:52:18
