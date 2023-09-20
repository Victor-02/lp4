package br.edu.ifms.cadastrodeanimal.service;

import br.edu.ifms.cadastrodeanimal.exception.AnimalNotFoundException;
import br.edu.ifms.cadastrodeanimal.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import br.edu.ifms.cadastrodeanimal.model.Animal;


import java.util.ArrayList;
import java.util.List;
@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal createAnimal(Animal animal) {
        Animal animalSalvo = animalRepository.save(animal);
        return animalSalvo;
    }

    public Animal findByName(String name) throws AnimalNotFoundException {
        Animal animalEncontrado = animalRepository.findByNome(name)
                .orElseThrow(() -> new AnimalNotFoundException(name));
        return animalEncontrado;
    }

    public List<Animal> listAll() {
        return new ArrayList<>(animalRepository.findAll());
    }

    public void deleteById(Long id) throws AnimalNotFoundException {
        verifyIfExists(id);
        animalRepository.deleteById(id);
    }

    private Animal verifyIfExists(Long id) throws AnimalNotFoundException {
        return animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));
    }
}
