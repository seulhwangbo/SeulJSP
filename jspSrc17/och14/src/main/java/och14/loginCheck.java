package och14;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class loginCheck
 */

// 어떤 프로그램이든 간에 sub2에 있는 것은 다 체크를 할 거야
@WebFilter("/sub2/*")
public class loginCheck extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public loginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("loginCheck doFilter....  ");
		// pass the request along the filter chain
		//chain.doFilter(request, response);
		// request.gets => 위에 session이 없기 때문에 형변환을 해야 한다
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		// 서버에서 session 도출하는 방법이다
		HttpSession session = httpServletRequest.getSession();
		
		System.out.println("logincheck doFilter....");
		
		if(session == null || session.equals("")) {
			httpServletResponse.sendRedirect("../login.jsp");
		}
		System.out.println("doFilter session ! =null");
		String id = (String)session.getAttribute("id");
		System.out.println("doFilter session id " + id);
		if(id == null || id.equals("")){
			httpServletResponse.sendRedirect("../login.jsp");
			System.out.println("doFilter session is not null id" + id);
		}
		
		//chain으로 가라
		chain.doFilter(request, response);
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
