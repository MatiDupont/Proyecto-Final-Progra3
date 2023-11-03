CREATE DATABASE IF NOT EXISTS bd_gameplay;

USE bd_gameplay;

CREATE TABLE IF NOT EXISTS usuarios (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `usuario` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `contraseña` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `id_juego` INT NOT NULL,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS batalla (
    `id_historial` INT NOT NULL AUTO_INCREMENT,
    `id_juego` INT NOT NULL,
    `datos_historial` LONGTEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `usuario` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id_historial`)
);

CREATE TABLE IF NOT EXISTS cartas (
    `id_historial` INT NOT NULL AUTO_INCREMENT,
    `id_juego` INT NOT NULL,
    `cartasJ1` LONGTEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `cartasJ2` LONGTEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `fecha_hora` TIMESTAMP NOT NULL,
    `usuario` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id_historial`)
);