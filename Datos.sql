
INSERT INTO `administrador` (`codigo`, `contrasena`, `email`, `nombre`) VALUES 
('109490831564', '123456', 'administrador1@hotmail.com', 'Julian Velez'), 
('1095566894', '123456', 'killemall@metallica.com', 'Brayan Polanco'), 
('456553220', '1236456', 'pollito@juanes.com', 'Roy Castillo');
--
-- Volcado de datos para la tabla `carrito`
--

INSERT INTO `carrito` (`codigo`, `activo`, `usuario_codigo`) VALUES
(1, b'1', '9788965'),
(2, b'1', '88788898'),
(3, b'1', '109455684');

--
-- Volcado de datos para la tabla `carrito_producto`
--

INSERT INTO `carrito_producto` (`carrito_codigo`, `producto_codigo`) VALUES
(1, '702745558'),
(2, '7027555489'),
(3, '70278895565');

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`codigo`, `nombre`) VALUES
(1, 'SUPERMERCADOS'),
(2, 'TÉCNOLOGIA'),
(3, 'SALUD');

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`codigo`, `nombre`) VALUES
(1, 'Armenia'),
(2, 'Cali'),
(3, 'Bogotá');

--
-- Volcado de datos para la tabla `comentario`
--

INSERT INTO `comentario` (`codigo`, `calificacion`, `timestamp`, `mensaje`, `respuesta`, `producto_codigo`, `usuario_codigo`) VALUES
(1, 5, '2021-10-21 01:08:00', 'El producto era todo lo que esperaba estoy muy contento con mi compra me alegra hacer tratos asi.', 'Muchas gracias por tu compra', '702745558', '9788965'),
(2, 0, '2021-10-21 01:08:00', 'El producto estaba en mal estado', 'Pailas perdio', '7027555489', '88788898'),
(3, 3, '2021-10-21 01:08:54', 'Todo bien se demoró mucho en llegar', 'Gracias por su compra', '70278895565', '109455684');

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`codigo`, `timestamp`, `medio_pago`, `usuario_codigo`) VALUES
(1, '2021-10-21 01:10:11', 'TARGETA DE CREDITO', '9788965'),
(2, '2021-10-21 01:10:11', 'PSE', '88788898'),
(3, '2021-10-21 01:10:25', 'CONTRAENTREGA', '109455684');

--
-- Volcado de datos para la tabla `detalle_compra`
--

INSERT INTO `detalle_compra` (`codigo`, `precio_poducto`, `unidades`, `compra_codigo`, `producto_codigo`) VALUES
(1, 150000, 5, 2, '702745558'),
(2, 50000, 1, 3, '7027555489'),
(3, 1500, 1, 1, '70278895565');

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`codigo`, `descripcion`, `descuento`, `fecha_lmite`, `nombre`, `precio`, `unidades`, `ciudad_codigo`, `usuario_codigo`) VALUES
('702745558', 'Teclado Unitec muy negrito y muy bonito funciona y tiene todas las teclas', 5, '2021-10-30', 'Teclado Unitec', 25000, 150, 3, '88788898'),
('7027555489', 'Computador todo en uno dell de 4 gb de disco duro y 128 Mg de memoria ram ideal para videojuegos', 0, '2021-12-02', 'Computador dell', 5489650, 10, 2, '109455684'),
('70278895565', 'Chocolatina Jet cubierta de chispitas de chocolate y maní', 10, '2021-10-26', 'Chocolatina Jet', 1500, 1500, 1, '9788965');

--
-- Volcado de datos para la tabla `subasta`
--

INSERT INTO `subasta` (`codigo`, `fecha_limite`, `producto_codigo`) VALUES
(1, '2021-10-21 03:12:40', '702745558'),
(2, '2021-10-21 03:12:40', '7027555489'),
(3, '2021-10-21 03:12:49', '70278895565');

--
-- Volcado de datos para la tabla `subasta_usuario`
--

INSERT INTO `subasta_usuario` (`codigo`, `fecha_subasta`, `valor`, `subasta_codigo`, `usuario_codigo`) VALUES
(1, '2021-10-21 03:12:55', 1500000, 1, '9788965'),
(2, '2021-10-31 20:12:55', 80000, 2, '88788898'),
(3, '2021-10-21 03:13:21', 60000, 3, '109455684');

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`codigo`, `contrasena`, `email`, `nombre`, `direccion`, `fecha_nacimiento`, `ciudad_codigo`) VALUES
('109455684', 'muslllamj', 'jim@uq.com', 'Emiliano Suarez', 'Calle bogotana al lado de las pizzas', '2021-10-05', 1),
('88788898', 'osama', 'aoio@hotmail.com', 'Armando Casas', 'Calle 4 #66-49', '2021-10-09', 2),
('9788965', '4555665', 'amantedelacomida@gmail.com', 'Homero Polo', 'Avenida siempre viva 743', '2015-10-22', 3);

--
-- Volcado de datos para la tabla `usuario_telefono`
--

INSERT INTO `usuario_telefono` (`usuario_codigo`, `telefono`, `telefono_key`) VALUES
('9788965', '7222558', 'CASA');



