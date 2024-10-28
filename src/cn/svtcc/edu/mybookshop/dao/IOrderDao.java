package cn.svtcc.edu.mybookshop.dao;

import java.util.List;

import cn.svtcc.edu.mybookshop.entity.OrderBook;
import cn.svtcc.edu.mybookshop.entity.OrderBookCustom;
import cn.svtcc.edu.mybookshop.entity.Orders;
/**
 * 订单操接口类
 * @author 猿一只
 *
 */
public interface IOrderDao {
	/**
	 * 添加订单
	 * @param order
	 * @return 订单Id
	 */
	public int addOrder(Orders order);
	/**
	 * 添加详细订单
	 * @param orderBook
	 * @return boolean
	 */
	public boolean addOrderBook(OrderBook orderBook);
	
	/**
	 * 根据当前登录用户Id获取对应的订单
	 * @param userId
	 * @return
	 */
	public List<OrderBookCustom> getOrder(int userId);
	
}
