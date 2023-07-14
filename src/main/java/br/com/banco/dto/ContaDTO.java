package br.com.banco.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class ContaDTO {
    
    @NonNull
    private String nomeResponsavel;

}
