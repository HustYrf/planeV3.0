package hust.plane.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Upload {
    /**
     * 上传文件

     * @param serverPath    服务器地址:(http://172.16.5.102:8090/)
     * @param path             文件路径（不包含服务器地址：upload/）
     * @param
     * @return
     */ //                                                           232.11.11.34       /ImageTask        /ImageResource       /ImageAlarm
    public static String upload(Client client, MultipartFile file, String serverPath, String path,String taskResource,String taskAlarm,String taskDir){
        // 文件名称生成策略（UUID uuid = UUID.randomUUID()）
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatDate = format.format(d);
        String str = "";
        for(int i=0 ;i <5; i++){
            int n = (int)(Math.random()*90)+10;
            str += n;
        }
        // 获取文件的扩展名
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        // 文件名
        String fileName = formatDate + str + "." + extension;
        //相对路径
        String relaPath = path + taskDir + "/"+ fileName;

//        String a = serverPath + path.substring(0, path.lastIndexOf("/"));
//         String serverFileAddress = "D:"+File.separator+"Games"+File.separator+taskDir;
        StringBuilder serverFileAddress = new StringBuilder();
        serverFileAddress.append("home").append(File.separator).append("gxdx_ai").append(File.separator).append("file-workspace")
                .append(File.separator).append(path).append(File.separator).append(taskDir);//任务文件夹地址


        String taskBaseDir = serverFileAddress.toString();   //任务基本路径
        String taskResourceDir = taskBaseDir + File.separator + taskResource;  // 任务原图片文件夹
        String  taskAlarmDir =  taskBaseDir + File.separator + taskAlarm;     // 任务告警图片文件夹

        File file2 = new File(taskBaseDir);
        if(!file2.exists()){
            boolean mkdirs2 = file2.mkdirs();
            try {
                Runtime.getRuntime().exec("chmod 777 -R " + serverFileAddress.toString());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("任务基本文件夹权限授予失败！");
            }
        }

        File file3 = new File(taskResourceDir);
        if(!file3.exists()){
            boolean mkdirs3 = file3.mkdirs();
            try {
                Runtime.getRuntime().exec("chmod 777 -R " + taskResourceDir);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("任务源文件文件夹权限授予失败！");
            }
        }

        File file4 = new File(taskAlarmDir);
        if(!file4.exists()){
            boolean mkdirs4 = file4.mkdirs();
            try {
                Runtime.getRuntime().exec("chmod 777 -R " + taskAlarmDir);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("任务告警文件夹权限授予失败！");
            }
        }


        // 另一台tomcat的URL（真实路径）
        String realPath = serverPath + relaPath;
        // 设置请求路径
        WebResource resource = client.resource(realPath);

        // 发送开始post get put（基于put提交）
        try {
            resource.put(String.class, file.getBytes());
            return fileName+";"+relaPath+";"+realPath;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 删除文件
     * @param filePath（文件完整地址：http://172.16.5.102:8090/upload/1234.jpg）
     * @return
     */
    public static String delete(String filePath){
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
