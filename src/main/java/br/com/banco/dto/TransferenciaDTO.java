package br.com.banco.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransferenciaDTO {

    private LocalDateTime dataTransferencia;

    private LocalDateTime periodo;

    private BigDecimal valor;

    private String tipo;

    private String nomeOperadorTransacao;

    private Long contaId;

    TransferenciaDTO() {
    }
}
