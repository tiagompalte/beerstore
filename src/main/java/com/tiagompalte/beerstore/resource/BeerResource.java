package com.tiagompalte.beerstore.resource;

import com.tiagompalte.beerstore.model.Beer;
import com.tiagompalte.beerstore.repository.Beers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerResource {

    @Autowired
    private Beers beers;

    @GetMapping
    public List<Beer> all() {
        return beers.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer create(@Valid @RequestBody Beer beer) {
        return beers.save(beer);
    }

}
