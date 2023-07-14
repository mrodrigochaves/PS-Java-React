package br.com.banco;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import java.time.LocalDateTime;

@SpringBootApplication
public class BancoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BancoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ContaRepository contaRepository, TransferenciaRepository transferenciaRepository) {
        return args -> {
            Conta conta = new Conta();
            conta.setNomeResponsavel("João da Silva");
            contaRepository.save(conta);


            Transferencia transferencia = new Transferencia();
            transferencia.setDataTransferencia(LocalDateTime.now());
            transferencia.setValor(100.00);
            transferencia.setTipo("DEPOSITO");
            transferencia.setNomeOperadorTransacao("Maria da Silva");
            transferencia.setConta(conta);
            transferenciaRepository.save(transferencia);

        };
    }

}
