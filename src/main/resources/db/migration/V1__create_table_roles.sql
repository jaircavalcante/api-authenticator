CREATE TABLE IF NOT EXISTS roles (
    id bigint not null auto_increment,
	role VARCHAR(50) not null,

	primary key(id)
);

INSERT INTO roles(id, role) VALUES(1, "ADMIN");
INSERT INTO roles(id, role) VALUES(2, "USER")