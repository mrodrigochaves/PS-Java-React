package br.com.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.dto.ContaDTO;
import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;

@Service
public class BancoServiceImpl implements BancoService {
    
    private final ContaRepository contaRepository;
    private final TransferenciaRepository transferenciaRepository;
    private ModelMapper mapper;

    @Autowired
    public BancoServiceImpl(ContaRepository contaRepository, TransferenciaRepository transferenciaRepository, ModelMapper mapper) {
        this.contaRepository = contaRepository;
        this.transferenciaRepository = transferenciaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ContaDTO> create(@Valid ContaDTO request) {
        Conta conta = mapper.map(request, Conta.class);
        Conta savedConta = contaRepository.saveAndFlush(conta);
        return Optional.of(mapper.map(savedConta, ContaDTO.class));
    }

    @Override
    public List<ContaDTO> getAll() {
        List<Conta> contas = contaRepository.findAll();
        return mapContasListToDTO(contas);
    }

    @Override
    public Optional<ContaDTO> getById(Long id) {
        return contaRepository.findById(id)
                .map(conta -> mapper.map(conta, ContaDTO.class));
    }

    @Override
    public Optional<ContaDTO> deleteById(Long id) {
        Optional<Conta> conta = contaRepository.findById(id);
        conta.ifPresent(ct -> contaRepository.deleteById(id));
        return conta.map(ct -> mapper.map(ct, ContaDTO.class));
    }

    @Override
    public List<ContaDTO> getByResponsavel(String responsavel) {
        List<Conta> contas = contaRepository.findByNomeResponsavel(responsavel);
        return mapContasListToDTO(contas);
    }

    @Override
    public Optional<ContaDTO> update(Long id, @Valid ContaDTO request) {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isPresent()) {
            Conta conta = contaOptional.get();
            conta.setNomeResponsavel(request.getNomeResponsavel());
            
            contaRepository.save(conta);
            return Optional.of(mapper.map(conta, ContaDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TransferenciaDTO> createTransferencia(@Valid TransferenciaDTO request) {
        Transferencia transferencia = mapper.map(request, Transferencia.class);
        Transferencia savedTransferencia = transferenciaRepository.saveAndFlush(transferencia);
        return Optional.of(mapper.map(savedTransferencia, TransferenciaDTO.class));
    }

    @Override
    public List<TransferenciaDTO> getAllTransferencias() {
        List<Transferencia> transferencias = transferenciaRepository.findAll();
        return mapTransferenciasListToDTO(transferencias);
    }

    @Override
    public Optional<TransferenciaDTO> getTransferenciaById(Long id) {
        return transferenciaRepository.findById(id)
                .map(transferencia -> mapper.map(transferencia, TransferenciaDTO.class));
    }

    @Override
    public Optional<TransferenciaDTO> deleteTransferenciaById(Long id) {
        Optional<Transferencia> transferencia = transferenciaRepository.findById(id);
        transferencia.ifPresent(tf -> transferenciaRepository.deleteById(id));
        return transferencia.map(tf -> mapper.map(tf, TransferenciaDTO.class));
    }

    @Override
    public List<TransferenciaDTO> getTransferenciasByTipo(String tipo) {
        List<Transferencia> transferencias = transferenciaRepository.findByTipo(tipo);
        return mapTransferenciasListToDTO(transferencias);
    }

    @Override
    public List<TransferenciaDTO> getTransferenciasByConta(Long contaId) {
        List<Transferencia> transferencias = transferenciaRepository.findByContaId(contaId);
        return mapTransferenciasListToDTO(transferencias);
    }

    @Override
    public Optional<TransferenciaDTO> updateTransferencia(Long id, @Valid TransferenciaDTO request) {
        Optional<Transferencia> transferenciaOptional = transferenciaRepository.findById(id);
        if (transferenciaOptional.isPresent()) {
            Transferencia transferencia = transferenciaOptional.get();
            transferencia.setDataTransferencia(request.getDataTransferencia());
            transferencia.setValor(request.getValor());
            transferencia.setTipo(request.getTipo());
            transferencia.setNomeOperadorTransacao(request.getNomeOperadorTransacao());
            
            transferenciaRepository.save(transferencia);
            return Optional.of(mapper.map(transferencia, TransferenciaDTO.class));
        }
        return Optional.empty();
    }

    private List<ContaDTO> mapContasListToDTO(List<Conta> contas) {
        return contas.stream()
                .map(conta -> mapper.map(conta, ContaDTO.class))
                .collect(Collectors.toList());
    }

    private List<TransferenciaDTO> mapTransferenciasListToDTO(List<Transferencia> transferencias) {
        return transferencias.stream()
                .map(transferencia -> mapper.map(transferencia, TransferenciaDTO.class))
                .collect(Collectors.toList());
    }
}
