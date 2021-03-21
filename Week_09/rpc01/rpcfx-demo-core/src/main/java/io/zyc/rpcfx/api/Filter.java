package io.zyc.rpcfx.api;

public interface Filter {
  boolean filter(RpcfxRequest request);
}
