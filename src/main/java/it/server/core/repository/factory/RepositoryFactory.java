package it.server.core.repository.factory;

import it.server.core.exception.InitException;
import it.server.core.exception.UserErrorCode;
import it.server.core.exception.UserException;
import it.server.core.repository.user.IUserRepository;
import it.server.core.repository.user.PostgresUserRepository;
import it.server.core.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFactory extends BaseRepositoryFactory {

  private static final Logger logger = LoggerFactory.getLogger(RepositoryFactory.class);

  @Autowired
  private JdbcOperations jdbcOperations;

  @Override
  public IUserRepository getUserRepository(String type) throws InitException {
    logger.debug("REPO FACTORY getting repo with type {}", type);
    IUserRepository userRepository = switch (type.toLowerCase()) {
      case "postgres" -> new PostgresUserRepository(jdbcOperations);
      case "memory" -> new UserRepository();
      default -> throw new InitException(
              "Unsupported repository type " + type + " or repository type not specified",
              new IllegalArgumentException()
      );
    };
    return userRepository;
  }
}
