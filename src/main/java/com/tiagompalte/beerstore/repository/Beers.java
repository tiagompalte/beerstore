package com.tiagompalte.beerstore.repository;

import com.tiagompalte.beerstore.model.Beer;
import com.tiagompalte.beerstore.model.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Beers extends JpaRepository<Beer, Long> {

    Optional<Beer> findByNameAndType(String name, BeerType type);

}
