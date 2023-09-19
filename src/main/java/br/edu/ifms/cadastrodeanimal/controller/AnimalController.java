package br.edu.ifms.cadastrodeanimal.controller;

import br.edu.ifms.cadastrodeanimal.dto.AnimalDTO;
import br.edu.ifms.cadastrodeanimal.exception.AnimalNotFoundException;
import br.edu.ifms.cadastrodeanimal.model.Animal;
import br.edu.ifms.cadastrodeanimal.service.AnimalService;
import br.edu.ifms.cadastrodeanimal.service.ResponsavelService;
import org.springframework.http.HttpStatus;
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
    public String home(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("responsaveis", responsavelService.listAll());
        return "novoAnimal";
    }

    @PostMapping("/criar-animal")
    public String cadastrar(@Valid @ModelAttribute("animal") AnimalDTO animal) {
        System.out.println(animal);
        animalService.createAnimal(animal);
        return "redirect:/novo-animal";
    }

    @GetMapping("/{name}")
    public AnimalDTO findByName(@PathVariable String name) throws AnimalNotFoundException {
        return animalService.findByName(name);
    }

    @RequestMapping("/listar-animais")
    public String listarAnimais(Model model) {
        List<AnimalDTO> animais = animalService.listAll();
        model.addAttribute("animais", animais);
        return "listarAnimais";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws AnimalNotFoundException {
        animalService.deleteById(id);
        //redirect:to = "/listar";
    }
}
