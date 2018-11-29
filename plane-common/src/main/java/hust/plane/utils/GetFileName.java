package hust.plane.utils;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GetFileName
 * @Descripition TODO
 * @Author Administrator
 * @Date 2018/11/7 15:19
 **/
public class GetFileName {

    // 如果文件夹为空的话就会产生空指针问题
    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        if(!file.exists()){
            return null;
        }
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
              files.add(tempList[i].getName());
            }
        }
        return files;
    }

//    public static void main(String[] args) {
//        System.out.println(JsonUtils.objectToJson(getFiles("D:\\pic1")));
//    }
}
