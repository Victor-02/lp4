package br.edu.ifms.cadastrodeanimal.controller;

import br.edu.ifms.cadastrodeanimal.exception.ResponsavelNotFoundException;
import br.edu.ifms.cadastrodeanimal.model.Responsavel;
import br.edu.ifms.cadastrodeanimal.service.ResponsavelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @GetMapping("/novo-responsavel")
    public String home(Model model) {
        model.addAttribute("responsavel", new Responsavel());
        return "novoResponsavel";
    }

    @PostMapping("/criar-responsavel")
    public void cadastrar(Responsavel responsavel) {
        System.out.println("Cadastrando..." + responsavel.getNome());
    }

    @RequestMapping("/listar-responsaveis")
    public String listarResponsaveis(Model model) {
        List<Responsavel> responsaveis = responsavelService.listAll();
        model.addAttribute("responsaveis", responsaveis);
        return "listarResponsaveis";
    }

    @DeleteMapping("/deletar-responsavel/{id}")
    public String DeletarResponsavel(@PathVariable Long id) throws ResponsavelNotFoundException {
        responsavelService.deleteById(id);
        return "redirect:/listar-responsavel";
    }
}
