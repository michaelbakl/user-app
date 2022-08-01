package it.server.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class GetUserRequest {

  @JsonProperty
  private final @Getter String userId;

  public GetUserRequest(@JsonProperty("userId") final String userId) {
    this.userId = userId;
  }
}
