package dao;

import dao.connection.ConexaoMySQL;
import model.Acomodacao;
import model.Cliente;
import model.Exemplo;
import model.Reserva;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaDao {
    public static Boolean inserir(
            Long idAcomodacao,
            Long idCliente,
            Integer qtdHospedes,
            LocalDate dataInicio,
            LocalDate dataFim,
            Double valorTotal
    ) {

        try {
            String sql = "INSERT INTO reserva (id_acomodacao,id_cliente,qtd_hospedes,data_inicio,data_fim,valor_total) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, idAcomodacao);
            preparacao.setLong(2, idCliente);
            preparacao.setInt(3, qtdHospedes);
            preparacao.setDate(4, Date.valueOf(dataInicio));
            preparacao.setDate(5, Date.valueOf(dataFim));
            preparacao.setDouble(6, valorTotal);
            int contLinhasAfetadas = preparacao.executeUpdate();
            return contLinhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean deletar(Long id) {
        try {
            String sql = "DELETE FROM reserva WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            int contLinhasAfetadas = preparacao.executeUpdate();
            return contLinhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean atualizar(
            Long id,
            Long idAcomodacao,
            Long idCliente,
            LocalDate dataInicio,
            LocalDate dataFim,
            Double valorTotal,
            Integer qtdHospodes
    ) {
        try {
            String sql = "UPDATE reserva  SET id_acomodacao = ?, id_cliente = ?, data_inicio = ?, data_fim = ?, valor_total = ?, qtd_hospedes = ? WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, idAcomodacao);
            preparacao.setLong(2, idCliente);
            preparacao.setDate(3, Date.valueOf(dataInicio));
            preparacao.setDate(4, Date.valueOf(dataFim));
            preparacao.setDouble(5, valorTotal);
            preparacao.setInt(6, qtdHospodes);
            preparacao.setLong(7, id);
            int contLinhasAfetadas = preparacao.executeUpdate();
            return contLinhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Reserva> selecionar() {
        try {
            String sql = "SELECT * FROM reserva ORDER BY id";
            Statement stmt = ConexaoMySQL.get().createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            ArrayList<Reserva> lista = new ArrayList<>();
            while (resultado.next()) {

                Long id = resultado.getLong("id");
                Long idAcomodacao = resultado.getLong("id_acomodacao");
                Long idCliente = resultado.getLong("id_cliente");
                Integer qtdHospedes = resultado.getInt("qtd_hospedes");
                Date dataInicio = resultado.getDate("data_inicio");
                Date dataFim = resultado.getDate("data_fim");
                Double valorTotal = resultado.getDouble("valor_total");
                Acomodacao acomodacao = selecionarAcomodacaoPorId(idAcomodacao);
                Cliente cliente = selecionarClientePorId(idCliente);
                Reserva reserva = new Reserva(
                        id,
                        acomodacao,
                        cliente,
                        qtdHospedes,
                        dataInicio.toLocalDate(),
                        dataFim.toLocalDate(),
                        valorTotal
                );

                lista.add(reserva);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    public Reserva selecionarPorId(Long id) {
        try {
            String sql = "SELECT * FROM reserva WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();

            if (resultado.next()) {
                Reserva reserva = new Reserva(
                        resultado.getLong("id"),
                        resultado.getInt("qtd_hospedes"),
                        resultado.getDouble("valor_total")

                );
                return reserva;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Cliente selecionarClientePorId(Long id) {
        try {
            String sql = "select c.*, p.nome_completo, p.data_nascimento , p.documento, p.pais,p.estado,p.cidade from cliente c join pessoa p on p.id = c.id_pessoa where c.id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();
            if (resultado.next()) {
                Cliente cliente = new Cliente(

                        resultado.getString("nome_completo"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getString("documento"),
                        resultado.getString("pais"),
                        resultado.getString("estado"),
                        resultado.getString("cidade"),
                        resultado.getLong("id"),
                        resultado.getBoolean("fidelidade"),
                        resultado.getString("observacao")
                );
                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Acomodacao selecionarAcomodacaoPorId(Long id) {
        try {
            String sql = "SELECT * FROM acomodacao WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();

            if (resultado.next()) {
                Acomodacao acomodacao = new Acomodacao(
                        resultado.getLong("id"),
                        resultado.getString("nome"),
                        resultado.getDouble("valor_diaria"),
                        resultado.getInt("limite_hospedes"),
                        resultado.getString("descricao")
                );
                return acomodacao;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean compararReserva(Long idAcomodacao,
                                   Long idCliente,
                                   Integer qtdHospedes,
                                   LocalDate dataInicio,
                                   LocalDate dataFim,
                                   Double valorTotal) {
        try {
            String sql = "SELECT COUNT(*) FROM reserva WHERE id_acomodacao = ? AND ? < data_fim AND ? > data_inicio";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, idAcomodacao);
            preparacao.setDate(2, Date.valueOf(dataInicio));
            preparacao.setDate(3, Date.valueOf(dataFim));
            ResultSet resultado = preparacao.executeQuery();
            resultado.next();
            int quantidadeReserva = resultado.getInt(1);
            if (quantidadeReserva == 0) {
                return inserir(idAcomodacao, idCliente, qtdHospedes, dataInicio, dataFim, valorTotal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }


}
