package it.server.web.config;

import it.server.core.repository.user.IUserRepository;
import it.server.core.repository.user.PostgresUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

/**
 * repository configuration
 * configures the repository which will be used in the application
 */
@Configuration
public class RepositoryConfiguration {

  /**
   * configuration bean for user repository
   *
   * @param jdbcOperations - jdbc
   * @return postgres implementation of IUserRepository
   */
  @Bean
  public IUserRepository getUserRepository(final JdbcOperations jdbcOperations) {
    return new PostgresUserRepository(jdbcOperations);
  }

}
