package br.edu.ifms.cadastrodeanimal.controller;

import br.edu.ifms.cadastrodeanimal.model.Animal;
import br.edu.ifms.cadastrodeanimal.service.AnimalService;
import br.edu.ifms.cadastrodeanimal.service.ResponsavelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("responsaveis", responsavelService.listAll());
        model.addAttribute("animal", new Animal());
        return "novoAnimal";
    }

    @PostMapping("/criar-animal")
    public String criarAnimal(@Valid @ModelAttribute("animal") Animal animal, RedirectAttributes attributes) {
        if (animal.getPeso() == null || animal.getIdade() == null || animal.getNome() == null || animal.getDoenca() == null || animal.getResponsavel() == null) {
            attributes.addFlashAttribute("erro", "Preencha todos os campos!");
            return "redirect:/";
        }
        animalService.createAnimal(animal);
        return "redirect:/listar-animais";
    }

    @GetMapping("/listar-animais")
    public String listarAnimais(Model model) {
        List<Animal> animais = animalService.listAll();
        model.addAttribute("animais", animais);
        return "listarAnimais";
    }

    @GetMapping("/excluir-animal/{id}")
    public String deleteById(@PathVariable Long id) {
        animalService.deleteById(id);
        return "redirect:/listar-animais";
    }

}
