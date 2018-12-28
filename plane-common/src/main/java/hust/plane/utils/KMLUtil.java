package hust.plane.utils;

import hust.plane.utils.pojo.PlanePathVo;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

//生成KML文件
public class KMLUtil {

    //解析planepath数据库text的数据,装换成数据
    public static List<PlanePathVo> textToList(String path, String s) {
        ArrayList<ArrayList<Double>> pathList = LineUtil.stringLineToList(path);
        List<PlanePathVo> pList = new ArrayList<PlanePathVo>();
        String sList[] = s.split(",");
        for (int i = 0; i < sList.length; i++) {
            PlanePathVo vo = new PlanePathVo();
            double height = Double.parseDouble(sList[i]);
            double longitude = pathList.get(i).get(0);
            double latitude = pathList.get(i).get(1);
            vo.setHeight(height);
            vo.setLongitude(longitude);
            vo.setLatitude(latitude);
            pList.add(vo);
        }
        return pList.size() > 0 ? pList : null;
    }

    //将飞行路径数据生成kml文件
    public static void importKML(String filePath, List<PlanePathVo> plist) {
        File file = new File(filePath);
        Element root = DocumentHelper.createElement("kml");//创建根节点kml;
        Document document = DocumentHelper.createDocument(root);
        document.setXMLEncoding("UTF-8");
        //给根节点kml添加属性
        root.addNamespace("xmlns", "http://www.opengis.net/kml/2.2");
        root.addNamespace("gx", "http://www.google.com/kml/ext/2.2");
        //给根节点kml添加子节点
        Element documentElement = root.addElement("Document");
        Element folderDe = documentElement.addElement("Folder");
        Element PlacemarkDe = folderDe.addElement("Placemark");
        Element styleE = PlacemarkDe.addElement("Style");
        Element LineStyle = styleE.addElement("LineStyle");
        LineStyle.addElement("color").addText("ed0000ff");
        LineStyle.addElement("width").addText("5");
        Element trackE = PlacemarkDe.addElement("gx:Track");
        for (PlanePathVo pvo : plist) {
            trackE.addElement("gx:coord").addText(pvo.getLongitude() + " " + pvo.getLatitude() + " " + pvo.getHeight());
        }
        //将文件写出
        try {
            Writer fileWriter = new FileWriter(filePath);
            OutputFormat format = new OutputFormat();
            format.setEncoding("UTF-8");
            format.setNewlines(true);
            format.setIndent(true);
            XMLWriter xmlWriter = new XMLWriter(fileWriter, format);
            xmlWriter.write(document);
            xmlWriter.flush();
            xmlWriter.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
