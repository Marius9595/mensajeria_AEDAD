-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`articulo` (
  `id_articulo` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id_articulo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`provincia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`provincia` (
  `id_provincia` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`id_provincia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `id_usuario` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Apellidos` VARCHAR(45) NULL,
  `fecha_ultima_conection` DATETIME NOT NULL,
  `id_provincia` INT NOT NULL,
  `permisos` ENUM("0", "1", "2", "3") NOT NULL,
  PRIMARY KEY (`id_usuario`),
  INDEX `fk_usuario_provincia1_idx` (`id_provincia` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_provincia1`
    FOREIGN KEY (`id_provincia`)
    REFERENCES `mydb`.`provincia` (`id_provincia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pedido` (
  `id_articulo` INT NOT NULL,
  `provincia_id_provincia` INT NOT NULL,
  `id_cliente` INT NOT NULL,
  `id_repartidor` INT NULL,
  `fecha_entrega` DATETIME NULL,
  `num_articulos` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_articulo`, `id_cliente`),
  INDEX `fk_cliente_has_articulo_articulo1_idx` (`id_articulo` ASC) VISIBLE,
  INDEX `fk_pedido_provincia1_idx` (`provincia_id_provincia` ASC) VISIBLE,
  INDEX `fk_pedido_usuario1_idx` (`id_cliente` ASC) VISIBLE,
  INDEX `fk_pedido_usuario2_idx` (`id_repartidor` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_has_articulo_articulo1`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `mydb`.`articulo` (`id_articulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_provincia1`
    FOREIGN KEY (`provincia_id_provincia`)
    REFERENCES `mydb`.`provincia` (`id_provincia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_usuario1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `mydb`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_usuario2`
    FOREIGN KEY (`id_repartidor`)
    REFERENCES `mydb`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
