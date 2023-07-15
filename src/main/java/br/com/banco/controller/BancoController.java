package br.com.banco.controller;

import br.com.banco.dto.ContaDTO;
import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.model.Conta;
import br.com.banco.service.BancoService;
import lombok.AllArgsConstructor;
import br.com.banco.repository.ContaRepository;


import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
@AllArgsConstructor
public class BancoController {

    private final BancoService bancoService;
    private final ContaRepository contaRepository;

    
    @GetMapping
    public List<Conta> list() {
        return contaRepository.findAll();
    }


    @PostMapping("/conta")
    public ResponseEntity<String> criarConta(@RequestBody ContaDTO conta) {
        bancoService.createConta(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Conta criada com sucesso.");
    }

    @GetMapping("/transferencia")
    public ResponseEntity<List<TransferenciaDTO>> listTransferencias() {
        List<TransferenciaDTO> transferencias = bancoService.getAllTransferencias();
        return ResponseEntity.ok(transferencias);
    }

    @PostMapping("/transferencia")
    public ResponseEntity<String> realizarTransferencia(@RequestBody TransferenciaDTO transferencia) {
        bancoService.createTransferencia(transferencia);
        return ResponseEntity.status(HttpStatus.CREATED).body("Transferência realizada com sucesso.");
    }

    @ControllerAdvice
    public class ErrorHandler {

        @ExceptionHandler(value = { NotFoundException.class })
        public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
         
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }

        

    }
}
