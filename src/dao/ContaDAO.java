package dao;

import java.sql.*;
import model.Conta;
import util.Conexao;

public class ContaDAO {

    public Conta buscarContaPorCPF(String cpf) {
        String sql = "SELECT * FROM contas WHERE cpf = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Conta(
                        rs.getString("cpf"),
                        rs.getString("banco"),
                        rs.getDouble("saldo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarSaldo(Conta conta) {
        String sql = "UPDATE contas SET saldo = ? WHERE cpf = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, conta.getSaldo());
            stmt.setString(2, conta.getCpf());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}