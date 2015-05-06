package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Cart;
import model.Product;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

import dao.DaoFactory;
import dao.ProductDao;

@RestController
@RequestMapping("/cartsJson")
@SessionAttributes("cart")
public class CartJsonController {

private final ProductDao pdao = DaoFactory.getInstance().getProductDao();
	
	@ModelAttribute("cart")
	public Cart createCart(){
		return new Cart();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView show(Model model, @ModelAttribute(value="cart") Cart cart){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("catalogueJson");
		List<Product> products = pdao.getAllProducts();
		modelAndView.addObject("products", products);
		return modelAndView;
	}

	@RequestMapping(value="/add/{productId}",method = RequestMethod.GET,produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> add(@PathVariable int productId, Model model, @ModelAttribute("cart") Cart cart){
		Product p = pdao.getProductById(productId);
		cart.addItem(p); //add the item in cart
		Map<String, Object> result=new HashMap<String,Object>();
		result.put("sucess", true);
		result.put("cart", cart);
		return result;
	}


	@RequestMapping(value="/remove/{productId}",method = RequestMethod.GET,produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> remove(@PathVariable int productId, Model model,@ModelAttribute("cart") Cart cart){
		boolean success=false;
		if (cart.getItems().size()>0){
			if (cart.getItem(productId)!=null){
				cart.deleteItem(productId);
				success=true;
			}
		}
		Map<String, Object> result=new HashMap<String,Object>();
		result.put("sucess", success);
		result.put("cart", cart);
		return result;
	}
	
	@RequestMapping(value="/getCartItem",method = RequestMethod.GET,produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> add(Model model, @ModelAttribute("cart") Cart cart){
		Map<String, Object> result=new HashMap<String,Object>();
		result.put("sucess", true);
		result.put("cart", cart);
		return result;
	}

}
