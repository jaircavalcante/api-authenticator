create table users(
    id bigint not null auto_increment,
	username VARCHAR(50) not null,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	password VARCHAR(200) not null,
	email VARCHAR(100) not null,
	enabled boolean not null,

	primary key(id)
);