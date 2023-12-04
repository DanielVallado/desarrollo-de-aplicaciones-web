-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema uadybank
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema uadybank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `uadybank` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `uadybank` ;

-- -----------------------------------------------------
-- Table `uadybank`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uadybank`.`clients` (
  `matricula` VARCHAR(8) NOT NULL,
  `creation_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` VARCHAR(255) NOT NULL,
  `verified` TINYINT(1) NOT NULL DEFAULT '0',
  `name` VARCHAR(255) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `phone_number` VARCHAR(15) NOT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT '1',
  `address` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`matricula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `uadybank`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uadybank`.`accounts` (
  `id_account` BIGINT NOT NULL AUTO_INCREMENT,
  `status` TINYINT(1) NOT NULL DEFAULT '1',
  `matricula` VARCHAR(8) NULL DEFAULT NULL,
  PRIMARY KEY (`id_account`),
  UNIQUE INDEX `UK_dhqsdpwi8sb8sei91e9wcma51` (`matricula` ASC) VISIBLE,
  CONSTRAINT `FKsjg6dwhn7solll34rmgkb066p`
    FOREIGN KEY (`matricula`)
    REFERENCES `uadybank`.`clients` (`matricula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `uadybank`.`cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uadybank`.`cards` (
  `id_card` VARCHAR(16) NOT NULL,
  `balance` DECIMAL(13,4) NOT NULL DEFAULT '0',
  `creation_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` TINYINT(1) NOT NULL DEFAULT '1',
  `card_type` VARCHAR(255) NOT NULL DEFAULT 'classic',
  `vip` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_card`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `uadybank`.`accounts_cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uadybank`.`accounts_cards` (
  `account_id_account` BIGINT NOT NULL,
  `cards_id_card` VARCHAR(16) NOT NULL,
  UNIQUE INDEX `UK_b4mm68j24nsqv1sbg7ed5exh6` (`cards_id_card` ASC) VISIBLE,
  INDEX `FK4xi12dum6aal8ocecobaqtu8a` (`account_id_account` ASC) VISIBLE,
  CONSTRAINT `FK4xi12dum6aal8ocecobaqtu8a`
    FOREIGN KEY (`account_id_account`)
    REFERENCES `uadybank`.`accounts` (`id_account`),
  CONSTRAINT `FK6t5rckw368cmwn6nijnxep6pv`
    FOREIGN KEY (`cards_id_card`)
    REFERENCES `uadybank`.`cards` (`id_card`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `uadybank`.`administrators`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uadybank`.`administrators` (
  `id_employee` BIGINT NOT NULL AUTO_INCREMENT,
  `creation_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` VARCHAR(255) NOT NULL,
  `verified` TINYINT(1) NOT NULL DEFAULT '0',
  `name` VARCHAR(255) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `phone_number` VARCHAR(15) NOT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_employee`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `uadybank`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uadybank`.`transactions` (
  `id_transaction` BIGINT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(13,4) NOT NULL DEFAULT '0',
  `description` VARCHAR(255) NULL DEFAULT '',
  `transaction_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `transaction_type` VARCHAR(255) NOT NULL DEFAULT '',
  `destination` VARCHAR(16) NULL DEFAULT '0',
  PRIMARY KEY (`id_transaction`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `uadybank`.`cards_transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uadybank`.`cards_transactions` (
  `card_id_card` VARCHAR(16) NOT NULL,
  `transactions_id_transaction` BIGINT NOT NULL,
  UNIQUE INDEX `UK_ewjsbb570ehphhdsll05p0y2m` (`transactions_id_transaction` ASC) VISIBLE,
  INDEX `FK2sh4two600g30s5er07xwym6v` (`card_id_card` ASC) VISIBLE,
  CONSTRAINT `FK2sh4two600g30s5er07xwym6v`
    FOREIGN KEY (`card_id_card`)
    REFERENCES `uadybank`.`cards` (`id_card`),
  CONSTRAINT `FKiv62qe2uoe73sv6urx8icymso`
    FOREIGN KEY (`transactions_id_transaction`)
    REFERENCES `uadybank`.`transactions` (`id_transaction`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
