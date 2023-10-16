package br.edu.ifms.cadastrodeanimal.controller;

import br.edu.ifms.cadastrodeanimal.exception.AnimalNotFoundException;
import br.edu.ifms.cadastrodeanimal.model.Animal;
import br.edu.ifms.cadastrodeanimal.service.AnimalService;
import br.edu.ifms.cadastrodeanimal.service.ResponsavelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "redirect:/novo-animal";
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
    public String deleteById(@PathVariable Long id) throws AnimalNotFoundException {
        animalService.deleteById(id);
        return "redirect:/listar-responsaveis";
    }

    @GetMapping("/responsavel-animal/{id}")
    public String getAnimaisPorResponsavel(@PathVariable Long id, Model model) {
        model.addAttribute("animais", animalService.findByResponsavel(id));
        return "listarAnimais";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") long id, RedirectAttributes attributes,
                             Model model) {
        try {
            Animal animal = animalService.findById(id);
            model.addAttribute("animal", animal);
            return "novoAnimal";
        } catch (AnimalNotFoundException e) {
            attributes.addFlashAttribute("mensagemErro", e.getMessage());
        }
        return "redirect:/listar-animais";
    }

    @PostMapping("/editar/{id}")
    public String editarAnimal(@PathVariable("id") long id,
                               @ModelAttribute("animal") @Valid Animal animal,
                               BindingResult erros) throws AnimalNotFoundException {
        if (erros.hasErrors()) {
            animal.setId(id);
            return "/alterar-animal";
        }
        animalService.editarAnimal(animal);
        return "redirect:/listar-animais";
    }

}
