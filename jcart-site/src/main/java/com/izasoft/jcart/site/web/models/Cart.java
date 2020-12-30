package com.izasoft.jcart.site.web.models;

import java.util.ArrayList;
import java.util.List;

import com.izasoft.jcart.domain.*;

public class Cart {
 
	private List<LineItem> items;
	private Customer customer;
	private Payment payment;
	private Address deliveryAddress;
	
	public Cart() {
		items = new ArrayList<LineItem>();
		customer = new Customer();
		payment = new Payment();
		deliveryAddress = new Address();
	}
	
	public void addItem(Product product) {
		for(LineItem lineItem:items) {
			if(lineItem.getProduct().getSku().equals(product.getSku())) {
				lineItem.setQuantity(lineItem.getQuantity()+1);
				return;
			}
		}
		LineItem item = new LineItem(product, 1);
		this.items.add(item);
	}
	
	public void removeItem(String sku) {
		LineItem item = null;
		for(LineItem lineItem:items) {
			if(lineItem.getProduct().getSku().equals(sku)) {
				item = lineItem;
				break;
			}
		}
		if(item!=null) {
			items.remove(item);
		}
	}
	
	public void updateItemQuantity(Product product, int quantity) {
		for(LineItem lineItem:items) {
			if(lineItem.getProduct().getSku().equals(product.getSku())) {
				lineItem.setQuantity(quantity);
			}
		}
	}
	
	
	
	public List<LineItem> getItems() {
		return items;
	}
	public void setItems(List<LineItem> items) {
		this.items = items;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	
	
}
