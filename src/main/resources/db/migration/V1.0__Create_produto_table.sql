CREATE TABLE produtos (
    nome VARCHAR(255) PRIMARY KEY,
    descricao VARCHAR(255),
    categoria INT2,
    valor NUMERIC(38, 2)
);

CREATE TABLE clientes (
    cpf VARCHAR(11) PRIMARY KEY,
    nome VARCHAR(255)
);

CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    nome_lanche VARCHAR(255),
    nome_acompanhamento VARCHAR(255),
    nome_bebida VARCHAR(255),
    nome_sobremesa VARCHAR(255),
    status_pedido INT2
);

INSERT INTO clientes (cpf, nome) VALUES
('12345678901', 'Cliente 1'),
('98765432109', 'Cliente 2'),
('55555555555', 'Cliente 3');


INSERT INTO produtos (nome, descricao, categoria, valor) VALUES
('x_salada', 'Descrição do Produto 1', '0', 29.99),
('strogonoff_de_frango', 'Descrição do Produto 2', '0', 39.99),
('coca-cola_600ml', 'coca_cola de 600 Ml', '1', 9.99),
('salada_alface', 'Acompanhamento de salada de alface', '2', 2.99),
('pudim_de_leite', 'Sobremesa pudim de leite', '3', 9.99);


