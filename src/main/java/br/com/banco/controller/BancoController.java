package br.com.banco.controller;

import br.com.banco.dto.ContaDTO;
import br.com.banco.model.Conta;

import br.com.banco.service.BancoService;

import br.com.banco.repository.ContaRepository;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class BancoController {

    @Autowired
    private final BancoService bancoService;
    private final ContaRepository contaRepository;

    public BancoController(BancoService bancoService, ContaRepository contaRepository) {
        this.bancoService = bancoService;
        this.contaRepository = contaRepository;
    }
    
    @GetMapping("/contas")
    public List<Conta> listContas() {
        return contaRepository.findAll();
    }


    @PostMapping
    public ResponseEntity<ContaDTO> createConta(@RequestBody @Valid ContaDTO request) {
        Optional<ContaDTO> response = bancoService.createConta(request);
        return response.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }


        

    
}
