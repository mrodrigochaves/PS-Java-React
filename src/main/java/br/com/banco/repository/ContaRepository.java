package br.com.banco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.banco.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

    List<Conta> findByNomeResponsavel(String responsavel);
    
}
