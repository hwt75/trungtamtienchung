-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: web_dam_hoi
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `email` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('hoangminh','1'),('huutuan','1'),('nguyenlong','1'),('tmtuan','1'),('truonglong','1');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billexport`
--

DROP TABLE IF EXISTS `billexport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billexport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `note` text,
  `total` int NOT NULL,
  `created_at` datetime NOT NULL,
  `id_staff_created` int NOT NULL,
  `updated_at` datetime NOT NULL,
  `id_staff_updated` int NOT NULL,
  `id_custom` int NOT NULL,
  `id_carrier` int NOT NULL,
  `time_start_borrowed` date NOT NULL,
  `time_end_borrowed` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_custom` (`id_custom`),
  KEY `id_staff_created` (`id_staff_created`),
  KEY `id_staff_updated` (`id_staff_updated`),
  KEY `id_carrier` (`id_carrier`),
  CONSTRAINT `billexport_ibfk_2` FOREIGN KEY (`id_staff_created`) REFERENCES `staff` (`id`),
  CONSTRAINT `billexport_ibfk_3` FOREIGN KEY (`id_staff_updated`) REFERENCES `staff` (`id`),
  CONSTRAINT `billexport_ibfk_4` FOREIGN KEY (`id_carrier`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billexport`
--

LOCK TABLES `billexport` WRITE;
/*!40000 ALTER TABLE `billexport` DISABLE KEYS */;
INSERT INTO `billexport` VALUES (15,'',63000,'2023-07-19 09:57:27',1,'2023-08-07 11:05:56',14,3,15,'2023-05-05','2023-06-06'),(16,NULL,172000,'2023-07-19 09:58:48',1,'2023-07-19 09:59:29',1,3,15,'2023-06-06','2023-05-05'),(17,NULL,100000,'2023-07-19 10:02:27',1,'2023-07-19 10:02:50',1,3,15,'2023-06-06','2023-05-05');
/*!40000 ALTER TABLE `billexport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billexport_detail`
--

DROP TABLE IF EXISTS `billexport_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billexport_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_product` int NOT NULL,
  `id_billexport` int NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_billexport` (`id_billexport`),
  KEY `id_product` (`id_product`),
  CONSTRAINT `billexport_detail_ibfk_1` FOREIGN KEY (`id_billexport`) REFERENCES `billexport` (`id`),
  CONSTRAINT `billexport_detail_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billexport_detail`
--

LOCK TABLES `billexport_detail` WRITE;
/*!40000 ALTER TABLE `billexport_detail` DISABLE KEYS */;
INSERT INTO `billexport_detail` VALUES (7,12,15,1),(8,13,15,1),(9,14,15,1),(10,13,16,4),(11,14,16,4),(12,15,17,2);
/*!40000 ALTER TABLE `billexport_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billimport`
--

DROP TABLE IF EXISTS `billimport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billimport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `total` int NOT NULL,
  `created_at` datetime NOT NULL,
  `id_staff_created` int NOT NULL,
  `updated_at` datetime NOT NULL,
  `id_staff_updated` int NOT NULL,
  `id_supplier` int NOT NULL,
  `id_carrier` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_supplier` (`id_supplier`),
  KEY `id_staff_created` (`id_staff_created`),
  KEY `id_staff_updated` (`id_staff_updated`),
  KEY `id_carrier` (`id_carrier`),
  CONSTRAINT `billimport_ibfk_1` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id`),
  CONSTRAINT `billimport_ibfk_2` FOREIGN KEY (`id_staff_created`) REFERENCES `staff` (`id`),
  CONSTRAINT `billimport_ibfk_3` FOREIGN KEY (`id_staff_updated`) REFERENCES `staff` (`id`),
  CONSTRAINT `billimport_ibfk_4` FOREIGN KEY (`id_carrier`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billimport`
--

LOCK TABLES `billimport` WRITE;
/*!40000 ALTER TABLE `billimport` DISABLE KEYS */;
INSERT INTO `billimport` VALUES (7,115000,'2023-07-19 09:55:04',1,'2023-07-19 09:55:54',1,3,15),(8,180000,'2023-07-19 09:56:16',1,'2023-07-19 09:56:57',1,4,15);
/*!40000 ALTER TABLE `billimport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billimport_detail`
--

DROP TABLE IF EXISTS `billimport_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billimport_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_billimport` int NOT NULL,
  `id_product` int NOT NULL,
  `price` int NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_billimport` (`id_billimport`),
  KEY `id_product` (`id_product`),
  CONSTRAINT `billimport_detail_ibfk_1` FOREIGN KEY (`id_billimport`) REFERENCES `billimport` (`id`),
  CONSTRAINT `billimport_detail_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billimport_detail`
--

LOCK TABLES `billimport_detail` WRITE;
/*!40000 ALTER TABLE `billimport_detail` DISABLE KEYS */;
INSERT INTO `billimport_detail` VALUES (9,7,12,10000,4),(10,7,13,15000,5),(11,8,14,20000,5),(12,8,15,40000,2);
/*!40000 ALTER TABLE `billimport_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Vacxin'),(2,'Thuốc');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custom`
--

DROP TABLE IF EXISTS `custom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) NOT NULL,
  `gender` varchar(25) NOT NULL,
  `email` varchar(250) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL,
  `address` varchar(250) NOT NULL,
  `created_at` datetime NOT NULL,
  `id_staff_created` int NOT NULL,
  `updated_at` datetime NOT NULL,
  `id_staff_updated` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_staff_created` (`id_staff_created`),
  KEY `id_staff_updated` (`id_staff_updated`),
  CONSTRAINT `custom_ibfk_1` FOREIGN KEY (`id_staff_created`) REFERENCES `staff` (`id`),
  CONSTRAINT `custom_ibfk_2` FOREIGN KEY (`id_staff_updated`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom`
--

LOCK TABLES `custom` WRITE;
/*!40000 ALTER TABLE `custom` DISABLE KEYS */;
INSERT INTO `custom` VALUES (3,'Nguyễn Phong','Nam','nguyenphong','213123213','bách khoa','2023-07-18 05:42:22',1,'2023-07-18 05:42:22',1),(4,'Hữu Long','Nam','huulong','123213123','hà nội','2023-07-18 05:42:40',1,'2023-07-18 05:42:40',1),(5,'văn đại','Nam','vandai','213123123','hà nội','2023-07-18 05:43:14',1,'2023-07-18 05:43:14',1),(6,'Văn long','Nam','vanlong@gmail.com','021398723','nam định ','2023-08-07 10:35:48',1,'2023-08-07 10:35:48',1),(7,'Lâm Tùng','Nam','lamtung@gmail.com','091238122','Thái Bình','2023-08-07 10:36:18',1,'2023-08-07 10:36:18',1);
/*!40000 ALTER TABLE `custom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `title` varchar(250) NOT NULL,
  `description` varchar(500) NOT NULL,
  `linkimage` text NOT NULL,
  `price` int NOT NULL,
  `count` int NOT NULL,
  `created_at` datetime NOT NULL,
  `id_staff_created` int NOT NULL,
  `updated_at` datetime NOT NULL,
  `id_staff_updated` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_staff_created` (`id_staff_created`),
  KEY `id_staff_updated` (`id_staff_updated`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id_staff_created`) REFERENCES `staff` (`id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`id_staff_updated`) REFERENCES `staff` (`id`),
  CONSTRAINT `product_ibfk_3` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (12,1,'covid 1','Vaxcin điều trị covid 19','C:\\Users\\JARVIS\\Desktop\\anh1.jpg',20000,3,'2023-07-19 09:52:20',1,'2023-08-07 10:40:25',1),(13,1,'nanocovax','vacxin 2','C:\\Users\\JARVIS\\Desktop\\anh2.jpg',20000,0,'2023-07-19 09:52:48',1,'2023-08-07 10:40:46',1),(14,1,'vaxin sởi','chuyên điều trị bệnh sởi ','C:\\Users\\JARVIS\\Desktop\\anh3.jpg',23000,0,'2023-07-19 09:53:14',1,'2023-08-07 10:41:24',1),(15,2,'Thuốc bổ máu','thuốc','C:\\Users\\JARVIS\\Desktop\\phong1.jpg',50000,0,'2023-07-19 09:53:41',1,'2023-08-07 10:45:11',1),(16,2,'thuốc chống viêm ','thuốc dặc trị ','C:\\Users\\JARVIS\\Desktop\\phong2.jpg',60000,0,'2023-07-19 09:54:06',1,'2023-08-07 10:45:30',1),(17,1,'vaxin ho lao','vacxin chống ho lao','C:\\Users\\JARVIS\\Desktop\\anh2.jpg',30000,0,'2023-07-20 10:34:10',1,'2023-08-07 10:45:58',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Bác sĩ quản lý'),(2,'Bác sĩ tiếp tân '),(3,'Nhân viên kho'),(4,'Bác sĩ điều trị ');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) NOT NULL,
  `gender` varchar(25) NOT NULL,
  `email` varchar(250) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL,
  `address` varchar(250) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `role_id` int NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `email` (`email`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `staff_ibfk_2` FOREIGN KEY (`email`) REFERENCES `account` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'tran tuan','nam','tmtuan','012345565','hà nội','2023-06-03 00:00:00','2023-06-03 00:00:00',1,'Đang làm'),(12,'Hữu Tuấn','Nam','huutuan','012321324','hà nội','2023-07-18 05:34:01','2023-07-18 05:34:01',1,'Đang làm'),(13,'Nguyễn Long','Nam','nguyenlong','3423425342','bách khoa','2023-07-18 05:34:30','2023-07-18 05:34:30',2,'Đang làm'),(14,'Hoàng Minh','Nam','hoangminh','123213124','hai bà trưng','2023-07-18 05:39:56','2023-07-18 05:39:56',3,'Đang làm'),(15,'Trương Long','Nam','truonglong','213123124','nội bài','2023-07-18 05:40:30','2023-07-18 05:40:30',4,'Đang làm');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phoneNumber` int NOT NULL,
  `email` varchar(250) NOT NULL,
  `address` varchar(500) NOT NULL,
  `created_at` datetime NOT NULL,
  `id_staff_created` int NOT NULL,
  `updated_at` datetime NOT NULL,
  `id_staff_updated` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_staff_created` (`id_staff_created`),
  KEY `id_staff_updated` (`id_staff_updated`),
  CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`id_staff_created`) REFERENCES `staff` (`id`),
  CONSTRAINT `supplier_ibfk_2` FOREIGN KEY (`id_staff_updated`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (3,'vaxin vietnam',123123124,'vietnam@gmail.com','hai bà trưng','2023-07-18 05:41:27',1,'2023-08-07 10:38:29',1),(4,'Dược phẩm halico',12321321,'halico@gmail.com','bách khoa','2023-07-18 05:41:49',1,'2023-08-07 10:39:10',1),(5,'Dược phẩm xiaomi',123123124,'xiaomi@gmail.com','hai bà trưng','2023-08-07 10:37:32',1,'2023-08-07 10:39:40',1),(6,'vacxin vinaxin',123123124,'vacxin@gmail.com','hai bà trưng','2023-08-07 10:37:54',1,'2023-08-07 10:37:54',1),(7,'Dược phẩm Tâm Bình',12321321,'tambinh@gmail.com','bách khoa','2023-08-07 10:38:46',1,'2023-08-07 10:38:46',1);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-08 13:39:27
