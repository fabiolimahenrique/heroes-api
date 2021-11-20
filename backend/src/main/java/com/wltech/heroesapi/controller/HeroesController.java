package com.wltech.heroesapi.controller;

import com.wltech.heroesapi.document.Heroes;
import com.wltech.heroesapi.repository.HeroesRepository;
import com.wltech.heroesapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HeroesController {

    @Autowired
    private HeroesService heroesService;

    @Autowired
    private HeroesRepository heroesRepository;


    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItens(){
        Log.info("requesting the list off all heroes");
        return heroesService.findAll();
    }

    @GetMapping
    public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id){
        log.info("requesting the hero wit id {}", id);
        return heroesService.findByIdHero(id)
                .map( (item) -> new ResponseEntity<>(item, HttpStatus.ok))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMappingdigi
    @ResponseStatus(code= HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes){
        log.info("a new hero was created");
        return heroesService.save(heroes);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.CONTINUE)
    public Mono<HttpStatus> deleteByHero(@PathVariable String id) {
        heroesService.deleteByHero(id);
        log.info("deleting a hero with id {}", id);
        return Mono.justOrEmpty(HttpStatus.CONTINUE);
    }

}
