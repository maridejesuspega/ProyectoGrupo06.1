-- Create and use the database
CREATE DATABASE IF NOT EXISTS `vida_animal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `vida_animal`;

-- Drop existing tables in reverse order of dependencies
DROP TABLE IF EXISTS `veterinarios`;
DROP TABLE IF EXISTS `usuarios`;
DROP TABLE IF EXISTS `usuario`;
DROP TABLE IF EXISTS `rol`;
DROP TABLE IF EXISTS `productos`;
DROP TABLE IF EXISTS `mascotas`;
DROP TABLE IF EXISTS `historial_medico`;
DROP TABLE IF EXISTS `detalles_compras`;
DROP TABLE IF EXISTS `compras`;
DROP TABLE IF EXISTS `comentarios`;
DROP TABLE IF EXISTS `citas`;
DROP TABLE IF EXISTS `categoria`;

-- Create tables
CREATE TABLE `categoria` (
  `id_categoria` bigint NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `ruta_imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insert categories
INSERT INTO `categoria` (descripcion, activo, ruta_imagen) VALUES 
('Alimentos', 1, 'https://images.unsplash.com/photo-1585846888147-c924c1febe0b?q=80&w=1000&auto=format&fit=crop'),
('Medicamentos', 1, 'https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?q=80&w=1000&auto=format&fit=crop'),
('Accesorios', 1, 'https://images.unsplash.com/photo-1583337130417-3346a1be7dee?q=80&w=1000&auto=format&fit=crop'),
('Higiene', 1, 'https://images.unsplash.com/photo-1583512603866-910c8542ba1b?q=80&w=1000&auto=format&fit=crop');

-- Create products table
CREATE TABLE `productos` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `cantidad` int NOT NULL,
  `ruta_imagen` varchar(255) DEFAULT NULL,
  `activo` varchar(255) DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insert sample products
INSERT INTO `productos` VALUES 
(1,'Royal Canin Adult','Alimento premium para perros adultos',25000,50,'https://static.miscota.com/media/1/photos/products/007605/RC-VET-DRY-DogAD-MV-1-es-ES-62fcc35d65959_g.jpg','1','Alimentos'),
(2,'Antipulgas Nexgard','Tratamiento mensual contra pulgas y garrapatas',15000,30,'https://acdn.mitiendanube.com/stores/830/783/products/n-41a101-05aefb9b7cc8427d5a16757810847077-640-0.jpg','1','Medicamentos'),
(3,'Collar ajustable','Collar de nylon resistente para perros',8000,100,'https://www.unimart.com/cdn/shop/files/AksiPetitCollarAjustableparaPerroPetit_TallaS_1024x.jpg?v=1712031036','1','Accesorios'),
(4,'Shampoo Premium','Shampoo hipoalergénico para mascotas',12000,40,'https://ferreteriavidri.com/images/items/large/406834.jpg?v=20241201','1','Higiene');

-- Create other tables
CREATE TABLE `citas` (
  `id_cita` int NOT NULL AUTO_INCREMENT,
  `id_mascota` int DEFAULT NULL,
  `id_veterinario` int DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `estado` enum('pendiente','confirmada','cancelada') DEFAULT 'pendiente',
  PRIMARY KEY (`id_cita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `comentarios` (
  `id_comentario` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int DEFAULT NULL,
  `id_producto` int DEFAULT NULL,
  `id_cita` int DEFAULT NULL,
  `calificacion` int DEFAULT NULL,
  `comentario` text,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_comentario`),
  CONSTRAINT `comentarios_chk_1` CHECK (((`calificacion` >= 1) and (`calificacion` <= 5)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `compras` (
  `id_compra` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `metodo_pago` enum('tarjeta_credito','tarjeta_debito','transferencia') NOT NULL,
  PRIMARY KEY (`id_compra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `detalles_compras` (
  `id_detalle` int NOT NULL AUTO_INCREMENT,
  `id_compra` int DEFAULT NULL,
  `id_producto` int DEFAULT NULL,
  `cantidad` int NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_detalle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `historial_medico` (
  `id_historial` int NOT NULL AUTO_INCREMENT,
  `id_mascota` int DEFAULT NULL,
  `fecha` date NOT NULL,
  `diagnostico` text,
  `tratamiento` text,
  `notas` text,
  PRIMARY KEY (`id_historial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mascotas` (
  `id_mascota` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `edad` int DEFAULT NULL,
  PRIMARY KEY (`id_mascota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rol` (
  `id_rol` bigint NOT NULL AUTO_INCREMENT,
  `id_usuario` bigint DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insert roles
INSERT INTO `rol` VALUES (1,NULL,'ROLE_USER'),(2,1,'ROLE_USER'),(3,NULL,'ROLE_USER'),(4,NULL,'ROLE_USER');

CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `ruta_imagen` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insert sample users
INSERT INTO `usuario` VALUES 
(1,_binary '','Peña Garcia','mariadejesuspega@gmail.com','María','$2a$10$Tn.6EhIsF77RQdkBlYB3Ve5OWYidvc2OQIWUp4YoXKqJJGsCSQ20q',NULL,'50671904148','maria'),
(2,_binary '\0','Peña Garcia','mariadejesuspega@outlook.com','María','$2a$10$AyC9623E8MM.ksLtnrFhcuU1Xh.eGQ/cryBslR38SC.ed/Yj/Auku',NULL,'50671904148','marii');

CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `contrasena` varchar(255) NOT NULL,
  `rol` enum('cliente','veterinario','administrador') NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `veterinarios` (
  `id_veterinario` int NOT NULL AUTO_INCREMENT,
  `especialidad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_veterinario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;