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
    //生成一个字节码增强器
    Enhancer enhancer = new Enhancer();
    //设置父类 增强器会生成一个子类继承目标类 只不过增强器对这个子类做了增强
    enhancer.setSuperclass(targetObj.getClass());
    //这里还有过滤器可以玩 实现自定义过滤效果
    //enhancer.setCallbackFilter();
    //设置回调函数
    enhancer.setCallback(this);
    //如果上面设置了callbackFilter 这里就需要使用数组形式 里面是实现了MethodInterceptor接口的各个类
    //enhancer.setCallbacks();
    //返回一个代理对象
    return enhancer.create();
  }

  @Override
  public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy)
      throws Throwable {
    System.out.printf("=====我是方法(%s)的切面逻辑 pre=====\n",method.getName());
    //下面两个代码的作用是一样的  仔细体会下
    //Object invoke = method.invoke(targetObj, args);
    Object invoke = methodProxy.invokeSuper(o, args);
    System.out.printf("=====我是方法(%s)的切面逻辑 after=====\n",method.getName());
    return invoke;
  }
}
