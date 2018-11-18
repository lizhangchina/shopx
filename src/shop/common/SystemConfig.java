package shop.common;

import java.io.IOException;
import java.util.Properties;

import shop.servlet.DownloadServlet;

public class SystemConfig {
	static Properties prop;
	static{
		prop = new Properties();
		try {
			prop.load(DownloadServlet.class.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getFileSystemRootPath(){
		return prop.getProperty("file_store_root_path");
	}
}
