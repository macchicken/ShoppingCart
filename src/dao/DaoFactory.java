package dao;

import java.util.List;
import java.util.Properties;

import model.Product;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * The Factory responsible for creating proper dao 
 * @author zhouy
 *
 */
public class DaoFactory {

	private ProductDao pDao = null;
	static Log log = LogFactory.getLog(DaoFactory.class);
	private DaoFactory(){
		getProductDao();
	}
	
	private static class DaoFactoryHolder{
		private static final DaoFactory INSTANCE = new DaoFactory();
	}

	public static DaoFactory getInstance(){
		return DaoFactoryHolder.INSTANCE;
	}
	
	public ProductDao getProductDao(){
		if (pDao == null){
			Properties properties = new Properties() ;
			try{
				properties.load(this.getClass().getResourceAsStream("/shoppingcart.properties"));
				String className = properties.getProperty("dao.ProductDaoName");
				if (className!=null){
					pDao = (ProductDao)Class.forName(className).newInstance();
					log.info("Using " + className + " to get ProductInfo...");
				}else{
					log.info("property not found, using default implementation");
					System.out.println("property not found, using default implementation");
					pDao = new ProductDaoMemImpl();
				}
			}catch (Exception e){ 
				log.info(e.getMessage());
				e.printStackTrace();
				pDao =  new ProductDaoMemImpl();
				System.out.println("Exception, using default implementation");
				return pDao;
			}
		}
		return pDao;
	}	
	
	public static void main(String[] argv){
		DaoFactory df = DaoFactory.getInstance();
		ProductDao pDao = df.getProductDao();
		List<Product> products=pDao.getAllProducts();
//		Log log = LogFactory.getLog(DaoFactory.class);
		
		Product newBook = new Product("The Children Act","Ian MacEwan's new novel", 17.64);
		pDao.addProduct(newBook);
		
		log.info(pDao.getAllProducts());
		
		Product p = pDao.getProductById(3);
		
		log.info(p);
		
		newBook.setPrice(29.95);
		pDao.updateProduct(newBook);
		log.info(p);
	}
}

