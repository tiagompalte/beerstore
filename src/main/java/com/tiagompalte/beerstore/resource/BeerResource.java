package com.tiagompalte.beerstore.resource;

import com.tiagompalte.beerstore.model.Beer;
import com.tiagompalte.beerstore.repository.Beers;
import com.tiagompalte.beerstore.service.BeerService;
import com.tiagompalte.beerstore.service.exception.EntityNotFoundException;
import javafx.scene.shape.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
public class BeerResource {

    @Autowired
    private Beers beers;

    @Autowired
    private BeerService beerService;

    @GetMapping
    public List<Beer> all() {
        return beers.findAll();
    }

    @GetMapping("/{id}")
    public Beer getById(@PathVariable Long id)
    {
        Optional<Beer> beer = beers.findById(id);

        if(!beer.isPresent()) {
            throw new EntityNotFoundException("Beer");
        }

        return beer.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer create(@Valid @RequestBody Beer beer) {
        return beerService.save(beer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Beer update(@PathVariable Long id, @Valid @RequestBody Beer beer) {
        beer.setId(id);
        return beerService.save(beer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        beerService.delete(id);
    }

}
