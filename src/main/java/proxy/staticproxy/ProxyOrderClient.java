package proxy.staticproxy;

import service.OrderService;
import service.impl.OutSideOrderServiceImpl;

/**
 * 订单代理类
 * @author clewill
 * @create 2021:05:11 14:18
 **/
public class ProxyOrderClient implements OrderService {

  /**
   * 真正的订单服务
   */
  private final OrderService orderService;

  public ProxyOrderClient(){
    this.orderService = new OutSideOrderServiceImpl();
  }

  public ProxyOrderClient(OrderService orderService){
    this.orderService = orderService;
  }
  @Override
  public void saveOrders() {
    orderService.saveOrders();
  }
}
