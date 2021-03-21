package com.zyc.rpc.api;

public interface Filter {
  boolean filter(RpcfxRequest request);
}
