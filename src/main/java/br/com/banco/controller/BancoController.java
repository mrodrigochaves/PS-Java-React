package br.com.banco.controller;

import br.com.banco.dto.ContaDTO;
import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.model.Transferencia;
import br.com.banco.service.BancoService;

import br.com.banco.repository.TransferenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class BancoController {

    @Autowired
    private final BancoService bancoService;
    private final TransferenciaRepository transferenciaRepository;

    public BancoController(BancoService bancoService,
            TransferenciaRepository transferenciaRepository) {
        this.bancoService = bancoService;
        this.transferenciaRepository = transferenciaRepository;
    }

    // Busca uma transferencia por conta_id
    @GetMapping("/transferencias/contas/{conta_id}")
    public ResponseEntity<List<Transferencia>> getTransferenciasByContaId(@PathVariable("conta_id") Long contaId) {
        List<Transferencia> transferencias = transferenciaRepository.findByContaId(contaId);

        if (!transferencias.isEmpty()) {
            return ResponseEntity.ok(transferencias);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Busca transferencias sem filtro
    @GetMapping("/transferencias")
    public List<Transferencia> listTransferencias() {
        return transferenciaRepository.findAll();
    }

    // Busca transferencias por data
    @GetMapping("/transferencias/data/{data_transferencia}")
    public ResponseEntity<List<Transferencia>> getTransferenciasByDataTransferencia(
            @PathVariable("data_transferencia") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataTransferencia) {
        List<Transferencia> transferencias = transferenciaRepository.findByDataTransferencia(dataTransferencia);

        if (!transferencias.isEmpty()) {
            return ResponseEntity.ok(transferencias);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Busca uma transferencia por nome do operador
    @GetMapping("/transferencias/nome_operador/{nome_operador_transacao}")
    public ResponseEntity<List<Transferencia>> getTransferenciasNomeOperadorTransacao(@PathVariable("nome_operador_transacao") String nomeOperadorTransacao) {
        List<Transferencia> transferencias = transferenciaRepository.findByNomeOperadorTransacao(nomeOperadorTransacao);

        if (!transferencias.isEmpty()) {
            return ResponseEntity.ok(transferencias);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Criar uma transferencia
    @PostMapping("/transferencia")
    public ResponseEntity<String> createTransferencia(@RequestBody @Valid TransferenciaDTO request) {
        bancoService.createTransferencia(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Transferência realizada com sucesso");
    }

    // Criar uma conta
    @PostMapping("/conta")
    public ResponseEntity<String> createConta(@RequestBody @Valid ContaDTO request) {
        bancoService.createConta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Conta criada com sucesso");
    }

    

}
