package com.dagong.restful;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理跨域的Filter
 * 
 * @author DAGONG
 *
 */

public class CrossFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CrossFilter() {
        System.out.println(CrossFilter.class.getCanonicalName());
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * 服务器端Cross跨域问题解决
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse)response;
		System.out.println(req.getHeader("Access-Control-Allow-Origin"));
		resp.setHeader("Access-Control-Allow-Origin", "*");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
