package hust.plane.constant;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义一些常用的字符串
 *
 * @param
 * @author rfYang
 * @date 2018/7/3 18:22
 * @return
 */
@Component
public class WebConst {
    public static final String SUPER_ADMINISTRATOR_VIEW = "super_administrator";
    public static final String SEARCH_NO_USERNAME = "S_N_NAME";

    public static String LOGIN_SESSION_KEY = "login_user";

    public static final String USER_IN_COOKIE = "S_L_ID";
    /**
     * 成功返回
     */
    public static String SUCCESS_RESULT = "SUCCESS";
    /**
     * aes加密加盐
     */
    public static String AES_SALT = "0123456789abcdef";
    /**
     * 搜索不用用户序号
     */
    public static String SEARCH_NO_USERID = "S_N_ID";
    /**
     * 图片的路径
     */
    public static String ALARM_PIC_PATH = "D:/100MEDIA";

    /**
     * 异常信息统一头信息<br>
     * 非常遗憾的通知您,程序发生了异常
     */
    public static final String Exception_Head = "boom。炸了。";
    /**
     * 缓存键值
     */
    public static final Map<Class<?>, String> cacheKeyMap = new HashMap<>();
    /**
     * 保存文件所在路径的key，eg.FILE_MD5:1243jkalsjflkwaejklgjawe
     */
    public static final String FILE_MD5_KEY = "FILE_MD5:";
    /**
     * 保存上传文件的状态
     */
    public static final String FILE_UPLOAD_STATUS = "FILE_UPLOAD_STATUS";
}
