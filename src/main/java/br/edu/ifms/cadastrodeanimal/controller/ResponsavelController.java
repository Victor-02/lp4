package br.edu.ifms.cadastrodeanimal.controller;

import br.edu.ifms.cadastrodeanimal.model.Animal;
import br.edu.ifms.cadastrodeanimal.model.Responsavel;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class ResponsavelController {

    private final ResponsavelService responsavelService;
    private final AnimalService animalService;

    public ResponsavelController(ResponsavelService responsavelService, AnimalService animalService) {
        this.responsavelService = responsavelService;
        this.animalService = animalService;
    }

    @GetMapping("/novo-responsavel/{id}")
    public String novoAnimal(Model model, @PathVariable Long id) {
        Responsavel responsavel = new Responsavel();
        List<Animal> animais = new ArrayList<>();
        Animal animal = animalService.findById(id);
        animal.setResponsavel(responsavel);
        animais.add(animal);
        responsavel.setAnimais(animais);
        model.addAttribute("responsavel", responsavel);
        model.addAttribute("id", id);
        return "novoResponsavel";
    }

    @PostMapping("/criar-responsavel/{id}")
    public String criarResponsavel(@Valid @ModelAttribute("responsavel") Responsavel responsavel, @PathVariable Long id, RedirectAttributes attributes) {
        if (responsavel.getNome().isEmpty() || responsavel.getTelefone().isEmpty() || responsavel.getCpf().isEmpty() || responsavel.getEndereco().isEmpty()) {
            attributes.addFlashAttribute("erro", "Preencha todos os campos!");
            return "redirect:/novo-responsavel/" + id;
        }
        responsavelService.createResponsavel(responsavel);
        return "redirect:/listar-animais";
    }
}
