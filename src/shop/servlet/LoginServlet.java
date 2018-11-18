package shop.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.Cart;
import shop.model.Item;
import shop.model.User;
import shop.repository.CartDao;
import shop.repository.ItemDAO;
import shop.repository.UserDAO;


@WebServlet(value="/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -218057050017718904L;
	UserDAO userDao = new UserDAO();
	CartDao cartDao = new CartDao();
	ItemDAO itemdao = new ItemDAO();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		User user= (User)request.getSession().getAttribute("user");
		if (user!=null){
			request.getRequestDispatcher("/item").forward(request, response);
		}else{
		
			String username = request.getParameter("username");
			String password = request.getParameter("password");
		
			user = userDao.findUser(username);
			if(user!=null&&user.getPassword().equals(password)){
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				Map<String,Map<String,Object>> itemCart = (Map<String,Map<String,Object>>)session.getAttribute("itemCart");
				for(Map<String,Object> m:itemCart.values()){
					String itemId = ((Item)m.get("item")).getId();
					int count = Integer.parseInt(m.get("itemCount").toString());
					float priceTotal = Float.parseFloat(m.get("itemPriceTotal").toString());
					try {
						cartDao.saveToCart(username, itemId, count,priceTotal);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				itemCart = new HashMap<String,Map<String,Object>>();
				session.setAttribute("itemCart", itemCart);
				
				List<Cart> cartList;
				try {
					cartList = cartDao.getCart(username);
					for(Cart cart:cartList){
						Item item = itemdao.findItem(cart.getItemId());
						Map<String,Object> itemMap = new HashMap<String,Object>();
						itemMap.put("item", item);
						itemMap.put("itemCount", cart.getCount());
						itemMap.put("itemPriceTotal", cart.getPriceTotal());
						itemCart.put(cart.getItemId(), itemMap);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				request.getRequestDispatcher("/item").forward(request, response);
			}else{
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		
	}
	
}
