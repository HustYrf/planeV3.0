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
    public static String upload(Client client, MultipartFile file, String serverPath, String path,String taskDir){
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
        String relaPath = path + taskDir +"ImageResource" +"/"+ fileName;

//        String a = serverPath + path.substring(0, path.lastIndexOf("/"));
//         String serverFileAddress = "D:"+File.separator+"Games"+File.separator+taskDir;
        StringBuilder taskFileAddress = new StringBuilder();
        taskFileAddress.append(File.separator).append("home").append(File.separator).append("gxdx_ai").append(File.separator).append("file-workspace")
                .append(File.separator).append("ImageTask").append(File.separator).append(taskDir);//任务文件夹地址
        
        StringBuilder sourceFileAddress = new StringBuilder();
        sourceFileAddress.append(File.separator).append("home").append(File.separator).append("gxdx_ai").append(File.separator).append("file-workspace")
                .append(File.separator).append("ImageTask").append(File.separator).append(taskDir).append(File.separator).append("ImageResource");//源任务文件夹地址
        
        StringBuilder alarmFileAddress = new StringBuilder();
        alarmFileAddress.append(File.separator).append("home").append(File.separator).append("gxdx_ai").append(File.separator).append("file-workspace")
                .append(File.separator).append("ImageTask").append(File.separator).append(taskDir).append(File.separator).append("ImageAlarm");//告警任务文件夹地址
        
        
        File file2 = new File(taskFileAddress.toString());
        if(!file2.exists()){
            boolean mkdirs2 = file2.mkdirs();
            try {
                Runtime.getRuntime().exec("chmod 777 -R " + taskFileAddress.toString());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("任务基本文件夹权限授予失败！");
            }
        }
        File file3 = new File(sourceFileAddress.toString());
        if(!file3.exists())
        {
        	  boolean mkdirs = file3.mkdirs();
              try {
                  Runtime.getRuntime().exec("chmod 777 -R " + sourceFileAddress.toString());
              } catch (IOException e) {
                  e.printStackTrace();
                  System.out.println("文件夹权限授予失败");
              }
              System.out.println(mkdirs);
        }
        
        File file4 = new File(alarmFileAddress.toString());
        if(!file3.exists())
        {
        	  boolean mkdirs = file4.mkdirs();
              try {
                  Runtime.getRuntime().exec("chmod 777 -R " + alarmFileAddress.toString());
              } catch (IOException e) {
                  e.printStackTrace();
                  System.out.println("文件夹权限授予失败");
              }
              System.out.println(mkdirs);
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
