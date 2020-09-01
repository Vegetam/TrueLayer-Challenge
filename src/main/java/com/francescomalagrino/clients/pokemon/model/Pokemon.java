package com.francescomalagrino.clients.pokemon.model;

import com.francescomalagrino.clients.pokemon.model.Pokemon.Species;

public class Pokemon 
{

	  private Species species;

	  public Species getSpecies() {
	    return species;
	  }

	  public Pokemon() {
	  }

	  public Pokemon(Species species) {
	    this.species = species;
	  }

	  public static class Species {
	    private String name;
	    private String url;

	    public Species() {
	    }

	    public Species(String name, String url) {
	      this.name = name;
	      this.url = url;
	    }

	    public String getName() {
	      return name;
	    }

	    public String getUrl() {
	      return url;
	    }
	  }
}
