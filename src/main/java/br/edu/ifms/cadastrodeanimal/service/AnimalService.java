package br.edu.ifms.cadastrodeanimal.service;

import br.edu.ifms.cadastrodeanimal.exception.AnimalNotFoundException;
import br.edu.ifms.cadastrodeanimal.model.Responsavel;
import br.edu.ifms.cadastrodeanimal.repository.AnimalRepository;
import br.edu.ifms.cadastrodeanimal.repository.ResponsavelRepository;
import org.springframework.stereotype.Service;
import br.edu.ifms.cadastrodeanimal.model.Animal;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final ResponsavelRepository responsavelRepository;

    public AnimalService(AnimalRepository animalRepository, ResponsavelRepository responsavelRepository) {
        this.animalRepository = animalRepository;
        this.responsavelRepository = responsavelRepository;
    }

    public void createAnimal(Animal animal) {
        Animal animalSalvo = animalRepository.save(animal);
        Optional<Responsavel> responsavel = responsavelRepository.findById(animal.getResponsavel().getId());
        List<Animal> animais = responsavel.get().getAnimais();
        animais.add(animal);
        responsavel.get().setAnimais(animais);
        responsavelRepository.save(responsavel.get());
    }

    public Animal findByName(String name) throws AnimalNotFoundException {
        Animal animalEncontrado = animalRepository.findByNome(name)
                .orElseThrow(() -> new AnimalNotFoundException(name));
        return animalEncontrado;
    }

    public List<Animal> findByResponsavel(Long id) {
        return new ArrayList<Animal>(animalRepository.findAllByResponsavelId(id));
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

    public Animal findById(Long id) throws AnimalNotFoundException {
        return animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));
    }

    public void editarAnimal(Animal animal) throws AnimalNotFoundException {
        animalRepository.save(animal);
    }
}
