package br.edu.ifms.cadastrodeanimal.controller;

import br.edu.ifms.cadastrodeanimal.dto.ResponsavelDTO;
import br.edu.ifms.cadastrodeanimal.exception.ResponsavelNotFoundException;
import br.edu.ifms.cadastrodeanimal.model.Responsavel;
import br.edu.ifms.cadastrodeanimal.service.ResponsavelService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        List<ResponsavelDTO> responsaveis = responsavelService.listAll();
        model.addAttribute("responsaveis", responsaveis);
        return "/listar-responsaveis";
    }

    @DeleteMapping("/deletar-responsavel/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String DeletarResponsavel(@PathVariable Long id) throws ResponsavelNotFoundException {
        responsavelService.deleteById(id);
        return "redirect:/listar-responsavel";
    }
}
