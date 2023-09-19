package br.edu.ifms.cadastrodeanimal.mapper;

import br.edu.ifms.cadastrodeanimal.dto.AnimalDTO;
import br.edu.ifms.cadastrodeanimal.model.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnimalMapper {

    AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);

    Animal toModel(AnimalDTO aniamlDTO);

    AnimalDTO toDTO(Animal animal);
}