package proxy.cglib;

/**
 * 测试类 cglib
 * cglib特点：
 * 1.面向类的  注意OrderService没有实现任何接口
 * 2.底层是通过字节码增强生成一个目标类的子类作为代理类 因此目标类(OrderService)不能是final修饰的
 * 3.如果代理类是个接口的实现类 那么spring内部默认是使用jdk动态代理机制 但是如果被代理类不是个实现类 spring会强制使用cglib来实现动态代理
 * @author clewill
 * @create 2021:06:17 11:15
 **/
public class CglibDynamicProxyClient {
    public static void main(String[] args){
      CglibDynamicProxy cglibDynamicProxy = new CglibDynamicProxy();
      OrderService orderService = (OrderService)cglibDynamicProxy.newInstance(new OrderService());
      orderService.saveOrders();
    }
}
