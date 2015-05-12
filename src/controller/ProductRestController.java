package controller;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import model.ProductV2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/spcing/restsc/products")
public class ProductRestController {

	static Log log = LogFactory.getLog(ProductRestController.class);
	String classname = this.getClass().getName();
	private String remote;
	private GenericType<List<ProductV2>> productListWrapper = new GenericType<List<ProductV2>>() {};
	private Client client = ClientBuilder.newClient();

	@PostConstruct
	public void init(){
		Properties properties = new Properties() ;
		try {
			properties.load(this.getClass().getResourceAsStream("/shoppingcart.properties"));
			this.remote = properties.getProperty("remote.service");
		} catch (IOException e) {
			System.out.println("using local default");
			this.remote="http://localhost:10080";
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listAll(Model model){
		WebTarget root = client.target(this.remote+"/ShoppingCart/rest/productsStore/");
		List<ProductV2> result=root.request(MediaType.TEXT_XML).get(productListWrapper);
		model.addAttribute("products", result);
		return "productRestlist";
	}

	@RequestMapping(value="/search",method = RequestMethod.GET)
	public String searchProducts(@RequestParam(value="keyword", required=false, defaultValue="tiger") String keyword,Model model){
		WebTarget root = client.target(this.remote+"/ShoppingCart/rest/productsStore/");
		System.out.println("keyword "+keyword);
		List<ProductV2> result=root.path("search/"+keyword).request(MediaType.TEXT_XML).get(productListWrapper);
		model.addAttribute("products", result);
		return "productRestlist";
	}
	
}
