package it.server.core.model;

import lombok.Getter;
import lombok.Setter;

/**
 * model class User
 */
public class User {

  private @Getter @Setter String id;

  private @Getter @Setter String fullName;

  /**
   * constructor
   *
   * @param id - user`s id
   * @param fullName - user` full name
   */
  public User(final String id, final String fullName) {
    this.id = id;
    this.fullName = fullName;
  }
}
