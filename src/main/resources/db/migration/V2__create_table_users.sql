CREATE TABLE IF NOT EXISTS users(
    id bigint not null auto_increment,
	firstName VARCHAR(50),
	lastName VARCHAR(50),
	password VARCHAR(200) not null,
	email VARCHAR(100) not null,
	enabled boolean not null,

	primary key(id)
);