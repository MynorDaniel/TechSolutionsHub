-- Creacion del esquema
CREATE SCHEMA TechSolutionsHub;
USE TechSolutionsHub;

-- Tabla Usuario
CREATE TABLE usuario (
    nombre VARCHAR(100) PRIMARY KEY,
    clave VARCHAR(100) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    rol VARCHAR(20) NOT NULL
);

-- Tabla Componente
CREATE TABLE componente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    costo DECIMAL(10,2) NOT NULL,
    estado VARCHAR(100)
);

-- Tabla Molde
CREATE TABLE molde (
    nombre VARCHAR(100) PRIMARY KEY,
    costo DECIMAL(10,2)
);

-- Tabla Molde-Componente (Relación)
CREATE TABLE molde_componente (
    molde VARCHAR(100),
    componente INT,
    PRIMARY KEY (molde, componente),
    FOREIGN KEY (molde) REFERENCES molde(nombre),
    FOREIGN KEY (componente) REFERENCES componente(id)
);

-- Tabla Cliente
CREATE TABLE cliente (
    nit VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(100) NOT NULL
);

-- Tabla Computadora
CREATE TABLE computadora (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(100),
    cliente VARCHAR(50),
    molde VARCHAR(100),
    estado VARCHAR(20) NOT NULL,
    precio_venta DECIMAL(10,2),
    fecha_ensamble DATE NOT NULL,
    fecha_venta DATE,
    FOREIGN KEY (usuario) REFERENCES usuario(nombre),
    FOREIGN KEY (cliente) REFERENCES cliente(nit),
    FOREIGN KEY (molde) REFERENCES molde(nombre)
);

