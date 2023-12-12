package br.edu.ifms.cadastrodeanimal.service;

import br.edu.ifms.cadastrodeanimal.model.Animal;
import br.edu.ifms.cadastrodeanimal.repository.AnimalRepository;
import org.springframework.stereotype.Service;

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

    public List<Animal> listAll() {
        return new ArrayList<>(animalRepository.findAll());
    }

    public void deleteById(Long id) {
        verifyIfExists(id);
        animalRepository.deleteById(id);
    }

    public Animal verifyIfExists(Long id) {
        return animalRepository.findById(id).orElseThrow();
    }

    public void updateAnimal(Animal animal) {
        verifyIfExists(animal.getId());
        animalRepository.save(animal);
    }
}
