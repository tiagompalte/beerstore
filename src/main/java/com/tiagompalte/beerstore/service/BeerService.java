package com.tiagompalte.beerstore.service;

import com.tiagompalte.beerstore.model.Beer;
import com.tiagompalte.beerstore.repository.Beers;
import com.tiagompalte.beerstore.service.exception.EntityAlreadyExistException;
import com.tiagompalte.beerstore.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeerService {

    private Beers beers;

    private static final String BEER = "Beer";

    public BeerService(@Autowired Beers beers) {
        this.beers = beers;
    }

    public Beer save(final Beer beer) {
        verifyIfBeerExists(beer);
        return beers.save(beer);
    }

    private void verifyIfBeerExists(final Beer beer) {

        Optional<Beer> beerByNameAndType = beers.findByNameAndType(beer.getName(), beer.getType());

        if (beerByNameAndType.isPresent() && (beer.isNew() ||
                isUpdatingToADifferentBeer(beer, beerByNameAndType))) {
            throw new EntityAlreadyExistException(BEER);
        }
    }

    private boolean isUpdatingToADifferentBeer(Beer beer, Optional<Beer> beerByNameAndType) {
        return !beer.isNew() && !beerByNameAndType.get().equals(beer);
    }

    public void delete(final Long id) {

        Optional<Beer> beer = beers.findById(id);

        if(!beer.isPresent()) {
            throw new EntityNotFoundException(BEER);
        }

        beers.delete(beer.get());
    }
}
