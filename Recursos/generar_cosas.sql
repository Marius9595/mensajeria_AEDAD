insert mensajeria.provincia(mensajeria.provincia.nombre) values ('A Coruña'), 
('Álava'), ('Albacete'), ('Alicante'), ('Almería'), ('Asturias'), ('Badajoz'), ('Baleares'), 
('Barcelona'), ('Burgos'),  ('Cáceres'),  ('Cádiz'),  ('Cantabria'),  ('Castellón'),  ('Ciudad Real'), ('Córdoba'),  ('Cuenca'), ('Girona'), 
('Granada'),  ('Guadalajara'),  ('Gipuzkoa'),  ('Huelva'),  ('Huesca'),  ('Jaén'),  ('La Rioja'),  ('Las Palmas'),  ('León'),  ('Lérida'), 
('Lugo'),  ('Madrid'),  ('Málaga'),  ('Murcia'),  ('Navarra'),  ('Ourense'),  ('Palencia'),  ('Pontevedra'),  ('Salamanca'),  ('Segovia'), 
('Sevilla'),  ('Soria'), ('Tarragona'), ('Santa Cruz de Tenerife'), ('Teruel'), ('Toledo'), ('Valencia'), ('Valladolid'), (' Vizcaya'), ('Zamora'), ('Soria'), 
('Zaragoza'), ('Ceuta'), ('Melilla');

insert mensajeria.articulo(descripcion) values ('pack1'), ('pack2'), ('pack3'), ('pack4'), ('pack5'), ('pack6'), ('pack7'), ('pack8');

INSERT INTO `mensajeria`.`usuario` (`Nombre`, `Apellidos`, `Correo`, `Password`, `fecha_ultima_conection`, `id_provincia`, `permisos`) VALUES
('Paco', 'Apellido1', 'Correo1@domino.es', '123456', CURRENT_TIMESTAMP(), 10, "0"),
('Pepe', 'Apellido1', 'Correo2@domino.es', '123456', CURRENT_TIMESTAMP(), 10, "0"),
('Juan', 'Apellido1', 'Correo3@domino.es', '123456', CURRENT_TIMESTAMP(), 21, "0"),
('Felipe', 'Apellido1', 'Correo4@domino.es', '123456', CURRENT_TIMESTAMP(), 11, "2"),
('Carlos', 'Apellido1', 'Correo5@domino.es', '123456', CURRENT_TIMESTAMP(), 15, "2"),
('Jony', 'Apellido1', 'Correo6@domino.es', '123456', CURRENT_TIMESTAMP(), 42, "3"),
('Mario', 'Apellido1', 'Correo7@domino.es', '123456', CURRENT_TIMESTAMP(), 42, "3"),
('Dani', 'Apellido1', 'Correo8@domino.es', '123456', CURRENT_TIMESTAMP(), 23, "1"),
('Carla', 'Apellido1', 'Correo9@domino.es', '123456', CURRENT_TIMESTAMP(), 30, "1");

INSERT INTO `mensajeria`.`pedido` (`id_articulo`, `id_provincia`, `id_cliente`, `id_repartidor`, `fecha_entrega`, `num_articulos`) 
VALUES 	(1, 42, 2, null, null, 12), 
		(2, 24, 3, null, null, 2), 
		(3, 30, 4, 8, null, 1), 
		(4, 10, 2, 9, null, 20), 
		(5, 1, 3, 8, CURRENT_TIMESTAMP(), 4), 
		(6, 2, 4, 9, CURRENT_TIMESTAMP(), 11);