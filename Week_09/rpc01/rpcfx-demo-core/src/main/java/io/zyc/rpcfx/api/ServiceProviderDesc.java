package io.zyc.rpcfx.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceProviderDesc {
  private String host;
  private int port;
  private String serviceClass;
}
