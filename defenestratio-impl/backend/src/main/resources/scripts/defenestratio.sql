-- -----------------------------------------------------
-- Table `defenestratio`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`users` (
                                                       `id` VARCHAR(60) NOT NULL,
                                                       `username` VARCHAR(45) NOT NULL,
                                                       `password` VARCHAR(45) NOT NULL,
                                                       `disabled` TINYINT(1) NULL DEFAULT 0,
                                                       PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `defenestratio`.`profiles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`profiles` (
                                                          `id` VARCHAR(60) NOT NULL,
                                                          `firstname` VARCHAR(45) NULL,
                                                          `lastname` VARCHAR(45) NULL,
                                                          `total_points` INT NULL,
                                                          `rating` INT NULL,
                                                          `user_id` VARCHAR(60) NOT NULL,
                                                          PRIMARY KEY (`id`),
                                                          INDEX `profile_user_id_idx` (`user_id` ASC),
                                                          CONSTRAINT `profile_user_id`
                                                              FOREIGN KEY (`user_id`)
                                                                  REFERENCES `defenestratio`.`users` (`id`)
                                                                  ON DELETE CASCADE
                                                                  ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `defenestratio`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`roles` (
                                                       `id` VARCHAR(60) NOT NULL,
                                                       `name` VARCHAR(45) NULL,
                                                       `description` TEXT(200) NULL,
                                                       `user_id` VARCHAR(60) NOT NULL,
                                                       PRIMARY KEY (`id`),
                                                       INDEX `role_user_id_idx` (`user_id` ASC),
                                                       CONSTRAINT `role_user_id`
                                                           FOREIGN KEY (`user_id`)
                                                               REFERENCES `defenestratio`.`users` (`id`)
                                                               ON DELETE NO ACTION
                                                               ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `defenestratio`.`challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`challenge` (
                                                           `id` VARCHAR(60) NOT NULL,
                                                           `name` VARCHAR(45) NULL,
                                                           `description` TEXT(200) NULL,
                                                           `level` INT NULL,
                                                           `points` INT NULL,
                                                           PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `defenestratio`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`images` (
                                                        `id` VARCHAR(60) NOT NULL,
                                                        `name` VARCHAR(45) NULL,
                                                        `type` VARCHAR(45) NULL,
                                                        `picture` LONGBLOB NULL,
                                                        PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `defenestratio`.`challenge_results`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`challenge_results` (
                                                                   `id` VARCHAR(60) NOT NULL,
                                                                   `image_id` VARCHAR(60) NOT NULL,
                                                                   `approved` TINYINT(1) NOT NULL DEFAULT 1,
                                                                   `comment` VARCHAR(45) NULL,
                                                                   PRIMARY KEY (`id`),
                                                                   INDEX `result_image_idx` (`image_id` ASC),
                                                                   CONSTRAINT `result_image`
                                                                       FOREIGN KEY (`image_id`)
                                                                           REFERENCES `defenestratio`.`images` (`id`)
                                                                           ON DELETE NO ACTION
                                                                           ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `defenestratio`.`user_challenges`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`user_challenges` (
                                                                 `id` VARCHAR(60) NOT NULL,
                                                                 `user_id` VARCHAR(60) NOT NULL,
                                                                 `challenge_id` VARCHAR(60) NOT NULL,
                                                                 `status` VARCHAR(45) NOT NULL,
                                                                 `due date` DATETIME NULL,
                                                                 `result_id` VARCHAR(60) NULL,
                                                                 PRIMARY KEY (`id`),
                                                                 INDEX `user_challenges_user_idx` (`user_id` ASC),
                                                                 INDEX `user_challenges_challenge_idx` (`challenge_id` ASC),
                                                                 INDEX `user_challenges_result_idx` (`result_id` ASC),
                                                                 CONSTRAINT `user_challenges_user`
                                                                     FOREIGN KEY (`user_id`)
                                                                         REFERENCES `defenestratio`.`users` (`id`)
                                                                         ON DELETE NO ACTION
                                                                         ON UPDATE NO ACTION,
                                                                 CONSTRAINT `user_challenges_challenge`
                                                                     FOREIGN KEY (`challenge_id`)
                                                                         REFERENCES `defenestratio`.`challenge` (`id`)
                                                                         ON DELETE NO ACTION
                                                                         ON UPDATE NO ACTION,
                                                                 CONSTRAINT `user_challenges_result`
                                                                     FOREIGN KEY (`result_id`)
                                                                         REFERENCES `defenestratio`.`challenge_results` (`id`)
                                                                         ON DELETE NO ACTION
                                                                         ON UPDATE NO ACTION)
    ENGINE = InnoDB;
