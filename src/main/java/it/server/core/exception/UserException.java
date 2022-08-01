package it.server.core.exception;

import lombok.Getter;

/**
 * exception class for user
 */
public class UserException extends Exception {
  private final @Getter UserErrorCode errorCode;

  /**
   * constructor
   *
   * @param errorCode - UserErrorCode enum
   */
  public UserException(final UserErrorCode errorCode) {
    this.errorCode = errorCode;
  }
}
