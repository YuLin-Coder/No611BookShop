package cn.svtcc.edu.mybookshop.entity;

import java.io.Serializable;
/**
 * 订单明细表实体类
 * @author 猿一只
 *
 */
public class OrderBook implements Serializable {
	private int id;
	private int orderId;
	private int bookId;
	private int quantity;
	private double unitPrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
}
