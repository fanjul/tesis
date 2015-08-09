

CREATE DATABASE IF NOT EXISTS `movedb`;
USE `movedb`;

#
# Table structure for table 'EstadosXTipoIndicador'
#

DROP TABLE IF EXISTS `EstadosXTipoIndicador`;

CREATE TABLE `EstadosXTipoIndicador` (
  `IdTipoIndicador` INTEGER NOT NULL, 
  `IdEstado` INTEGER NOT NULL, 
  `Estado` VARCHAR(255), 
  `RepresentacionCromatica` VARCHAR(255), 
  `Observaciones` LONGTEXT, 
  INDEX (`IdEstado`), 
  INDEX (`IdTipoIndicador`), 
  PRIMARY KEY (`IdTipoIndicador`, `IdEstado`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'EstadosXTipoIndicador'
#

INSERT INTO `EstadosXTipoIndicador` (`IdTipoIndicador`, `IdEstado`, `Estado`, `RepresentacionCromatica`, `Observaciones`) VALUES (1, 1, 'Crítico', 'Rojo', NULL);
# 1 records

#
# Table structure for table 'Grafico'
#

DROP TABLE IF EXISTS `Grafico`;

CREATE TABLE `Grafico` (
  `Id` INTEGER NOT NULL AUTO_INCREMENT, 
  `TipoGrafico` VARCHAR(255), 
  `Observaciones` VARCHAR(255), 
  PRIMARY KEY (`Id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'Grafico'
#

# 0 records

#
# Table structure for table 'HSTUmbralesXEstadosXIndicador'
#

DROP TABLE IF EXISTS `HSTUmbralesXEstadosXIndicador`;

CREATE TABLE `HSTUmbralesXEstadosXIndicador` (
  `IdIndicador` INTEGER NOT NULL, 
  `IdEstadoTipoIndicador` INTEGER NOT NULL, 
  `InicioUmbral` DATETIME NOT NULL, 
  `FinUmbral` DATETIME NOT NULL, 
  `OperadorUmbralSuperior` VARCHAR(255), 
  `ValorUmbralSuperior` DOUBLE NULL, 
  `OperadorUmbralInferior` VARCHAR(255), 
  `ValorUmbralInferior` DOUBLE NULL, 
  `Observaciones` LONGTEXT, 
  INDEX (`IdEstadoTipoIndicador`), 
  PRIMARY KEY (`IdIndicador`, `IdEstadoTipoIndicador`, `InicioUmbral`, `FinUmbral`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'HSTUmbralesXEstadosXIndicador'
#

INSERT INTO `HSTUmbralesXEstadosXIndicador` (`IdIndicador`, `IdEstadoTipoIndicador`, `InicioUmbral`, `FinUmbral`, `OperadorUmbralSuperior`, `ValorUmbralSuperior`, `OperadorUmbralInferior`, `ValorUmbralInferior`, `Observaciones`) VALUES (1, 1, '2013-01-01 00:00:00', '0213-12-31 00:00:00', '<', 20, '>=', 40, NULL);
# 1 records

#
# Table structure for table 'Indicador'
#

DROP TABLE IF EXISTS `Indicador`;

CREATE TABLE `Indicador` (
  `Id` DECIMAL(18,0) NOT NULL, 
  `Codigo` VARCHAR(255), 
  `Nombre` VARCHAR(60), 
  `IdUnidadDeMedida` INTEGER, 
  `Direccion` INTEGER, 
  `Formula` VARCHAR(100), 
  `FichaMetodologica` LONGTEXT, 
  `IdGrafico` INTEGER, 
  `IdResponsable` INTEGER, 
  `Frecuencia` VARCHAR(50), 
  `Periodo` VARCHAR(50), 
  `FechaUltimaActualizacion` DATETIME, 
  `IdTipoIndicador` INTEGER, 
  `Observaciones` LONGTEXT, 
  INDEX (`Id`), 
  INDEX (`IdUnidadDeMedida`), 
  PRIMARY KEY (`Id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'Indicador'
#

# 0 records

#
# Table structure for table 'Persona'
#

DROP TABLE IF EXISTS `Persona`;

CREATE TABLE `Persona` (
  `Id` INTEGER NOT NULL AUTO_INCREMENT, 
  `Nombre` VARCHAR(255), 
  `Apellido` VARCHAR(255), 
  `email` VARCHAR(255), 
  `TipoDocumento` VARCHAR(255), 
  `NroDocumento` VARCHAR(255), 
  `Cargo` VARCHAR(255), 
  `Observaciones` VARCHAR(255), 
  PRIMARY KEY (`Id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'Persona'
#

# 0 records

#
# Table structure for table 'TipoIndicador'
#

DROP TABLE IF EXISTS `TipoIndicador`;

CREATE TABLE `TipoIndicador` (
  `ID` INTEGER NOT NULL AUTO_INCREMENT, 
  `Tipo` VARCHAR(255), 
  `Observaciones` LONGTEXT, 
  INDEX (`ID`), 
  PRIMARY KEY (`ID`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'TipoIndicador'
#

# 0 records

#
# Table structure for table 'UmbralesXEstadosXIndicador'
#

DROP TABLE IF EXISTS `UmbralesXEstadosXIndicador`;

CREATE TABLE `UmbralesXEstadosXIndicador` (
  `IdIndicador` INTEGER NOT NULL, 
  `IdEstadoTipoIndicador` INTEGER NOT NULL, 
  `InicioUmbral` DATETIME, 
  `FinUmbral` DATETIME, 
  `OperadorUmbralSuperior` VARCHAR(255), 
  `ValorUmbralSuperior` DOUBLE NULL, 
  `OperadorUmbralInferior` VARCHAR(255), 
  `ValorUmbralInferior` DOUBLE NULL, 
  `Observaciones` LONGTEXT, 
  INDEX (`IdEstadoTipoIndicador`), 
  PRIMARY KEY (`IdIndicador`, `IdEstadoTipoIndicador`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'UmbralesXEstadosXIndicador'
#

INSERT INTO `UmbralesXEstadosXIndicador` (`IdIndicador`, `IdEstadoTipoIndicador`, `InicioUmbral`, `FinUmbral`, `OperadorUmbralSuperior`, `ValorUmbralSuperior`, `OperadorUmbralInferior`, `ValorUmbralInferior`, `Observaciones`) VALUES (1, 1, '2014-01-01 00:00:00', '2014-01-31 00:00:00', '<', 30, '>=', 50, NULL);
# 1 records

#
# Table structure for table 'UnidadesDeMedida'
#

DROP TABLE IF EXISTS `UnidadesDeMedida`;

CREATE TABLE `UnidadesDeMedida` (
  `Id` INTEGER NOT NULL AUTO_INCREMENT, 
  `UnidadDeMedida` VARCHAR(255), 
  `Observaciones` LONGTEXT, 
  PRIMARY KEY (`Id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'UnidadesDeMedida'
#

INSERT INTO `UnidadesDeMedida` (`Id`, `UnidadDeMedida`, `Observaciones`) VALUES (1, 'Día', NULL);
INSERT INTO `UnidadesDeMedida` (`Id`, `UnidadDeMedida`, `Observaciones`) VALUES (2, 'Semana', NULL);
INSERT INTO `UnidadesDeMedida` (`Id`, `UnidadDeMedida`, `Observaciones`) VALUES (3, 'Mes', NULL);
INSERT INTO `UnidadesDeMedida` (`Id`, `UnidadDeMedida`, `Observaciones`) VALUES (4, 'Año', NULL);
# 4 records

#
# Table structure for table 'ValorIndicador'
#

DROP TABLE IF EXISTS `ValorIndicador`;

CREATE TABLE `ValorIndicador` (
  `IdIndicador` INTEGER NOT NULL, 
  `Fecha` DATETIME NOT NULL, 
  `Valor` DOUBLE NULL, 
  `Estado` VARCHAR(255), 
  `Variacion` DOUBLE NULL, 
  `SignoVariacion` INTEGER, 
  `Observaciones` LONGTEXT, 
  INDEX (`IdIndicador`), 
  PRIMARY KEY (`IdIndicador`, `Fecha`)
) ENGINE=myisam DEFAULT CHARSET=utf8;

SET autocommit=1;

#
# Dumping data for table 'ValorIndicador'
#

INSERT INTO `ValorIndicador` (`IdIndicador`, `Fecha`, `Valor`, `Estado`, `Variacion`, `SignoVariacion`, `Observaciones`) VALUES (1, '2013-12-30 00:00:00', 75, NULL, NULL, NULL, NULL);
# 1 records

