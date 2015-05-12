package model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
public class ProductV2{

	@NotEmpty(message="You must supply a title")
	private String title;
	
	private String description,imageUrl;
	@NotNull(message="You must specify the price")
	@Min(value=0, message="Price needs to be a positive number")
	private double price;
	private String productId; //default id will be modified by the storage
	private String originImg;
	
	public ProductV2(){		
	}
	
	public ProductV2(String title, String description, double price){
		this.title = title;
		this.description = description;
		this.price = price;
	}
	
	public ProductV2(String productId, String title, String description, double price){
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.price = price;
	}
	public ProductV2(String productId, String title, String description, String imgUrl, double price){
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.imageUrl = imgUrl;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	

	public String getOriginImg() {
		return originImg;
	}

	public void setOriginImg(String originImg) {
		this.originImg = originImg;
	}

	public String toString(){
		return "Product " + productId + ": " + title + "@" + price;
	}

}
