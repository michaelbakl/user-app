package it.server.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class CreateUserRequest {

  @JsonProperty
  private final @Getter String userId;

  @JsonProperty
  private final @Getter String userFullName;

  public CreateUserRequest(
          @JsonProperty("userId") final String userId,
          @JsonProperty("userFullName") final String userFullName
  ) {
    this.userId = userId;
    this.userFullName = userFullName;
  }
}
