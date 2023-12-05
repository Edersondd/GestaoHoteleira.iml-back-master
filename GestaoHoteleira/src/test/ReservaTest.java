package test;

import controller.ExemploController;
import controller.ReservaController;
import model.Acomodacao;
import model.Cliente;
import model.Pessoa;

import java.sql.Date;
import java.time.LocalDate;

public class ReservaTest {


    public String testeCadastro(
            Long idAcomodacao,
            Long idCliente,
            Integer qtdHospedes,
            LocalDate dataInicio,
            LocalDate dataFim,
            Double valorTotal
    ) {
        ReservaController reservaController = new ReservaController();
        String resposta = reservaController.cadastrar(idAcomodacao, idCliente, qtdHospedes, dataInicio, dataFim, valorTotal);
        return resposta;
    }

    public String testeListagem() {
        ReservaController reservaController = new ReservaController();
        String resposta = reservaController.listar();
        return resposta;
    }

    public String testeExclusao(Long id) {
        ReservaController reservaController = new ReservaController();
        String resposta = reservaController.excluir(id);
        return resposta;
    }

    public String testeAlteracao(
            Long id,
            Long idAcomodacao,
            Long idCliente,
            LocalDate dataInicio,
            LocalDate dataFim,
            Double valorTotal,
            Integer qtdHospodes
    ) {
        ReservaController reservaController = new ReservaController();
        String resposta = reservaController.alterar(id, idAcomodacao, idCliente, dataInicio, dataFim, valorTotal, qtdHospodes);
        return resposta;
    }

}
