CREATE TABLE nf_json (
	id INT NOT NULL AUTO_INCREMENT,
	dados_json JSON NULL DEFAULT NULL CHECK (json_valid(dados_json)),
	PRIMARY KEY (id)
);


INSERT INTO nf_json (dados_json) VALUES
('{
  "informacoesGerais": {
      "numero": "123456789",
      "serie": "1",
      "dataEmissao": "2024-11-12T15:30:00",
      "dataEntradaSaida": "2024-11-12T15:45:00",
      "naturezaOperacao": "Venda",
      "modelo": "55",
      "tipo": "1",  
      "chaveAcesso": "12345678901234567890123456789012345678901234567890",
      "status": "Autorizada"  
    },
    "emitente": {
      "cnpj": "12345678000195",
      "razaoSocial": "Empresa Exemplo Ltda",
      "nomeFantasia": "Exemplo Comércio",
      "endereco": {
        "logradouro": "Rua Exemplo, 123",
        "numero": "123",
        "complemento": "Sala 101",
        "bairro": "Centro",
        "municipio": "Cidade Exemplo",
        "uf": "SP",
        "cep": "12345000",
        "pais": "Brasil"
      },
      "inscricaoEstadual": "1234567890",
      "inscricaoMunicipal": "9876543210",
      "email": "contato@empresaexemplo.com.br",
      "telefone": "(11) 1234-5678"
    },
    "destinatario": {
      "cpfCnpj": "98765432100123",
      "razaoSocial": "Cliente Exemplo Ltda",
      "nomeFantasia": "Cliente Exemplo",
      "endereco": {
        "logradouro": "Av. Cliente, 456",
        "numero": "456",
        "complemento": "Apto 301",
        "bairro": "Bairro Cliente",
        "municipio": "Cidade Cliente",
        "uf": "SP",
        "cep": "98765000",
        "pais": "Brasil"
      },
      "inscricaoEstadual": "0987654321",
      "email": "cliente@exemplo.com.br",
      "telefone": "(11) 9876-5432"
    },
    "itens": [
      {
        "item": 1,
        "descricao": "Produto Exemplo A",
        "codigo": "12345",
        "quantidade": 10,
        "unidade": "un",
        "valorUnitario": 100.00,
        "valorTotal": 1000.00,
        "ncm": "12345678",
        "cfop": "5101",
        "icms": {
          "codigo": "00",
          "baseCalculo": 1000.00,
          "aliquota": 18.00,
          "valor": 180.00
        },
        "ipi": {
          "codigo": "50",
          "baseCalculo": 1000.00,
          "aliquota": 5.00,
          "valor": 50.00
        }
      },
      {
        "item": 2,
        "descricao": "Produto Exemplo B",
        "codigo": "67890",
        "quantidade": 5,
        "unidade": "un",
        "valorUnitario": 150.00,
        "valorTotal": 750.00,
        "ncm": "87654321",
        "cfop": "5102",
        "icms": {
          "codigo": "00",
          "baseCalculo": 750.00,
          "aliquota": 18.00,
          "valor": 135.00
        },
        "ipi": {
          "codigo": "50",
          "baseCalculo": 750.00,
          "aliquota": 5.00,
          "valor": 37.50
        }
      }
    ],
    "totais": {
      "valorProdutos": 1750.00,
      "valorFrete": 100.00,
      "valorSeguro": 0.00,
      "valorDesconto": 0.00,
      "valorImpostos": 402.50,
      "valorTotal": 1850.00
    },
    "impostos": {
      "icms": {
        "totalBaseCalculo": 1750.00,
        "totalValor": 315.00
      },
      "ipi": {
        "totalBaseCalculo": 1750.00,
        "totalValor": 87.50
      }
    },
    "informacoesAdicionais": {
      "observacoes": "Este é um exemplo de nota fiscal.",
      "informacoesComplementares": "Venda para entrega em domicílio."
    },
    "transporte": {
      "modal": "Rodoviário",
      "frete": "Por conta do destinatário",
      "placaVeiculo": "ABC1234",
      "ufVeiculo": "SP"
    }
}'
);


INSERT INTO nf_json (dados_json) VALUES
('{
  "informacoesGerais": {
    "numero": "100000001",
    "serie": "1",
    "dataEmissao": "2025-01-15T10:00:00",
    "dataEntradaSaida": "2025-01-15T10:20:00",
    "naturezaOperacao": "Venda",
    "modelo": "55",
    "tipo": "1",
    "chaveAcesso": "11111111111111111111111111111111111111111111111111",
    "status": "Autorizada"
  },
  "emitente": {
    "cnpj": "11111111000191",
    "razaoSocial": "Distribuidora Alfa Ltda",
    "nomeFantasia": "Alfa Distribuição",
    "endereco": {
      "logradouro": "Rua Alfa, 100",
      "numero": "100",
      "complemento": "Bloco A",
      "bairro": "Industrial",
      "municipio": "Alfacidade",
      "uf": "MG",
      "cep": "11111000",
      "pais": "Brasil"
    },
    "inscricaoEstadual": "1111111111",
    "inscricaoMunicipal": "1112223334",
    "email": "contato@alfa.com.br",
    "telefone": "(31) 2222-1111"
  },
  "destinatario": {
    "cpfCnpj": "22222222000199",
    "razaoSocial": "Cliente Beta S/A",
    "nomeFantasia": "Beta",
    "endereco": {
      "logradouro": "Av. Beta, 200",
      "numero": "200",
      "complemento": "Sala 2",
      "bairro": "Centro",
      "municipio": "Betópolis",
      "uf": "MG",
      "cep": "22222000",
      "pais": "Brasil"
    },
    "inscricaoEstadual": "2222222222",
    "email": "compras@beta.com.br",
    "telefone": "(31) 3333-2222"
  },
  "itens": [
    {
      "item": 1,
      "descricao": "Notebook X1",
      "codigo": "NBX100",
      "quantidade": 2,
      "unidade": "un",
      "valorUnitario": 3000.00,
      "valorTotal": 6000.00,
      "ncm": "84713012",
      "cfop": "5102",
      "icms": {
        "codigo": "00",
        "baseCalculo": 6000.00,
        "aliquota": 12.00,
        "valor": 720.00
      },
      "ipi": {
        "codigo": "50",
        "baseCalculo": 6000.00,
        "aliquota": 4.00,
        "valor": 240.00
      }
    }
  ],
  "totais": {
    "valorProdutos": 6000.00,
    "valorFrete": 200.00,
    "valorSeguro": 50.00,
    "valorDesconto": 0.00,
    "valorImpostos": 960.00,
    "valorTotal": 6250.00
  },
  "impostos": {
    "icms": {
      "totalBaseCalculo": 6000.00,
      "totalValor": 720.00
    },
    "ipi": {
      "totalBaseCalculo": 6000.00,
      "totalValor": 240.00
    }
  },
  "informacoesAdicionais": {
    "observacoes": "Nota emitida conforme contrato de fornecimento.",
    "informacoesComplementares": "Entrega em 5 dias úteis."
  },
  "transporte": {
    "modal": "Rodoviário",
    "frete": "Por conta do emitente",
    "placaVeiculo": "XYZ1234",
    "ufVeiculo": "MG"
  }
}');

INSERT INTO nf_json (dados_json) VALUES
('{
  "informacoesGerais": {
    "numero": "100000002",
    "serie": "1",
    "dataEmissao": "2025-02-10T09:30:00",
    "dataEntradaSaida": "2025-02-10T09:45:00",
    "naturezaOperacao": "Devolução",
    "modelo": "55",
    "tipo": "1",
    "chaveAcesso": "22222222222222222222222222222222222222222222222222",
    "status": "Cancelada"
  },
  "emitente": {
    "cnpj": "33333333000188",
    "razaoSocial": "Comercial Gama Ltda",
    "nomeFantasia": "Gama Atacado",
    "endereco": {
      "logradouro": "Rua Gama, 50",
      "numero": "50",
      "complemento": "",
      "bairro": "Comércio",
      "municipio": "Gamópolis",
      "uf": "RJ",
      "cep": "33333000",
      "pais": "Brasil"
    },
    "inscricaoEstadual": "3333333333",
    "inscricaoMunicipal": "4445556667",
    "email": "vendas@gama.com.br",
    "telefone": "(21) 4444-3333"
  },
  "destinatario": {
    "cpfCnpj": "44444444000177",
    "razaoSocial": "Supermercado Delta",
    "nomeFantasia": "Delta",
    "endereco": {
      "logradouro": "Av. Delta, 99",
      "numero": "99",
      "complemento": "Galpão 3",
      "bairro": "Distrito",
      "municipio": "Deltania",
      "uf": "RJ",
      "cep": "44444000",
      "pais": "Brasil"
    },
    "inscricaoEstadual": "4444444444",
    "email": "compras@delta.com.br",
    "telefone": "(21) 5555-4444"
  },
  "itens": [
    {
      "item": 1,
      "descricao": "Cadeira Escritório",
      "codigo": "CADEIRA01",
      "quantidade": 4,
      "unidade": "un",
      "valorUnitario": 250.00,
      "valorTotal": 1000.00,
      "ncm": "94013010",
      "cfop": "5201",
      "icms": {
        "codigo": "00",
        "baseCalculo": 1000.00,
        "aliquota": 12.00,
        "valor": 120.00
      },
      "ipi": {
        "codigo": "50",
        "baseCalculo": 1000.00,
        "aliquota": 3.00,
        "valor": 30.00
      }
    }
  ],
  "totais": {
    "valorProdutos": 1000.00,
    "valorFrete": 0.00,
    "valorSeguro": 0.00,
    "valorDesconto": 0.00,
    "valorImpostos": 150.00,
    "valorTotal": 1150.00
  },
  "impostos": {
    "icms": {
      "totalBaseCalculo": 1000.00,
      "totalValor": 120.00
    },
    "ipi": {
      "totalBaseCalculo": 1000.00,
      "totalValor": 30.00
    }
  },
  "informacoesAdicionais": {
    "observacoes": "Devolução de mercadoria por avaria.",
    "informacoesComplementares": "Acompanhada de laudo técnico."
  },
  "transporte": {
    "modal": "Rodoviário",
    "frete": "Sem frete",
    "placaVeiculo": "DEF5678",
    "ufVeiculo": "RJ"
  }
}');

SELECT * FROM nf_json
WHERE JSON_EXTRACT(dados_json, '$.destinatario.nomeFantasia') = 'Delta';

SELECT * FROM nf_json
WHERE JSON_EXTRACT(dados_json, '$.transporte.placaVeiculo') = 'DEF5678';

ALTER TABLE nf_json 
	ADD COLUMN vl_total DECIMAL(10,2) AS (JSON_EXTRACT(dados_json, '$.totais.valorTotal')) VIRTUAL;
	
-- importante retirar dados de uma tabela JSON e colocar em outra tabela (VIRTUAL ou STORED) para facilitar calculos e afins
SELECT SUM(vl_total) FROM nf_json;

-- não é possível fazer alterações nas tabelas VIRTUAL e STORED pois elas apenas puxam os dados da tabela JSON
UPDATE nf_json SET vl_total = 2150.00 WHERE id = 3;

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