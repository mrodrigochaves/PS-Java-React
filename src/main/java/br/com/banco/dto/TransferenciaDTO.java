package br.com.banco.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;



import lombok.Data;
import lombok.NonNull;

@Data
public class TransferenciaDTO {
    
    @NonNull
    private LocalDateTime dataTransferencia;

    @NonNull
    private BigDecimal valor;

    @NonNull
    private String tipo;

    @NonNull
    private String nomeOperadorTransacao;

    @NonNull
    private Long contaId;

}
