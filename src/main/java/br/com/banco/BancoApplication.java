package br.com.banco;


import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;



@SpringBootApplication
public class BancoApplication implements CommandLineRunner {
    private final ContaRepository contaRepository;
    private final TransferenciaRepository transferenciaRepository;

    public BancoApplication(ContaRepository contaRepository, TransferenciaRepository transferenciaRepository) {
        this.contaRepository = contaRepository;
        this.transferenciaRepository = transferenciaRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BancoApplication.class, args);
    }

    @Override
    public void run(String... args) {
      
        Conta conta1 = new Conta();
        conta1.setNomeResponsavel("Fulano");
        contaRepository.save(conta1);

        Conta conta2 = new Conta();
        conta2.setNomeResponsavel("Sicrano");
        contaRepository.save(conta2);

      
        Transferencia transferencia1 = new Transferencia();
        transferencia1.setDataTransferencia(LocalDate.of(2023, 1, 1));
        transferencia1.setValor(new BigDecimal("30895.46"));
        transferencia1.setTipo("DEPOSITO");
        transferencia1.setNomeOperadorTransacao("Maria");
        transferencia1.setConta(conta1);
        transferenciaRepository.save(transferencia1);

        Transferencia transferencia2 = new Transferencia();
        transferencia2.setDataTransferencia(LocalDate.of(2023, 2, 3));
        transferencia2.setValor(new BigDecimal("12.24"));
        transferencia2.setTipo("DEPOSITO");
        transferencia2.setNomeOperadorTransacao("Joao");
        transferencia2.setConta(conta2);
        transferenciaRepository.save(transferencia2);

       
        System.out.println("Contas:");
        contaRepository.findAll().forEach(System.out::println);

        System.out.println("Transferências:");
        transferenciaRepository.findAll().forEach(System.out::println);
    }
}