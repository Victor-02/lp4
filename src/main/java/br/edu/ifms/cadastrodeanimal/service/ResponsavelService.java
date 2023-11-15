package br.edu.ifms.cadastrodeanimal.service;

import br.edu.ifms.cadastrodeanimal.exception.ResponsavelNotFoundException;
import br.edu.ifms.cadastrodeanimal.model.Responsavel;
import br.edu.ifms.cadastrodeanimal.repository.ResponsavelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponsavelService {
    private final ResponsavelRepository responsavelRepository;

    public ResponsavelService(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    public Responsavel createResponsavel(Responsavel responsavel) {
        Responsavel savedResponsavel = responsavelRepository.save(responsavel);
        return savedResponsavel;
    }

    public List<Responsavel> listaResponsaveis() {
        List<Responsavel> responsavelList = new ArrayList<>();
        for (Responsavel responsavel : responsavelRepository.findAll()
        ) {
            if (responsavel.getAnimais() == null) {
                responsavel.setAnimais(new ArrayList<>());
            }
            responsavelList.add(responsavel);
        }
        return responsavelList;
    }

    public void deleteById(Long id) throws ResponsavelNotFoundException {
        if (verifyIfExists(id) != null) {
            responsavelRepository.deleteById(id);
        }
    }

    private Responsavel verifyIfExists(Long id) throws ResponsavelNotFoundException {
        return responsavelRepository.findById(id)
                .orElseThrow(() -> new ResponsavelNotFoundException(id));
    }

    public Responsavel buscaResponsavelPorId(Long id){return null;}
}
