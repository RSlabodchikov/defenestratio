-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `defenestratio` DEFAULT CHARACTER SET latin1;
USE `defenestratio`;

-- -----------------------------------------------------
-- Table `defenestratio`.`challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`challenge`
(
    `id`          INT(11) NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45) NULL DEFAULT NULL,
    `short_description` varchar(255)    NULL DEFAULT NULL,
    `full_description` varchar(255)    NULL DEFAULT NULL,
    `level`       INT(11)     NULL DEFAULT NULL,
    `points`      INT(11)     NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `defenestratio`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`images`
(
    `id`      INT(11) NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(45) NULL DEFAULT NULL,
    `type`    VARCHAR(45) NULL DEFAULT NULL,
    `picture` LONGBLOB    NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `defenestratio`.`challenge_results`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`challenge_results`
(
    `id`       INT(11) NOT NULL AUTO_INCREMENT,
    `image_id` INT(11) NULL,
    `approved` TINYINT(1)  NOT NULL,
    `comment`  VARCHAR(45) NULL     DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `result_image_idx` (`image_id` ASC),
    CONSTRAINT `result_image`
        FOREIGN KEY (`image_id`)
            REFERENCES `defenestratio`.`images` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `defenestratio`.`profiles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`profiles`
(
    `id`           INT(11) NOT NULL AUTO_INCREMENT,
    `first_name`    VARCHAR(45) NULL DEFAULT NULL,
    `last_name`     VARCHAR(45) NULL DEFAULT NULL,
    `total_points` INT(11)     NULL DEFAULT NULL,
    `rating`       INT(11)     NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;



-- -----------------------------------------------------
-- Table `defenestratio`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`users`
(
    `id`         INT(11) NOT NULL AUTO_INCREMENT,
    `username`   VARCHAR(45) NOT NULL,
    `password`   VARCHAR(45) NOT NULL,
    `role`       VARCHAR(60) NOT NULL,
    `disabled`   TINYINT(1)  NOT NULL,
    `profile_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `user_profile_id_idx` (`profile_id` ASC),
    CONSTRAINT `user_profile_id`
        FOREIGN KEY (`profile_id`)
            REFERENCES `defenestratio`.`profiles` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `defenestratio`.`user_challenges`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `defenestratio`.`user_challenges`
(
    `id`           INT(11) NOT NULL AUTO_INCREMENT,
    `user_id`      INT(11) NOT NULL,
    `challenge_id` INT(11) NOT NULL,
    `status`       VARCHAR(45) NOT NULL,
    `due_date`     DATETIME    NULL DEFAULT NULL,
    `result_id`    INT(11) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `user_challenges_user_idx` (`user_id` ASC),
    INDEX `user_challenges_challenge_idx` (`challenge_id` ASC),
    INDEX `user_challenges_result_idx` (`result_id` ASC),
    CONSTRAINT `user_challenges_challenge`
        FOREIGN KEY (`challenge_id`)
            REFERENCES `defenestratio`.`challenge` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `user_challenges_result`
        FOREIGN KEY (`result_id`)
            REFERENCES `defenestratio`.`challenge_results` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `user_challenges_user`
        FOREIGN KEY (`user_id`)
            REFERENCES `defenestratio`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;
