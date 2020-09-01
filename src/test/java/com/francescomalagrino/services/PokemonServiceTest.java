package com.francescomalagrino.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.francescomalagrino.api.exception.CustomException;
import com.francescomalagrino.api.exception.ExceptionDetails;
import com.francescomalagrino.api.exception.GlobalExceptionHandler;
import com.francescomalagrino.clients.pokemon.PokemonApiClient;
import com.francescomalagrino.clients.pokemon.model.DescriptionEntry;
import com.francescomalagrino.clients.pokemon.model.Language;
import com.francescomalagrino.clients.pokemon.model.Pokemon;
import com.francescomalagrino.clients.pokemon.model.Species;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PokemonServiceTest {

  @Mock
  private PokemonApiClient pokemonApiClient;

  private PokemonService pokemonService;
  @Before
  public void setup() {
    pokemonService = new PokemonService(pokemonApiClient);
  }

  @Test
  public void shouldRetrieveAPokemonDescriptionInEnglish() throws Exception {
    // in a real project all of these data would probably be separated into test doubles / test
    // data generators to avoid cluttering the test cases themselves
    DescriptionEntry expectedDescription =
        new DescriptionEntry("correct", new Language("en"));
    List<DescriptionEntry> descriptionEntries = Arrays.asList(
        new DescriptionEntry("wrong", new Language("jp")),
        expectedDescription,
        new DescriptionEntry("wrong again", new Language("it"))
    );
    Pokemon pokemon = new Pokemon();
    Species species = new Species(descriptionEntries);
    when(pokemonApiClient.getPokemon("pokemon")).thenReturn(pokemon);
    when(pokemonApiClient.getSpeciesFor(pokemon)).thenReturn(species);
    String description = pokemonService.getPokemonDescriptionInEnglish("pokemon");

    assertEquals(expectedDescription.getText(), description);
  }

  @Test
  public void shouldThrowIfThereIsNoDescriptionInEnglish() throws Exception {
    List<DescriptionEntry> descriptionEntries = Arrays.asList(
        new DescriptionEntry("wrong", new Language("jp")),
        new DescriptionEntry("wrong again", new Language("it"))
    );
    Pokemon pokemon = new Pokemon();
    Species species = new Species(descriptionEntries);
    when(pokemonApiClient.getPokemon("pokemon")).thenReturn(pokemon);
    when(pokemonApiClient.getSpeciesFor(pokemon)).thenReturn(species);
    pokemonService.getPokemonDescriptionInEnglish("pokemon");
  }

  @Test
  public void shouldThrowIfNoDescriptionsAreAvaliable() throws Exception {
    Pokemon pokemon = new Pokemon();
    Species species = new Species(null);
    when(pokemonApiClient.getPokemon("pokemon")).thenReturn(pokemon);
    when(pokemonApiClient.getSpeciesFor(pokemon)).thenReturn(species);
    pokemonService.getPokemonDescriptionInEnglish("pokemon");
	
  }

  @Test(expected = Exception.class)
  public void shouldThrowIfClientErrors() throws Exception {
    when(pokemonApiClient.getPokemon("pokemon")).thenThrow(new Exception());
    pokemonService.getPokemonDescriptionInEnglish("pokemon");
  }

}