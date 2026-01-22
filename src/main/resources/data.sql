-- Inserir clientes (Tabela: customers)
INSERT INTO customers (name, email, phone, address, date_registration, active) VALUES
('João Silva', 'joao@email.com', '(11) 99999-1111', 'Rua A, 123 - São Paulo/SP', NOW(), true),
('Maria Santos', 'maria@email.com', '(11) 99999-2222', 'Rua B, 456 - São Paulo/SP', NOW(), true),
('Pedro Oliveira', 'pedro@email.com', '(11) 99999-3333', 'Rua C, 789 - São Paulo/SP', NOW(), true);

-- Inserir restaurantes (Assumindo que a tabela se chama 'restaurants' conforme o padrão)
-- Nota: Adicionei campos baseados no seu controller (nome, categoria, endereco, telefone, taxa_entrega)
-- Tente esta versão (ajustada para os nomes que o Hibernate costuma criar automaticamente)
INSERT INTO restaurants (name, category, address, phone, fee_delivery, active) VALUES
('Pizzaria Bella', 'Italiana', 'Av. Paulista, 1000 - São Paulo/SP', '(11) 3333-1111', 5.00, true),
('Burger House', 'Hamburgueria', 'Rua Augusta, 500 - São Paulo/SP', '(11) 3333-2222', 3.50, true),
('Sushi Master', 'Japonesa', 'Rua Liberdade, 200 - São Paulo/SP', '(11) 3333-3333', 8.00, true);

-- Inserir produtos (Tabela: products)
INSERT INTO products (name, description, price, category, available, restaurant_id) VALUES
('Pizza Margherita', 'Molho de tomate, mussarela e manjericão', 35.90, 'Pizza', true, 1),
('Pizza Calabresa', 'Molho de tomate, mussarela e calabresa', 38.90, 'Pizza', true, 1),
('X-Burger', 'Hambúrguer, queijo, alface e tomate', 18.90, 'Hambúrguer', true, 2),
('Combo Sashimi', '15 peças de sashimi variado', 45.90, 'Sashimi', true, 3);

-- Inserir pedidos (Tabela: orders)
-- Ajustado: 'PENDENTE' vira 'PENDING' e 'CONFIRMADO' vira 'CONFIRMED'
INSERT INTO orders (number_order, date_order, status, fee_delivery, total_value, delivery_adress, customer_id, restaurant_id) VALUES
('PED123', NOW(), 'PENDING', 5.00, 40.90, 'Rua A, 123 - São Paulo/SP', 1, 1),
('PED124', NOW(), 'CONFIRMED', 3.50, 22.40, 'Rua B, 456 - São Paulo/SP', 2, 2);

-- Inserir itens dos pedidos (Tabela: itens_order)
INSERT INTO itens_order (amount, unit_price, subtotal, order_id, product_id) VALUES
(1, 35.90, 35.90, 1, 1),
(1, 18.90, 18.90, 2, 3);