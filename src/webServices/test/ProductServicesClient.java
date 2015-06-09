package webServices.test;
import java.util.List;

import javax.xml.ws.WebServiceRef;
public class ProductServicesClient {
	@WebServiceRef(wsdlLocation="http://localhost:10090/shoppingCart/productServices?wsdl")
	
	public static void main(String[] argv){
		
		ProductServices port = new ProductServicesService().getProductServicesPort();
		Product prod = port.getProductById(1);
		System.out.println(prod.getTitle());
		List<Product> plist=port.getAllProducts();
		plist.forEach(product->System.out.println(product));
//		port.deleteProduct(1);
//		Product prod2 = port.getProductById(1);
//		System.out.println(prod2);
		System.out.println("add new prodcut");
		Product newProd=new Product();
		newProd.setDescription(prod.getDescription());
		newProd.setImageUrl(prod.getImageUrl());
		newProd.setPrice(prod.getPrice());
		newProd.setProductId(-1);
		newProd.setTitle(prod.getTitle());
		port.addProduct(newProd);
		System.out.println("new prodcut list");
		plist=port.getAllProducts();
		plist.forEach(product->System.out.println(product));
	}
	
}
