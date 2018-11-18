package shop.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.Item;
import shop.model.User;
import shop.repository.*;


@WebServlet("/item/*")
public class ItemServlet extends HttpServlet {

	ItemDAO itemdao = new ItemDAO();
	CartDao cartDao = new CartDao();
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		String path = req.getPathInfo();
		if (path==null||"/".equals(path)) {
			index(req,resp);
		} else if (path.equals("/addToCart")) {
			addToCart(req,resp);
		} else if (path.equals("/removeItem")) {
			removeItem(req,resp);
		}else if (path.equals("/saveCart")) {
			saveCart(req,resp);
		} 
	}
	
	private void index(HttpServletRequest req, HttpServletResponse resp){
		
		List<Item> itemList = itemdao.listItem();
		req.setAttribute("itemList", itemList);
		try {
			req.getRequestDispatcher("/WEB-INF/jsp/item.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addToCart(HttpServletRequest req, HttpServletResponse resp){
		HttpSession session= req.getSession();
		String id=req.getParameter("itemId");
		int count = Integer.parseInt(req.getParameter("count"));
		
		Map<String,Map<String,Object>> itemCart = (Map<String,Map<String,Object>>)session.getAttribute("itemCart");
		if(itemCart == null){
			itemCart = new HashMap<String,Map<String,Object>>();
			session.setAttribute("itemCart", itemCart);
		}
		
		Map<String,Object> itemMap = itemCart.get(id);
		Item item = itemdao.findItem(id);
		if(itemMap==null) {
			itemMap = new HashMap<String,Object>();
			itemMap.put("item", item);
			itemMap.put("itemCount", 0);
			itemMap.put("itemPriceTotal", 0.0f);
			itemCart.put(id, itemMap);
		}
		
		int itemCount = Integer.parseInt(itemMap.get("itemCount").toString())+count;
		float itemPriceTotal = itemCount*item.getPrice();
		
		itemMap.put("itemCount", itemCount);
		itemMap.put("itemPriceTotal", itemPriceTotal);
		User user = (User)session.getAttribute("user");
		if(user!=null){
			try {
				cartDao.saveToCart(user.getUsername(), id, count,itemPriceTotal);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			//req.getRequestDispatcher("/item").forward(req, resp);
			resp.sendRedirect(req.getContextPath()+"/item");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void removeItem(HttpServletRequest req, HttpServletResponse resp){
		HttpSession session= req.getSession();
		String id=req.getParameter("itemId");
		Map<String,Map<String,Object>> itemCart = (Map<String,Map<String,Object>>)session.getAttribute("itemCart");
		
		itemCart.remove(id);
		User user = (User)session.getAttribute("user");
		if(user!=null){
			try {
				cartDao.deleteCartItem(user.getUsername(), id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			//req.getRequestDispatcher("/item").forward(req, resp);
			resp.sendRedirect(req.getContextPath()+"/item");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void saveCart(HttpServletRequest req, HttpServletResponse resp){
		
		try {
			//req.getRequestDispatcher("/item").forward(req, resp);
			resp.sendRedirect(req.getContextPath()+"/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
