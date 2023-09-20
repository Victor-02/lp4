package br.edu.ifms.cadastrodeanimal.controller;

import br.edu.ifms.cadastrodeanimal.exception.AnimalNotFoundException;
import br.edu.ifms.cadastrodeanimal.exception.ResponsavelNotFoundException;
import br.edu.ifms.cadastrodeanimal.model.Animal;
import br.edu.ifms.cadastrodeanimal.service.AnimalService;
import br.edu.ifms.cadastrodeanimal.service.ResponsavelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AnimalController {

    private final AnimalService animalService;
    private final ResponsavelService responsavelService;

    public AnimalController(AnimalService animalService, ResponsavelService responsavelService) {
        this.animalService = animalService;
        this.responsavelService = responsavelService;
    }

    @GetMapping("/novo-animal")
    public String novoAnimal(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("responsaveis", responsavelService.listAll());
        return "novoAnimal";
    }

    @PostMapping("/criar-animal")
    public String criarAnimal(@Valid @ModelAttribute("animal") Animal animal) {
        System.out.println(animal);
        animalService.createAnimal(animal);
        return "redirect:/listar-responsaveis";
    }

    @GetMapping("/buscar-animal/{name}")
    public Animal buscarPorNome(@PathVariable String nome) throws AnimalNotFoundException {
        return animalService.findByName(nome);
    }

    @GetMapping("/listar-animais")
    public String listarAnimais(Model model) {
        List<Animal> animais = animalService.listAll();
        model.addAttribute("animais", animais);
        return "listarAnimais";
    }

    @DeleteMapping("/excluir-animal/{id}")
    public String excluirPorId(@PathVariable Long id) throws AnimalNotFoundException {
        animalService.deleteById(id);
        return "redirect:/listar-responsaveis";
    }

    @GetMapping("/responsavel-animal/{id}")
    public String getAnimaisPorResponsavel(@PathVariable Long id, Model model) {
        model.addAttribute("animais", animalService.findByResponsavel(id));
        return "listarAnimais";
    }
}
