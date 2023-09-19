package br.edu.ifms.cadastrodeanimal.model;

import br.edu.ifms.cadastrodeanimal.dto.ResponsavelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String doenca;

    @Column(nullable = false)
    private Double peso;

    @ManyToOne
    private Responsavel responsavel;
}

