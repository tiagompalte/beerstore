package com.tiagompalte.beerstore.repository;

import com.tiagompalte.beerstore.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Beers extends JpaRepository<Beer, Long> {

}
