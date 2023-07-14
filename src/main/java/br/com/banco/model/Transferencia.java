package br.com.banco.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
    
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.NonNull;
    
@Entity
@Table(name = "transferencia")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "data_transferencia", nullable = false)
    private LocalDateTime dataTransferencia;
    
    @Column(nullable = false)
    private BigDecimal valor;
    
    @Column(nullable = false)
    private String tipo;
    
    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;


    public void setDataTransferencia(LocalDateTime now) {
    }

     public void setValor(double d) {
    }

    public void setTipo(String string) {
    }

    public void setConta(Conta conta2) {
    }

    public void setNomeOperadorTransacao(String string) {
    }

    public void setValor(@NonNull BigDecimal valor2) {
    }

}
    



