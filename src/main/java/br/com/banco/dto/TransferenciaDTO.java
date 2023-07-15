package br.com.banco.dto;


import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NonNull;

@Data
public class TransferenciaDTO {
    
    @NonNull
    private LocalDateTime dataTransferencia;

    @NotBlank
    private double valor;

    @NonNull
    private String tipo;

    @NonNull
    private String nomeOperadorTransacao;

    @NonNull
    private Long contaId;

}
