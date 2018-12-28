package hust.plane.web.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统登录验证
 */

public class SessionHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object userId = request.getSession().getAttribute("userId");
        if (userId != null) {
            return true;
        }
        if (isAjax(request)) {
            response.setCharacterEncoding("UTF-8");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
            return false;
        }
        response.sendRedirect(request.getContextPath() + "/admin/login");
        return false;
    }

    private boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {
            return true;
        } else {
            return false;
        }
    }

}
