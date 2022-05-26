-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlyktx
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethoadon` (
  `id_CTHD` int NOT NULL AUTO_INCREMENT,
  `chisotieuthu` int DEFAULT NULL,
  `thanhtien` double DEFAULT NULL,
  `id_dichvu` int DEFAULT NULL,
  `id_hoadon` int DEFAULT NULL,
  PRIMARY KEY (`id_CTHD`),
  KEY `id_cthd_dicvu_idx` (`id_dichvu`),
  KEY `id_cthd_hoadon_idx` (`id_hoadon`),
  CONSTRAINT `id_cthd_dicvu` FOREIGN KEY (`id_dichvu`) REFERENCES `dichvudiennuoc` (`id_dichvu`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_cthd_hoadon` FOREIGN KEY (`id_hoadon`) REFERENCES `hoadon` (`id_hoadon`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
INSERT INTO `chitiethoadon` VALUES (31,12,42000,1,17),(32,3,12000,2,17),(33,11,38500,1,18),(34,12,48000,2,18),(41,10,35000,1,22),(42,10,40000,2,22),(45,7,24500,1,24),(46,69,276000,2,24),(47,75,262500,1,25),(48,30,120000,2,25),(49,70,245000,1,26),(50,32,128000,2,26),(51,80,280000,1,27),(52,25,100000,2,27),(53,66,231000,1,28),(54,45,180000,2,28),(55,75,262500,1,29),(56,32,128000,2,29),(57,87,304500,1,30),(58,42,168000,2,30),(59,68,238000,1,31),(60,24,96000,2,31),(61,78,273000,1,32),(62,45,180000,2,32),(63,68,238000,1,33),(64,32,128000,2,33),(65,98,343000,1,34),(66,45,180000,2,34),(67,76,266000,1,35),(68,21,84000,2,35),(69,45,157500,1,36),(70,21,84000,2,36),(71,69,241500,1,37),(72,43,172000,2,37),(73,87,304500,1,38),(74,56,224000,2,38),(75,65,227500,1,39),(76,32,128000,2,39),(77,87,304500,1,40),(78,25,100000,2,40),(79,87,304500,1,41),(80,30,120000,2,41);
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danhmucnoithat`
--

DROP TABLE IF EXISTS `danhmucnoithat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhmucnoithat` (
  `id_noithat` int NOT NULL,
  `ten` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `soluongton` int NOT NULL DEFAULT '100',
  PRIMARY KEY (`id_noithat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhmucnoithat`
--

LOCK TABLES `danhmucnoithat` WRITE;
/*!40000 ALTER TABLE `danhmucnoithat` DISABLE KEYS */;
INSERT INTO `danhmucnoithat` VALUES (1,'Giường tầng',100),(2,'Bàn học',300),(3,'Ghế',1000),(4,'Bóng đèn tròn',3000),(5,'Lavabo',500),(6,'Tủ đồ',763);
/*!40000 ALTER TABLE `danhmucnoithat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dichvudiennuoc`
--

DROP TABLE IF EXISTS `dichvudiennuoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dichvudiennuoc` (
  `id_dichvu` int NOT NULL AUTO_INCREMENT,
  `tendichvu` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dongia` double DEFAULT '3000',
  PRIMARY KEY (`id_dichvu`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dichvudiennuoc`
--

LOCK TABLES `dichvudiennuoc` WRITE;
/*!40000 ALTER TABLE `dichvudiennuoc` DISABLE KEYS */;
INSERT INTO `dichvudiennuoc` VALUES (1,'Điện',3500),(2,'Nước',4000);
/*!40000 ALTER TABLE `dichvudiennuoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (5);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `id_hoadon` int NOT NULL AUTO_INCREMENT,
  `tongtien` double DEFAULT NULL,
  `ngayxuatHD` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id_phong` varchar(5) DEFAULT NULL,
  `trangthaithanhtoan` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id_hoadon`),
  KEY `id_hoadon_phong_idx` (`id_phong`),
  CONSTRAINT `id_hoadon_phong` FOREIGN KEY (`id_phong`) REFERENCES `phong` (`id_phong`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (17,54000,'2022-05-15 21:42:34','P1003',_binary ''),(18,86500,'2022-05-15 21:45:33','P1003',_binary '\0'),(22,75000,'2022-05-17 18:26:01','P1008',_binary ''),(24,300500,'2022-05-19 10:55:32','P1008',_binary '\0'),(25,382500,'2022-05-20 15:18:33','P1001',_binary ''),(26,373000,'2022-05-20 15:18:45','P1001',_binary '\0'),(27,380000,'2022-05-20 15:19:07','P1002',_binary '\0'),(28,411000,'2022-05-20 15:19:16','P1002',_binary ''),(29,390500,'2022-05-20 15:20:20','P1012',_binary ''),(30,472500,'2022-05-20 15:20:27','P1012',_binary ''),(31,334000,'2022-05-20 15:20:45','P1010',_binary '\0'),(32,453000,'2022-05-20 15:20:52','P1010',_binary ''),(33,366000,'2022-05-20 15:21:04','P1011',_binary ''),(34,523000,'2022-05-20 15:21:21','P1006',_binary ''),(35,350000,'2022-05-20 15:21:28','P1006',_binary ''),(36,241500,'2022-05-20 15:21:45','P1005',_binary '\0'),(37,413500,'2022-05-20 15:21:54','P1005',_binary '\0'),(38,528500,'2022-05-20 15:22:05','P1004',_binary ''),(39,355500,'2022-05-20 15:22:10','P1004',_binary ''),(40,404500,'2022-05-20 15:22:25','P1007',_binary '\0'),(41,424500,'2022-05-20 15:22:33','P1009',_binary '\0');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hosochuyenphong`
--

DROP TABLE IF EXISTS `hosochuyenphong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hosochuyenphong` (
  `id_hosocp` int NOT NULL AUTO_INCREMENT,
  `mota` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ngaytao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id_user` int DEFAULT NULL,
  `trangthai` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_hosocp`),
  KEY `id_hscp_user_idx` (`id_user`),
  CONSTRAINT `id_hscp_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hosochuyenphong`
--

LOCK TABLES `hosochuyenphong` WRITE;
/*!40000 ALTER TABLE `hosochuyenphong` DISABLE KEYS */;
INSERT INTO `hosochuyenphong` VALUES (15,'','2022-05-19 13:03:14',26,_binary ''),(16,'Phòng quá ồn','2022-05-20 10:10:16',24,_binary '\0'),(17,'Các bạn chung phòng thường xuyên gây ồn, khiến e mất tập trung','2022-05-20 15:14:07',29,_binary '');
/*!40000 ALTER TABLE `hosochuyenphong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hosodangky`
--

DROP TABLE IF EXISTS `hosodangky`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hosodangky` (
  `id_hosodk` int NOT NULL AUTO_INCREMENT,
  `ngaytao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ngaynhanphong` date DEFAULT NULL,
  `ngaytraphong` date DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  `trangthai` bit(1) DEFAULT NULL,
  `mota` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_hosodk`),
  KEY `id_hsdk_user_idx` (`id_user`),
  CONSTRAINT `id_hsdk_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hosodangky`
--

LOCK TABLES `hosodangky` WRITE;
/*!40000 ALTER TABLE `hosodangky` DISABLE KEYS */;
INSERT INTO `hosodangky` VALUES (5,'2022-05-17 19:50:40','2022-05-29','2022-06-05',26,_binary '',NULL),(6,'2022-05-19 12:38:14','2022-04-25','2022-05-08',24,NULL,NULL),(7,'2022-05-19 15:26:56','2022-05-15','2022-05-29',26,NULL,NULL),(8,'2022-05-19 15:40:25','2021-07-20','2022-07-20',26,_binary '',NULL),(9,'2022-05-20 15:00:38','2022-05-17','2022-05-31',28,_binary '',NULL),(10,'2022-05-20 15:12:20','2022-05-20','2022-08-17',29,_binary '',NULL);
/*!40000 ALTER TABLE `hosodangky` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong`
--

DROP TABLE IF EXISTS `phong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phong` (
  `id_phong` varchar(5) NOT NULL,
  `loaiphong` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `soluongSV` int NOT NULL DEFAULT '6',
  `id_toanha` varchar(5) NOT NULL,
  PRIMARY KEY (`id_phong`),
  KEY `id_phong_toanha_idx` (`id_toanha`),
  CONSTRAINT `id_phong_toanha` FOREIGN KEY (`id_toanha`) REFERENCES `toanha` (`id_toanha`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong`
--

LOCK TABLES `phong` WRITE;
/*!40000 ALTER TABLE `phong` DISABLE KEYS */;
INSERT INTO `phong` VALUES ('P1001','Phòng 8 giường',7,'B05'),('P1002','Phòng 6 giường',5,'B05'),('P1003','Phòng 8 giường',7,'B05'),('P1004','Phòng 8 giường',7,'C01'),('P1005','Phòng 4 giường',3,'C01'),('P1006','Phòng 6 giường',4,'C01'),('P1007','Phòng 4 giường',4,'D05'),('P1008','Phòng 6 giường',5,'D05'),('P1009','Phòng 6 giường',6,'D05'),('P1010','Phòng 6 giường',6,'BA2'),('P1011','Phòng 2 giường',1,'BA2'),('P1012','Phòng 4 giường',3,'BA2');
/*!40000 ALTER TABLE `phong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong_noithat`
--

DROP TABLE IF EXISTS `phong_noithat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phong_noithat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_phong` varchar(5) NOT NULL,
  `id_noithat` int NOT NULL,
  `soluong` int DEFAULT '15',
  PRIMARY KEY (`id`),
  KEY `id_phong_nt_phong_idx` (`id_phong`),
  KEY `id_phong_nt_noithat_idx` (`id_noithat`),
  KEY `id_phongnt_noithat_idx` (`id_noithat`),
  CONSTRAINT `id_phong_nt_noithat` FOREIGN KEY (`id_noithat`) REFERENCES `danhmucnoithat` (`id_noithat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_phong_nt_phong` FOREIGN KEY (`id_phong`) REFERENCES `phong` (`id_phong`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong_noithat`
--

LOCK TABLES `phong_noithat` WRITE;
/*!40000 ALTER TABLE `phong_noithat` DISABLE KEYS */;
INSERT INTO `phong_noithat` VALUES (1,'P1001',1,7),(2,'P1001',2,2),(3,'P1001',4,4),(9,'P1001',3,6),(12,'P1005',2,10),(29,'P1002',1,2),(31,'P1004',1,6),(32,'P1008',2,4),(35,'P1008',6,5),(36,'P1008',4,10),(37,'P1008',3,12),(38,'P1002',2,4),(39,'P1002',3,6),(40,'P1002',4,4),(41,'P1002',5,1),(42,'P1002',6,2),(43,'P1003',1,4),(44,'P1003',2,4),(45,'P1003',4,5),(46,'P1011',2,2),(47,'P1011',6,1),(48,'P1011',1,2),(49,'P1012',1,2),(50,'P1012',2,2),(51,'P1012',5,1),(52,'P1012',6,2),(53,'P1010',1,6),(54,'P1010',2,6),(55,'P1010',4,4),(56,'P1010',3,6),(57,'P1010',5,1),(58,'P1010',6,3),(59,'P1006',1,2),(60,'P1006',3,5),(61,'P1006',4,2),(62,'P1004',6,2),(63,'P1004',2,2),(64,'P1005',1,6),(65,'P1005',5,2),(66,'P1007',1,4),(67,'P1007',4,3),(68,'P1007',5,3),(69,'P1009',1,4),(70,'P1009',2,4),(71,'P1009',3,4),(72,'P1009',5,2),(73,'P1009',6,2);
/*!40000 ALTER TABLE `phong_noithat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'user'),(2,'admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_users`
--

DROP TABLE IF EXISTS `roles_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_users` (
  `id_user` int NOT NULL,
  `id_role` int NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `id_roles_user_idx` (`id_user`),
  KEY `id_roles_role_idx` (`id_role`),
  KEY `id_role_roles_idx` (`id_role`),
  CONSTRAINT `id_roles_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_roles_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_users`
--

LOCK TABLES `roles_users` WRITE;
/*!40000 ALTER TABLE `roles_users` DISABLE KEYS */;
INSERT INTO `roles_users` VALUES (24,1),(24,2),(26,1),(28,1),(28,2),(29,1);
/*!40000 ALTER TABLE `roles_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `toanha`
--

DROP TABLE IF EXISTS `toanha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `toanha` (
  `id_toanha` varchar(5) NOT NULL,
  `ten` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'D05',
  PRIMARY KEY (`id_toanha`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `toanha`
--

LOCK TABLES `toanha` WRITE;
/*!40000 ALTER TABLE `toanha` DISABLE KEYS */;
INSERT INTO `toanha` VALUES ('B01','Toà B01'),('B03','Toà B03'),('B04','Toà B04'),('B05','Toà B05'),('BA1','Toà BA1'),('BA2','Toà BA2'),('BA3','Toà BA3'),('BA4','Toà BA4'),('BA5','Toà BA5'),('C01','Toà C01'),('C02','Toà C02'),('C04','Toà C04'),('C05','Toà C05'),('C06','Toà C06'),('D02','Toà D02'),('D03','Toà D03'),('D04','Toà D04'),('D05','Toà D05'),('D06','Toà D06'),('E01','Toà E01'),('F01','Toà F01'),('F02','Toà F02'),('G01','Toà G01');
/*!40000 ALTER TABLE `toanha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `hoten` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngaysinh` date DEFAULT '2001-01-01',
  `gioitinh` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'Nam',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `diachicutru` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `truonghoc` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `nienkhoa` int DEFAULT '2019',
  `tinh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'Sóc Trăng',
  `huyen` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'Mỹ Xuyên',
  `xa` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'TT. Mỹ Xuyên',
  `id_phong` varchar(5) DEFAULT 'P1008',
  `cmnd/cccd` varchar(15) DEFAULT '123456789',
  `mssv` varchar(15) DEFAULT '1911065963',
  `enabled` bit(1) NOT NULL,
  `verification_code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user_phong_idx` (`id_phong`),
  CONSTRAINT `id_user_phong` FOREIGN KEY (`id_phong`) REFERENCES `phong` (`id_phong`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (24,'user2k1','$2a$10$5gNrpSDoOAnqPRHTAlXpNOKKs00Xo28Y4r2FQqun1WglddvvJ6TVO','duongquocan222@gmail.com','Dương An','2022-05-14','Nam','0335183057','205/45 huyện lộ 14','/assets/avatar/2.jpg','Hutech',2019,'Sóc Trăng','Mỹ Xuyên','TT Mỹ Xuyên','P1009','366356852','1911065963',_binary '',NULL),(26,'test2k1','$2a$10$n9i59hD9xE3lhMV.g2T.9OPP8juKlfS/p7yhlR2qSL8ncrGAvybaS','duongquocan2k1@gmail.com','Dương Quốc An','2001-04-20','Nam','0335183057','205/45 huyện lộ 14','/assets/avatar/1.jpg','Hutech',2019,'Sóc Trăng','Mỹ Xuyên','TT Mỹ Xuyên','P1008','366356852','1911065963',_binary '',NULL),(28,'toan','$2a$10$fE40.k.nJKzMGAdjY5g3nejm3zf0gIBxBFp0Rq0HTCF3VxQcZ/OG2','nhbtoan1503@gmail.com','Nguyen Huy Bao Toan','2001-03-15','Nam','0934942127','Quận 4, TPHCM','/assets/avatar/hinh.jpg','Hutech',2019,'Quảng Ngãi','Quảng Ngãi','Quảng Ngãi','P1001','212583214','1911066628',_binary '',NULL),(29,'tung','$2a$10$v7stfjbrC8b/tOGFOdUHEOceGs6Z1hPg3amisQgr/hAmFOu9F0VUm','duongngo0298@gmail.com','Nguyễn Diệp Thanh Tùng','2001-05-21','Nam','0934943057','Phú Mỹ Hưng','/assets/avatar/tung.jpg','Hutech',2019,'Hồ Chí Minh','Quận 7','','P1002','23123252312','1911075863',_binary '',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-20 22:25:41
