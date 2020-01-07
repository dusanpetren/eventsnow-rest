DROP TABLE IF EXISTS `COMMENT`;
DROP TABLE IF EXISTS `EVENT`;
DROP TABLE IF EXISTS `USER`;


CREATE TABLE `USER` (
                      `id` int(11) NOT NULL AUTO_INCREMENT,
                      `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                      `firstname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                      `lastname`   varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                      `password`   varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                      `email`      varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                      `gender`     varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                      `type`       varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                      `created_at` timestamp NOT NULL                   DEFAULT current_timestamp() ON UPDATE current_timestamp(),
                      `edited_at`  timestamp                            DEFAULT NULL ON UPDATE current_timestamp(),
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# TABLE STRUCTURE FOR: EVENT
#


CREATE TABLE `EVENT` (
                       `id`         int(11)      NOT NULL AUTO_INCREMENT,
                       `title`      varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                       `content`    text COLLATE utf8_unicode_ci         DEFAULT NULL,
                       `type`       varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                       `created_at` timestamp    NOT NULL                DEFAULT current_timestamp(),
                       `event_time` timestamp    NOT NULL                DEFAULT current_timestamp(),
                       `latitude`   varchar(255) NOT NULL                DEFAULT '00',
                       `longitude`  varchar(255) NOT NULL                DEFAULT '00',
                       `author`     int(11)                              DEFAULT NULL,
                       PRIMARY KEY (`id`),
                       KEY `author` (`author`),
                       CONSTRAINT `EVENT_ibfk_1` FOREIGN KEY (`author`) REFERENCES `USER` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



#
# TABLE STRUCTURE FOR: COMMENT
#

CREATE TABLE `COMMENT` (
                         `id`         int(11)   NOT NULL AUTO_INCREMENT,
                         `content`    text COLLATE utf8_unicode_ci         DEFAULT NULL,
                         `status`     varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                         `author`     int(11)                              DEFAULT NULL,
                         `created_at` timestamp NOT NULL                   DEFAULT current_timestamp(),
                         `edited_at`  timestamp                            DEFAULT NULL ON UPDATE current_timestamp(),
                         `event`      int(11)                              DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `author` (`author`),
                         KEY `event` (`event`),
                         CONSTRAINT `COMMENT_ibfk_1` FOREIGN KEY (`author`) REFERENCES `USER` (`id`),
                         CONSTRAINT `COMMENT_ibfk_2` FOREIGN KEY (`event`) REFERENCES `EVENT` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

