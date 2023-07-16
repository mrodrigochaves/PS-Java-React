package br.com.banco.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NonNull;

@Data
public class ContaDTO {

    @NonNull
    private Long id;

    @NotBlank
    private String nomeResponsavel;

    public ContaDTO() {

    }

}
