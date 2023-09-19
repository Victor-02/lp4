package br.edu.ifms.cadastrodeanimal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnimalNotFoundException extends Exception {

    public AnimalNotFoundException(String animalNome) {
        super(String.format("Animal com o nome %s não foi encontrado no sistema .", animalNome));
    }

    public AnimalNotFoundException(Long id) {
        super(String.format("Animal com o id %s não foi encontrado.", id));
    }
}
