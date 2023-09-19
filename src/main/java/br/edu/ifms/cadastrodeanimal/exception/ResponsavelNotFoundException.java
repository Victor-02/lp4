package br.edu.ifms.cadastrodeanimal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResponsavelNotFoundException extends Exception {

    public ResponsavelNotFoundException(String animalNome) {
        super(String.format("Responsavel com o nome %s não foi encontrado no sistema .", animalNome));
    }

    public ResponsavelNotFoundException(Long id) {
        super(String.format("Responsavel com o id %s não foi encontrado.", id));
    }
}
