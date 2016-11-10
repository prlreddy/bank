package com.ibm.wcs;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//user defined imports
import com.ibm.wcs.exception.ProductNotInCartException;
import com.ibm.wcs.model.Product;

public class ShoppingCartService {

	Map<Integer, Product> cart = null;

	public ShoppingCartService() {
		cart = new HashMap<>();
	}

	public void addItem(Product product) {
		int productId = product.getId();

		if (cart.containsKey(productId)) {
			Product p = cart.get(productId);
			p.setQty(p.getQty() + 1);
		} else {
			cart.put(productId, product);
		}
	}

	public int countItems() {
		int count = 0;
		Set<Integer> keys = cart.keySet();
		for (Integer key : keys) {
			Product product = cart.get(key);
			count = count + product.getQty();
		}
		return count;
	}
	
	public void removeItem(Product product) throws ProductNotInCartException {
		int productId = product.getId();

		if (cart.containsKey(productId)) {
			cart.remove(productId);
		} else {
			throw new ProductNotInCartException("Product with id ("+productId+") not in the cart!");
		}
	}
	
	public double totalPrice() {
		double total = 0.0;
		Set<Integer> keys = cart.keySet();
		for (Integer key : keys) {
			Product product = cart.get(key);
			double temp = product.getPrice() * product.getQty();
			total = total + temp;
		}
		return total;
	}
	
	public void emptyCart() {
		cart.clear();
	}
	
	public Collection<Product> cartDetails() {
		return cart.values();
	}

	public String placeOrder() {
		//20 DB logic
		
		/*try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		String orderId = "O"+System.nanoTime();
		try(FileWriter writer = new FileWriter("./target/orders.txt", true)){
			writer.write("===================Order ("+orderId+") Start=================\n");
			Set<Integer> keys = cart.keySet();

			for (Integer key : keys) {
				Product product = cart.get(key);
				writer.write(product.toString());
				writer.write("\n");
						
			}

			writer.write("===================Order ("+orderId+") End=================\n");
			
			return orderId;
		}catch(IOException exception){
			exception.printStackTrace();
		}
		return null;
	}
}
