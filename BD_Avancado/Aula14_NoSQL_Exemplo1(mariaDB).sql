/*
Consulta 1
*/

-- Criando a tabela
CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dados JSON
);

DESC produtos;

-- Inserindo dados
INSERT INTO produtos (dados) VALUES
  ('{"nome": "Calça","cor": "azul", "tamanho": "G", "material": "Jeans"}');
INSERT INTO produtos (dados) VALUES
  ('{"nome": "Camiseta","cor": "azul","tamanho": "M","material": "algodão"}');
INSERT INTO produtos (dados) VALUES	
  ('{"nome": "Notebook","marca": "Dell","RAM": "16GB","SSD": "512GB"}');
INSERT INTO produtos (dados) VALUES	
  ('{"nome": "Tênis","cor": "preto","tamanho": "42","tipo": "corrida"}');
INSERT INTO produtos (dados) VALUES	
  ('{"nome": "Smartphone","marca": "Samsung","modelo": "Galaxy S21","armazenamento": "128GB","cor": "preto"}');
INSERT INTO produtos (dados) VALUES
  ('{"nome": "Mochila","cor": "verde","capacidade": "20L","material": "nylon"}');
INSERT INTO produtos (dados) VALUES	
  ('{"nome": "Relógio","marca": "Casio","tipo": "digital","resistencia_agua": "sim"}');
INSERT INTO produtos (dados) VALUES	
  ('{"nome": "Fone de Ouvido","marca": "Sony","tipo": "Bluetooth","cor": "branco"}');
INSERT INTO produtos (dados) VALUES	
  ('{"nome": "Cadeira Gamer","marca": "DXRacer","cor": "vermelho","ajuste_altura": "sim","reclinacao": "150 graus"}');
INSERT INTO produtos (dados) VALUES	
  ('{"nome": "Bicicleta","marca": "Caloi","tipo": "mountain bike","tamanho_aro": "29","cor": "azul","freios": "disco"}');
INSERT INTO produtos (dados) VALUES	
  ('{"nome": "Livro","titulo": "Dom Quixote","autor": "Miguel de Cervantes","idioma": "português"}');
  

/*
Consulta 2
*/

/*
| Finalidade                     | MySQL (5.7+, 8.x)                            | MariaDB (10.2+, 10.5)                          |
| ------------------------------ | -------------------------------------------- | ---------------------------------------------- |
| Acessar valor (`->`)           | `json_column->'$.caminho'`                   | ❌ **Não suportado**                            |
| Acessar valor (`JSON_EXTRACT`) | `JSON_EXTRACT(json_column, '$.campo')`       | ✅ Igual ao MySQL                               |
| Remover aspas (`JSON_UNQUOTE`) | `JSON_UNQUOTE(JSON_EXTRACT(...))`            | ✅ Igual ao MySQL                               |
| Verificar se JSON é válido     | `JSON_VALID(json_column)`                    | ✅ Igual ao MySQL                               |
| Inserir/alterar valor          | `JSON_SET(json_column, '$.campo', valor)`    | ✅ Igual ao MySQL                               |
| Remover campo                  | `JSON_REMOVE(json_column, '$.campo')`        | ✅ Igual ao MySQL                               |
| Adicionar novo campo           | `JSON_INSERT(json_column, '$.campo', valor)` | ✅ Igual ao MySQL                               |
| Contar elementos de array      | `JSON_LENGTH(json_column)`                   | ✅ Igual ao MySQL                               |
| Verificar existência           | `JSON_CONTAINS(json_column, valor)`          | ✅ Com limitações em MariaDB (10.2+)            |
| Retorna tipo do valor          | `JSON_TYPE(json_column)`                     | ✅ Igual ao MySQL                               |
| Mesclar objetos JSON           | `JSON_MERGE_PRESERVE(...)` (MySQL 8.0+)      | `JSON_MERGE(...)` (sem `_PRESERVE` em MariaDB) |
| Formatar como texto            | `JSON_PRETTY(json_column)`                   | ✅ Igual ao MySQL                               |
*/

-- Consultando todos os dados JSON
SELECT * FROM produtos;

-- Extraindo valores específicos
SELECT id, 
		 JSON_EXTRACT(dados, '$.nome') AS nome,
       JSON_UNQUOTE(JSON_EXTRACT(dados, '$.cor')) AS cor, 
       JSON_UNQUOTE(JSON_EXTRACT(dados, '$.tamanho')) AS tamanho FROM produtos
WHERE JSON_EXTRACT(dados, '$.cor') = 'azul';

-- Agrupando dados de produtos por cor
SELECT JSON_EXTRACT(dados, '$.cor') AS cor,
		 COUNT(*) AS qtde
		 FROM produtos
GROUP BY JSON_EXTRACT(dados, '$.cor');

-- adicionando uma nova tag
UPDATE produtos SET dados = JSON_SET(dados, '$.fornecedor', 'Hering')
	WHERE id = 1;

-- Buscar todos os livros (verifica se a chave titulo existe)
SELECT 
    id,
    JSON_UNQUOTE(JSON_EXTRACT(dados, '$.nome')) AS nome,
    JSON_UNQUOTE(JSON_EXTRACT(dados, '$.titulo')) AS titulo
FROM produtos
WHERE JSON_EXTRACT(dados, '$.titulo') IS NOT NULL;


/*
Consulta 3
*/

SELECT * FROM produtos;
DESC produtos;

-- adcionando colunas à tabela produto
ALTER TABLE produtos
	ADD COLUMN nome VARCHAR(100) AS (JSON_UNQUOTE(JSON_EXTRACT(dados, '$.nome'))) VIRTUAL;

ALTER TABLE produtos
	ADD COLUMN cor VARCHAR(100) AS (JSON_UNQUOTE(JSON_EXTRACT(dados, '$.cor'))) STORED;
	
SELECT * FROM produtos
WHERE nome = 'notebook';

UPDATE produtos SET dados = JSON_SET(dados, '$.nome', 'Notebook slim')
	WHERE nome = 'notebook';
	
UPDATE produtos SET dados = JSON_SET(dados, '$.cor', 'Prata')
	WHERE nome = 'notebook slim';

-- só é possível criar index de colunas do tipo STORED
CREATE INDEX idx_produtos_cor ON produtos(cor);

EXPLAIN
SELECT * FROM produtos
WHERE cor = 'prata';