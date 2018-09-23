package hust.plane.web.controller.admin;

import hust.plane.constant.WebConst;
import hust.plane.mapper.pojo.User;
import hust.plane.service.interFace.UserGroupService;
import hust.plane.service.interFace.UserService;
import hust.plane.utils.PlaneUtils;
import hust.plane.utils.pojo.JsonView;
import hust.plane.utils.pojo.MapCache;
import hust.plane.utils.pojo.TipException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller("adminIndexController")
@RequestMapping(value = "/admin")
public class IndexController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	private MapCache cache = MapCache.single();

	@Value("${BASE_IMAGE_URL}") // 服务器地址
	private String BASE_IMAGE_URL;

	@Value("${USER_DIR}") // 文件夹
	private String USER_DIR;

	@Autowired
	private UserService userService;
	@Resource
	private UserGroupService userGroupService;

	/**
	 * 登陆Get请求
	 *
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginView() {
		return "login";
	}

	/**
	 * 登陆
	 *
	 * @param name
	 * @param password
	 * @param remeber_me
	 * @param request
	 * @param response
	 * @return
	 */
	// 用户登陆Ajax请求
	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String doLogin(@RequestParam String name, @RequestParam String password,
			@RequestParam(required = false) String remeber_me, HttpServletRequest request,
			HttpServletResponse response) {
		// 得到缓存中登陆失败的次数
		Integer error_count = cache.get("login_error_count");

		try {
			User user = userService.login(name, password);
			int count = userService.modifyUpdateTimeWithUserName(name);
			if (count != 1) {
				throw new TipException("用户登录时间修改异常");
			}
			// 把用户保存在session中

			user.setIcon(BASE_IMAGE_URL + USER_DIR + user.getIcon()); // 添加图片服务器位置

			request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
			List<Integer> groupIdList = userGroupService.selectGroupIdWithUserId(user.getId());
			if (groupIdList.contains(Integer.valueOf(1))) {
				request.getSession().setAttribute(WebConst.SUPER_ADMINISTRATOR_VIEW, user);
			}
			if (!groupIdList.contains(Integer.valueOf(1)) && !groupIdList.contains(Integer.valueOf(2))) {
				throw new TipException("您无权进入该系统");
			}
			if (StringUtils.isNotBlank(remeber_me)) {
				PlaneUtils.setCookie(response, user.getId());
			}
		} catch (Exception e) {
			error_count = null == error_count ? 1 : error_count + 1;
			if (error_count > 3) {
				return JsonView.render(1, "您输入密码已经错误超过3次，请3分钟后尝试");
			}
			cache.set("login_error_count", error_count, 10 * 60);
			String msg = "登录失败";
			if (e instanceof TipException) {
				msg = e.getMessage();
			} else {
				LOGGER.error(msg, e);
			}
			return JsonView.render(1, msg);
		}
		return JsonView.render("");
	}

	/**
	 * 注册get请求
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String doRegister(Model mv) {
		return "register";
	}

	/**
	 * 注册post请求
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRegister(@RequestParam String name, @RequestParam String password, @RequestParam String worknumber,
			HttpServletRequest request, HttpSession session) {
		try {
			int count = userService.register(name, password, worknumber);
			if (count < 0) {
				return JsonView.render(1, "注册失败，请重新注册");
			}
		} catch (Exception e) {
			String msg = "注册失败";
			if (e instanceof TipException) {
				msg = e.getMessage();
			} else {
				LOGGER.error(msg, e);
			}
			return JsonView.render(1, msg);
		}
		return JsonView.render(0, "注册成功,等待管理员确认！");
	}

	/**
	 * 用户退出登陆
	 *
	 * @param [request,
	 *            response, session]
	 * @return void
	 * @author rfYang
	 * @date 2018/7/3 18:07
	 */
	@RequestMapping(value = "/logout")
	public void doLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
		session.removeAttribute(WebConst.SUPER_ADMINISTRATOR_VIEW);
		Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
		cookie.setValue(null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		try {
			response.sendRedirect("login");
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("注销失败", e);
		}
	}

	/**
	 * 个人设置GET请求
	 *
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/passwordEdit")
	public String editPwd(Model mv) {

		mv.addAttribute("curNav", "passwordEdit");
		return "passwordEdit";
	}

	// 返回修改个人信息页面
	@RequestMapping(value = "/profileEdit")
	public String profileEditView(Model mv, HttpServletRequest request) {

		User user = PlaneUtils.getLoginUser(request);
		mv.addAttribute("user", user);
		mv.addAttribute("curNav", "profileEdit");
		return "profileEdit";
	}

	// 修改个人信息 Ajax请求
	@RequestMapping(value = "/doprofileEdit", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String profileEdit(HttpServletRequest request, User user2) {

		User user = PlaneUtils.getLoginUser(request);

		if (request.getContentType() != null) { // 如果有上传图片的需求的话

			if (request.getContentType().contains("multipart/form-data")) {

				MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;

				// 根据input name名称获取文件对象
				CommonsMultipartFile cm = (CommonsMultipartFile) req.getFile("file");
				if (!cm.isEmpty()) {

					byte[] fbytes = cm.getBytes();
					String newFileName = "";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
					newFileName = sdf.format(new Date());
					Random r = new Random();
					for (int i = 0; i < 3; i++) {
						newFileName = newFileName + r.nextInt(10);
					}
					// 获取文件扩展名
					String originalFilename = cm.getOriginalFilename();
					String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

					// 创建jesy服务器，进行跨服务器上传
					Client client = Client.create();
					// 把文件关联到远程服务器
					
					WebResource resource = client.resource(BASE_IMAGE_URL + USER_DIR + newFileName + suffix);
					// 上传
					resource.put(String.class, fbytes);
					user2.setIcon(newFileName + suffix);
				}
			}
		}

		user2.setId(user.getId());
		
		if (userService.updateByUser(user2) == true)
			//更新session内容
			{
			    User user3 = userService.getUserById(user.getId());
			    user3.setIcon(BASE_IMAGE_URL + USER_DIR + user3.getIcon());
			    request.getSession().removeAttribute(WebConst.LOGIN_SESSION_KEY);
			    request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user3);
			    
			    return JsonView.render(0, "修改成功");
			}
		else
			return JsonView.render(0, "修改失败");
	}

	@RequestMapping(value = "/doPasswordEdit", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String doEditPwd(@RequestParam String oldpassword, @RequestParam String password,
			HttpServletRequest request) {
		try {
			userService.modifyPwd(request, oldpassword, password);
		} catch (Exception e) {
			String msg = "更改失败";
			if (e instanceof TipException) {
				msg = e.getMessage();
			} else {
				LOGGER.error(msg, e);
			}
			return JsonView.render(1, msg);
		}
		return JsonView.render(0, "修改成功");
	}

}
