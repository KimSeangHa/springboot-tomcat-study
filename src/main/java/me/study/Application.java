package me.study;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Application {
	// SpringBoot 내장 웹 서버 사용하지 않고 tomcat 만들기.
	public static void main(String[] args) {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		
		Context context = tomcat.addContext("/", "/");
		
		// servlet make
		HttpServlet servlet = new HttpServlet() {
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse res) {
				PrintWriter writer;
				try {
					writer = res.getWriter();
					
					writer.println("<html><head><title>");
					writer.println("Hey, tomcat");
					writer.println("</title><head>");
					writer.println("<body><h1>Hello Tomcat</h1></body>");
					writer.println("</html>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		
		String servletName = "helloServlet";
		tomcat.addServlet("/", servletName, servlet);
		context.addServletMappingDecoded("/hello", servletName);
		
		try {
			tomcat.start();
			tomcat.getServer().await();
		} catch (LifecycleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
