package it.server.core.repository.user;

import it.server.core.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;

/**
 * Postgres' implementation of IUserRepository
 */
public class PostgresUserRepository implements IUserRepository {

  private final Logger LOGGER = LoggerFactory.getLogger(PostgresUserRepository.class);

  private final JdbcOperations jdbcOperations;

  public PostgresUserRepository(final JdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  @Override
  public User getById(final String userId) {
    LOGGER.debug("REPO get by id {}", userId);
    String sql = "SELECT * FROM \"user\" WHERE id = ?";
    return jdbcOperations.queryForObject(sql, (resultSet, i) ->
            new User(resultSet.getString(1), resultSet.getString(2)),
            userId
    );
  }

  @Override
  public void createUser(final String userId, final String userFullName) {
    LOGGER.debug("REPO create user with id {} and full name {}", userId, userFullName);
    String sql = "INSERT INTO \"user\" (id, fullName) VALUES (?, ?) on conflict do nothing";
    jdbcOperations.update(sql, userId, userFullName);
  }

  @Override
  public Integer updateUser(final String userId, final String userFullName) {
    LOGGER.debug("REPO update user by id {} full name {}", userId, userFullName);
    String sql = "UPDATE \"user\" SET fullname = ? WHERE id = ?";
    return jdbcOperations.update(sql, userFullName, userId);
  }

  @Override
  public Integer deleteUser(final String userId) {
    LOGGER.debug("REPO delete user by id {}", userId);
    String sql = "DELETE FROM \"user\" WHERE id = ?";
    return jdbcOperations.update(sql, userId);
  }

  @Override
  public boolean containsUser(final String userId) {
    LOGGER.debug("REPO check contains by id {}", userId);
    String sql = "SELECT * FROM \"user\" WHERE id = ?";
    return jdbcOperations.queryForObject(sql, (resultSet, i) ->
            new User(resultSet.getString(1), resultSet.getString(2)
            ), userId) != null;
  }
}
