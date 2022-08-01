package it.server.core.repository.user;

import it.server.core.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Memory implementation of IUserRepository
 */
@Repository
public class UserRepository implements IUserRepository {

  private final Map<String, User> userMap;

  /**
   * constructor
   */
  public UserRepository() {
    this.userMap = new HashMap<>();
  }

  @Override
  public User getById(final String userId) {
    return userMap.get(userId);
  }

  @Override
  public void createUser(String userId, String userFullName) {
    userMap.put(userId, new User(userId, userFullName));
  }

  @Override
  public Integer updateUser(String userId, String userFullName) {
    User user = userMap.get(userId);
    if (user == null) {
      return 0;
    }
    user.setFullName(userFullName);
    userMap.put(userId, user);
    return 1;
  }

  @Override
  public Integer deleteUser(String userId) {
    if (userMap.containsKey(userId)) {
      userMap.remove(userId);
      return 1;
    }
    return 0;
  }

  @Override
  public boolean containsUser(String userId) {
    return userMap.containsKey(userId);
  }
}

