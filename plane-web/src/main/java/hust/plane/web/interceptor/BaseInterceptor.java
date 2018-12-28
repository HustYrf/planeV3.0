package hust.plane.web.interceptor;

import hust.plane.constant.WebConst;
import hust.plane.mapper.pojo.User;
import hust.plane.service.interFace.UserService;
import hust.plane.utils.PlaneUtils;
import hust.plane.utils.UUID;
import hust.plane.utils.pojo.MapCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基本自定义拦截器
 *
 * @param
 * @author rfYang
 * @date 2018/7/3 14:28
 * @return
 */
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "user-agent";
    private MapCache cache = MapCache.single();
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String contextPath = httpServletRequest.getContextPath();
        String uri = httpServletRequest.getRequestURI();
        //LOGGER.info("UserAget:{}", httpServletRequest.getHeader(USER_AGENT));
        //LOGGER.info("用户访问地址：{}，来路地址：{}", uri, IPKIT.getIpAddrByRequest(httpServletRequest));

        //对下载apk文件的请求放行
        if (uri.startsWith(contextPath) && uri.startsWith(contextPath + "/AppDownload")) {
            // httpServletResponse.sendRedirect(contextPath + "/admin/login");
            return true;
        }

        //请求拦截器
        User user = PlaneUtils.getLoginUser(httpServletRequest);
        if (user == null) {
            Integer uid = PlaneUtils.getCookieUid(httpServletRequest);
            if (uid != null) {
                user = userService.queryUserById(uid.intValue());
                httpServletRequest.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
            }
        }


        if (uri.startsWith(contextPath) && !uri.startsWith(contextPath + "/admin/login") && !uri.startsWith(contextPath + "/admin/register") && user == null) {
            httpServletResponse.sendRedirect(contextPath + "/admin/login");
            return false;
        }

        //设置get请求的token
        if (httpServletRequest.getMethod().equals("GET")) {
            String csrf_token = UUID.UU64();
            cache.hset("csrf_token", csrf_token, uri, 10 * 60);//10min
            httpServletRequest.setAttribute("_csrf_token", csrf_token);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
