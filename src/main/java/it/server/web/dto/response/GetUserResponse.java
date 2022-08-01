package it.server.web.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUserResponse {

  @JsonProperty
  private String userId;

  @JsonProperty
  private String userFullName;

  public GetUserResponse(
          @JsonProperty("userId") String userId,
          @JsonProperty("userFullName") String userFullName
  ) {
    this.userId = userId;
    this.userFullName = userFullName;
  }
}
