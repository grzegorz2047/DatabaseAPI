CREATE TABLE IF NOT EXISTS `Messages` (
  `msgid` bigint(20) NOT NULL AUTO_INCREMENT,
  `minigame` varchar(16) CHARACTER SET latin1 NOT NULL,
  `language` varchar(5) CHARACTER SET latin1 NOT NULL,
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `message` text CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`msgid`),
  KEY `minigame` (`minigame`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `JakiesIdTrybuMoney` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `money` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userid` (`userid`),
  KEY `userid_2` (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `JakiesIdTrybuShopItems` (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `material` varchar(32) NOT NULL,
  `amount` int(11) NOT NULL,
  `ench1` varchar(32) DEFAULT NULL,
  `ench2` varchar(32) DEFAULT NULL,
  `type` varchar(4) NOT NULL,
  `slot` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `data` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `itemid` (`itemid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `JakiesIdTrybuShopItemsPurchased` (
  `userid` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `purchasetime` bigint(20) NOT NULL,
  PRIMARY KEY (`userid`,`itemid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `JakiesIdTrybuStats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `wins` int(11) NOT NULL,
  `lose` int(11) NOT NULL,
  `kills` int(11) NOT NULL,
  `deaths` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userid` (`userid`),
  KEY `userid_2` (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `Settings` (
  `settingsid` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(32) NOT NULL,
  `value` varchar(120) CHARACTER SET utf8 NOT NULL,
  `type` varchar(32) NOT NULL,
  PRIMARY KEY (`settingsid`),
  UNIQUE KEY `path` (`path`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
