package shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.model.User;

/**
 * 
 * @author zhangli
 *
 */
public class UserDAO {

	public User findUser(String username) {
		User user = null;
		Connection conn = null;
		try {
			// 连接数据库
			conn = SysDataSource.getInstance().getConnection();
			// 设定命令
			PreparedStatement pstmt = conn
					.prepareStatement("select * from user where username = ?");
			pstmt.setString(1, username);// 指定命令参数
			ResultSet rs = pstmt.executeQuery();// 执行并返回结果
			if (rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRolename(rs.getString("rolename"));
				user.setName(rs.getString("name"));
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
		return user;
	}
	

	public List<User> listUser() {
		List<User> list = new ArrayList<User>();
		Connection conn = null;
		try {
			conn = SysDataSource.getInstance().getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("select * from user");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRolename(rs.getString("rolename"));
				user.setName(rs.getString("name"));
				list.add(user);

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
	
	public List<User> listUser(int size, int page){
		List<User> list = new ArrayList<User>();
		Connection conn = null;
		try {
			int startIndex = size*(page-1);
			conn = SysDataSource.getInstance().getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("select * from user limit "+startIndex+","+size);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRolename(rs.getString("rolename"));
				user.setName(rs.getString("name"));
				list.add(user);

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
	public List<User> listUser(int size, int page,String username){
		List<User> list = new ArrayList<User>();
		Connection conn = null;
		try {
			int startIndex = size*(page-1);
			conn = SysDataSource.getInstance().getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("select * from user where username like ? limit ?,?");
			pstmt.setString(1, "%"+username+"%");
			pstmt.setInt(2, startIndex);
			pstmt.setInt(3, size);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRolename(rs.getString("rolename"));
				user.setName(rs.getString("name"));
				list.add(user);

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
	public List<User> listUser(String rolename) {
		List<User> list = new ArrayList<User>();
		Connection conn = null;
		try {
			conn = SysDataSource.getInstance().getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("select * from user where rolename = ?");
			pstmt.setString(1, rolename);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRolename(rs.getString("rolename"));
				user.setName(rs.getString("name"));
				list.add(user);
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
	
	public int getUserCount(){
		int count=0;
		Connection conn = null;
		try {
			conn = SysDataSource.getInstance().getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("select count(*) from user");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}
	
	public int getUserCount(String username){
		int count=0;
		Connection conn = null;
		try {
			conn = SysDataSource.getInstance().getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("select count(*) from user where username like ?");
			pstmt.setString(1, "%"+username+"%");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

	public boolean insert(User user) {
		boolean b = false;
		Connection conn = null;
		try {
			conn = SysDataSource.getInstance().getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("insert into user(username,password,rolename,name) values(?,?,?,?)");
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			;
			pstmt.setString(3, user.getRolename());
			pstmt.setString(4, user.getName());
			b = pstmt.execute();
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

		return b;
	}

	public boolean delete(String username) {
		boolean delflag = false;
		Connection conn = null;
		try {
			// 连接数据库
			conn = SysDataSource.getInstance().getConnection();
			// 设定命令
			PreparedStatement pstmt = conn
					.prepareStatement("delete from user where username = ?");
			pstmt.setString(1, username.trim());// 指定命令参数
			delflag = pstmt.execute();// 执行并返回结果
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
		return delflag;
	}

	public boolean edit(User userUpdate){
		boolean editflag = false;
		Connection conn = null;
		try {
			// 连接数据库
			conn = SysDataSource.getInstance().getConnection();
			// 设定命令
			PreparedStatement pstmt = conn
					.prepareStatement("update user set password = ?, name = ? where username = ?");
			pstmt.setString(1, userUpdate.getPassword());// 指定命令参数
			pstmt.setString(2, userUpdate.getName());
			pstmt.setString(3, userUpdate.getUsername());
			editflag = pstmt.execute();// 执行并返回结果
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
		return editflag;
	}
	public String[][] toData() {
		List<User> list = listUser();
		String[][] data = new String[list.size()][4];
		for (int i = 0; i < list.size(); i++) {
			data[i] = list.get(i).toData();
		}
		return data;

	}

}
