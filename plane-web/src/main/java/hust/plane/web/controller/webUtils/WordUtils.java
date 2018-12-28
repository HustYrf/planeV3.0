package hust.plane.web.controller.webUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

public class WordUtils {


    public static void exportMillCertificateWord(HttpServletRequest request, HttpServletResponse response, Map map, String filename) throws IOException {

        Configuration configuration = null;
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        configuration.setServletContextForTemplateLoading(request.getSession().getServletContext(), "/WEB-INF/ftl"); // FTL文件所存在的位置

        Template freemarkerTemplate = configuration.getTemplate("template.ftl");
        File file = null;
        InputStream fin = null;
        ServletOutputStream out = null;
        try {

            String realpath = request.getSession().getServletContext().getRealPath("/");
            // 调用工具类的createDoc方法生成Word文档
            file = createDoc(map, freemarkerTemplate, realpath + filename);

            fin = new FileInputStream(file);

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件名
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));

            out = response.getOutputStream();
            byte[] buffer = new byte[1024];  // 缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } finally {
            if (fin != null) fin.close();
            if (out != null) out.close();
            if (file != null) file.delete(); // 删除临时文件
        }
    }

    private static File createDoc(Map<?, ?> dataMap, Template template, String filename) {

        File f = new File(filename);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(fileOutputStream, "utf-8");
            template.process(dataMap, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return f;
    }

    public static String getRootPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        String rootPath = "";
        //windows下
        if ("\\".equals(File.separator)) {
            rootPath = path.substring(1, path.indexOf("/WEB-INF/classes"));
            rootPath = rootPath.replace("/", "\\");
        }
        //linux下
        if ("/".equals(File.separator)) {
            rootPath = path.substring(0, path.indexOf("/WEB-INF/classes"));
            rootPath = rootPath.replace("\\", "/");
        }
        return rootPath;
    }

}