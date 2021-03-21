package io.zyc.rpcfx.provider;

import io.zyc.rpcfx.api.User;
import io.zyc.rpcfx.api.UserService;

public class UserServiceImpl implements UserService {

  @Override
  public User findById(int id) {
    return new User(id,"zyc" + System.currentTimeMillis());
  }
}
