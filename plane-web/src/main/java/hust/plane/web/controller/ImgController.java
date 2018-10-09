package hust.plane.web.controller;

import com.sun.jersey.api.client.Client;
import hust.plane.service.interFace.TaskService;
import hust.plane.utils.JsonUtils;
import hust.plane.utils.Upload;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "picture")
public class ImgController {
    private Logger logger = LoggerFactory.getLogger(ImgController.class);
    @Resource
    private TaskService taskService;

    @Value(value = "${TASK_DIR}")    //后台图片保存地址
    private String imgPath;

    @Value(value = "${BASE_IMAGE_URL}")
    private String uploadHost;    // 项目host路径


    @RequestMapping(value = "/{taskId}")
    public String toPicIndex(@PathVariable(value = "taskId") String taskId, Model model) {
       // String ImgFolder = taskService.selectImgFolderWithId(Integer.parseInt(taskId));
    	String ImgFolder = taskId;
        model.addAttribute("ImgFolder", ImgFolder);
        return "uploadFile";
    }

//    @RequestMapping(value = "/fileUpload/{taskDir}", method = RequestMethod.POST)
//    @ResponseBody
//    public void uploadSysHeadImg(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "taskDir") String taskDir) {
//        JSONObject jo = new JSONObject();
//        try {
////            imgPath = imgPath + "4taskFolder/";
//            MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) request;
//            Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
//            Client client = new Client();//实例化一个jersey
//            List<String> fileNameList = new ArrayList<>();
//            List<String> relaPathList = new ArrayList<>();
//            List<String> realPathList = new ArrayList<>();
//            for (MultipartFile pic : files.values()) {
//                String uploadInfo = Upload.upload(client, pic, request, response, uploadHost, imgPath);
//                if (!"".equals(uploadInfo)) {    //上传成功
//                    String[] infoList = uploadInfo.split(";");
//                    fileNameList.add(infoList[0]);    //文件名
//                    relaPathList.add(infoList[1]);    //相对路径
//                    realPathList.add(infoList[2]);    //真实完整路径
//                } else {    //上传失败
//                    fileNameList.add("");
//                    relaPathList.add("");
//                    realPathList.add("");
//                }
//            }
//            jo.put("success", 1);
//            jo.put("error", null);
//            jo.put("fileNameList", fileNameList);
//            jo.put("relaPathList", relaPathList);
//            jo.put("realPathList", realPathList);
//        } catch (Exception e) {
//            jo.put("success", 0);
//            jo.put("error", "上传失败");
//        }
//        JsonUtils.renderJson(response, jo);
//    }

    @RequestMapping(value = "uploadSysHeadImg/{taskDir}", method = RequestMethod.POST)
    @ResponseBody
    public void uploadSysHeadImg(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "taskDir") String taskDir) {
        JSONObject jo = new JSONObject();
        try {

            MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest)request;
            Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
            Client client = new Client();//实例化一个jersey
            List<String> fileNameList = new ArrayList<>();
            List<String> relaPathList = new ArrayList<>();
            List<String> realPathList = new ArrayList<>();

            // 这里新建文件夹，并授予权限,新建文件夹是可以成功的，但是赋予权限失败
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
                boolean mkdirs = file2.mkdirs();
                if(mkdirs == false){
                    System.out.println("任务基本文件夹创建失败！");
                }
            }

            File file3 = new File(sourceFileAddress.toString());
            if(!file3.exists())
            {
                boolean mkdirs = file3.mkdirs();
                if(mkdirs == false){
                    System.out.println("任务源文件夹创建失败！");
                }
            }

            File file4 = new File(alarmFileAddress.toString());
            if(!file4.exists())
            {
                boolean mkdirs = file4.mkdirs();
                if(mkdirs == false){
                    System.out.println("任务告警文件夹创建失败！");
                }
            }

            File basefile = new File(taskFileAddress.toString());   //如果文件夹存在的话，那么递归赋权限
            if(basefile.exists()){
                try {
                    Process proc = Runtime.getRuntime().exec("chmod -R 777 " + taskFileAddress.toString());
                    InputStream stderr = proc.getErrorStream();
                    InputStreamReader isr = new InputStreamReader(stderr);
                    BufferedReader br = new BufferedReader(isr);
                    String line = null;
                    while ((line = br.readLine()) != null)
                        System.out.println(line);
                    int exitVal = proc.waitFor();
                    System.out.println("Process exitValue: " + exitVal);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("任务文件夹及子文件夹权限授予失败！");
                }
            }


            for (MultipartFile pic : files.values()) {

                String uploadInfo = Upload.upload(client, pic, uploadHost,imgPath,taskDir);

                if (!"".equals(uploadInfo)) {    //上传成功
                    String[] infoList = uploadInfo.split(";");
                    fileNameList.add(infoList[0]);    //文件名
                    relaPathList.add(infoList[1]);    //相对路径
                    realPathList.add(infoList[2]);    //真实完整路径
                } else {    //上传失败
                    fileNameList.add("");
                    relaPathList.add("");
                    realPathList.add("");
                }
            }
            jo.put("success", 1);
            jo.put("error", null);
            jo.put("fileNameList", fileNameList);
            jo.put("relaPathList", relaPathList);
            jo.put("realPathList", realPathList);
        } catch (Exception e) {
            jo.put("success", 0);
            jo.put("error", "上传失败");
        }
        JsonUtils.renderJson(response,jo);
    }
}
