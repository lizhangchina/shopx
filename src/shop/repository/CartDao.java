package shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shop.model.Cart;
import shop.model.Item;


public class CartDao {

	public void saveToCart(String username,String itemId,int count,float priceTotal) throws SQLException{
		Connection conn = SysDataSource.getInstance().getConnection();
		String sql = "select cartid,item.itemid,price,priceTotal,count from cart,item where username=? and cart.itemId=? and cart.itemId=item.itemid";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, itemId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			int id = rs.getInt("cartid");
			int new_count = rs.getInt("count")+count;
			float new_priceTotal = new_count*rs.getFloat("price");
			sql = "update cart set count = ?,priceTotal=? where cartid = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, new_count);
			ps.setFloat(2, new_priceTotal);
			ps.setInt(3, id);
			ps.executeUpdate();
			ps.close();
		}else{
			sql = "insert into cart(username,itemid,count,priceTotal) values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, itemId);
			ps.setInt(3, count);
			ps.setFloat(4, priceTotal);
			ps.executeUpdate();
			ps.close();
		}
		conn.close();
	}
	
	public List<Cart> getCart(String username) throws SQLException{
		List<Cart> list = new ArrayList<Cart>();
		Connection conn = SysDataSource.getInstance().getConnection();
		String sql = "select * from cart where username = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			String itemId = rs.getString("itemid");
			int count = rs.getInt("count");
			float priceTotal = rs.getFloat("priceTotal");
			Cart cart = new Cart();
			cart.setUsername(username);
			cart.setCount(count);
			cart.setItemId(itemId);
			cart.setPriceTotal(priceTotal);
			list.add(cart);
		}
		rs.close();
		conn.close();
		return list;
	}
	
	
}
