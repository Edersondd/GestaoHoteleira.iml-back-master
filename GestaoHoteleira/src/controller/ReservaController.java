package controller;

import dao.ReservaDao;
import model.Reserva;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaController {

    public String cadastrar(
            Long idAcomodacao,
            Long idCliente,
            Integer qtdHospedes,
            LocalDate dataInicio,
            LocalDate dataFim,
            Double valorTotal
    ) {
        // Validar IDs não nulos ou zero
        if (idAcomodacao == null || idAcomodacao <= 0) {
            return "[ERRO] O campo 'idAcomodacao' é obrigatório e deve ser maior que zero. " +
                    "\nReserva não cadastrada!";
        }

        if (idCliente == null || idCliente <= 0) {
            return "[ERRO] O campo 'idCliente' é obrigatório e deve ser maior que zero. " +
                    "\nReserva não cadastrada!";
        }

        // Validar outros campos não nulos
        if (qtdHospedes == null || qtdHospedes <= 0) {
            return "[ERRO] O campo 'qtdHospedes' é obrigatório e deve ser maior que zero. " +
                    "\nReserva não cadastrada!";
        }

        if (dataInicio == null) {
            return "[ERRO] O campo 'dataInicio' é obrigatório. " +
                    "\nReserva não cadastrada!";
        }

        if (dataFim == null) {
            return "[ERRO] O campo 'dataFim' é obrigatório. " +
                    "\nReserva não cadastrada!";
        }

        if (valorTotal == null) {
            return "[ERRO] O campo 'valorTotal' é obrigatório. " +
                    "\nReserva não cadastrada!";
        }

        ReservaDao reservaDao = new ReservaDao();

        if (reservaDao.compararReserva(
                idAcomodacao,
                idCliente,
                qtdHospedes,
                dataInicio,
                dataFim,
                valorTotal
        )) {
            return "[OK] Reserva cadastrada com sucesso!";
        } else {
            return "[ERRO] Quarto indisponível nessa data. Reserva não cadastrada!";
        }
    }

    public String listar() {
        ReservaDao reservaDao = new ReservaDao();
        ArrayList<Reserva> lista = reservaDao.selecionar();

        // Verificar se a lista está vazia
        if (lista.isEmpty()) {
            return "Não há reservas cadastradas.";
        }

        StringBuilder conteudo = new StringBuilder();
        for (Reserva reserva : lista) {
            conteudo.append(reserva).append("\n");
        }

        return conteudo.toString();
    }

    public String excluir(Long id) {
        ReservaDao reservaDao = new ReservaDao();

        // Validar se o ID é nulo ou menor que ou igual a zero
        if (id == null || id <= 0) {
            return "[ERRO] ID inválido. Reserva não excluída!";
        }

        // Verificar se a reserva existe antes de tentar excluir
        if (reservaDao.selecionarPorId(id) == null) {
            return "[ERRO] Não encontrado. Reserva não excluída!";
        }

        if (reservaDao.deletar(id)) {
            return "[OK] Reserva excluída com sucesso!";
        } else {
            return "[ERRO] Erro desconhecido. Reserva não excluída!";
        }
    }

    public String alterar(Long id, Long idAcomodacao, Long idCliente, LocalDate dataInicio, LocalDate dataFim, Double valorTotal, Integer qtdHospedes) {
        ReservaDao reservaDao = new ReservaDao();

        // Validar IDs não nulos ou zero
        if (id == null || id <= 0) {
            return "[ERRO] ID inválido. Reserva não alterada!";
        }

        if (idAcomodacao == null || idAcomodacao <= 0) {
            return "[ERRO] O campo 'idAcomodacao' é obrigatório e deve ser maior que zero. " +
                    "\nReserva não alterada!";
        }

        if (idCliente == null || idCliente <= 0) {
            return "[ERRO] O campo 'idCliente' é obrigatório e deve ser maior que zero. " +
                    "\nReserva não alterada!";
        }

        // Validar outros campos não nulos
        if (qtdHospedes == null || qtdHospedes <=0) {
            return "[ERRO] O campo 'qtdHospedes' é obrigatório e deve ser maior que zero. " +
                    "\nReserva não alterada!";
        }

        if (dataInicio == null) {
            return "[ERRO] O campo 'dataInicio' é obrigatório. " +
                    "\nReserva não alterada!";
        }

        if (dataFim == null) {
            return "[ERRO] O campo 'dataFim' é obrigatório. " +
                    "\nReserva não alterada!";
        }

        if (valorTotal == null) {
            return "[ERRO] O campo 'valorTotal' é obrigatório. " +
                    "\nReserva não alterada!";
        }

        // Verificar se a reserva existe antes de tentar alterar
        if (reservaDao.selecionarPorId(id) == null) {
            return "[ERRO] Não encontrado. Reserva não alterada!";
        }

        if (reservaDao.atualizar(id, idAcomodacao, idCliente, dataInicio, dataFim, valorTotal, qtdHospedes)) {
            return "[OK] Reserva alterada com sucesso!";
        } else {
            return "[ERRO] Erro desconhecido. Reserva não alterada!";
        }
    }
}