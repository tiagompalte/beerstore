package com.tiagompalte.beerstore.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import com.tiagompalte.beerstore.model.Beer;
import com.tiagompalte.beerstore.model.BeerType;
import com.tiagompalte.beerstore.repository.Beers;
import com.tiagompalte.beerstore.service.exception.EntityAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

public class BeerServiceTest {

    private BeerService beerService;

    @Mock
    private Beers beersMocked;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        beerService = new BeerService(beersMocked);
    }

    @Test(expected = EntityAlreadyExistException.class)
    public void should_deny_creation_of_beer_that_exists() {
        Beer beerInDatabase = new Beer();
        beerInDatabase.setId(10L);
        beerInDatabase.setName("Heineken");
        beerInDatabase.setType(BeerType.LAGER);
        beerInDatabase.setVolume(new BigDecimal("355"));

        when(beersMocked.findByNameAndType("Heineken", BeerType.LAGER)).thenReturn(Optional.of(beerInDatabase));

        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));

        beerService.save(newBeer);
    }

    @Test
    public void should_create_new_beer() {

        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));

        Beer newBeerInDatabase = new Beer();
        newBeerInDatabase.setId(10L);
        newBeerInDatabase.setName("Heineken");
        newBeerInDatabase.setType(BeerType.LAGER);
        newBeerInDatabase.setVolume(new BigDecimal("355"));

        when(beersMocked.save(newBeer)).thenReturn(newBeerInDatabase);

        Beer beerSaved = beerService.save(newBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));
    }

    @Test
    public void should_update_beer() {

        Beer updateBeer = new Beer();
        updateBeer.setId(10L);
        updateBeer.setName("Heineken");
        updateBeer.setType(BeerType.LAGER);
        updateBeer.setVolume(new BigDecimal("355"));

        Beer newBeerInDatabase = new Beer();
        newBeerInDatabase.setId(10L);
        newBeerInDatabase.setName("Heineken");
        newBeerInDatabase.setType(BeerType.LAGER);
        newBeerInDatabase.setVolume(new BigDecimal("355"));

        when(beersMocked.save(updateBeer)).thenReturn(newBeerInDatabase);

        Beer beerSaved = beerService.save(updateBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));
    }

}
