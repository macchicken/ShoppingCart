package model;
import java.util.*;
public class Cart {
	private Map<Integer,CartItem> carts;


	public Cart(){
		carts = new HashMap<Integer,CartItem>();
	}

	public void addItem(Product p){
		int productId = p.getProductId();
		CartItem ci = carts.get(productId);
		if (ci == null){
			carts.put(productId, new CartItem(p,1)); // add the new product			
		}else{ // increase the quantity
			ci.increaseQuantity();
		}
	}

	public void deleteItem(int productId){
		CartItem ci = carts.get(productId);
		if (ci!=null){
			ci.decreaseQuantity();
			if (ci.getQuantity()==0){
				carts.remove(productId);
			}
		}
	}

	
	public Collection<CartItem> getItems() {
		return carts.values();
	}

	public CartItem getItem(int productId) {
		return carts.get(productId);
	}
	
	public double getTotal(){
		double total = 0.0;
		for (CartItem ci: carts.values()){
			total += ci.getQuantity() * ci.getProduct().getPrice();
		}
		return total;
	}

}
