package shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.model.Item;


public class ItemDAO {
	public List<Item> listItem() {
		List<Item> list = new ArrayList<Item>();
		Connection conn = null;
		try {
			conn = SysDataSource.getInstance().getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("select * from item");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getString("itemid"));
				item.setName(rs.getString("itemname"));
				item.setImageurl(rs.getString("imageurl"));
				item.setPrice(rs.getFloat("price"));
				item.setStock(rs.getInt("stock"));
				list.add(item);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	public List<Item> listItem(int size, int page){
		List<Item> list = new ArrayList<Item>();
		Connection conn = null;
		try {
			int startIndex = size*(page-1);
			conn = SysDataSource.getInstance().getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("select * from item limit "+startIndex+","+size);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getString("itemid"));
				item.setName(rs.getString("itemname"));
				item.setImageurl(rs.getString("imageurl"));
				item.setPrice(rs.getFloat("price"));
				item.setStock(rs.getInt("stock"));
				list.add(item);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public Item findItem(String itemid) {
		Item item = null;
		Connection conn = null;
		try {
			// 连接数据库
			conn = SysDataSource.getInstance().getConnection();
			// 设定命令
			PreparedStatement pstmt = conn
					.prepareStatement("select * from item where itemid = ?");
			pstmt.setString(1, itemid);// 指定命令参数
			ResultSet rs = pstmt.executeQuery();// 执行并返回结果
			if (rs.next()) {
				item = new Item();
				item.setId(rs.getString("itemid"));
				item.setName(rs.getString("itemname"));
				item.setPrice(rs.getFloat("price"));
				item.setStock(rs.getInt("stock"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
}
