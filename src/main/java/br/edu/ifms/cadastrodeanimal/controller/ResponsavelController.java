package br.edu.ifms.cadastrodeanimal.controller;

import br.edu.ifms.cadastrodeanimal.model.Responsavel;
import br.edu.ifms.cadastrodeanimal.service.ResponsavelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @GetMapping("/novo-responsavel")
    public String novoAnimal(Model model) {
        model.addAttribute("responsavel", new Responsavel());
        return "novoResponsavel";
    }

    @PostMapping("/criar-responsavel")
    public String criarResponsavel(@Valid @ModelAttribute("responsavel") Responsavel responsavel) {
        responsavelService.createResponsavel(responsavel);
        return "redirect:/novo-responsavel";
    }
}
