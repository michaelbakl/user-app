package it.server.core.repository.user;

import it.server.core.model.User;
import org.springframework.stereotype.Repository;

/**
 * interface for user repositories
 */
@Repository
public interface IUserRepository {

  /**
   * returns user by id
   * @param userId user`s id
   * @return user if exists, otherwise {@code null}
   */
  User getById(String userId);

  /**
   * creates user
   *
   * @param userId - user`s id (uuid)
   * @param userFullName - user`s full name
   */
  void createUser(String userId, String userFullName);

  /**
   * updates user
   *
   * @param userId - user id
   * @param userFullName - user`s full name
   * @return value of updated rows
   */
  Integer updateUser(String userId, String userFullName);

  /**
   * deletes user
   *
   * @param userId - user`s id
   * @return value of deleted rows
   */
  Integer deleteUser(String userId);

  /**
   * method for checking user`s existence
   *
   * @param userId - user`s id (uuid)
   * @return true if user exists, otherwise false
   */
  boolean containsUser(String userId);

}
