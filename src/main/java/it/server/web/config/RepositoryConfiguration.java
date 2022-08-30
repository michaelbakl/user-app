package it.server.web.config;

import it.server.core.exception.InitException;
import it.server.core.repository.factory.BaseRepositoryFactory;
import it.server.core.repository.factory.RepositoryFactory;
import it.server.core.repository.user.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * repository configuration
 * configures the repository which will be used in the application
 */
@Configuration
public class RepositoryConfiguration {

  @Value("${repository.type:memory}")
  private String type;

  private static final Logger logger = LoggerFactory.getLogger(RepositoryConfiguration.class);

  /**
   * configuration bean for repository factory
   * @return implementation of an abstract class BaseRepositoryFactory
   */
  @Bean
  public BaseRepositoryFactory getRepositoryFactory() {
    return new RepositoryFactory();
  }

  /**
   * configuration bean for user repository
   *
   * @return implementation of IUserRepository
   */
  @Autowired
  @Bean
  public IUserRepository getUserRepository(BaseRepositoryFactory factory) throws InitException {
      logger.info("Getting repo type: " + type);
      return factory.getUserRepository(type);
  }

}
