import controller.ExemploController;
import dao.ReservaDao;
import model.*;
import model.enums.TipoMidia;
import test.ExemploTest;
import test.ReservaTest;

import java.time.LocalDate;
import java.util.Date;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Protótipo de Aplicação para Gestão Hoteleira");
//
//        Pessoa p1 = new Pessoa(
//            "José Pereira da Silva",
//            LocalDate.of(1980, 12, 20),
//            "123-456",
//            "Brasil",
//            "SC",
//            "Tubarão"
//        );
//        System.out.println("Pessoa:");
//        System.out.println(p1);
//
//        Funcionario f1 = new Funcionario(
//            "Manoel da Silva",
//            LocalDate.of(1960, 01, 02),
//            "789-888",
//            "Brasil",
//            "SC",
//            "Jaguaruna",
//            "Serviços Gerais",
//            3000.0
//        );
//        System.out.println("Funcionário:");
//        System.out.println(f1);
//
//        Cliente clienteTeste = new Cliente(
//                "Lionel Messi",
//                LocalDate.of(1970, 02, 10),
//                "454547848",
//                "Argentina",
//                "Abc",
//                "Buenos Aires",
//                true,
//                "Jogadô caro."
//        );
//        System.out.println("Cliente:");
//        System.out.println(clienteTeste);
//
//        Acomodacao a1 = new Acomodacao(
//            "Chalé Família",
//            400.0,
//            6,
//            "Isto é uma descrição"
//        );
//        System.out.println("Acomodação:");
//        System.out.println(a1);
//
//        MidiaAcomodacao ma1 = new MidiaAcomodacao(a1, TipoMidia.IMAGEM, "varanda.jpg");
//        System.out.println("Mídia acomodação:");
//        System.out.println(ma1);
//
//        Pacote pc1 = new Pacote("Combo Natal", a1, 4, 1500.0);
//        System.out.println("Pacote:");
//        System.out.println(pc1);

//        Reserva r1 = new Reserva(
//            a1,
//            clienteTeste,
//            2,
//            LocalDate.of(2023, 11, 14),
//            LocalDate.of(2023, 11, 16),
//            100.0
//        );
//        System.out.println("Reserva:");
//        System.out.println(r1);

        ReservaTest reservaTest = new ReservaTest();
//        System.out.println(
//                reservaTest.testeCadastro(
//                        7L,
//                        0L,
//                        2,
//                        LocalDate.of(2023, 11, 25),
//                        LocalDate.of(2023, 11, 27),
//                        300.0
//                ));


//        System.out.println("\nLista de reservas:");
//        System.out.println(
//                reservaTest.testeListagem()
//        );

        System.out.println(
                reservaTest.testeExclusao(29L)
        );

//        System.out.println(
//                reservaTest.testeAlteracao(29L,
//                        7L,
//                        22L,
//                        LocalDate.of(2023,5,9),
//                        LocalDate.of(2023,5,10),
//                        300.0,
//                        3)
//        );

//        Exemplo exemplo = new Exemplo("Isto é um texto", 99);
//        System.out.println("Exemplo:");
//        System.out.println(exemplo);
//
//        ExemploTest exemploTest = new ExemploTest();
//        System.out.println(exemploTest.testeCadastro(null, 587));
//        System.out.println(exemploTest.testeListagem());
//        System.out.println(exemploTest.testeAlteracao(6L, "", 99));
//        System.out.println(exemploTest.testeExclusao(null));

    }
}
