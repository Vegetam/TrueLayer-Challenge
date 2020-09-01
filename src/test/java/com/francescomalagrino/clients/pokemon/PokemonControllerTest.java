package com.francescomalagrino.clients.pokemon;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.francescomalagrino.api.exception.ExceptionDetails;
import com.francescomalagrino.api.exception.GlobalExceptionHandler;
import com.francescomalagrino.api.model.ShakespearePokemon;
import com.francescomalagrino.controller.PokemonController;
import com.francescomalagrino.services.PokemonService;
import com.francescomalagrino.services.ShakespeareService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PokemonControllerTest {

  @Mock
  private PokemonService pokemonService;
  @Mock
  private ShakespeareService shakespeareService;

  private PokemonController pokemonController;

  @Before
  public void setUp() {
    pokemonController = new PokemonController(pokemonService, shakespeareService);
  }

  @Test
  public void shouldReturnAValidPokemonResponse() throws Exception {
    String pokemonName = "a pokemon";
    String originalDescription = "description";
    String translatedDescription = "translated description";
    when(pokemonService.getPokemonDescriptionInEnglish(pokemonName))
        .thenReturn(originalDescription);
    when(shakespeareService.getTranslatedDescription(originalDescription))
        .thenReturn(translatedDescription);
    ShakespearePokemon expectedResponse = new ShakespearePokemon(
      pokemonName,
      translatedDescription
    );
    ShakespearePokemon pokemonResponse = pokemonController.pokemon(pokemonName);
    assertEquals(expectedResponse, pokemonResponse);
  }

  @Test(expected = Exception.class)
  public void shouldThrowIfPokemonServiceFails() throws Exception {
    String pokemonName = "a pokemon";
    when(pokemonService.getPokemonDescriptionInEnglish(pokemonName))
        .thenThrow(new Exception());
    pokemonController.pokemon(pokemonName);
  }

  @Test(expected = Exception.class)
  public void shouldThrowIfShakespeareServiceFails() throws Exception {
    String pokemonName = "a pokemon";
    when(pokemonService.getPokemonDescriptionInEnglish(pokemonName))
        .thenReturn("desc");
    when(shakespeareService.getTranslatedDescription("desc"))
        .thenThrow(new Exception());
    pokemonController.pokemon(pokemonName);
  }
}