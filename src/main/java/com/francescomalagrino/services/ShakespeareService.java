package com.francescomalagrino.services;

import com.francescomalagrino.api.exception.CustomException;
import com.francescomalagrino.clients.shakespeare.ShakespeareApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShakespeareService {

  private ShakespeareApiClient shakespeareApiClient;

  @Autowired
  public ShakespeareService(ShakespeareApiClient shakespeareApiClient) {
    this.shakespeareApiClient = shakespeareApiClient;
  }

  public String getTranslatedDescription(String description) {
    try {
      return shakespeareApiClient.getTranslation(description).getContents().getTranslated();
    } catch (Exception e) {
      // in production code we might want a specific exception handler / message etc.
      throw new CustomException("Error : " + shakespeareApiClient.getTranslation(description).getContents().getTranslated(), e);
    }
  }
}