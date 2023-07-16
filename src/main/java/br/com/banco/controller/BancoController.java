package br.com.banco.controller;

import br.com.banco.dto.ContaDTO;
import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.service.BancoService;

import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class BancoController {

    @Autowired
    private final BancoService bancoService;
    private final ContaRepository contaRepository;
    private final TransferenciaRepository transferenciaRepository;

    public BancoController(BancoService bancoService, ContaRepository contaRepository,  TransferenciaRepository transferenciaRepository    ) {
        this.bancoService = bancoService;
        this.contaRepository = contaRepository;
        this.transferenciaRepository = transferenciaRepository;
    }
    
    @GetMapping("/contas")
    public List<Conta> listContas() {
        return contaRepository.findAll();
    }


    @PostMapping("/conta")
    public ResponseEntity<String> createConta(@RequestBody @Valid ContaDTO request) {
        bancoService.createConta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Conta criada com sucesso");
    }

     @GetMapping("/transferencias")
    public List<Transferencia> listTransferencias() {
        return transferenciaRepository.findAll();
    }

    @PostMapping("/transferencia")
    public ResponseEntity<String> createTransferencia(@RequestBody @Valid TransferenciaDTO request) {
        bancoService.createTransferencia(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Transferência realizada com sucesso");
    }
        

    
}
