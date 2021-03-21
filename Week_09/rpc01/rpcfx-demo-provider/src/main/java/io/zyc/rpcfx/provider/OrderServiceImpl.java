package io.zyc.rpcfx.provider;

import io.zyc.rpcfx.api.Order;
import io.zyc.rpcfx.api.OrderService;

public class OrderServiceImpl implements OrderService {
  @Override
  public Order findOrderById(int id) {
    return new Order(id,"CuiCui" + System.currentTimeMillis(),9.9f);
  }
}
