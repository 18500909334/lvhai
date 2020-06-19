package com.ucap.zfw.util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;








import com.ucap.zfw.task.MessageInfo;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelTest {
	private static final List<File> files=new ArrayList<File>();
	
	public static void main(String args[]) throws IOException,
	RowsExceededException, WriteException {
	List<MessageInfo> models = new ArrayList<MessageInfo>();
	for (int i = 0; i < 10; i++) {
		MessageInfo model = new MessageInfo();
		model.setMsgID("name" + i);
		model.setAddress("pwd" + i);
		models.add(model);
	}

	File f = new File("E:/turan.xls");

	WritableWorkbook workbook = Workbook.createWorkbook(f);
	WritableSheet sheet = workbook.createSheet("sheet1", 0);


	sheet.mergeCells(0, 0, 6, 0);
	sheet.setRowView(0, 1200);
	WritableFont wf = new WritableFont(WritableFont.TIMES, 18,
	WritableFont.BOLD, true, UnderlineStyle.NO_UNDERLINE,
	Colour.RED);
	WritableCellFormat wcf = new WritableCellFormat(wf);
	Label lab = null;
	lab = new Label(0, 0, "Turan test");
	sheet.addCell(lab);
	for (int i = 0; i < models.size(); i++) {
		MessageInfo tmp = models.get(i);
		lab = new Label(0, i + 1, tmp.getMsgID());
		sheet.addCell(lab);
		lab = new Label(1, i + 1, tmp.getAddress());
		sheet.addCell(lab);
	}
	workbook.write();
	workbook.close();

	}
	public static void MesstoEcxel(List<MessageInfo> models,String name) throws IOException, RowsExceededException, WriteException{
		String filepath = Constant.fileParh+DateUtil.todayFormat();
		File dirname = new File(filepath); 
		if (!dirname.isDirectory()) 
		{ //目录不存在 
		     dirname.mkdir(); //创建目录
		}
		File f = new File(filepath+"/"+name+"-"+models.size()+"条.xls");


		WritableWorkbook workbook = Workbook.createWorkbook(f);
		WritableSheet sheet = workbook.createSheet("sheet1", 0);
		//sheet.mergeCells(0, 0, 1, 0);
		WritableFont wf = new WritableFont(WritableFont.TIMES, 12,
		WritableFont.BOLD, true, UnderlineStyle.NO_UNDERLINE,
		Colour.RED);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		Label lab = null;
		lab = new Label(0, 0, "序号");
		sheet.addCell(lab);
		lab = new Label(1, 0, "办件编号");
		sheet.addCell(lab);
		lab = new Label(2, 0, "问题归属地");
		sheet.addCell(lab);
		lab = new Label(3, 0, "留言人姓名");
		sheet.addCell(lab);
		lab = new Label(4, 0, "留言时间");
		sheet.addCell(lab);
		lab = new Label(5, 0, "留言人电话");
		sheet.addCell(lab);
		lab = new Label(6, 0, "留言正文");
		sheet.addCell(lab);
		for (int i = 0; i < models.size(); i++) {
			MessageInfo tmp = models.get(i);
			System.out.println("======"+tmp.getMsgID());
			String j = String.valueOf(i+1);
			lab = new Label(0, i + 1, j);
			sheet.addCell(lab);
			lab = new Label(1, i + 1, tmp.getMsgID());
			sheet.addCell(lab);
			lab = new Label(2, i + 1, tmp.getArea());
			sheet.addCell(lab);
			lab = new Label(3, i + 1, tmp.getFullName());
			sheet.addCell(lab);
			lab = new Label(4, i + 1, tmp.getCreateTime());
			sheet.addCell(lab);
			lab = new Label(5, i + 1, tmp.getTel());
			sheet.addCell(lab);
			lab = new Label(6, i + 1, tmp.getMsgContent());
			sheet.addCell(lab);
	   }
		workbook.write();
		workbook.close();
		System.out.println("game over");
	}
	
	public static void MesstoAllEcxel(List<MessageInfo> models,String name) throws IOException, RowsExceededException, WriteException{
		//List<MessageInfo> models = new ArrayList<MessageInfo>();
		/*for (int i = 0; i < 10; i++) {
			MessageInfo model = new MessageInfo();
		model.setMsgID("name" + i);
		model.setAddress("pwd" + i);
		models.add(model);
		}*/


		File f = new File("E:/toExcel/"+name+"-"+models.size()+"条.xls");


		WritableWorkbook workbook = Workbook.createWorkbook(f);
		WritableSheet sheet = workbook.createSheet("sheet1", 0);


		//sheet.mergeCells(0, 0, 1, 0);
		WritableFont wf = new WritableFont(WritableFont.TIMES, 12,
		WritableFont.BOLD, true, UnderlineStyle.NO_UNDERLINE,
		Colour.RED);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		Label lab = null;
		lab = new Label(0, 0, "序号");
		sheet.addCell(lab);
		lab = new Label(1, 0, "办件编号");
		sheet.addCell(lab);
		lab = new Label(2, 0, "问题归属地");
		sheet.addCell(lab);
		lab = new Label(3, 0, "留言人姓名");
		sheet.addCell(lab);
		lab = new Label(4, 0, "留言时间");
		sheet.addCell(lab);
		lab = new Label(5, 0, "留言人电话");
		sheet.addCell(lab);
		lab = new Label(6, 0, "留言正文");
		sheet.addCell(lab);
		lab = new Label(7, 0, "标题");
		sheet.addCell(lab);
		lab = new Label(8, 0, "昵称");
		sheet.addCell(lab);
		for (int i = 0; i < models.size(); i++) {
			MessageInfo tmp = models.get(i);
			System.out.println("======"+tmp.getMsgID());
			String j = String.valueOf(i+1);
		lab = new Label(0, i + 1, j);
		sheet.addCell(lab);
		lab = new Label(1, i + 1, tmp.getMsgID());
		sheet.addCell(lab);
		lab = new Label(2, i + 1, tmp.getArea());
		sheet.addCell(lab);
		lab = new Label(3, i + 1, tmp.getFullName());
		sheet.addCell(lab);
		lab = new Label(4, i + 1, tmp.getCreateTime());
		sheet.addCell(lab);
		lab = new Label(5, i + 1, tmp.getTel());
		sheet.addCell(lab);
		lab = new Label(6, i + 1, tmp.getMsgContent());
		sheet.addCell(lab);
		lab = new Label(7, i + 1, tmp.getMsgTitle());
		sheet.addCell(lab);
		lab = new Label(8, i + 1, tmp.getNickname());
		sheet.addCell(lab);
	}
		workbook.write();
		workbook.close();
		System.out.println("game over");
	}
	public static void MesstoEcxelNow(List<MessageInfo> models,String name) throws IOException, RowsExceededException, WriteException{
		//List<MessageInfo> models = new ArrayList<MessageInfo>();
		/*for (int i = 0; i < 10; i++) {
			MessageInfo model = new MessageInfo();
		model.setMsgID("name" + i);
		model.setAddress("pwd" + i);
		models.add(model);
		}*/

		
		File f = new File("F:/excel/-"+name+".xls");


		WritableWorkbook workbook = Workbook.createWorkbook(f);
		WritableSheet sheet = workbook.createSheet("sheet1", 0);


		//sheet.mergeCells(0, 0, 1, 0);
		WritableFont wf = new WritableFont(WritableFont.TIMES, 12,
		WritableFont.BOLD, true, UnderlineStyle.NO_UNDERLINE,
		Colour.RED);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		
		Label lab = null;
		lab = new Label(0, 0, "办件编号");
		sheet.addCell(lab);
		lab = new Label(1, 0, "IP地址");
		sheet.addCell(lab);
		lab = new Label(2, 0, "发表时间");
		sheet.addCell(lab);
		lab = new Label(3, 0, "昵称");
		sheet.addCell(lab);
		lab = new Label(4, 0, "姓名");
		sheet.addCell(lab);
		lab = new Label(5, 0, "身份证号码");
		/*#办件编号	IP地址	发表时间	昵称	姓名	身份证号码	电话	邮箱	职业	地址	类别	主题	分类	部门	标题	内容*/
		sheet.addCell(lab);
		lab = new Label(6, 0, "电话");
		sheet.addCell(lab);
		lab = new Label(7, 0, "邮箱");
		sheet.addCell(lab);
		lab = new Label(8, 0, "职业");
		sheet.addCell(lab);
		lab = new Label(9, 0, "地址");
		sheet.addCell(lab);
		lab = new Label(10, 0, "类别");
		sheet.addCell(lab);
		lab = new Label(11, 0, "主题");
		sheet.addCell(lab);
		lab = new Label(12, 0, "分类");
		sheet.addCell(lab);
		lab = new Label(13, 0, "部门");
		sheet.addCell(lab);
		lab = new Label(14, 0, "标题");
		sheet.addCell(lab);
		lab = new Label(15, 0, "内容");
		sheet.addCell(lab);
		for (int i = 0; i < models.size(); i++) {
			MessageInfo tmp = models.get(i);
			System.out.println("======"+tmp.getMsgID());
			String j = String.valueOf(i+1);
		lab = new Label(0, i + 1, tmp.getMsgID());
		sheet.addCell(lab);
		lab = new Label(1, i + 1, tmp.getMsgIP());
		sheet.addCell(lab);
		lab = new Label(2, i + 1, tmp.getCreateTime());
		sheet.addCell(lab);
		lab = new Label(3, i + 1, tmp.getNickname());
		sheet.addCell(lab);
		lab = new Label(4, i + 1, tmp.getFullName());
		sheet.addCell(lab);
		lab = new Label(5, i + 1, tmp.getCerCode());
		sheet.addCell(lab);
		lab = new Label(6, i + 1, tmp.getTel());
		sheet.addCell(lab);
		lab = new Label(7, i + 1, tmp.getEmail());
		sheet.addCell(lab);
		lab = new Label(8, i + 1, tmp.getTelAttribution());
		sheet.addCell(lab);
		lab = new Label(9, i + 1, tmp.getArea());
		sheet.addCell(lab);
		lab = new Label(10, i + 1, tmp.getCategory());
		sheet.addCell(lab);
		lab = new Label(11, i + 1, tmp.getTopic());
		sheet.addCell(lab);
		lab = new Label(12, i + 1, tmp.getMsgClassify());
		sheet.addCell(lab);
		lab = new Label(13, i + 1, tmp.getDeptName());
		sheet.addCell(lab);
		lab = new Label(14, i + 1, tmp.getMsgTitle());
		sheet.addCell(lab);
		lab = new Label(15, i + 1, tmp.getMsgContent());
		sheet.addCell(lab);
	
	}
		workbook.write();
		workbook.close();
		System.out.println("game over");
	}
}