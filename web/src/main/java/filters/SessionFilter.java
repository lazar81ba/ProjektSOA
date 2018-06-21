package filters;

import ejb.SessionService;
import managedBeans.RemoteSessionService;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = "/*", asyncSupported = true)
public class SessionFilter implements Filter{

    private RemoteSessionService sessionService;
    private String redirectPath = "/web/userAlreadyLoggedIn.xhtml";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            sessionService = new RemoteSessionService();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(!request.getRequestURI().equals(redirectPath)) {

            if (sessionService.wasSessionCreated(request.getUserPrincipal().getName())) {
                if (!sessionService.getSession(request.getUserPrincipal().getName()).equals(request.getSession(false).getId())) {
                    HttpServletResponse response = (HttpServletResponse) servletResponse;
                    response.sendRedirect(redirectPath);
                    return;
                }
            } else {
                sessionService.addSession(request.getUserPrincipal().getName(), request.getSession(false).getId());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
