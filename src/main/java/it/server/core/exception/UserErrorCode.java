package it.server.core.exception;

import lombok.Getter;

/**
 * enum class with error codes for exceptions
 */
public enum UserErrorCode {
  UNABLE_TO_CREATE_REPOSITORY("Unable to create repository"),

  USER_NOT_FOUND("User not found"),

  WRONG_INPUTS("Wrong inputs"),
  CAN_NOT_CREATE_USER("Can not create user"),
  CAN_NOT_UPDATE_USER("Can not update user"),
  CAN_NOT_DELETE_USER("Can not delete user");

  private final @Getter String errorCode;

  UserErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }
}
