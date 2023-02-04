CREATE TABLE category (
	cid INT PRIMARY KEY AUTO_INCREMENT,
	cname VARCHAR(30) NOT NULL,
	state INT DEFAULT 1,
	order_number INT NOT NULL,
	description VARCHAR(100) NOT NULL,
	create_time DATETIME
);