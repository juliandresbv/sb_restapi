package com.sb_restapi.adapter.controllers.person.responses;

public class CreatePersonResponse {
  private String id;

  public CreatePersonResponse(String id) {
    this.id = id;
  }

  public String getId() {
    return this.id;
  }
}
