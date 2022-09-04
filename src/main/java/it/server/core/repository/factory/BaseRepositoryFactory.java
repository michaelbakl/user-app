package it.server.core.repository.factory;

import it.server.core.exception.InitException;
import it.server.core.repository.user.IUserRepository;


public abstract class BaseRepositoryFactory {

  public abstract IUserRepository getUserRepository(String type) throws InitException;

}
