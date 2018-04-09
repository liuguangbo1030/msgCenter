package com.msg.mc.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author cloudy
 * @version 1.0
 * @date 2016/9/1
 */
@Component
public class MessageSendFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(MessageSendFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//        if (request == null || request.getParameter("data") == null || request.getParameter("accessToken") == null) {
//
//            this.write(HttpResultUtil.getResult(90001), response);
//            return;
//        }
//
//        if(!this.validRequest(request)) {
//            this.write(HttpResultUtil.getResult(90001), response);
//            return;
//        }

        filterChain.doFilter(request, response);
	}
	
    private boolean validRequest(HttpServletRequest request) {
        return true;
    }

	private void write(String result, HttpServletResponse response) throws IOException{
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(result);
        out.close();
	}
}
