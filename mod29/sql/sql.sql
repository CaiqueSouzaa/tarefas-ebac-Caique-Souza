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