package it.server.core.repository.factory;

import it.server.core.repository.user.IUserRepository;
import it.server.core.repository.user.PostgresUserRepository;
import it.server.core.repository.user.UserRepository;
import org.springframework.beans.BeansException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

@Component
public final class RepositoryFactory {

  private RepositoryFactory() {
  }

  public static IUserRepository getBean(
          final String type,
          final JdbcOperations jdbcOperations
  ) throws BeansException {
    if ("postgres".equals(type)) {
      return new PostgresUserRepository(jdbcOperations);
    }
    return new UserRepository();
  }


}
