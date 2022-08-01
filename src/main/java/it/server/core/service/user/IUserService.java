package it.server.core.service.user;

import it.server.core.exception.UserException;
import it.server.web.dto.response.GetUserResponse;

/**
 * user service interface
 */
public interface IUserService {

  /**
   * returns user by id
   * @param userId user`s id
   * @return user if exists, otherwise {@code null}
   */
  GetUserResponse getById(String userId);

  /**
   * creates user
   *
   * @param userId - user`s id (uuid)
   * @param userFullName - user`s full name
   */
  void createUser(String userId, String userFullName) throws UserException;

  /**
   * updates user
   *
   * @param userId - user id
   * @param userFullName - user`s full name
   * @return value of updated rows
   */
  Integer updateUser(String userId, String userFullName) throws UserException;

  /**
   * deletes user
   *
   * @param userId - user`s id
   * @return value of deleted rows
   */
  Integer deleteUser(String userId) throws UserException;
}
