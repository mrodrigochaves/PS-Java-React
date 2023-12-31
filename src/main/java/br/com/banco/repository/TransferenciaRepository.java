package br.com.banco.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.banco.model.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>{

    List<Transferencia> findByTipo(String tipo);

    List<Transferencia> findByContaId(Long contaId);

    List<Transferencia> findByDataTransferencia(LocalDate dataTransferencia);

    List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);
    
}
