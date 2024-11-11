-- Criação da tabela "tb_clients"
CREATE TABLE tb_clients (
	id BIGINT NOT NULL,
	name VARCHAR(50) NOT NULL,
	surname VARCHAR(100) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	telephone VARCHAR(11) NOT NULL,
	age SMALLINT NOT NULL CHECK (age > 0 AND age < 150),
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pk_id_clients PRIMARY KEY(id),
	CONSTRAINT uq_cpf_clients UNIQUE(cpf),
	CONSTRAINT uq_telephone_clients UNIQUE(telephone)
);

-- Criação da sequence "sq_id_clients"
CREATE SEQUENCE sq_id_clients
START 1
INCREMENT 1
OWNED BY tb_clients.id;

-- Busca na tabela "tb_clients"
SELECT * FROM tb_clients;

-- Excluindo um registro da tabela "tb_clients"
DELETE FROM tb_clients;

-- Crição da tabela "tb_products"
create table tb_products (
	id bigint not null,
	code varchar (50) not null,
	name varchar(250) not null,
	description text,
	created_at timestamp not null default current_timestamp,
	constraint pk_id_products primary key(id),
	constraint uq_code_products unique(code)
);

-- Criação da sequence "sq_id_products"
create sequence sq_id_products
start 1
increment 1
owned by tb_products.id;

-- Busca na tabela "tb_products"
SELECT * FROM tb_products;

-- Exclusão dos registros de "tb_products"
delete from tb_products;

