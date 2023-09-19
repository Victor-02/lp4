package br.edu.ifms.cadastrodeanimal.dto;

import br.edu.ifms.cadastrodeanimal.model.Animal;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;


import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsavelDTO {

    @NotNull
    @Size(min = 1, max = 200)
    private String nome;

    @NotNull
    @CPF
    private String cpf;

    @NotNull
    @Size(min = 1, max = 200)
    private String endereco;

    @NotNull
    @Size(min = 8, max = 20)
    private String telefone;

    private List<AnimalDTO> animais;
}
