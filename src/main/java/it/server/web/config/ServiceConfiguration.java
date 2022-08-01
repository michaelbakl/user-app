package it.server.web.config;

import it.server.core.repository.user.IUserRepository;
import it.server.core.service.user.IUserService;
import it.server.core.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * service configuration class
 * configures IUserRepository implementation which will be used in the application
 */
@Configuration
public class ServiceConfiguration {

  /**
   * configuration bean for user service
   *
   * @param userRepository - IUserRepository implementation
   * @return IUserService implementation class
   */
  @Bean
  public IUserService getUserService(final IUserRepository userRepository) {
    return new UserService(userRepository);
  }
}
