package it.server.web.controller;

import it.server.core.exception.UserException;
import it.server.core.service.user.IUserService;
import it.server.web.dto.request.CreateUserRequest;
import it.server.web.dto.request.DeleteUserRequest;
import it.server.web.dto.request.GetUserRequest;
import it.server.web.dto.request.UpdateUserRequest;
import it.server.web.dto.response.GetUserResponse;
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
      GetUserResponse response = userService.getById(request.getUserId());
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    } catch (Exception e) {
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
      userService.createUser(request.getUserId(), request.getUserFullName());
      GetUserResponse response = userService.getById(request.getUserId());
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    } catch (UserException e) {
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
      userService.updateUser(request.getUserId(), request.getUserFullName());
      return ResponseEntity
              .ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(userService.getById(request.getUserId()));
    } catch (UserException e) {
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
      userService.deleteUser(request.getUserId());
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(request.getUserId());
    } catch (UserException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}
