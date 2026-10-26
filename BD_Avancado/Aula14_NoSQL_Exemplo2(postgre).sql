/*
Projeto supabase
senha = alunoFurb2026
*/

-- Criar a extensão
CREATE EXTENSION IF NOT EXISTS hstore;

-- Criar uma tabela com coluna hstore
CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome TEXT,
    atributos HSTORE
);

-- Inserir dados com hstore
INSERT INTO produto (nome, atributos)
VALUES
  ('Camiseta', 'cor=>"azul", tamanho=>"M", material=>"algodão"'),
  ('Notebook', 'marca=>"Dell", RAM=>"16GB", SSD=>"512GB"'),
  ('Tênis', 'cor=>"preto", tamanho=>"42", tipo=>"corrida"'),
  ('Smartphone', 'marca=>"Samsung", modelo=>"Galaxy S21", armazenamento=>"128GB", cor=>"preto"'),
  ('Mochila', 'cor=>"verde", capacidade=>"20L", material=>"nylon"'),
  ('Relógio', 'marca=>"Casio", tipo=>"digital", resistencia_agua=>"sim"'),
  ('Fone de Ouvido', 'marca=>"Sony", tipo=>"Bluetooth", cor=>"branco"'),
  ('Cadeira Gamer', 'marca=>"DXRacer", cor=>"vermelho", ajuste_altura=>"sim", reclinacao=>"150 graus"'),
  ('Bicicleta', 'marca=>"Caloi", tipo=>"mountain bike", tamanho_aro=>"29", cor=>"azul", freios=>"disco"'),
  ('Livro', 'titulo=>"Dom Quixote", autor=>"Miguel de Cervantes", idioma=>"português"');

select * from produto;

select nome as produto, atributos->'cor' as cor 
from produto
where atributos->'cor' = 'azul';

create index idx_produto_atributos on produto using GIN (atributos);

-- Converter os atributos em JSON
create table produtos_json
as 
SELECT id, nome, hstore_to_json(atributos) AS atributos_json
FROM produto;

select * from produtos_json;