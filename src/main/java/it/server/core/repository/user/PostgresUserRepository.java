package it.server.core.repository.user;

import it.server.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 * Postgres' implementation of IUserRepository
 */
@Repository
public class PostgresUserRepository implements IUserRepository {

  private final JdbcOperations jdbcOperations;

  public PostgresUserRepository(final JdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  @Override
  public User getById(final String userId) {
    String sql = "SELECT * FROM \"user\" WHERE id = ?";
    return jdbcOperations.queryForObject(sql, (resultSet, i) ->
            new User(resultSet.getString(1), resultSet.getString(2)),
            userId
    );
  }

  @Override
  public void createUser(final String userId, final String userFullName) {
    String sql = "INSERT INTO \"user\" (id, fullName) VALUES (?, ?) on conflict do nothing";
    jdbcOperations.update(sql, userId, userFullName);
  }

  @Override
  public Integer updateUser(final String userId, final String userFullName) {
    String sql = "UPDATE \"user\" SET fullname = ? WHERE id = ?";
    return jdbcOperations.update(sql, userFullName, userId);
  }

  @Override
  public Integer deleteUser(final String userId) {
    String sql = "DELETE FROM \"user\" WHERE id = ?";
    return jdbcOperations.update(sql, userId);
  }

  @Override
  public boolean containsUser(final String userId) {
    String sql = "SELECT * FROM \"user\" WHERE id = ?";
    return jdbcOperations.queryForObject(sql, (resultSet, i) ->
            new User(resultSet.getString(1), resultSet.getString(2)
            ), userId) != null;
  }
}
