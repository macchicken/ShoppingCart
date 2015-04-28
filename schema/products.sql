CREATE TABLE comp5347shop.products (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(100) NOT NULL,
  description varchar(200) DEFAULT NULL,
  imageUrl varchar(500) DEFAULT NULL,
  price double NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8