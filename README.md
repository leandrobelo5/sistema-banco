# 💳 Sistema Bancário em Java (MVC + JDBC + MySQL)

Projeto acadêmico que simula operações bancárias simples utilizando Java 17, padrão MVC, banco de dados MySQL via XAMPP e conexão JDBC. Duas instituições bancárias são representadas com regras específicas: **BancoA** e **BancoB**.

---

## 🧠 Visão Geral

O sistema permite ao usuário realizar:

- 📥 Depósitos
- 💸 Saques (com ou sem limite)
- 📄 Extratos
- 🔁 Transferências (somente no BancoA)

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **MySQL (XAMPP)**
- **JDBC**
- **Padrão MVC**
- **DAO (Data Access Object)**
- **Interface via Console (CLI)**

---

## 🗂️ Dados de Teste para Simulação

Você pode usar os seguintes dados no menu para testar as funcionalidades do sistema:

| Nome               | CPF          | Banco   | Saldo Inicial | Limite (se houver) |
|--------------------|--------------|---------|---------------|--------------------|
| João Silva         | `12345678900`| BancoA  | R$ 1.000,00    | ❌                 |
| Maria Oliveira     | `98765432100`| BancoA  | R$ 500,00      | ❌                 |
| Ana Souza          | `11122233344`| BancoB  | R$ 200,00      | ✅ R$ 500,00       |
| Pedro Ferreira     | `55566677788`| BancoB  | R$ 300,00      | ✅ R$ 500,00       |

---

## 🧾 Script SQL – Banco de Dados

Use este script para criar o banco e inserir os dados de teste no XAMPP/MySQL:

```sql
CREATE DATABASE IF NOT EXISTS sistema_banco;
USE sistema_banco;

CREATE TABLE IF NOT EXISTS contas (
    cpf VARCHAR(11) PRIMARY KEY,
    banco VARCHAR(10) NOT NULL,
    saldo DOUBLE NOT NULL
);

INSERT INTO contas (cpf, banco, saldo) VALUES
('12345678900', 'BancoA', 1000.00),
('98765432100', 'BancoA', 500.00),
('11122233344', 'BancoB', 200.00),
('55566677788', 'BancoB', 300.00);

