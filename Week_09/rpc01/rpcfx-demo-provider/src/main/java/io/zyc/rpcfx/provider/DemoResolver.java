package io.zyc.rpcfx.provider;

import io.zyc.rpcfx.api.RpcfxResolver;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {
  private ApplicationContext applicationContext;
  @Override
  public Object resolver(String serviceClass) {
    return this.applicationContext.getBean(serviceClass);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
