package och14;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

//@WebFilter("/sub1/*")
// sub1에서 돌아가는 모든 파일은 이 필터를 한 번 타라
public class SimpleFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("SimpleFilter init...");
		Filter.super.init(filterConfig);
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("filter 시작");
		// 또 다른 Filter 수정
		chain.doFilter(request, response);
		System.out.println("filter 끝");
	}

}
