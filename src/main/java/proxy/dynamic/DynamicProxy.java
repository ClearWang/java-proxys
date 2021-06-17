package proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 *
 * @author clewill
 * @create 2021:06:17 10:12
 **/
public class DynamicProxy implements InvocationHandler {

  private Object targetObj;

  public Object newProxyInstance(Object targetObj){
      this.targetObj = targetObj;
      //调用jdk的反射Proxy生成一个代理对象
      return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(),
          targetObj.getClass().getInterfaces(),this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.printf("=====我是方法(%s)的切面逻辑 pre=====\n",method.getName());
    Object invoke = method.invoke(targetObj, args);
    System.out.printf("=====我是方法(%s)的切面逻辑 after=====\n",method.getName());
    //注意这里invoke中传入的是targetObj而不是proxy 调用的是实际目标类的方法
    return invoke;
  }
}
