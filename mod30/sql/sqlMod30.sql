-------------------------------- Criação de tabelas --------------------------------------------

-- Tabela de clientes
CREATE TABLE tb_clients (
	id BIGINT NOT NULL,
	name VARCHAR(50) NOT NULL,
	surname VARCHAR(150) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	telephone VARCHAR(11) NOT NULL,
	email VARCHAR(255) NOT NULL,
	age SMALLINT NOT NULL CHECK(age > 0 AND age < 150),
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pk_id_clients PRIMARY KEY(id),
	CONSTRAINT uq_cpf_clients UNIQUE(cpf),
	CONSTRAINT uq_email_clients UNIQUE(email)
);

-- Tabela de produtos
CREATE TABLE tb_products (
	id BIGINT NOT NULL,
	code VARCHAR(50) NOT NULL,
	name VARCHAR(255) NOT NULL,
	description TEXT,
	price DECIMAL(10, 2) NOT NULL DEFAULT 0,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pk_id_products PRIMARY KEY(id),
	CONSTRAINT uq_code_products UNIQUE(code)
);

-- Tabela de estoque
CREATE TABLE tb_storage (
	id BIGINT NOT NULL,
	product_id BIGINT NULL,
	amount INT NOT NULL DEFAULT 0,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pk_id_storage PRIMARY KEY(id),
	CONSTRAINT fk_id_product FOREIGN KEY(product_id)
		REFERENCES tb_products(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT uq_id_product UNIQUE(product_id)
);

-- Tabela de status
CREATE TABLE tb_status (
	id BIGINT NOT NULL,
	name VARCHAR(50) NOT NULL,
	description TEXT,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pk_id_status PRIMARY KEY(id),
	CONSTRAINT uq_name_status UNIQUE(name)
);

-- Tabela de vendas
CREATE TABLE tb_sales (
	id BIGINT NOT NULL,
	client_id BIGINT NOT NULL,
	status_id BIGINT NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pk_id_sales PRIMARY KEY(id),
	CONSTRAINT fk_id_clients FOREIGN KEY(client_id)
		REFERENCES tb_clients(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT fk_id_status FOREIGN KEY(status_id)
		REFERENCES tb_status(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

-- Tabela de relação de vendas e produtos
CREATE TABLE tb_products_sales (
	id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	amount INT NOT NULL DEFAULT 0,
	price DECIMAL(99, 2) NOT NULL DEFAULT 0,
	sale_id BIGINT NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pk_id_products_sales PRIMARY KEY(id),
	CONSTRAINT fk_id_product FOREIGN KEY(product_id)
		REFERENCES tb_products(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT fk_id_sales FOREIGN KEY(sale_id)
		REFERENCES tb_sales(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

-------------------------------- Criação das sequencias ----------------------------------------

-- Sequencia de clientes
CREATE SEQUENCE sq_id_clients
START 1
INCREMENT 1
OWNED BY tb_clients.id;

-- Sequencia de produtos
CREATE SEQUENCE sq_id_products
START 1
INCREMENT 1
OWNED BY tb_products.id;

-- Sequencia de estoque
CREATE SEQUENCE sq_id_storage
START 1
INCREMENT 1
OWNED BY tb_storage.id;

-- Sequencia de status
CREATE SEQUENCE sq_id_status
START 1
INCREMENT 1
OWNED BY tb_status.id;

-- Sequencia de vendas
CREATE SEQUENCE sq_id_sales
START 1
INCREMENT 1
OWNED BY tb_sales.id;

-- Sequencia de relação de vendas e produtos
CREATE SEQUENCE sq_id_products_sales
START 1
INCREMENT 1
OWNED BY tb_products_sales.id;

--------------------------------- Criação de funções --------------------------------------------
-- Checar se o status da venda segue em aberto
CREATE OR REPLACE FUNCTION check_sale_status(sale_id BIGINT) RETURNS BOOLEAN AS
$$
DECLARE
	aberto_status_id BIGINT := 1;
	sale_status_id BIGINT;
BEGIN
	SELECT status_id INTO sale_status_id FROM tb_sales WHERE id = sale_id;

	if sale_status_id = aberto_status_id then
		RETURN true;
	else
		RETURN false;
	end if;
END;
$$
LANGUAGE plpgsql;

------------------------------ Criação das Trigger Functions ------------------------------------

-- Registrar um produto na tabela de estoque
CREATE OR REPLACE FUNCTION add_product_storage() RETURNS TRIGGER AS
$$
DECLARE
BEGIN
	INSERT INTO tb_storage (id, product_id)
		VALUES (NEXTVAL('sq_id_storage'), NEW.id);

	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- Excluir um produto da tabela de estoque
CREATE OR REPLACE FUNCTION del_product_storage() RETURNS TRIGGER AS
$$
DECLARE
BEGIN
	DELETE FROM tb_storage WHERE product_id = OLD.id;

	RETURN OLD;
END;
$$
LANGUAGE plpgsql;

-- Subtrair quantia de produtos ao realizar uma venda
CREATE OR REPLACE FUNCTION sub_product_storage() RETURNS TRIGGER AS
$$
DECLARE
	storage_amount INT;
	product_code VARCHAR(50);
BEGIN
	SELECT s.amount, p.code INTO storage_amount, product_code FROM tb_storage AS s
		LEFT JOIN tb_products AS p
		ON s.product_id = p.id
		WHERE s.id = NEW.product_id;

	if (NOT check_sale_status(NEW.sale_id)) then 
		raise exception 'A venda [%] não está em aberta.', NEW.sale_id;
	end if;
	
	if storage_amount < NEW.amount then
		raise exception 'Estoque insuficiente para o produto [%]. Disponível: %, Solicitado: %', product_code, storage_amount, NEW.amount;
	else
		UPDATE tb_storage SET amount = storage_amount - NEW.amount WHERE product_id = NEW.product_id;
	end if;

	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- Atualizar o preço dos itens vendidos na tabela tb_products_sales
CREATE OR REPLACE FUNCTION upd_product_sale_price() RETURNS TRIGGER AS
$$
DECLARE
	product_price DECIMAL(10, 2);
BEGIN

	if (NOT check_sale_status(NEW.sale_id)) then 
		raise exception 'A venda [%] não está em aberta.', NEW.sale_id;
	end if;
	
	SELECT price INTO product_price FROM tb_products WHERE id = NEW.product_id;

	UPDATE tb_products_sales SET price = product_price * NEW.amount WHERE id = NEW.id;

	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

------------------------------ Criação das Triggers ---------------------------------------------

-- Trigger para registrar um produto na tabela de estoque
CREATE TRIGGER tg_add_product_storage
AFTER INSERT ON tb_products
FOR EACH ROW
EXECUTE FUNCTION add_product_storage();

-- Trigger para excluir um produto da tabela de estoque
CREATE TRIGGER tg_del_product_storage
BEFORE DELETE ON tb_products
FOR EACH ROW
EXECUTE FUNCTION del_product_storage();

-- Trigger para subtrair a quantia de produtos na venda
CREATE TRIGGER tg_sub_product_storage
AFTER INSERT ON tb_products_sales
FOR EACH ROW
EXECUTE FUNCTION sub_product_storage();

-- Trigger para atualizar o preço vendido dos itens na tabela tb_products_sales
CREATE TRIGGER tg_upd_product_sale_price
AFTER INSERT ON tb_products_sales
FOR EACH ROW
EXECUTE FUNCTION upd_product_sale_price();

------------------------------ Exclusão das trigger functions -----------------------------------

-- Exclusão da function trigger "add_product_storage";
DROP FUNCTION add_product_storage();

------------------------------ Exclusão das tabelas ---------------------------------------------

-- Exclusão da tabela de clientes
DROP TABLE tb_clients;

-- Exclusão da tabela de produtos
DROP TABLE tb_products;

-- Exclusão da tabela de estoque
DROP TABLE tb_storage;

-- Exclusão da tabela de status
DROP TABLE tb_status;

-- Exclusão da tabela de vendas
DROP TABLE tb_sales;

-- Exclusão da tabela de relação de vendas e produtos
DROP TABLE tb_products_sales;

------------------------------ Inserção de valores -----------------------------------------------

-- Registrando clientes
INSERT INTO tb_clients (id, name, surname, cpf, telephone, email, age)
	VALUES (NEXTVAL('sq_id_clients'), 'Caique', 'Souza', '43295783724', '11928583285', 'caique@email.com', 21);

-- Registrando produtos
INSERT INTO tb_products (id, code, name, description, price)
	VALUES (NEXTVAL('sq_id_products'), 'ES-0001', 'Mouse', 'Mouse Games Logitech', 243.42);
	
INSERT INTO tb_products (id, code, name, description, price)
	VALUES (NEXTVAL('sq_id_products'), 'ES-0002', 'Keyboard', 'Mechanical Keyboard Razer', 729.90);

INSERT INTO tb_products (id, code, name, description, price)
    VALUES (NEXTVAL('sq_id_products'), 'ES-0003', 'Headset', 'Gaming Headset HyperX', 399.99);

INSERT INTO tb_products (id, code, name, description, price)
    VALUES (NEXTVAL('sq_id_products'), 'ES-0004', 'Monitor', '27-inch LED Monitor Samsung', 1549.89);

INSERT INTO tb_products (id, code, name, description, price)
    VALUES (NEXTVAL('sq_id_products'), 'ES-0005', 'Chair', 'Ergonomic Gaming Chair DXRacer', 1899.75);

INSERT INTO tb_products (id, code, name, description, price)
    VALUES (NEXTVAL('sq_id_products'), 'ES-0006', 'Webcam', 'Full HD Webcam Logitech', 329.50);

-- Registrando status
INSERT INTO tb_status (id, name)
	VALUES (NEXTVAL('sq_id_status'), 'EM ABERTO');
	
INSERT INTO tb_status (id, name)
	VALUES (NEXTVAL('sq_id_status'), 'FINALIZADA');
	
INSERT INTO tb_status (id, name)
	VALUES (NEXTVAL('sq_id_status'), 'CANCELADA');

-- Registrando venda
INSERT INTO tb_sales (id, client_id, status_id)
	VALUES (NEXTVAL('sq_id_sales'), 1, 1);

-- Registando relação de venda e produto
INSERT INTO tb_products_sales (id, product_id, amount, sale_id)
	VALUES (NEXTVAL('sq_id_products_sales'), 6, 1, 2);


------------------------------ Visualização de dados ----------------------------------------------

-- Registros de clientes
SELECT * FROM tb_clients;

-- Registros de produtos
SELECT * FROM tb_products;

-- Registros de estoque
SELECT * FROM tb_storage;

-- Registros de status
SELECT * FROM tb_status;

-- Registros de vendas
SELECT * FROM tb_sales;
SELECT sa.id AS id, sa.client_id AS client_id, st.name AS status FROM tb_sales AS sa
LEFT JOIN tb_status AS st
ON sa.status_id = st.id;

-- Registros de relação de vendas e produtos
SELECT * FROM tb_products_sales;
SELECT * FROM tb_products_sales WHERE sale_id = 1;
SELECT SUM(price) FROM tb_products_sales WHERE sale_id = 2;
SELECT SUM(amount) FROM tb_products_sales WHERE sale_id = 1;
SELECT COUNT(id) FROM tb_products_sales WHERE sale_id = 2;

------------------------------------ Atualização de registros -------------------------------------

-- Atualizando produtos
UPDATE tb_storage SET amount = 24 WHERE product_id = 1;
UPDATE tb_storage SET amount = 52 WHERE product_id = 2;
UPDATE tb_storage SET amount = 234 WHERE product_id = 3;
UPDATE tb_storage SET amount = 45 WHERE product_id = 4;
UPDATE tb_storage SET amount = 81 WHERE product_id = 5;
UPDATE tb_storage SET amount = 6273 WHERE product_id = 6;

-- Atualizando vendas
UPDATE tb_sales SET status_id = 2 WHERE id = 2;

-------------------------------- Exclusão de registros --------------------------------------------

-- Excluir de clientes

-- Excluir de produtos
DELETE FROM tb_products WHERE id = 2;

-- Excluir de estoque