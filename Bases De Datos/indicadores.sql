-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 07-10-2015 a las 17:34:14
-- Versión del servidor: 5.6.12-log
-- Versión de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `indicadores`
--
CREATE DATABASE IF NOT EXISTS `indicadores` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `indicadores`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadosxtipoindicador`
--

CREATE TABLE IF NOT EXISTS `estadosxtipoindicador` (
  `idEstado` int(11) NOT NULL,
  `idTipoIndicador` int(11) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `representacionCromatica` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idEstado`,`idTipoIndicador`),
  KEY `FK_estadosXTipoIndicador_tipoIndicador` (`idTipoIndicador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grafico`
--

CREATE TABLE IF NOT EXISTS `grafico` (
  `id` int(11) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `tipoGrafico` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hstumbralesxestadosxindicador`
--

CREATE TABLE IF NOT EXISTS `hstumbralesxestadosxindicador` (
  `finUmbral` datetime NOT NULL,
  `idEstadoTipoIndicador` int(11) NOT NULL,
  `idIndicador` int(11) NOT NULL,
  `inicioUmbral` datetime NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `operadorUmbralInferior` varchar(255) DEFAULT NULL,
  `operadorUmbralSuperior` varchar(255) DEFAULT NULL,
  `valorUmbralInferior` double DEFAULT NULL,
  `valorUmbralSuperior` double DEFAULT NULL,
  PRIMARY KEY (`finUmbral`,`idEstadoTipoIndicador`,`idIndicador`,`inicioUmbral`),
  KEY `FK_HSTumbralesXEstadosXIndicador_tipoIndicador` (`idEstadoTipoIndicador`),
  KEY `FK_HSTumbralesXEstadosXIndicador_indicador` (`idIndicador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `indicador`
--

CREATE TABLE IF NOT EXISTS `indicador` (
  `id` int(11) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `direccion` int(11) DEFAULT NULL,
  `fechaUltimaActualizacion` datetime DEFAULT NULL,
  `fichaMetodologica` varchar(255) DEFAULT NULL,
  `formula` varchar(255) DEFAULT NULL,
  `frecuencia` varchar(255) DEFAULT NULL,
  `idGrafico` int(11) DEFAULT NULL,
  `idResponsable` int(11) DEFAULT NULL,
  `idTipoIndicador` int(11) DEFAULT NULL,
  `idUnidadDeMedida` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `periodo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_indicador_grafico` (`idGrafico`),
  KEY `FK_indicador_persona` (`idResponsable`),
  KEY `FK_indicador_tipoIndicador` (`idTipoIndicador`),
  KEY `FK_indicador_unidadDeMedida` (`idUnidadDeMedida`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE IF NOT EXISTS `persona` (
  `id` int(11) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `nroDocumento` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `tipoDocumento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tablero`
--

CREATE TABLE IF NOT EXISTS `tablero` (
  `id` int(11) NOT NULL,
  `fechaUltimaActualizacion` datetime DEFAULT NULL,
  `idIndicador` int(11) DEFAULT NULL,
  `idResponsable` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tablero_indicador` (`idIndicador`),
  KEY `FK_tablero_persona` (`idResponsable`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoindicador`
--

CREATE TABLE IF NOT EXISTS `tipoindicador` (
  `id` int(11) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `umbralesxestadosxindicador`
--

CREATE TABLE IF NOT EXISTS `umbralesxestadosxindicador` (
  `idEstadoTipoIndicador` int(11) NOT NULL,
  `idIndicador` int(11) NOT NULL,
  `finUmbral` datetime DEFAULT NULL,
  `inicioUmbral` datetime DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `operadorUmbralInferior` varchar(255) DEFAULT NULL,
  `operadorUmbralSuperior` varchar(255) DEFAULT NULL,
  `valorUmbralInferior` double DEFAULT NULL,
  `valorUmbralSuperior` double DEFAULT NULL,
  PRIMARY KEY (`idEstadoTipoIndicador`,`idIndicador`),
  KEY `FK_umbralesXEstadosXIndicador_indicador` (`idIndicador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `unidadesdemedida`
--

CREATE TABLE IF NOT EXISTS `unidadesdemedida` (
  `id` int(11) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `unidadDeMedida` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valorindicador`
--

CREATE TABLE IF NOT EXISTS `valorindicador` (
  `fecha` datetime NOT NULL,
  `idIndicador` int(11) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `signoVariacion` int(11) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `variacion` double DEFAULT NULL,
  PRIMARY KEY (`fecha`,`idIndicador`),
  KEY `FK_valorIndicador_indicador` (`idIndicador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `estadosxtipoindicador`
--
ALTER TABLE `estadosxtipoindicador`
  ADD CONSTRAINT `FK_estadosXTipoIndicador_tipoIndicador` FOREIGN KEY (`idTipoIndicador`) REFERENCES `tipoindicador` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `hstumbralesxestadosxindicador`
--
ALTER TABLE `hstumbralesxestadosxindicador`
  ADD CONSTRAINT `FK_HSTumbralesXEstadosXIndicador_indicador` FOREIGN KEY (`idIndicador`) REFERENCES `indicador` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_HSTumbralesXEstadosXIndicador_tipoIndicador` FOREIGN KEY (`idEstadoTipoIndicador`) REFERENCES `tipoindicador` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `indicador`
--
ALTER TABLE `indicador`
  ADD CONSTRAINT `FK_indicador_unidadDeMedida` FOREIGN KEY (`idUnidadDeMedida`) REFERENCES `unidadesdemedida` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_indicador_grafico` FOREIGN KEY (`idGrafico`) REFERENCES `grafico` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_indicador_persona` FOREIGN KEY (`idResponsable`) REFERENCES `persona` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_indicador_tipoIndicador` FOREIGN KEY (`idTipoIndicador`) REFERENCES `tipoindicador` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `tablero`
--
ALTER TABLE `tablero`
  ADD CONSTRAINT `FK_tablero_persona` FOREIGN KEY (`idResponsable`) REFERENCES `persona` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_tablero_indicador` FOREIGN KEY (`idIndicador`) REFERENCES `indicador` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `umbralesxestadosxindicador`
--
ALTER TABLE `umbralesxestadosxindicador`
  ADD CONSTRAINT `FK_umbralesXEstadosXIndicador_indicador` FOREIGN KEY (`idIndicador`) REFERENCES `indicador` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_umbralesXEstadosXIndicador_tipoIndicador` FOREIGN KEY (`idEstadoTipoIndicador`) REFERENCES `tipoindicador` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `valorindicador`
--
ALTER TABLE `valorindicador`
  ADD CONSTRAINT `FK_valorIndicador_indicador` FOREIGN KEY (`idIndicador`) REFERENCES `indicador` (`id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
