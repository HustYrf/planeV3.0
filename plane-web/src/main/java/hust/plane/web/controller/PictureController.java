//package hust.plane.web.controller;
//
//
//import hust.plane.constant.MultipartFileParam;
//import hust.plane.constant.WebConst;
//import hust.plane.service.interFace.StorageService;
//import hust.plane.service.interFace.TaskService;
//import hust.plane.web.controller.vo.ResultStatus;
//import hust.plane.web.controller.vo.ResultVo;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.io.FileUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//
//@Controller
//@RequestMapping(value = "/picture")
//public class PictureController {
//    private Logger logger = LoggerFactory.getLogger(PictureController.class);
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private StorageService storageService;
//
//    @Resource
//    private TaskService taskService;
//
//    @RequestMapping(value = "/{taskId}")
//    public String toPicIndex(@PathVariable(value = "taskId")String taskId, Model model){
//        String ImgFolder = taskService.selectImgFolderWithId(Integer.parseInt(taskId));
//        model.addAttribute("ImgFolder",ImgFolder);
//        return "uploadFile";
//    }
//
//    /**
//     * 秒传判断，断点判断
//     *
//     * @return
//     */
//    @RequestMapping(value = "checkFileMd5", method = RequestMethod.POST)
//    @ResponseBody
//    public Object checkFileMd5(String md5) throws IOException {
//        Object processingObj = stringRedisTemplate.opsForHash().get(WebConst.FILE_UPLOAD_STATUS, md5);
//        if (processingObj == null) {
//            return new ResultVo(ResultStatus.NO_HAVE);
//        }
//        String processingStr = processingObj.toString();
//        boolean processing = Boolean.parseBoolean(processingStr);
//        String value = stringRedisTemplate.opsForValue().get(WebConst.FILE_MD5_KEY + md5);
//        if (processing) {
//            return new ResultVo(ResultStatus.IS_HAVE, value);
//        } else {
//            File confFile = new File(value);
//            byte[] completeList = FileUtils.readFileToByteArray(confFile);
//            List<String> missChunkList = new LinkedList<>();
//            for (int i = 0; i < completeList.length; i++) {
//                if (completeList[i] != Byte.MAX_VALUE) {
//                    missChunkList.add(i + "");
//                }
//            }
//            return new ResultVo<>(ResultStatus.ING_HAVE, missChunkList);
//        }
//    }
//
//    /**
//     * 上传文件
//     *
//     * @param param
//     * @param request
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "/fileUpload/{taskDir}", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity fileUpload(@PathVariable(value = "taskDir") String taskDir, MultipartFileParam param, HttpServletRequest request) {
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//        if (isMultipart) {
//            logger.info("上传文件start。");
//            try {
//                // 方法1
//                //storageService.uploadFileRandomAccessFile(param);
//                // 方法2 这个更快点
//                storageService.uploadFileByMappedByteBuffer(param,taskDir);
//            } catch (IOException e) {
//                e.printStackTrace();
//                logger.error("文件上传失败。{}", param.toString());
//                return ResponseEntity.ok().body("上传失败，请重新上传。");
//            }
//            logger.info("上传文件end。");
//        }
//        return ResponseEntity.ok().body("上传成功。");
//
//    }
//}