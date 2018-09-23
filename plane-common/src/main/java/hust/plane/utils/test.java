package hust.plane.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import hust.plane.utils.pojo.PlanePathVo;
import hust.plane.utils.pojo.RouteExcel;

public class test {
	
	@Test
	public void wrtitetest() throws Exception
	{
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("hello");
		XSSFRow row=sheet.createRow(0);
		XSSFCell cell=row.createCell(2);
		cell.setCellValue("hello");
		FileOutputStream out=new FileOutputStream(new File("E:\\hello.xlsx"));
		workbook.write(out);
		workbook.close();
		out.close();
	}
	
	@Test
    public void read07() throws Exception
    {
        //创建输入流
        FileInputStream fis = new FileInputStream(new File("E:\\hello.xlsx"));
        //由输入流得到工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        //得到工作表
        XSSFSheet sheet = workbook.getSheet("hello");
        //得到行,0表示第一行
        XSSFRow row = sheet.getRow(0);
        //创建单元格行号由row确定,列号作为参数传递给createCell;第一列从0开始计算
        XSSFCell cell = row.getCell(2);
        //给单元格赋值
        String cellValue = cell.getStringCellValue();
        System.out.println("C1的值是"+cellValue);
        int a[][] = new int[10][30];
        for(int i=0;i<a.length;i++)
        {
            System.out.println(i);
        }
        workbook.close();
        fis.close();
    }
	@Test
	public void test1()
	{
		List<RouteExcel> readExcellist = new ArrayList<RouteExcel>();
		RouteExcel routeExcel=new RouteExcel();
		routeExcel.setId(1.0);
		routeExcel.setLongitude(20.99);
		routeExcel.setLatitude(30.99);
		readExcellist.add(routeExcel);
		RouteExcel routeExcel2=new RouteExcel();
		routeExcel2.setId(2.0);
		routeExcel2.setLongitude(10.999999);
		routeExcel2.setLatitude(30.99);
		readExcellist.add(routeExcel2);
		StringBuffer s=new StringBuffer();
		s.append("LineString(");
		for(int i=0;i<readExcellist.size();i++)
		{
			Double a=readExcellist.get(i).getLongitude();
			Double b=readExcellist.get(i).getLatitude();
			String s1=a+" "+b+",";
			s.append(s1);
			
		}
		s.deleteCharAt(s.length()-1);
		s.append(")");
		System.out.println(s);
	}
	//测试kml文件
	@Test
	public void test2()
	{
		String path="E:\\hello.kml";
		List<PlanePathVo> plist = new ArrayList<PlanePathVo>();
		PlanePathVo vo= new PlanePathVo();
		vo.setLongitude(1.22222);
		vo.setLatitude(3.1111);
		vo.setHeight(1.2222);
		PlanePathVo vo1= new PlanePathVo();
		vo1.setLongitude(4.22222);
		vo1.setLatitude(6.1111);
		vo1.setHeight(5.2222);
		plist.add(vo);
		plist.add(vo1);
		KMLUtil.importKML(path, plist);
		
	}


	public static void main(String[] args) {
		
		
	}

}
