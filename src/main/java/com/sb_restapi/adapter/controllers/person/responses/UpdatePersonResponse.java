package com.sb_restapi.adapter.controllers.person.responses;

public class UpdatePersonResponse {
  private String id;

  public UpdatePersonResponse(String id) {
    this.id = id;
  }

  public String getId() {
    return this.id;
  }
}
