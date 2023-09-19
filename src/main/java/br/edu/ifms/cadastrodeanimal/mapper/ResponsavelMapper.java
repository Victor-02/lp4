package br.edu.ifms.cadastrodeanimal.mapper;

import br.edu.ifms.cadastrodeanimal.dto.ResponsavelDTO;
import br.edu.ifms.cadastrodeanimal.model.Responsavel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponsavelMapper {

    ResponsavelMapper INSTANCE = Mappers.getMapper(ResponsavelMapper.class);

    Responsavel toModel(ResponsavelDTO aniamlDTO);

    ResponsavelDTO toDTO(Responsavel animal);
}