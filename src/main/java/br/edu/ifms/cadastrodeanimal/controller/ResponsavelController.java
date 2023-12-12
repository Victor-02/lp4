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

    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @GetMapping("/")
    public String novoAnimal(Model model) {
        Responsavel responsavel = new Responsavel();
        model.addAttribute("responsavel", responsavel);
        return "novoResponsavel";
    }

    @PostMapping("/criar-responsavel")
    public String criarResponsavel(@Valid @ModelAttribute("responsavel") Responsavel responsavel, RedirectAttributes attributes) {
        if (responsavel.getNome().isEmpty() || responsavel.getTelefone().isEmpty() || responsavel.getCpf().isEmpty() || responsavel.getEndereco().isEmpty()) {
            attributes.addFlashAttribute("erro", "Preencha todos os campos!");
            return "redirect:/novo-responsavel/";
        }
        responsavelService.createResponsavel(responsavel);
        return "redirect:/listar-responsaveis";
    }

    @GetMapping("/listar-responsaveis")
    public String listarResponsaveis(Model model) {
        List<Responsavel> responsaveis = responsavelService.listAll();
        model.addAttribute("responsaveis", responsaveis);
        return "listarResponsaveis";
    }
}
