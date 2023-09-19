package br.edu.ifms.cadastrodeanimal.repository;

import br.edu.ifms.cadastrodeanimal.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<Animal> findByNome(String name);
}
