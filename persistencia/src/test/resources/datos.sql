--CIUDAD--

INSERT INTO `ciudad` (`codigo`, `nombre`) VALUES
                                              (4, 'ARMENIA'),
                                              (5, 'BOGOTA'),
                                              (6, 'CALI'),
                                              (7, 'CALARCA'),
                                              (9, 'MANIZALES'),
                                              (10, 'MEDELLIN'),
                                              (11, 'PALMIRA'),
                                              (12, 'TULUA'),
                                              (13, 'BUCARAMANGA'),
                                              (14, 'CARTAGENA');

--TIPO DOCUMENTO--

INSERT INTO `tipo_documento` (`codigo`, `tipo_documento`) VALUES
                                                              (1, 'CEDULA DE CIUDADANIA'),
                                                              (2, 'TARJETA DE IDENTIDAD'),
                                                              (3, 'CEDULA EXTRANGERA'),
                                                              (4, 'PASAPORTE');


--USUARIO--


INSERT INTO `usuario` (`codigo`, `contrasena`, `email`, `nombre`, `direccion`, `fecha_nacimiento`, `ciudad_codigo`, `tipo_documento_codigo`) VALUES
                                                                                                                                                 ('100466788', 'contrasena', 'juan@gmail.com', 'juan', 'Barrio tulipanes', '1920-08-17', 12, 1),
                                                                                                                                                 ('1094895697', 'contrasena', 'juliana@gmail.com', 'juliana gonzalez', 'carrera 35a #8-22', '1988-03-31', 9, 1),
                                                                                                                                                 ('1094908238', 'contraseña', 'jimv9200@gmail.com', 'Jorge Ivan Martinez', 'calle 24n #13-30', '1989-10-09', 4, 1),
                                                                                                                                                 ('109745566', 'contrasena', 'iraida@gmail.com', 'Iraida Sonzoneti', 'calle 19n #18-55', '1977-10-04', 11, 3),
                                                                                                                                                 ('7666389', 'contrasena', 'sergio@gmail.com', 'Sergio Buitrago', 'calle 55a #88-100', '1971-06-10', 5, 1),
                                                                                                                                                 ('97382009', 'contraseña', 'victor@gmail.com', 'victor hugo', 'calle 4 #66-49', '1983-09-29', 6, 1);

--TELEFONO USUARIO--

INSERT INTO `usuario_telefono` (`usuario_codigo`, `telefono`, `telefono_key`) VALUES
                                                                                  ('100466788', '3218899198', 'oficina'),
                                                                                  ('1094895697', '3277718827', 'celular'),
                                                                                  ('1094908238', '3216371746', 'celular'),
                                                                                  ('109745566', '355661772', 'Oficina'),
                                                                                  ('7666389', '32182888999', 'casa'),
                                                                                  ('97382009', '772771888', 'casa');


--ADMINISTRADOR--


-- INSERT INTO `administrador` (`codigo`, `contrasena`, `email`, `nombre`, `tipo_documento_codigo`) VALUES
--    ('1094998883', '123456', 'admin@gmail.com', 'Roberto', 1);


--CATEGORÍA--


INSERT INTO `categoria` (`codigo`, `nombre`) VALUES
                                                 (1, 'TECNOLOGÍA'),
                                                 (2, 'HOGAR'),
                                                 (3, 'DEPORTES'),
                                                 (4, 'ROPA'),
                                                 (5, 'AUTOMOBILES'),
                                                 (6, 'JOYERÍA'),
                                                 (7, 'ALIMENTOS'),
                                                 (8, 'DULCES'),
                                                 (9, 'ACCESORIOS');

--MEDIO DE PAGO--


INSERT INTO `medio_pago` (`codigo`, `descripcion`) VALUES
                                                       (1, 'PSE'),
                                                       (2, 'TARJETA DE CREDITO'),
                                                       (3, 'CONTRAENTREGA'),
                                                       (4, 'BALOTO');

--PRODUCTOS--

INSERT INTO `producto` (`id`, `codigo`, `descripcion`, `descuento`, `fecha_lmite`, `nombre`, `precio`, `unidades`, `ciudad_codigo`, `usuario_codigo`) VALUES
                                                                                                                                                          (1, '7764553669', 'La chocolatina mas rica del mundo', 5.5, '2021-11-30', 'chocolatina Jumbo Jet', 9500, 100, 5, '7666389'),
                                                                                                                                                          (2, '766255344', 'Bicicleta todo terreno marca QI de 9 velocidades y frenos de disco mecanicos', 10, '2021-11-30', 'Bicicleta QI', 3250000, 5, 5, '7666389'),
                                                                                                                                                          (3, '66242777288', 'Televisor sony 21 pulgadas curvo 8K 60MHGZ', 30, '2021-12-31', 'Televisor sony 21', 1925900, 76, 4, '97382009'),
                                                                                                                                                          (4, '888266367', 'Computador dell pentium 9 64gb Memoria Ram 10Hb de disco duro', 0, '2022-01-05', 'computador dell', 6578000, 6, 6, '97382009'),
                                                                                                                                                          (5, '882363687', 'Celular Iphone X de 64Gb en buen estado', 50, '2022-01-26', 'Iphone X', 1500000, 1, 5, '100466788'),
                                                                                                                                                          (6, '88819199287', 'Computador acer procesador I5 de septima generación ', 15, '2021-12-22', 'Compuador acer', 1700500, 15, 4, '100466788'),
                                                                                                                                                          (7, '5551442626', 'Pantalones negros marca americano en todas las tallas', 20, '2021-12-17', 'Jeans Americanino', 183500, 120, 14, '109745566'),
                                                                                                                                                          (8, '77528836', 'Maletín toto para campamento de 20 litros ', 40, '2021-11-30', 'Maletín toto', 120000, 3, 10, '109745566'),
                                                                                                                                                          (9, '772579', 'Galletas Navideñas marca festival de 100g para compartir con la familia y los amigos', 0, '2021-11-22', 'Galletas Navideñas', 12200, 999, 11, '1094895697'),
                                                                                                                                                          (10, '88162677', 'Tenis Ronning shoes marca adidas en todas las tallas', 15, '2022-02-08', 'Tenis Adidas roe', 345000, 5, 13, '1094895697'),
                                                                                                                                                          (11, '99127753', 'Teclado acer inalambrico para celular tablets y computadores', 10, '2021-11-30', 'Teclado Acer', 78000, 30, 5, '1094908238'),
                                                                                                                                                          (12, '553442791', 'Tarjeta de red para computador con antena para tener la mejor cobertura en tus señales', 12, '2021-12-15', 'Tarjeta de red', 25000, 10, 7, '1094908238');


--CATEGORIA DEL PRODUCTO--

INSERT INTO `categoria_producto` (`categoria_codigo`, `producto_id`) VALUES
                                                                         (7, 1),
                                                                         (8, 1),
                                                                         (3, 2),
                                                                         (1, 3),
                                                                         (2, 3),
                                                                         (1, 4),
                                                                         (1, 5),
                                                                         (1, 6),
                                                                         (4, 7),
                                                                         (9, 8),
                                                                         (7, 9),
                                                                         (8, 9),
                                                                         (4, 10),
                                                                         (1, 11),
                                                                         (1, 12);

--COMPRA--

INSERT INTO `compra` (`codigo`, `timestamp`, `medio_pago_codigo`, `usuario_codigo`) VALUES
                                                                                        (1, '2021-11-01 03:08:08', 1, '7666389'),
                                                                                        (2, '2021-11-01 03:21:08', 1, '97382009'),
                                                                                        (3, '2021-11-01 03:21:08', 2, '100466788'),
                                                                                        (4, '2021-11-01 03:21:33', 3, '109745566'),
                                                                                        (5, '2021-11-01 03:21:33', 4, '1094895697'),
                                                                                        (6, '2021-11-01 03:21:50', 1, '1094908238'),
                                                                                        (7, '2021-11-01 03:21:50', 2, '1094908238'),
                                                                                        (8, '2021-11-01 03:22:13', 1, '1094895697'),
                                                                                        (9, '2021-11-01 03:22:13', 3, '1094908238');

--CHAT--

INSERT INTO `chat` (`codigo`, `comprador_codigo`, `vendedor_codigo`) VALUES
                                                                         (1, '7666389', '97382009'),
                                                                         (2, '100466788', '109745566'),
                                                                         (3, '7666389', '1094908238'),
                                                                         (4, '1094895697', '1094908238');

--DETALLE COMPRA--


INSERT INTO `detalle_compra` (`codigo`, `precio_poducto`, `unidades`, `compra_codigo`, `producto_id`) VALUES
                                                                                                          (1, 9500, 2, 1, 1),
                                                                                                          (2, 25000, 1, 1, 12),
                                                                                                          (3, 78000, 3, 1, 11),
                                                                                                          (4, 12200, 90, 2, 9),
                                                                                                          (5, 6578000, 1, 2, 4),
                                                                                                          (6, 1700500, 1, 3, 6),
                                                                                                          (7, 3250000, 1, 4, 2),
                                                                                                          (8, 9500, 4, 4, 1),
                                                                                                          (9, 183500, 2, 4, 7),
                                                                                                          (10, 345000, 1, 5, 10),
                                                                                                          (11, 1925900, 1, 6, 3),
                                                                                                          (12, 183500, 1, 7, 7),
                                                                                                          (13, 345000, 1, 7, 10),
                                                                                                          (14, 1925900, 1, 8, 3),
                                                                                                          (15, 6578000, 1, 9, 4),
                                                                                                          (16, 120000, 1, 9, 8),
                                                                                                          (17, 12200, 20, 9, 9),
                                                                                                          (18, 25000, 2, 9, 12),
                                                                                                          (19, 78000, 5, 9, 11),
                                                                                                          (20, 3250000, 1, 9, 2);

--COMENTARIOS--


INSERT INTO `comentario` (`codigo`, `calificacion`, `timestamp`, `mensaje`, `respuesta`, `producto_id`, `usuario_codigo`) VALUES
                                                                                                                              (1, 3, '2021-11-01 03:46:10', 'Las chocolatinas estaban muy ricas pero estaban un poco derretidas', 'Gracias por su compra', 1, '7666389'),
                                                                                                                              (2, 0, '2021-11-01 03:46:10', 'Las chocolatinas venian destapadas y en mal estado', 'Lamentamos que su producto no llegara satisfactoriamente', 1, '109745566'),
                                                                                                                              (3, 5, '2021-11-01 03:48:50', 'Todo muy bien todo correcto', NULL, 2, '1094908238'),
                                                                                                                              (4, 4, '2021-11-01 03:48:50', 'Estuvo bien la entrega', 'Muchas gracias por su compra', 2, '109745566'),
                                                                                                                              (5, 4, '2021-11-01 03:50:55', 'Bien', 'Gracias por la compra', 3, '1094908238'),
                                                                                                                              (6, 5, '2021-11-01 03:50:55', 'Excelente vendedor', 'Muchas gracias', 3, '1094895697'),
                                                                                                                              (7, 1, '2021-11-01 03:53:22', 'Pesimo servicio estuvo muy mal', 'Lo sentimos por su mala experiencia', 4, '1094908238'),
                                                                                                                              (8, 3, '2021-11-01 03:53:22', 'se demoró un poco el envio', NULL, 4, '97382009'),
                                                                                                                              (9, 2, '2021-11-01 03:55:54', 'No estoy satisfecho con el producto', 'Lamentamos su experiencia', 6, '109745566'),
                                                                                                                              (10, 4, '2021-11-01 03:55:54', 'Muy bien todo', 'Muchas gracias por sus palabras nos alegra mucho', 8, '1094908238'),
                                                                                                                              (11, 5, '2021-11-01 03:57:41', 'excelente', 'estamos a sus servicios', 7, '109745566'),
                                                                                                                              (12, 4, '2021-11-01 03:57:41', 'excelente servicio', 'estamos para servirle', 7, '1094908238'),
                                                                                                                              (13, 2, '2021-11-01 03:59:35', 'no me gusto el producto', ':(', 9, '97382009'),
                                                                                                                              (14, 3, '2021-11-01 03:59:35', 'muy regular el producto', 'XD', 9, '1094908238'),
                                                                                                                              (15, 5, '2021-11-01 04:01:06', '.', NULL, 10, '1094895697'),
                                                                                                                              (16, 4, '2021-11-01 04:01:06', 'todo estuvo bien', 'muchas gracias por su compra :)', 10, '1094908238'),
                                                                                                                              (17, 3, '2021-11-01 04:02:55', 'Todo estuvo bien un poco demorada la entrega', 'Por culpa de los mamertos que estan en paro', 11, '7666389'),
                                                                                                                              (18, 4, '2021-11-01 04:02:55', 'Me gusto todo', 'Gracias por su compra', 11, '1094908238'),
                                                                                                                              (19, 0, '2021-11-01 04:04:17', 'pesimo producto', 'perdio', 12, '7666389'),
                                                                                                                              (20, 1, '2021-11-01 04:04:17', 'Muy mal producto no es el publicado', 'Haga el reclamo', 12, '1094908238');


--FAVORITOS--


INSERT INTO `favorito` (`codigo`, `producto_id`, `usuario_codigo`) VALUES
                                                                       (1, 8, '1094908238'),
                                                                       (2, 5, '1094908238'),
                                                                       (3, 11, '7666389'),
                                                                       (4, 6, '97382009'),
                                                                       (5, 2, '97382009'),
                                                                       (6, 2, '109745566'),
                                                                       (7, 4, '109745566'),
                                                                       (8, 12, '109745566'),
                                                                       (9, 2, '1094895697');



--MENSAJES--


INSERT INTO `mensaje` (`codigo`, `fecha`, `mensaje`, `chat_codigo`) VALUES
                                                                        (1, '2021-11-01 04:34:49', '---comprador---\r\nEl producto esta disponible para armenia?\r\n---vendedor---\r\nSi señor\r\n---comprador---\r\nMe podría hacer un descuento\r\n---vendedor---\r\nel precio es fijo', 1),
                                                                        (2, '2021-11-01 04:37:12', '---comprador---\r\nEl producto esta disponible para armenia?\r\n---vendedor---\r\nSi señor\r\n---comprador---\r\nMe podría hacer un descuento\r\n---vendedor---\r\nel precio es fijo', 2),
                                                                        (3, '2021-11-01 04:37:12', '---comprador---\r\nEl producto esta disponible para armenia?\r\n---vendedor---\r\nSi señor\r\n---comprador---\r\nMe podría hacer un descuento\r\n---vendedor---\r\nel precio es fijo', 3),
                                                                        (4, '2021-11-01 04:37:29', '---comprador---\r\nEl producto esta disponible para armenia?\r\n---vendedor---\r\nSi señor\r\n---comprador---\r\nMe podría hacer un descuento\r\n---vendedor---\r\nel precio es fijo', 4);


--SUBASTA--


INSERT INTO `subasta` (`codigo`, `fecha_limite`, `producto_id`) VALUES
    (1, '2021-11-01 05:04:31', 11),
    (2, '2021-11-01 05:04:31', 2),
    (3, '2021-11-11 05:04:31', 5);


--SUBASTA USUARIO--



INSERT INTO `subasta_usuario` (`codigo`, `fecha_subasta`, `valor`, `subasta_codigo`, `usuario_codigo`) VALUES
                                                                                                           (1, '2021-11-01 05:04:46', 30000, 1, '1094908238'),
                                                                                                           (2, '2021-11-01 05:04:46', 35000, 1, '1094895697'),
                                                                                                           (3, '2021-11-01 05:04:46', 38000, 2, '1094908238'),
                                                                                                           (4, '2021-11-01 05:04:46', 50000, 2, '1094895697'),
                                                                                                           (5, '2021-11-01 05:04:46', 3000, 3, '1094908238');