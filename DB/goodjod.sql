SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


use goodjod;

--
-- Estructura de tabla para la tabla `carreras`
--

CREATE TABLE `carreras` (
  `idPerfilProfesional` int(11) NOT NULL,
  `PPNombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `carreras` (`idPerfilProfesional`, `PPNombre`) VALUES
(1, 'Técnico de Fibra Óptica Planta Interna'),
(2, 'Técnico de Fibra Óptica Panta Externa'),
(3, 'Ingeniero de Redes'),
(4, 'Ingeniero de Tráfico de Red'),
(5, 'Ingeniero de Sistemas'),
(6, 'Especialista de conecterización de Fibra Óptica'),
(7, 'Especialista de MUFAS Ópticas'),
(8, 'Especialista de cableado Estructurado'),
(9, 'Técnico en Red Ftth');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distrito`
--

CREATE TABLE `distrito` (
  `idDistrito` int(11) NOT NULL,
  `DNombre` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `distrito` (`idDistrito`, `DNombre`) VALUES
(1, 'Callao'),
(2, 'Bellavista'),
(3, 'Carmen de La Legua'),
(4, 'La Perla'),
(5, 'La Punta'),
(6, 'Mi Perú'),
(7, 'Ventanilla'),
(8, 'Lima'),
(9, 'Ancón'),
(10, 'Ate'),
(11, 'Barranco'),
(12, 'Breña'),
(13, 'Carabayllo'),
(14, 'Cieneguilla'),
(15, 'Chaclacayo'),
(16, 'Chorrillos'),
(17, 'Comas'),
(18, 'El Agustino'),
(19, 'Independencia'),
(20, 'Jesús María'),
(21, 'La Molina'),
(22, 'La Victoria'),
(23, 'Lince'),
(24, 'Los Olivos'),
(25, 'Lurigancho'),
(26, 'Lurín'),
(27, 'Magdalena del Mar'),
(28, 'Miraflores'),
(29, 'Pachacámac'),
(30, 'Pucusana'),
(31, 'Pueblo Libre'),
(32, 'Puente Piedra'),
(33, 'Punta Hermosa'),
(34, 'Punta Negra'),
(35, 'Rímac'),
(36, 'San Bartolo'),
(37, 'San Borja'),
(38, 'San Isidro'),
(39, 'San Juan de Lurigancho'),
(40, 'San Juan de Miraflores'),
(41, 'San Luis'),
(42, 'San Martín de Porres'),
(43, 'San Miguel'),
(44, 'Santa Anita'),
(45, 'Santa María del Mar'),
(46, 'Santa Rosa'),
(47, 'Santiago de Surco'),
(48, 'Surquillo'),
(49, 'Villa El Salvador'),
(50, 'Villa María del Triunfo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `Eid` int(11) NOT NULL,
  `ErazonSocial` varchar(100) NOT NULL,
  `Eruc` varchar(12) NOT NULL,
  `Ecelular` varchar(9) NOT NULL,
  `idDistrito` int(11) DEFAULT NULL,
  `Edireccion` varchar(150) DEFAULT NULL,
  `EfechaRegistro` date NOT NULL,
  `EcodigoPostal` int(11) DEFAULT NULL,
  `Epass` varchar(50) NOT NULL,
  `Eestado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `empresa` (`Eid`, `ErazonSocial`, `Eruc`, `Ecelular`, `idDistrito`, `Edireccion`, `EfechaRegistro`, `EcodigoPostal`, `Epass`, `Eestado`) VALUES
(1, 'probando', '123456789789', '987987987', NULL, NULL, '2109-05-01', NULL, 'admincompanu', 1),
(2, 'Facebook', '978787878787', '963654852', NULL, NULL, '2019-07-09', NULL, 'admin', 1);

--
--
-- Estructura de tabla para la tabla `tipopremiun`
--

CREATE TABLE `tipopremiun` (
  `idTipoPremiun` int(11) NOT NULL,
  `TPnombre` varchar(100) NOT NULL,
  `TPdescripcion` varchar(500) NOT NULL,
  `TPprecio` double NOT NULL,
  `TPcantidadPost` int(11) NOT NULL,
  `TPfechaInicio` datetime NOT NULL,
  `TPfechaExpiracion` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL primary key auto_increment,
  `UFoto` blob DEFAULT NULL,
  `Unombre` varchar(50) NOT NULL,
  `UPaterno` varchar(50) NOT NULL,
  `UMaterno` varchar(50) NOT NULL,
  `Udni` varchar(8) DEFAULT NULL,
  `UPasaporte` bigint(20) DEFAULT NULL,
  `UfechaNacimiento` date DEFAULT NULL,
  `Ucelular` varchar(11) DEFAULT NULL,
  `idPerfilP` int(11) DEFAULT NULL,
  `Ucorreo` varchar(50) NOT NULL,
  `idDistrito` int(11) DEFAULT NULL,
  `Udireccion` varchar(150) DEFAULT NULL,
  `Upass` varchar(20) NOT NULL,
  `idTipoPremiun` int(11) DEFAULT NULL,
  `UfechaRegistro` date NOT NULL,
  actividades_disponibles int,
  publicaciones_disponibles int,
  puntaje int,
  reputacion_ptos decimal(8,2),
  cantidad_votos int,
  `Uestado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO usuario VALUES
(null, NULL, 'Juan Carlos', 'Castillo', 'Aycachi', '70555913', NULL, '1993-04-01', '979666355', 5, 'carlosjordi@hotmail.com', 42, 'Las Margaritas', 'admin', NULL, '2019-07-02', 2, 1, 0, 0, 0, 1);

INSERT INTO usuario VALUES
(null, NULL, 'Max Anthony', 'Cachi', 'Ayala', '63571099', NULL, '1999-01-03', '985863707', 5, 'anthonyca18m@gmail.com', 17, 'comas city #123', 'admin', NULL, '2019-06-25', 2, 1, 0, 0, 0, 1);

INSERT INTO usuario VALUES
(null, NULL, 'Gustavo Adolfo', 'Lizarzaburu', 'Mercado', null, NULL, null, '935902409', 5, 'lizarzaburu@gmail.com', 42, 'Ovalo Granda', 'admin', NULL, '2019-07-02', 2, 1, 70, 9, 2, 1);

INSERT INTO usuario VALUES
(null, NULL, 'Yaser Andre', 'Quinonez', 'Chaponan', null, NULL, null, '922721204', 5, 'yaser@gmail.com', 1, 'Lejos de casa', 'admin', NULL, '2019-07-02', 2, 1, 0, 0, 0, 1);

create table tipo_recompensa
(
	id int auto_increment primary key,
    descripcion varchar(30)
);

insert into tipo_recompensa values(null, 'Puntos');
insert into tipo_recompensa values(null, 'Dinero');

create table actividad
(
	id int auto_increment primary key,
    titulo varchar(30),
    descripcion varchar(140),
    id_usuario int,
    fecha_creacion date,
    fecha_fin date, 
    participantes_actuales int, 
    participantes_requeridos int,
    foto varchar(255) default null,
    tipo_recompensa int,
    recompensa decimal(10,2),
    estado int,
    foreign key(tipo_recompensa) references tipo_recompensa(id),
    foreign key(id_usuario) references usuario(idUsuario)
);

insert into actividad values (null, 'La playita bonita', 'Playa bastante contaminada, muchas personas acuden a esta, pero nadie se toma la molestia de recoger la basura', 1, '2019-07-08', '2019-07-13', 0, 5, null, 1, 100, 1);
insert into actividad values (null, 'Parque Huaqueno', 'Ubicado en el cruce de Av. universitaria con antunez de mayolo. Referencia frente a la botica', 1, '2019-07-09', '2019-07-12', 0, 2, null, 1, 50, 1);
insert into actividad values (null, 'Jr. La Camita', 'Ya no se me ocurrio que mas escribir aca, asi que esto solo es para hacer bulto', 1, '2019-07-09', '2019-07-11', 0, 3, null, 1, 70, 1);

create table postulacion_actividad
(
	id int auto_increment primary key,
    id_actividad int not null,
    id_usuario int not null,
    estado int, -- 1: en espera | 2: aceptado | 3: rechazado
    foreign key(id_actividad) references actividad(id),
    foreign key(id_usuario) references usuario(idUsuario)
);

--
-- Índices para tablas volcadas
--


--
-- Indices de la tabla `carreras`
--
ALTER TABLE `carreras`
  ADD PRIMARY KEY (`idPerfilProfesional`);

--
-- Indices de la tabla `distrito`
--
ALTER TABLE `distrito`
  ADD PRIMARY KEY (`idDistrito`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`Eid`),
  ADD KEY `idDistrito` (`idDistrito`);


--
-- Indices de la tabla `tipopremiun`
--
ALTER TABLE `tipopremiun`
  ADD PRIMARY KEY (`idTipoPremiun`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD UNIQUE KEY `Udni` (`Udni`),
  ADD KEY `idDistrito` (`idDistrito`),
  ADD KEY `idPerfilP` (`idPerfilP`),
  ADD KEY `idTipoPremiun` (`idTipoPremiun`);

--
-- AUTO_INCREMENT de las tablas volcadas
--


--
-- AUTO_INCREMENT de la tabla `carreras`
--
ALTER TABLE `carreras`
  MODIFY `idPerfilProfesional` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `distrito`
--
ALTER TABLE `distrito`
  MODIFY `idDistrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `Eid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;


--
-- AUTO_INCREMENT de la tabla `tipopremiun`
--
ALTER TABLE `tipopremiun`
  MODIFY `idTipoPremiun` int(11) NOT NULL AUTO_INCREMENT;


--
-- Restricciones para tablas volcadas
--


--
-- Filtros para la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD CONSTRAINT `empresa_ibfk_1` FOREIGN KEY (`idDistrito`) REFERENCES `distrito` (`idDistrito`);


--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idDistrito`) REFERENCES `distrito` (`idDistrito`),
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`idPerfilP`) REFERENCES `carreras` (`idPerfilProfesional`),
  ADD CONSTRAINT `usuario_ibfk_3` FOREIGN KEY (`idTipoPremiun`) REFERENCES `tipopremiun` (`idTipoPremiun`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
