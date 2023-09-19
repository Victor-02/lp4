package br.edu.ifms.cadastrodeanimal.service;

import br.edu.ifms.cadastrodeanimal.dto.ResponsavelDTO;
import br.edu.ifms.cadastrodeanimal.exception.ResponsavelNotFoundException;
import br.edu.ifms.cadastrodeanimal.mapper.ResponsavelMapper;
import br.edu.ifms.cadastrodeanimal.model.Responsavel;
import br.edu.ifms.cadastrodeanimal.repository.ResponsavelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponsavelService {
    private static final ResponsavelMapper responsavelMapper = ResponsavelMapper.INSTANCE;
    private final ResponsavelRepository responsavelRepository;

    public ResponsavelService(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    public ResponsavelDTO createResponsavel(ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = responsavelMapper.toModel(responsavelDTO);
        Responsavel savedResponsavel = responsavelRepository.save(responsavel);
        return responsavelMapper.toDTO(savedResponsavel);
    }

    public List<ResponsavelDTO> listAll() {
        List<ResponsavelDTO> responsavelDTOList = new ArrayList<>();
        for (Responsavel responsavel : responsavelRepository.findAll()
        ) {
            if (responsavel.getAnimais() == null) {
                responsavel.setAnimais(new ArrayList<>());
            }
            responsavelDTOList.add(responsavelMapper.toDTO(responsavel));
        }
        return responsavelDTOList;
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
}
