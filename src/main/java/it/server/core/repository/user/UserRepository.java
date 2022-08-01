package it.server.core.repository.user;

import it.server.core.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Memory implementation of IUserRepository
 */
@Repository
public class UserRepository implements IUserRepository {

  private final Logger LOGGER = LoggerFactory.getLogger(PostgresUserRepository.class);

  private final Map<String, User> userMap;

  /**
   * constructor
   */
  public UserRepository() {
    this.userMap = new HashMap<>();
  }

  @Override
  public User getById(final String userId) {
    LOGGER.debug("REPO get by id {}", userId);
    return userMap.get(userId);
  }

  @Override
  public void createUser(String userId, String userFullName) {
    LOGGER.debug("REPO create user with id {} and full name {}", userId, userFullName);
    userMap.put(userId, new User(userId, userFullName));
  }

  @Override
  public Integer updateUser(String userId, String userFullName) {
    LOGGER.debug("REPO update user by id {}, full name {}", userId, userFullName);
    User user = userMap.get(userId);
    if (user == null) {
      LOGGER.error("REPO can not update user by id {}, full name {}", userId, userFullName);
      return 0;
    }
    user.setFullName(userFullName);
    userMap.put(userId, user);
    return 1;
  }

  @Override
  public Integer deleteUser(String userId) {
    LOGGER.debug("REPO delete user by id {}", userId);
    if (userMap.containsKey(userId)) {
      userMap.remove(userId);
      return 1;
    }
    return 0;
  }

  @Override
  public boolean containsUser(String userId) {
    LOGGER.debug("REPO check update user by id {}", userId);
    return userMap.containsKey(userId);
  }
}
