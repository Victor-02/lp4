package br.edu.ifms.cadastrodeanimal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String nome;

    @NotNull
    @Size(min = 1, max = 200)
    private String idade;

    @NotNull
    @Size(min = 1, max = 200)
    private String doenca;

    @NotNull
    @Size(min = 1, max = 200)
    private String quantity;

    private ResponsavelDTO responsavel;
}