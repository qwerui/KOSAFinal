package v0.project.mysite.main.common;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestLogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 요청 URL 로깅
        String requestURL = httpRequest.getRequestURL().toString();
        System.out.println("Requested URL: " + requestURL);

        chain.doFilter(request, response);
    }
}
