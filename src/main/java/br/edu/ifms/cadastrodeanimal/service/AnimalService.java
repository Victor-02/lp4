package br.edu.ifms.cadastrodeanimal.service;

import br.edu.ifms.cadastrodeanimal.dto.AnimalDTO;
import br.edu.ifms.cadastrodeanimal.exception.AnimalNotFoundException;
import br.edu.ifms.cadastrodeanimal.mapper.AnimalMapper;
import br.edu.ifms.cadastrodeanimal.model.Animal;
import br.edu.ifms.cadastrodeanimal.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper = AnimalMapper.INSTANCE;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public AnimalDTO createAnimal(AnimalDTO animalDTO) {
        Animal animal = animalMapper.toModel(animalDTO);
        Animal savedAnimal = animalRepository.save(animal);
        return animalMapper.toDTO(savedAnimal);
    }

    public AnimalDTO findByName(String name) throws AnimalNotFoundException {
        Animal foundAnimal = animalRepository.findByNome(name)
                .orElseThrow(() -> new AnimalNotFoundException(name));
        return animalMapper.toDTO(foundAnimal);
    }

    public List<AnimalDTO> listAll() {
        return animalRepository.findAll()
                .stream()
                .map(animalMapper::toDTO)
                .collect(Collectors.toList());
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
