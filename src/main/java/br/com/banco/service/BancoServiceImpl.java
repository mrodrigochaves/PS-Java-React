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
    public BancoServiceImpl(ContaRepository contaRepository, TransferenciaRepository transferenciaRepository,
            ModelMapper mapper) {
        this.contaRepository = contaRepository;
        this.transferenciaRepository = transferenciaRepository;
        this.mapper = mapper;
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
    public Optional<ContaDTO> createConta(@Valid ContaDTO request) {
        Conta conta = mapper.map(request, Conta.class);
        Conta savedConta = contaRepository.saveAndFlush(conta);
        return Optional.of(mapper.map(savedConta, ContaDTO.class));
    }

    @Override
    public List<ContaDTO> getAllContas() {
        List<Conta> contas = contaRepository.findAll();
        return mapContasListToDTO(contas);
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
