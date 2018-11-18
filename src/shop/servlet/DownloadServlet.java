package shop.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.common.SystemConfig;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getParameter("path");
		InputStream is = new FileInputStream(SystemConfig.getFileSystemRootPath()+path);
		
		response.setContentLength(is.available());
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[8192];
		int len;
		while((len=is.read(buffer))!=-1){
			os.write(buffer, 0, len);
		}
		os.close();
		is.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse reponse){
		
	}

}
