-- Inserts para a tabela endereco
INSERT INTO endereco VALUES
    (1, '100',  'Rua das Flores',       'Centro',     'Sao Paulo',      'SP'),
    (2, '200',  'Avenida Brasil',       'Copacabana', 'Rio de Janeiro', 'RJ'),
    (3, '300',  'XV de Novembro',   'Centro',     'Curitiba',       'PR'),
    (4, '50',   'Praça da Se',          'Se',         'Sao Paulo',      'SP'),
    (5, '150',  'Sete de Setembro', 'Centro',     'Porto Alegre',   'RS'),
    (6, '1000', 'Avenida Paulista',     'Bela Vista', 'Sao Paulo',      'SP');

-- Inserts para a tabela filme
INSERT INTO filme VALUES
    (1, 'O Poderoso Chefao', 19.99),
    (2, 'Matrix',            14.50),
    (3, 'Senhor dos Aneis',  18.75),
    (4, 'Inception',         17.99),
    (5, 'Avatar',            21.00),
    (6, 'Titanic',           15.30),
    (7, 'Gladiador',         16.50),
    (8, 'Jurassic Park',     13.90),
    (9, 'Star Wars',         20.00),
    (10, 'O Rei Leao',       12.80);

-- Inserts para a tabela cinema
INSERT INTO cinema VALUES
    (1, 'Cine Plaza', 1),
    (2, 'Cinemark',   2),
    (3, 'Cinepolis',  3),
    (4, 'Cine Arte',  4);

-- Inserts para a tabela secao
INSERT INTO secao VALUES
    -- Cinema 1
    (1,  '2024-10-20 14:00:00', 1, 1, 20),
    (2,  '2024-10-20 16:00:00', 1, 3, 15),
    (3,  '2024-10-20 18:00:00', 1, 4, 25),
    (4,  '2024-10-20 19:30:00', 1, 6, 10),
    -- Cinema 2
    (5,  '2024-10-21 14:00:00', 2, 2, 18),
    (6,  '2024-10-21 16:00:00', 2, 5, 22),
    (7,  '2024-10-21 18:00:00', 2, 7, 10),
    (8,  '2024-10-21 19:30:00', 2, 8, 15),
    -- Cinema 3
    (9,  '2024-10-22 14:00:00', 3, 1, 12),
    (10, '2024-10-22 16:00:00', 3, 4, 15),
    (11, '2024-10-22 18:00:00', 3, 6, 25),
    (12, '2024-10-22 19:30:00', 3, 9, 20),
    -- Cinema 4
    (13, '2024-10-23 14:00:00', 4, 3, 15),
    (14, '2024-10-23 16:00:00', 4, 4, 16),
    (15, '2024-10-23 18:00:00', 4, 7, 25),
    (16, '2024-10-23 19:30:00', 4, 8, 20);

-- Inserts para a tabela venda
INSERT INTO venda VALUES
    -- Vendas para a seção 1
    (1, 'João Silva',     1, 'Cartao de Credito',     1),
    (2, 'Maria Oliveira', 2, 'Dinheiro',              1),
    (3, 'Carlos Pereira', 3, 'Cartao de Debito',      1),
    (4, 'Ana Santos',     4, 'Pix',                   1),
    (5, 'Fernanda Costa', 5, 'Cartao de Credito',     1),

    -- Vendas para a seção 2
    (6,  'Roberto Almeida', 1, 'Dinheiro',             2),
    (7,  'Patricia Lima',   2, 'Cartao de Debito',     2),
    (8,  'Lucas Martins',   3, 'Pix',                  2),
    (9,  'Cláudia Rocha',   4, 'Cartao de Credito',    2),
    (10, 'Mariana Gomes',   5, 'Dinheiro',             2),

    -- Vendas para a seção 3
    (11, 'Ricardo Dias',     1, 'Cartao de Credito',  3),
    (12, 'Tatiane Ferreira', 2, 'Dinheiro',           3),
    (13, 'Felipe Cardoso',   3, 'Cartao de Debito',   3),
    (14, 'Juliana Pires',    4, 'Pix',                3),
    (15, 'Eduardo Almeida',  5, 'Cartao de Credito',  3),

    -- Vendas para a seção 4
    (16, 'Renato Martins',    1, 'Dinheiro',          4),
    (17, 'Viviane Souza',     2, 'Cartao de Debito',  4),
    (18, 'Hugo Alves',        3, 'Pix',               4),
    (19, 'Priscila Ferreira', 4, 'Cartao de Credito', 4),
    (20, 'Gustavo Rocha',     5, 'Dinheiro',          4),
    
    -- Vendas para a seção 5
    (21, 'Bianca Silva',    1, 'Cartao de Credito',   5),
    (22, 'Daniel Oliveira', 2, 'Dinheiro',            5),
    (23, 'Marcos Santos',   3, 'Cartao de Debito',    5),
    (24, 'Camila Costa',    4, 'Pix',                 5),
    (25, 'Andre Lima',      5, 'Cartao de Credito',   5),

    -- Vendas para a seção 6
    (26, 'Paula Almeida',    1, 'Dinheiro',           6),
    (27, 'Vinicius Martins', 2, 'Cartao de Debito',   6),
    (28, 'Tatiane Rocha',    3, 'Pix',                6),
    (29, 'Luana Pires',      4, 'Cartao de Credito',  6),
    (30, 'Leonardo Dias',    5, 'Dinheiro',           6);
