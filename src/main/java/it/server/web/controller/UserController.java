package it.server.web.controller;

import it.server.core.exception.UserException;
import it.server.core.repository.user.PostgresUserRepository;
import it.server.core.service.user.IUserService;
import it.server.web.dto.request.CreateUserRequest;
import it.server.web.dto.request.DeleteUserRequest;
import it.server.web.dto.request.GetUserRequest;
import it.server.web.dto.request.UpdateUserRequest;
import it.server.web.dto.response.GetUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * user controller class
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private final Logger LOGGER = LoggerFactory.getLogger(PostgresUserRepository.class);

  private final IUserService userService;

  public UserController(final IUserService userService) {
    this.userService = userService;
  }

  /**
   * get user by id method
   * returns user`s info by user`s id
   *
   * @param request - GetUserRequest dto object
   * @return ResponseEntity with GetUserResponse body
   */
  @GetMapping
  public ResponseEntity<GetUserResponse> getUserById(@RequestBody final GetUserRequest request) {
    try {
      LOGGER.debug("CONTROLLER get user by id {}", request.getUserId());
      GetUserResponse response = userService.getById(request.getUserId());
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    } catch (Exception e) {
      LOGGER.error("CONTROLLER can not get user by id {}", request.getUserId());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * create user by id method
   * creates user by id and full name from the request
   *
   * @param request - CreateUserRequest dto object
   * @return ResponseEntity with GetUserResponse body - info of created user
   */
  @PostMapping
  public ResponseEntity<GetUserResponse> createUser(@RequestBody final CreateUserRequest request) {
    try {
      LOGGER.debug("CONTROLLER create user with id {} and full name", request.getUserId());
      userService.createUser(request.getUserId(), request.getUserFullName());
      GetUserResponse response = userService.getById(request.getUserId());
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    } catch (UserException e) {
      LOGGER.debug(
              "CONTROLLER can not create user with id {} and full name {}",
              request.getUserId(),
              request.getUserFullName()
      );
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * update user method
   * updates user`s info in the repository by user`s id (uuid)
   *
   * @param request - UpdateUserRequest dto object
   * @return ResponseEntity with GetUserResponse body - info of updated user
   */
  @PostMapping("/update")
  public ResponseEntity<GetUserResponse> updateUser(@RequestBody final UpdateUserRequest request) {
    try {
      LOGGER.debug(
              "CONTROLLER update user by id {}, new full name {}",
              request.getUserId(),
              request.getUserFullName()
      );
      userService.updateUser(request.getUserId(), request.getUserFullName());
      return ResponseEntity
              .ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(userService.getById(request.getUserId()));
    } catch (UserException e) {
      LOGGER.error(
              "CONTROLLER can not update user by id {}, new full name {}",
              request.getUserId(),
              request.getUserFullName()
      );
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * delete user method
   * deletes user from repository by user`s id
   *
   * @param request DeleteUserRequest dto object
   * @return ResponseEntity with String body - deleted user`s id
   */
  @DeleteMapping
  public ResponseEntity<String> deleteUser(@RequestBody final DeleteUserRequest request) {
    try {
      LOGGER.debug("CONTROLLER delete user by id {}", request.getUserId());
      userService.deleteUser(request.getUserId());
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(request.getUserId());
    } catch (UserException e) {
      LOGGER.debug("CONTROLLER can not delete user by id {}", request.getUserId());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}
