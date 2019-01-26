package com.tiagompalte.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(final String entity) {
        super("generic-not-found", entity, HttpStatus.NOT_FOUND);
    }
}
