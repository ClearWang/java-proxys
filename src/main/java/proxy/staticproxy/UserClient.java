package proxy.staticproxy;

import java.util.Scanner;
import service.OrderService;
import service.impl.OrderServiceImpl;
import service.impl.OutSideOrderServiceImpl;

/**
 * 模拟用户客户端
 *     假设用户处于中国 现在想购买一个马来西亚的猫山王榴莲 那么用户有两种订单服务方式：
 *     1.直接调用本地订单服务-OrderServiceImpl(需要用户自己去马来西亚)   saveOrderByLocal
 *     2.通过代理完成订单服务-OutSideOrderServiceImpl(通过代理去完成)   saveOrderByProxy 缺点：如果想买中国榴莲 又不想跑
 *     需要找本地代理完成 麻烦 优化为saveOrderByProxyGlobal 全局代理模式
 *     3.通过全局代理完成订单服务 saveOrderByProxyGlobal 只需要告诉全局代理你需要买的是马来西亚榴莲还是中国榴莲
 * @author clewill
 * @create 2021:05:11 14:34
 **/
public class UserClient {
  public static void main(String[] args){
    System.out.println("订单前逻辑...");
    //saveOrderByLocal();
    //saveOrderByProxy();
    saveOrderByProxyGlobal();
    System.out.println("订单后逻辑...");
  }

  private static void saveOrderByLocal() {
    OrderService orderService = new OrderServiceImpl();
    orderService.saveOrders();
  }

  private static void saveOrderByProxy() {
    OrderService orderService = new ProxyOrderClient();
    orderService.saveOrders();
  }

  private static void saveOrderByProxyGlobal() {
    OrderService orderService;
    System.out.println("选择服务类型: ");
    System.out.println("  1 - 马来西亚榴莲");
    System.out.println("  2 - 泰国榴莲");
    System.out.println("  3 - 中国榴莲");
    String selection = getConsoleInput();
    switch (selection){
      case "1":
      case "2":
        orderService = new ProxyOrderClient(new OutSideOrderServiceImpl());
        orderService.saveOrders();
        break;
      case "3":
        orderService = new ProxyOrderClient(new OrderServiceImpl());
        orderService.saveOrders();
        break;
      default:
        System.out.println("=========您选择的服务类型不支持,请重新选择!=========");
        System.out.println();
        saveOrderByProxyGlobal();
    }
  }

  private static String getConsoleInput() {
    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextLine()) {
      return scanner.nextLine();
    } else {
      return getConsoleInput();
    }
  }
}
