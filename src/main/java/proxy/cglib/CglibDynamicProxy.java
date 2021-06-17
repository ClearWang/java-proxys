package proxy.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * code generation library
 *
 * @author clewill
 * @create 2021:06:17 10:52
 **/
public class CglibDynamicProxy implements MethodInterceptor {

  private Object targetObj;

  public Object newInstance(Object targetObj){
    this.targetObj = targetObj;
    //生成一个增强类
    Enhancer enhancer = new Enhancer();
    //设置父类
    enhancer.setSuperclass(targetObj.getClass());
    //设置回调函数
    enhancer.setCallback(this);
    //返回一个代理对象
    return enhancer.create();
  }

  @Override
  public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy)
      throws Throwable {
    System.out.printf("=====我是方法(%s)的切面逻辑 pre=====\n",method.getName());
    Object invoke = method.invoke(targetObj, args);
    System.out.printf("=====我是方法(%s)的切面逻辑 after=====\n",method.getName());
    return invoke;
  }
}
