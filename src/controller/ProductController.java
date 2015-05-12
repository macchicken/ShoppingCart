package controller;

import java.util.List;

import javax.validation.Valid;

import model.Product;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.DaoFactory;
import dao.ProductDao;

@Controller
@RequestMapping("/spcing/products")
public class ProductController {

	private final ProductDao pdao = DaoFactory.getInstance().getProductDao();
	static Log log = LogFactory.getLog(ProductController.class);
	String classname = this.getClass().getName();

	@RequestMapping(method = RequestMethod.GET)
	public String listAll(Model model){
		
		List<Product> products = pdao.getAllProducts();
		model.addAttribute("products", products);
		return "productlist";
	}
	
	@RequestMapping(value="/new", method = RequestMethod.GET)
	public String add(Model model){
		Product product = new Product(); // create an empty product
		model.addAttribute("product", product);
		return "product";
	}
	
	// this is the method for inserting a new product or updating an existing prouct
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("product") Product product, BindingResult result){
		
		if (result.hasErrors()) {
	        return "product";
		}
		// add your code here to save a new produce or update the existing product
		// a new product will have an initial id of -1
		int id=product.getProductId();
		if (id==-1){
			pdao.addProduct(product);
			log.info(classname+" add product "+product);
		}else{
			pdao.updateProduct(product);
			log.info(classname+" update product "+product);
		}
		// we redirect to the product list page
		// which will call the listAll method and show updated product list
		return "redirect:/products";
	}
	
	@RequestMapping("/edit/{productId}")
	public String edit(@PathVariable int productId, Model model){
		//add your code here to find a product based on its id
		//and put it in the model
		Product p=pdao.getProductById(productId);
		log.info(classname+" edit product: "+p);
		model.addAttribute("product", p);
		return "product";
	}
	
	@RequestMapping("/delete/{productId}")
	public String delete(@PathVariable int productId, Model model){
		log.info(classname+" delete product id: "+productId);
		pdao.deleteProduct(productId);
		return "redirect:/products";
	}
}
