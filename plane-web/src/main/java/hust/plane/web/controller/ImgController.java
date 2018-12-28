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

    @Value(value = "${uploadHost}")
    private String FILE_UPLOAD_HOST;

    @Value(value = "${uploadLocalHost}")
    private String FILE_UPLOAD_Local_HOST;

    @Value(value = "${SERVER_NAME}")
    private String SERVER_NAME;


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

    @RequestMapping(value = "uploadSysHeadImg/{missionId}", method = RequestMethod.POST)
    @ResponseBody
    public void uploadSysHeadImg(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "missionId") String missionId) {
        JSONObject jo = new JSONObject();
        try {

            MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
            Client client = new Client();//实例化一个jersey
            List<String> fileNameList = new ArrayList<>();
            List<String> relaPathList = new ArrayList<>();
            List<String> realPathList = new ArrayList<>();

            for (MultipartFile pic : files.values()) {

                //采用内外跨域
                String uploadInfo = Upload.upload(client, pic, uploadHost, FILE_UPLOAD_Local_HOST, imgPath, missionId);
                //采用本机上传
                //String uploadInfo = Upload.upload(client, pic, uploadHost, FILE_UPLOAD_HOST, imgPath, taskDir);
                //  String uploadInfo = Upload.upload(client, pic, uploadHost,imgPath,taskDir);
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
        JsonUtils.renderJson(response, jo);
    }
}
