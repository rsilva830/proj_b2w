CREATE DATABASE IF NOT EXISTS `planetas` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `planetas`;

DROP TABLE IF EXISTS `planeta`;
CREATE TABLE IF NOT EXISTS `planeta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `clima` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `terreno` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_publico` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;






