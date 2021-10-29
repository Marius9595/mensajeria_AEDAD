-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mensajeria
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mensajeria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mensajeria` DEFAULT CHARACTER SET utf8 ;
USE `mensajeria` ;

-- -----------------------------------------------------
-- Table `mensajeria`.`articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mensajeria`.`articulo` (
  `id_articulo` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id_articulo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mensajeria`.`provincia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mensajeria`.`provincia` (
  `id_provincia` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`id_provincia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mensajeria`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mensajeria`.`usuario` (
  `id_usuario` INT NOT NULL,
  `Nombre` VARCHAR(50) NULL,
  `Apellidos` VARCHAR(150) NULL,
  `Correo` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NULL,
  `fecha_ultima_conection` DATETIME NOT NULL,
  `id_provincia` INT NOT NULL,
  `permisos` ENUM("0", "1", "2", "3") NOT NULL,
  PRIMARY KEY (`id_usuario`),
  INDEX `fk_usuario_provincia1_idx` (`id_provincia` ASC),
  CONSTRAINT `fk_usuario_provincia1`
    FOREIGN KEY (`id_provincia`)
    REFERENCES `provincia` (`id_provincia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mensajeria`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mensajeria`.`pedido` (
  `id_articulo` INT NOT NULL,
  `id_provincia` INT NOT NULL,
  `id_cliente` INT NOT NULL,
  `id_repartidor` INT NULL,
  `fecha_entrega` DATETIME NULL,
  `num_articulos` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_articulo`, `id_cliente`),
  INDEX `fk_cliente_has_articulo_articulo1_idx` (`id_articulo` ASC),
  INDEX `fk_pedido_provincia1_idx` (`id_provincia` ASC),
  INDEX `fk_pedido_usuario1_idx` (`id_cliente` ASC),
  INDEX `fk_pedido_usuario2_idx` (`id_repartidor` ASC),
  CONSTRAINT `fk_cliente_has_articulo_articulo1`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `mensajeria`.`articulo` (`id_articulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_provincia1`
    FOREIGN KEY (`id_provincia`)
    REFERENCES `mensajeria`.`provincia` (`id_provincia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_usuario1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `mensajeria`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_usuario2`
    FOREIGN KEY (`id_repartidor`)
    REFERENCES `mensajeria`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
