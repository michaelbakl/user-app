package it.server.web.config;

import it.server.core.repository.factory.RepositoryFactory;
import it.server.core.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

/**
 * repository configuration
 * configures the repository which will be used in the application
 */
@Configuration
public class RepositoryConfiguration {

  @Value("${repository.type:memory}")
  private String repositoryName;

  /**
   * configuration bean for user repository
   *
   * @param jdbcOperations - jdbc
   * @return implementation of IUserRepository
   */
  @Bean
  public IUserRepository getUserRepository(JdbcOperations jdbcOperations) {
    return RepositoryFactory.getBean(repositoryName, jdbcOperations);
  }

}
