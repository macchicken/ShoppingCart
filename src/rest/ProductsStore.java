package rest;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import model.ProductV2;
/**
 * Representing the ProductResource in Rest web Services 
 */
@Path("/productsStore")
public class ProductsStore {
	private PhotoQuerySearch search=PhotoQuerySearch.getInstance();
	@Context UriInfo uriInfo; //like an instance variable definition
	
	/**
	 * Get all product. A simplified implementation without exception handling
	 */
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<ProductV2> getAllProducts() {
		return search.getProducts("tiger");
	}

	@GET
	@Path("search/{keyword}")
	@Produces(MediaType.TEXT_XML)
	public List<ProductV2> searchProducts(@PathParam("keyword") String keyword) {
		return search.getProducts(keyword);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_XML)
	public ProductV2 getProductById(@PathParam(value="id") int productId) {
		return null;
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response addProduct(JAXBElement<ProductV2> productXML) {
		Response res = Response.noContent().build();
		return res;
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_XML)
	public Response deleteProduct(@PathParam(value="id") int productId){
		Response res = Response.noContent().build();
		return res;
	}
}
