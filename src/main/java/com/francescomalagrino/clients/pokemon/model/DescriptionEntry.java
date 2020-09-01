package com.francescomalagrino.clients.pokemon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DescriptionEntry {
  @JsonProperty("flavor_text")
  private String text;

  public String getText() {
    return text;
  }


  public DescriptionEntry() {
  }

  public DescriptionEntry(String text) {
    this.text = text;

  }
}
