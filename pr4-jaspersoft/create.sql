CREATE DATABASE tienda CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE tienda;

CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(60)  NOT NULL,
    descripcion  VARCHAR(200)
);

CREATE TABLE productos (
    id_producto  INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    precio       DECIMAL(10,2) NOT NULL,
    stock        INT          NOT NULL DEFAULT 0,
    id_categoria INT          NOT NULL,
    CONSTRAINT fk_prod_cat FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(80)  NOT NULL,
    email      VARCHAR(100),
    ciudad     VARCHAR(60)
);

CREATE TABLE pedidos (
    id_pedido   INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente  INT  NOT NULL,
    fecha       DATE NOT NULL,
    CONSTRAINT fk_ped_cli FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

CREATE TABLE detalle_pedidos (
    id_detalle  INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido   INT            NOT NULL,
    id_producto INT            NOT NULL,
    cantidad    INT            NOT NULL DEFAULT 1,
    precio_unit DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_det_ped FOREIGN KEY (id_pedido)   REFERENCES pedidos(id_pedido),
    CONSTRAINT fk_det_pro FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

INSERT INTO categorias (nombre, descripcion) VALUES
  ('Electrónica',   'Dispositivos electrónicos y accesorios'),
  ('Informática',   'Ordenadores, periféricos y componentes'),
  ('Hogar',         'Artículos para el hogar y decoración'),
  ('Deportes',      'Material deportivo y ropa técnica'),
  ('Alimentación',  'Productos de alimentación y bebidas');

INSERT INTO productos (nombre, precio, stock, id_categoria) VALUES
  ('Smartphone X12',        349.99,  50, 1),
  ('Auriculares BT Pro',     79.99, 120, 1),
  ('Smartwatch S3',         129.99,  80, 1),
  ('Portátil UltraBook',    899.99,  30, 2),
  ('Ratón Inalámbrico',      24.99, 200, 2),
  ('Teclado Mecánico',       69.99, 150, 2),
  ('Monitor 27" 4K',        399.99,  40, 2),
  ('Cafetera Express',      149.99,  60, 3),
  ('Aspiradora Robot',      299.99,  35, 3),
  ('Silla Ergonómica',      249.99,  45, 3),
  ('Bicicleta de Montaña',  599.99,  20, 4),
  ('Mochila Trail 30L',      89.99,  90, 4),
  ('Zapatillas Running',    119.99, 110, 4),
  ('Pack Café Premium',      19.99, 300, 5),
  ('Aceite de Oliva Virgen', 12.99, 400, 5);

INSERT INTO clientes (nombre, email, ciudad) VALUES
  ('Ana García',       'ana.garcia@email.es',     'Madrid'),
  ('Carlos López',     'carlos.lopez@email.es',   'Barcelona'),
  ('María Rodríguez',  'maria.rodriguez@email.es','Valencia'),
  ('Pedro Martínez',   'pedro.martinez@email.es', 'Sevilla'),
  ('Laura Sánchez',    'laura.sanchez@email.es',  'Madrid'),
  ('Diego Fernández',  'diego.fernandez@email.es','Bilbao'),
  ('Sofía Torres',     'sofia.torres@email.es',   'Barcelona'),
  ('Miguel Ruiz',      'miguel.ruiz@email.es',    'Madrid'),
  ('Elena Moreno',     'elena.moreno@email.es',   'Valencia'),
  ('Javier Jiménez',   'javier.jimenez@email.es', 'Zaragoza');

INSERT INTO pedidos (id_cliente, fecha) VALUES
  (1, '2024-01-10'),
  (2, '2024-01-15'),
  (3, '2024-01-20'),
  (4, '2024-02-05'),
  (5, '2024-02-12'),
  (6, '2024-02-18'),
  (7, '2024-03-03'),
  (8, '2024-03-11'),
  (9, '2024-03-22'),
  (10,'2024-03-28'),
  (1, '2024-04-02'),
  (2, '2024-04-10'),
  (3, '2024-04-18'),
  (5, '2024-05-05'),
  (7, '2024-05-14');

INSERT INTO detalle_pedidos (id_pedido, id_producto, cantidad, precio_unit) VALUES
  (1, 1, 1, 349.99),
  (1, 2, 2,  79.99),
  (2, 4, 1, 899.99),
  (2, 5, 1,  24.99),
  (2, 6, 1,  69.99),
  (3, 8, 1, 149.99),
  (3,14, 3,  19.99),
  (4,11, 1, 599.99),
  (4,12, 1,  89.99),
  (5, 3, 1, 129.99),
  (5, 7, 1, 399.99),
  (6, 9, 1, 299.99),
  (6,10, 1, 249.99),
  (7,13, 2, 119.99),
  (7,15, 5,  12.99),
  (8, 1, 1, 349.99),
  (8, 6, 1,  69.99),
  (9, 4, 1, 899.99),
  (9, 2, 1,  79.99),
  (10,8, 1, 149.99),
  (10,14,2,  19.99),
  (11,11,1, 599.99),
  (12,3, 1, 129.99),
  (12,5, 2,  24.99),
  (13,9, 1, 299.99),
  (14,7, 1, 399.99),
  (14,6, 1,  69.99),
  (15,13,1, 119.99),
  (15,12,1,  89.99);
