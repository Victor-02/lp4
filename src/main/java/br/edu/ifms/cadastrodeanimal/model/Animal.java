package br.edu.ifms.cadastrodeanimal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"responsavel", "peso", "doenca", "idade", "nome"})
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

