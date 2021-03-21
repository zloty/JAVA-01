package io.zyc.rpcfx.provider;

import io.zyc.rpcfx.api.*;
import io.zyc.rpcfx.server.RpcfxInvoker;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@SpringBootApplication
@RestController
public class RpcfxServerApplication {
  public static void main(String[] args) throws Exception {

    // zk client
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
    CuratorFramework client =
      CuratorFrameworkFactory.builder().connectString("localhost:2181").namespace("rpcfx").retryPolicy(retryPolicy).build();
//    CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",retryPolicy);
    client.start();

    // todo 进一步优化，是在spring加载完成后，从里面拿到特定注解的bean，自动注册到zk
    String userService = "io.zyc.rpcfx.api.UserService";
    registerService(client,userService);
    String orderService = "io.zyc.rpcfx.api.OrderService";
    registerService(client,orderService);

    SpringApplication.run(RpcfxServerApplication.class,args);
  }

  private static void registerService(CuratorFramework client, String service) throws Exception {
    ServiceProviderDesc userServiceDesc = ServiceProviderDesc.builder()
      .host(InetAddress.getLocalHost().getHostAddress())
      .port(8080).serviceClass(service).build();

    try {
      if (null == client.checkExists().forPath("/" + service)) {
        // 创建一个持久节点
        client.create().withMode(CreateMode.PERSISTENT).forPath("/" + service,"service".getBytes());
      }
    } catch (Exception e){
      e.printStackTrace();
    }
    client.create().withMode(CreateMode.EPHEMERAL)
      .forPath("/" + service + "/" + userServiceDesc.getHost() + "_" + userServiceDesc.getPort(), "provider".getBytes());
  }

  @Autowired
  RpcfxInvoker invoker;

  @PostMapping("/")
  public RpcfxResponse invoke(@RequestBody  RpcfxRequest request){
    return invoker.invoke(request);
  }

  @Bean
  public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver){
    return new RpcfxInvoker(resolver);
  }

  @Bean
  public RpcfxResolver createResolver(){
    return new DemoResolver();
  }

  @Bean
  public UserService createUserService(){
    return new UserServiceImpl();
  }

  @Bean
  public OrderService createOrderService(){
    return new OrderServiceImpl();
  }
}
