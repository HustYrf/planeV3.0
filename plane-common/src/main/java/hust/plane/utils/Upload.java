package hust.plane.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Upload {
    /**
     * 上传文件
     *
     * @param serverPath 服务器地址:(http://172.16.5.102:8090/)
     * @param path       文件路径（不包含服务器地址：ImageTask/）
     * @param taskDir    任务文件夹
     * @return
     */
    public static String upload(Client client, MultipartFile file, String serverPath, String fileserverPath, String path, String taskDir) {
        // 文件名称生成策略（UUID uuid = UUID.randomUUID()）
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatDate = format.format(d);
        String str = "";
        for (int i = 0; i < 5; i++) {
            int n = (int) (Math.random() * 90) + 10;
            str += n;
        }

        // 获取文件的扩展名
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        // 文件名
        String fileName = formatDate + str + "." + extension;

        //相对路径   例: ImageTask/23/ImageResource/123.jpg
        String relativePath = path + taskDir + "/" + "ImageResource" + "/" + fileName;

        // 另一台tomcat的URL（文件上传本机真实路径,使用本机路径是因为服务器是做了网络映射，导致跨域端口被视为同源，所有用本机的lcoalhost作为跨域地址）
        String filelPath = fileserverPath + relativePath;

        // 另一台tomcat的URL（文件上传网络真实路径）
        String realPath = serverPath + relativePath;

        // 设置请求路径
        WebResource resource = client.resource(filelPath);

        // 发送开始post get put（基于put提交）
        try {
            resource.put(String.class, file.getBytes());
            return fileName + ";" + relativePath + ";" + realPath;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 删除文件
     *
     * @param filePath（文件完整地址：http://172.16.5.102:8090/ImageTask/23/TaskResource/1234.jpg）
     * @return
     */
    public static String delete(String filePath) {
        try {
            Client client = new Client();
            WebResource resource = client.resource(filePath);
            resource.delete();
            return "y";
        } catch (Exception e) {
            e.printStackTrace();
            return "n";
        }
    }
}