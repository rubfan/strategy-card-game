CREATE TABLE `User` (
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `username`     varchar(16)  NOT NULL UNIQUE,
    `password` varchar(16)  NOT NULL,
    `token`    varchar(128) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Account` (
    `id`   INT   NOT NULL AUTO_INCREMENT,
    `rank` FLOAT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Room` (
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        varchar(64)  NOT NULL,
    `description` varchar(128) NOT NULL,
    `min_rank`    FLOAT        NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Account_Room` (
    `id`              INT NOT NULL AUTO_INCREMENT,
    `room_id`         INT NULL,
    `account_1_id`    INT NULL,
    `account_2_id`    INT NULL,
    `start_game_time` DATETIME,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Account_Building` (
    `id`          INT   NOT NULL AUTO_INCREMENT,
    `account_id`  INT   NOT NULL,
    `building_id` INT   NOT NULL,
    `amount`      FLOAT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Account_Resource` (
    `id`          INT   NOT NULL AUTO_INCREMENT,
    `account_id`  INT   NOT NULL,
    `resource_id` INT   NOT NULL,
    `amount`      FLOAT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Account_Upgrade` (
    `id`         INT   NOT NULL AUTO_INCREMENT,
    `account_id` INT   NOT NULL,
    `upgrade_id` INT   NOT NULL,
    `amount`     FLOAT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Account_Achievement` (
    `id`             INT   NOT NULL AUTO_INCREMENT,
    `account_id`     INT   NOT NULL,
    `achievement_id` INT   NOT NULL,
    `amount`         FLOAT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Account_Notification` (
    `id`              INT NOT NULL AUTO_INCREMENT,
    `account_id`      INT NOT NULL,
    `notification_id` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Message` (
     `id`              INT          NOT NULL AUTO_INCREMENT,
     `text`            varchar(128) NOT NULL,
     `from_account_id` INT          NOT NULL,
     `to_account_id`   INT          NOT NULL,
     `time`            DATETIME     NOT NULL,
     PRIMARY KEY (`id`)
);

CREATE TABLE `Notification` (
    `id`               INT          NOT NULL AUTO_INCREMENT,
    `name`             varchar(64)  NOT NULL,
    `description`      varchar(128) NOT NULL,
    `show_from_scrach` INT          NULL,
    `resource_set_id`  INT          NULL,
    `building_set_id`  INT          NULL,
    `upgrade_set_id`   INT          NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Building` (
    `id`              INT          NOT NULL AUTO_INCREMENT,
    `name`            varchar(64)  NOT NULL,
    `description`     varchar(128) NOT NULL,
    `default_number`  INT          NULL,
    `resource_set_id` INT          NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Resource` (
    `id`             INT          NOT NULL AUTO_INCREMENT,
    `name`           varchar(64)  NOT NULL,
    `description`    varchar(128) NOT NULL,
    `default_number` INT          NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Upgrade` (
    `id`              INT          NOT NULL AUTO_INCREMENT,
    `name`            varchar(64)  NOT NULL,
    `description`     varchar(128) NOT NULL,
    `default_number`  INT          NULL,
    `resource_set_id` INT          NULL,
    `building_set_id` INT          NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Achievement` (
    `id`              INT          NOT NULL AUTO_INCREMENT,
    `name`            varchar(64)  NOT NULL,
    `description`     varchar(128) NOT NULL,
    `resource_set_id` INT          NULL,
    `building_set_id` INT          NULL,
    `upgrade_set_id`  INT          NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Card` (
    `id`                     INT          NOT NULL AUTO_INCREMENT,
    `name`                   varchar(64)  NOT NULL,
    `description`            varchar(128) NOT NULL,
    `card_group_id`          INT          NOT NULL,
    `player_resource_set_id` INT          NULL,
    `player_building_set_id` INT          NULL,
    `player_upgrade_set_id`  INT          NULL,
    `enemy_resource_set_id`  INT          NULL,
    `enemy_building_set_id`  INT          NULL,
    `enemy_upgrade_set_id`   INT          NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Card_Group` (
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        varchar(64)  NOT NULL,
    `description` varchar(128) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Building_Set` (
    `id`          INT NOT NULL AUTO_INCREMENT,
    `set_id`      INT,
    `building_id` INT NOT NULL,
    `amount`      FLOAT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Resource_Set` (
    `id`          INT NOT NULL AUTO_INCREMENT,
    `set_id`      INT,
    `resource_id` INT NOT NULL,
    `amount`      FLOAT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Upgrade_Set` (
    `id`         INT NOT NULL AUTO_INCREMENT,
    `set_id`     INT,
    `upgrade_id` INT NOT NULL,
    `amount`     FLOAT,
    PRIMARY KEY (`id`)
);