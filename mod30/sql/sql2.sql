SELECT
	p.id AS product_id,
	p.code AS code,
	p.name AS name,
	p.description AS description,
	p.price AS price,
	p.created_at AS product_created_at,
	s.id AS storage_id,
	s.amount AS amount,
	s.created_at AS storage_created_at
FROM tb_storage AS s
LEFT JOIN tb_products AS p
ON s.product_id = p.id
WHERE p.code = 'ES-0003';

UPDATE tb_storage AS s
SET amount = 23
FROM tb_products AS p
WHERE s.product_id = p.id
AND p.code = 'ES-0005';

SELECT * FROM tb_products;

SELECT * FROM tb_storage;

DELETE FROM tb_products;

UPDATE tb_storage AS s
SET amount = s.amount + 5
FROM tb_products AS p
WHERE s.product_id = p.id
AND p.code = 'ES-0003';

ALTER TABLE tb_storage
ADD COLUMN code VARCHAR(50);

ALTER TABLE tb_storage
ADD CONSTRAINT fk_code_product FOREIGN KEY(code)
REFERENCES tb_products(code)
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE tb_storage
DROP COLUMN code;

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

