package service.impl;

import service.OrderService;

/**
 * 外部订单服务  用户可能无法直接调用 比如海外订单 用户若想订购需通过代理
 *
 * @author clewill
 * @create 2021:05:11 14:31
 **/
public class OutSideOrderServiceImpl implements OrderService {

  @Override
  public void saveOrders() {
    System.out.println("调用外部订单服务！下单成功 单号 222222");
  }
}
