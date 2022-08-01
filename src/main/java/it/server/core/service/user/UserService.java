package it.server.core.service.user;

import it.server.core.exception.UserErrorCode;
import it.server.core.exception.UserException;
import it.server.core.model.User;
import it.server.core.repository.user.IUserRepository;
import it.server.web.dto.response.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * implementation of ISerService
 */
@Service
public class UserService implements IUserService {

  @Autowired
  private final IUserRepository userRepository;

  public UserService(final IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public GetUserResponse getById(String userId) {
    if (userId == null || "".equals(userId) || !isUUID(userId)) {
      return null;
    }
    User user = userRepository.getById(userId);
    return new GetUserResponse(user.getId(), user.getFullName());
  }

  @Override
  public void createUser(String userId, String userFullName) throws UserException {
    if (userId == null || "".equals(userId) || !isUUID(userId)) {
      throw new UserException(UserErrorCode.CAN_NOT_CREATE_USER);
    }
    userRepository.createUser(userId, userFullName);
  }

  @Override
  public Integer updateUser(String userId, String userFullName) throws UserException {
    if (
                    userId == null ||
                    "".equals(userId) ||
                    !isUUID(userId) ||
                    !userRepository.containsUser(userId)
    ) {
      throw new UserException(UserErrorCode.CAN_NOT_UPDATE_USER);
    }
    return userRepository.updateUser(userId, userFullName);
  }

  @Override
  public Integer deleteUser(String userId) throws UserException {
    if (
                    userId == null ||
                    "".equals(userId) ||
                    !isUUID(userId) ||
                    !userRepository.containsUser(userId)
    ) {
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
