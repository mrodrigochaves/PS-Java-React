package br.com.banco.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import br.com.banco.dto.ContaDTO;
import br.com.banco.dto.TransferenciaDTO;

public interface BancoService {

    // Conta
    Optional<ContaDTO> createConta(@RequestBody @Valid ContaDTO request);

    List<ContaDTO> getAllContas();

    

    // Transferencia
    Optional<TransferenciaDTO> createTransferencia(@Valid TransferenciaDTO request);

    List<TransferenciaDTO> getAllTransferencias();


}
