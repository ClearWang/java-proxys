package service.impl;

import service.OrderService;

/**
 * 本地订单服务 实现了OrderService接口
 *
 * @author clewill
 * @create 2021:05:11 14:27
 **/
public class OrderServiceImpl implements OrderService {

  @Override
  public void saveOrders() {
    System.out.println("调用本地订单服务！下单成功 单号 111111");
  }
}
