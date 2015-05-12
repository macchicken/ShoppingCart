package rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Product;


public class ProductRestClient {
	
	public static void main(String argv[]){
		Client client = ClientBuilder.newClient();
		String mediaType = MediaType.APPLICATION_XML;
		WebTarget root = client.target("http://localhost:10080/ShoppingCart/rest/products/"); 

		Product product = new Product("Shutter Island","Novel","webapplication.jpg",14.95);
		Entity<Product> productEntity = Entity.entity(product,mediaType);
		Response response = root.request().post(productEntity,Response.class);
		// Return code should be 201 == created resource
		System.out.println(response.getStatus());
		// Update product with id 1
		product = new Product(1, "Web Application Architecture","textbook","webapplication.jpg",50.95);
		response = root.request().post(Entity.entity(product,mediaType), Response.class);
		// Return code should be 204 == no content
		System.out.println(response.getStatus());		
		// Get product with id 1
		System.out.println(root.path("1").request().get(Response.class).readEntity(Product.class));
		System.out.println(root.path("2").request().delete(Response.class).getStatus());
		System.out.println(root.path("2").request().get(Response.class).readEntity(Product.class));
	}
}
