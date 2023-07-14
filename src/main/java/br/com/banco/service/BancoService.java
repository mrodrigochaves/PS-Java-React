package br.com.banco.service;

import java.util.Optional;

import java.util.List;
import br.com.banco.dto.ContaDTO;
import br.com.banco.dto.TransferenciaDTO;

public interface BancoService {

    // Conta
    Optional<ContaDTO> create(ContaDTO request);

    List<ContaDTO> getAll();

    Optional<ContaDTO> getById(Long id);

    Optional<ContaDTO> deleteById(Long id);

    List<ContaDTO> getByResponsavel(String responsavel);
    
    Optional<ContaDTO> update(Long id, ContaDTO request);


    // Transferencia
    Optional<TransferenciaDTO> createTransferencia(TransferenciaDTO request); 

    List<TransferenciaDTO> getAllTransferencias(); 

    Optional<TransferenciaDTO> getTransferenciaById(Long id); 

    Optional<TransferenciaDTO> deleteTransferenciaById(Long id); 

    List<TransferenciaDTO> getTransferenciasByTipo(String tipo); 

    List<TransferenciaDTO> getTransferenciasByConta(Long contaId);

    Optional<TransferenciaDTO> updateTransferencia(Long id, TransferenciaDTO request);

}
