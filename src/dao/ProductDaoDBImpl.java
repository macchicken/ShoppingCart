package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Product;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProductDaoDBImpl implements ProductDao {
	private DataSource ds;
	private String getProductByIdSQL = "SELECT * from products where id = ?",
				getAllProductsSQL = "SELECT * from products",
				deleteProductSQL = "DELETE from products where id = ?" ,
				insertProductSQL = "INSERT into products(title,description,price) values (?,?,?)" ,
				updateProductSQL = "UPDATE products set title = ?, description=?, price=? where id = ?";
	Log log = LogFactory.getLog(ProductDaoDBImpl.class);
	
	public ProductDaoDBImpl() throws Exception{
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			// Look up our data source
			ds = (DataSource) envCtx.lookup("jdbc/ShoppingCart");			
		}catch (NamingException e){
			throw new Exception("cannot find database");
		}catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void addProduct(Product p) {
		Connection conn=null;PreparedStatement ps=null;
		try{
			conn = ds.getConnection();
			ps = conn.prepareStatement(insertProductSQL);
			ps.setString(1, p.getTitle());
			ps.setString(2, p.getDescription());
			ps.setDouble(3, p.getPrice());
			ps.executeUpdate();
		}
		catch(SQLException e){
			log.error("can not insert product " + p);
		}finally{
			try {
				if (ps!=null){ps.close();}
				if (conn!=null){conn.close();}
			} catch (SQLException e) {
				log.error("can not close resource "+e.getMessage());
			}
		}

	}

	@Override
	public void deleteProduct(int productId) {
		Connection conn=null;PreparedStatement ps=null;
		try{
			conn = ds.getConnection();
			ps = conn.prepareStatement(deleteProductSQL);
			ps.setInt(1, productId);
			ps.executeUpdate();
		}
		catch(SQLException e){
			log.error("can not delete product " +productId);
		}finally{
			try {
				if (ps!=null){ps.close();}
				if (conn!=null){conn.close();}
			} catch (SQLException e) {
				log.error("can not close resource "+e.getMessage());
			}
		}

	}

	@Override
	public List<Product> getAllProducts() {
		Connection conn=null;PreparedStatement ps=null;ResultSet rs=null;
		List<Product> products = new ArrayList<Product>();
		try{
			conn =ds.getConnection();
			ps = conn.prepareStatement(getAllProductsSQL);
			rs = ps.executeQuery();
			while(rs.next()){
				products.add(new Product(rs.getInt("id"),
									rs.getString("title"),
									rs.getString("description"),
									rs.getDouble("price")));
			}
			return products;
		}catch (Exception e){
			e.printStackTrace();
			log.error("does not get products!");
			return null;
		}finally{
			try {
				if (rs!=null){rs.close();}
				if (ps!=null){ps.close();}
				if (conn!=null){conn.close();}
			} catch (SQLException e) {
				log.error("can not close resource "+e.getMessage());
			}
		}
	}

	@Override
	public Product getProductById(int productId) {
		Connection conn=null;PreparedStatement ps=null;ResultSet rs=null;
		try{
			conn = ds.getConnection();
			ps = conn.prepareStatement(getProductByIdSQL);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			Product p = null;
			if (rs.next()){
				p = new Product(rs.getInt("id"),
										rs.getString("title"),
										rs.getString("description"),
										rs.getDouble("price"));
			}
			return p;
		}catch (SQLException e){
			return null;
		}finally{
			try {
				if (rs!=null){rs.close();}
				if (ps!=null){ps.close();}
				if (conn!=null){conn.close();}
			} catch (SQLException e) {
				log.error("can not close resource "+e.getMessage());
			}
		}
	}

	@Override
	public void updateProduct(Product p) {
		Connection conn=null;PreparedStatement ps=null;
		try{
			conn = ds.getConnection();
			ps = conn.prepareStatement(updateProductSQL);
			ps.setString(1, p.getTitle());
			ps.setString(2, p.getDescription());
			ps.setDouble(3, p.getPrice());
			ps.setInt(4, p.getProductId());
			ps.executeUpdate();
		}
		catch(SQLException e){
			log.error("can not update product " + p);
		}finally{
			try {
				if (ps!=null){ps.close();}
				if (conn!=null){conn.close();}
			} catch (SQLException e) {
				log.error("can not close resource "+e.getMessage());
			}
		}


	}

}
