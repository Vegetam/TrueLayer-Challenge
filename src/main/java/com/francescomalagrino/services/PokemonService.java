package com.francescomalagrino.services;

import com.francescomalagrino.api.exception.CustomException;
import com.francescomalagrino.clients.pokemon.model.DescriptionEntry;
import com.francescomalagrino.clients.pokemon.model.Pokemon;
import com.francescomalagrino.clients.pokemon.PokemonApiClient;
import com.francescomalagrino.clients.pokemon.model.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

  private PokemonApiClient pokemonApiClient;

  @Autowired
  public PokemonService(PokemonApiClient pokemonApiClient) {
    this.pokemonApiClient = pokemonApiClient;
  }

  public String getPokemonDescriptionInEnglish(String pokemonName) {
    // I will makes two api calls for each pokemon we want to retrieve this is for test purpose not for a production software
    try {
      Pokemon pokemon = pokemonApiClient.getPokemon(pokemonName);
      Species species = pokemonApiClient.getSpeciesFor(pokemon);
      return species.getDescriptions()
          .stream()
          .filter(descriptionEntry -> descriptionEntry.getLanguage().getName().equals("en"))
          .map(DescriptionEntry::getText)
          .map(this::removeSpecialChars)
          .findFirst()
          .orElseThrow(Exception::new);
    } catch (Exception e) {
      // in production i can make a better Custom Exception with better message
      throw new CustomException("errore : ", e);
    }
  }

  private String removeSpecialChars(String original) {
    return original.replaceAll("\r", " ").replaceAll("\n", " ").replaceAll("\t", " s");
  }
}
