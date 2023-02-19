CREATE TABLE IF NOT EXISTS tokens (
    id bigint not null auto_increment,
	token VARCHAR(500) not null,
	tokenType VARCHAR(50),
	revoked boolean not null,
	expired boolean not null,

    constraint fk_tokens_users foreign key(id) references users(id),
	primary key(token)
);