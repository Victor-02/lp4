package br.edu.ifms.cadastrodeanimal.service;

import br.edu.ifms.cadastrodeanimal.model.Responsavel;
import br.edu.ifms.cadastrodeanimal.repository.ResponsavelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsavelService {
    private final ResponsavelRepository responsavelRepository;

    public ResponsavelService(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    public Responsavel createResponsavel(Responsavel responsavel) {
        Responsavel ResponsavelSalvo = responsavelRepository.save(responsavel);
        return ResponsavelSalvo;
    }

    public List<Responsavel> listAll() {
        return responsavelRepository.findAll();
    }
}
