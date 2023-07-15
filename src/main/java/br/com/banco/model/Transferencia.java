package br.com.banco.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.NonNull;

@Entity
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data_transferencia", nullable = false)
    private LocalDateTime dataTransferencia;

    @Column(name="valor", nullable = false)
    private double valor;

    @Column(name="tipo", nullable = false)
    private String tipo;

    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    public Transferencia() {
    }

    public Transferencia(Long id, LocalDateTime dataTransferencia, double valor, String tipo,
            String nomeOperadorTransacao, Conta conta) {
        this.id = id;
        this.dataTransferencia = dataTransferencia;
        this.valor = valor;
        this.tipo = tipo;
        this.nomeOperadorTransacao = nomeOperadorTransacao;
        this.conta = conta;
    }

    public void setDataTransferencia(@NonNull LocalDateTime dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public void setValor( double valor) {
        this.valor = valor;
    }

    public void setTipo(@NonNull String tipo) {
        this.tipo = tipo;
    }

    public void setNomeOperadorTransacao(@NonNull String nomeOperadorTransacao) {
        this.nomeOperadorTransacao = nomeOperadorTransacao;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

}
