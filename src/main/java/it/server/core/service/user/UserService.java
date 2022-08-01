package it.server.core.service.user;

import it.server.core.exception.UserErrorCode;
import it.server.core.exception.UserException;
import it.server.core.model.User;
import it.server.core.repository.user.IUserRepository;
import it.server.web.dto.response.GetUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * implementation of IUserService
 */
@Service
public class UserService implements IUserService {

  private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  private final IUserRepository userRepository;

  public UserService(final IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public GetUserResponse getById(String userId) {
    LOGGER.debug("SERVICE get user by id {}", userId);
    if (userId == null || "".equals(userId) || !isUUID(userId)) {
      LOGGER.error("SERVICE can not get user by id {}", userId);
      return null;
    }
    User user = userRepository.getById(userId);
    return new GetUserResponse(user.getId(), user.getFullName());
  }

  @Override
  public void createUser(String userId, String userFullName) throws UserException {
    LOGGER.debug("SERVICE create user with id {} and full name {}", userId, userFullName);
    if (userId == null || "".equals(userId) || !isUUID(userId)) {
      LOGGER.error("SERVICE can not create user with id {} and full name {}", userId, userFullName);
      throw new UserException(UserErrorCode.CAN_NOT_CREATE_USER);
    }
    userRepository.createUser(userId, userFullName);
  }

  @Override
  public Integer updateUser(String userId, String userFullName) throws UserException {
    LOGGER.debug("SERVICE update user with id {}", userId);
    if (
                    userId == null ||
                    "".equals(userId) ||
                    !isUUID(userId) ||
                    !userRepository.containsUser(userId)
    ) {
      LOGGER.error("SERVICE can not update user with id {}", userId);
      throw new UserException(UserErrorCode.CAN_NOT_UPDATE_USER);
    }
    return userRepository.updateUser(userId, userFullName);
  }

  @Override
  public Integer deleteUser(String userId) throws UserException {
    LOGGER.debug("SERVICE delete user with id {}", userId);
    if (
                    userId == null ||
                    "".equals(userId) ||
                    !isUUID(userId) ||
                    !userRepository.containsUser(userId)
    ) {
      LOGGER.error("SERVICE can not delete user with id {}", userId);
      throw new UserException(UserErrorCode.CAN_NOT_DELETE_USER);
    }
    return userRepository.deleteUser(userId);
  }

  private boolean isUUID(final String testUUID) {
    if (testUUID == null) {
      return false;
    }
    return java.util.regex.Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
            .matcher(testUUID).find();
  }
}
