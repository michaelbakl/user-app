package it.server.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * database configuration class
 * configures the driver class name, database url, username and password for database
 */
@Configuration
public class DatabaseConfiguration {

  @Value("${spring.datasource.driver-class-name}")
  private String driverClassName;

  @Value("${spring.datasource.url}")
  private String databaseUrl;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  /**
   * data source
   *
   * @return data source
   */
  @Bean
  @Primary
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(databaseUrl);
    dataSource.setUsername(username);
    dataSource.setPassword(password);

    return dataSource;
  }

  /**
   * template
   *
   * @param dataSource - data source
   * @return JdbcOperations
   */
  @Bean("template")
  @Primary
  public JdbcOperations template(final DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

}
