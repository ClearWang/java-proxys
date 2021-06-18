package proxy.dynamic;

import service.OrderService;
import service.impl.OrderServiceImpl;

/**
 * dynamic proxy
 * 动态代理的特点：
 * 1.面向接口的 必须有接口定义和接口实现 因为jdk reflect中的Proxy.newProxyInstance里面就需要传入一个当前类的接口
 * 2.底层是通过反射调用代理类方法,实现切面逻辑
 * 缺点：
 * 1.面向接口代理 没法实现面向对象的代理
 * 2.内存里的代理实例是运行时产生的,如果代理过多,会导致jvm永久区内存溢出
 * @author clewill
 * @create 2021:05:12 16:33
 **/
public class DynamicProxyUserClient {
    public static void main(String[] args){
        OrderService orderService = new OrderServiceImpl();
        //获取代理对象 代理对象本质上也是个OrderService
        OrderService proxyObj = (OrderService) new DynamicProxy().newProxyInstance(orderService);
        proxyObj.saveOrders();
    }
}
