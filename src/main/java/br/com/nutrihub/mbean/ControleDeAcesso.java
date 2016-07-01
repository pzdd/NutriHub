package br.com.nutrihub.mbean;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter(servletNames= {"Faces Servlet"})
public class ControleDeAcesso implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpSession session = req.getSession();

		if((session.getAttribute("USUARIOLogado") != null) ||
				(req.getRequestURI().endsWith("Login.xhtml"))||
				(req.getRequestURI().endsWith("Cadastro.xhtml"))||
				(req.getRequestURI().contains("javax.faces.resource/"))){
			arg2.doFilter(arg0, arg1);
		}else{
			redireciona("Login.xhtml",arg1);
		}
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	private void redireciona(String url,ServletResponse res ) throws IOException{
		HttpServletResponse req = (HttpServletResponse) res;
		req.sendRedirect(url);
	}

}
