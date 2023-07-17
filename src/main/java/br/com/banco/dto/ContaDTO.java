package br.com.banco.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ContaDTO {

    
    private Long id;

    @NotBlank
    private String nomeResponsavel;

    public ContaDTO() {

    }

}
