package com.tiagompalte.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistException extends BusinessException {

    public EntityAlreadyExistException(final String entity) {
        super("generic-already-exists", entity, HttpStatus.BAD_REQUEST);
    }

}
