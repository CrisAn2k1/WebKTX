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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
INSERT INTO `chitiethoadon` VALUES (31,12,42000,1,17),(32,3,12000,2,17),(33,11,38500,1,18),(34,12,48000,2,18),(41,10,35000,1,22),(42,10,40000,2,22),(45,7,24500,1,24),(46,69,276000,2,24);
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (17,54000,'2022-05-15 21:42:34','P111',_binary ''),(18,86500,'2022-05-15 21:45:33','P111',_binary '\0'),(22,75000,'2022-05-17 18:26:01','P1008',_binary ''),(24,300500,'2022-05-19 10:55:32','P1008',_binary '\0');
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hosochuyenphong`
--

LOCK TABLES `hosochuyenphong` WRITE;
/*!40000 ALTER TABLE `hosochuyenphong` DISABLE KEYS */;
INSERT INTO `hosochuyenphong` VALUES (15,'','2022-05-19 13:03:14',26,_binary ''),(16,'Phòng quá ồn','2022-05-20 10:10:16',24,_binary '\0');
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
  PRIMARY KEY (`id_hosodk`),
  KEY `id_hsdk_user_idx` (`id_user`),
  CONSTRAINT `id_hsdk_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hosodangky`
--

LOCK TABLES `hosodangky` WRITE;
/*!40000 ALTER TABLE `hosodangky` DISABLE KEYS */;
INSERT INTO `hosodangky` VALUES (5,'2022-05-17 19:50:40','2022-05-29','2022-06-05',26,_binary ''),(6,'2022-05-19 12:38:14','2022-04-25','2022-05-08',24,NULL),(7,'2022-05-19 15:26:56','2022-05-15','2022-05-29',26,NULL),(8,'2022-05-19 15:40:25','2021-07-20','2022-07-20',26,_binary '');
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
INSERT INTO `phong` VALUES ('P1001','Phòng 8 giường',7,'D05'),('P1008','Phòng 6 giường',5,'D05'),('P1009','Phòng 6 giường',6,'B05'),('P1107','Phòng 6 giường',5,'D05'),('P111','Phòng 8 giường',7,'D02'),('P1210','Phòng 8 giường',7,'C01'),('P703','Phòng 4 giường',3,'D02'),('P715','Phòng 6 giường',4,'B05');
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong_noithat`
--

LOCK TABLES `phong_noithat` WRITE;
/*!40000 ALTER TABLE `phong_noithat` DISABLE KEYS */;
INSERT INTO `phong_noithat` VALUES (1,'P1001',1,7),(2,'P1001',2,2),(3,'P1001',4,4),(9,'P1001',3,6),(12,'P703',2,10),(29,'P1107',1,NULL),(31,'P1210',1,6),(32,'P1008',2,4),(35,'P1008',6,5),(36,'P1008',4,10),(37,'P1008',3,12);
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
  `name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
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
INSERT INTO `roles_users` VALUES (24,1),(24,2),(26,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (24,'user2k1','$2a$10$5gNrpSDoOAnqPRHTAlXpNOKKs00Xo28Y4r2FQqun1WglddvvJ6TVO','duongquocan222@gmail.com','Dương An','2022-05-14','Nam','0335183057','205/45 huyện lộ 14','/assets/avatar/2.jpg','Hutech',2019,'Sóc Trăng','Mỹ Xuyên','TT Mỹ Xuyên','P1009','366356852','1911065963',_binary '',NULL),(26,'test2k1','$2a$10$n9i59hD9xE3lhMV.g2T.9OPP8juKlfS/p7yhlR2qSL8ncrGAvybaS','duongquocan2k1@gmail.com','Dương Quốc An','2001-04-20','Nam','0335183057','205/45 huyện lộ 14','/assets/avatar/1.jpg','Hutech',2019,'Sóc Trăng','Mỹ Xuyên','TT Mỹ Xuyên','P1008','366356852','1911065963',_binary '',NULL);
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

-- Dump completed on 2022-05-20 21:31:32
