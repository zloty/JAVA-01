package io.zyc.rpcfx.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.zyc.rpcfx.api.RpcfxRequest;
import io.zyc.rpcfx.api.RpcfxResolver;
import io.zyc.rpcfx.api.RpcfxResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxInvoker {

  private RpcfxResolver resolver;

  public RpcfxInvoker(RpcfxResolver resolver){
    this.resolver = resolver;
  }

  public RpcfxResponse invoke(RpcfxRequest request) {
    RpcfxResponse response = new RpcfxResponse();
    String serviceClass = request.getServiceClass();

    // todo 改成泛型和反射
    Object service = resolver.resolver(serviceClass);

    try {
      Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
      Object result = method.invoke(service, request.getParams());
      response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
      response.setStatus(true);
      return response;
    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
      response.setException(e);
      response.setStatus(false);
      return response;
    }
  }

  private Method resolveMethodFromClass(Class<?> klass, String methodName) {
    return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
  }

}
