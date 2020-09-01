package com.francescomalagrino.controller;

import com.francescomalagrino.api.model.ShakespearePokemon;
import com.francescomalagrino.services.PokemonService;
import com.francescomalagrino.services.ShakespeareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private PokemonService pokemonService;
    private ShakespeareService shakespeareService;

    @Autowired
    public PokemonController(
        PokemonService pokemonService,
        ShakespeareService shakespeareService) {
        this.pokemonService = pokemonService;
        this.shakespeareService = shakespeareService;
    }

    @GetMapping("/{pokemonName}")
    public ShakespearePokemon pokemon(@PathVariable String pokemonName) throws Exception {
        String pokemonDescription = pokemonService.getPokemonDescriptionInEnglish(pokemonName);
        String translatedDescription = shakespeareService.getTranslatedDescription(pokemonDescription);
        return new ShakespearePokemon(pokemonName, translatedDescription);
    }
    
}
