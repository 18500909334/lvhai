package com.ucap.zfw.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import com.ucap.restful.client.ResClientUtils;
import com.ucap.zfw.entity.Atach;
import com.ucap.zfw.entity.DataMigration;
import com.ucap.zfw.entity.Manuscript;
import com.ucap.zfw.service.DataMigrationService;
import com.ucap.zfw.util.DateUtil;
import com.ucap.zfw.util.Uuid;

@RestController
public class DataController {
	private static final String String = null;
	@Autowired
	private DataMigrationService dataMigrationService;
	@RequestMapping("/test")
	public String index() {
		
		System.out.println("12312312312312312");
		List<DataMigration> queryDataList = dataMigrationService.queryDataList();
		System.out.println(queryDataList.size());
		System.out.println(JSONArray.toJSON(queryDataList));
		
		return "index";
	}
	@RequestMapping("/addManuscript")
	public String   addManuscript() {
	
		System.out.println("===============迁移开始=====================");
		//List<DataMigration> queryDataList = dataMigrationService.queryDataList();
		List<Manuscript> manuscriptList = dataMigrationService.queryManuscriptList();
		System.out.println(JSONArray.toJSON(manuscriptList));
	    for (Manuscript manuscript : manuscriptList) {
	    	Map<String, String> map = new HashMap<String, String>();
	    	map.put("manuscriptId", Uuid.getUUID());
	    	map.put("title", manuscript.getTitle());
			map.put("subTitle", manuscript.getTitle());
	    	map.put("keyword", "");
			map.put("memo", "");
			map.put("channelId", "426d82c5689c4f4ea77f8d94f3c61a62");
			map.put("websiteId", "bfbb60a9a4ca4525a42aeb0c131d61df");
			map.put("userId", "9999999999999999");
			map.put("isPublish", "true");
			
			if(!StringUtils.isNullOrEmpty(manuscript.getPublishTime())){
				String publishTime = DateUtil.formatData(manuscript.getPublishTime());
				map.put("publishTime", publishTime);
				map.put("createdTime", publishTime);
			}else{
				map.put("publishTime", manuscript.getPublishTime());
				map.put("createdTime", manuscript.getPublishTime());
			}
			
		
			map.put("url", "");
			map.put("urlRule", "0");
			map.put("relevanceIds", "");
			if (manuscript.getContent() != null && !"".equals(manuscript.getContent())) {
				String content = replaceImgUrl(manuscript.getContent());
				map.put("content", content);
			} else {
				map.put("content", "<p></p>");
			}
			/*List<Atach> atachList = dataMigrationService.getAtachById("");
			for (Atach atach : atachList) {
				
			}*/
			map.put("meta_author", manuscript.getMeta_author());
			map.put("meta_laiyai", "");
			map.put("redirectUrl", "");
			map.put("spiderSourceId", "");
			map.put("spiderSourceName","");
			List<JSONObject> listJson = new ArrayList<JSONObject>();
			map.put("attachment", JSONArray.toJSON(listJson).toString());
			Object json = JSONObject.toJSON(map);
			/*String msg = ResClientUtils.sendPostManuscriptUrl("http://116.213.168.191:52002/website-webapp/rest/manuscript/addManuscript","demoCode", "abcd1234", map);
			JSONObject json2 = (JSONObject) JSONObject.toJSON(msg);
			Boolean status = json2.getBoolean("status");
			if(status==true){
				
			}else{
				System.out.println("推送失败的 稿件"+json);
			}*/
		}
		return "index";
	}
	/**
	 * 下载文件
	 * @param ms_q
	 * @throws IOException 
	 */
	@RequestMapping("/downloadFiles")
	public void downloadFiles() throws IOException {
		List<Atach> atachList = dataMigrationService.getAtachById("");
		for (Atach atach : atachList) {
			atach.getId();
			byte[] filedata = atach.getFiledata();
			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("F:\\excel\\file"));  
			//Writer writer = new OutputStreamWriter(new FileOutputStream(new File("")), "UTF-8");//创建输出流
			 outputStream.write(filedata);  
			 outputStream.flush();  
			 outputStream.close();
		}
		
	}
	public void downloadLocal(HttpServletResponse response) throws FileNotFoundException {
		// 下载本地文件https://www.cnblogs.com/rumengqiang/p/11156267.html
		String fileName = "Operator.doc".toString(); // 文件的默认保存名
		// 读到流中
		InputStream inStream = new FileInputStream("c:/Operator.doc");// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
		while ((len = inStream.read(b)) > 0)
		response.getOutputStream().write(b, 0, len);
		inStream.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
	}
	public static int getOccur(String src,String find){
	   int o = 0; 
	   int index=-1;
	    while((index=src.indexOf(find,index))>-1){  
		 ++index;  
		 ++o; 
	   } 
	 return o;
   }
	public static String replaceImgUrl(String con){
		 String tempstr = "";
		 String temp2 = "";
		 String temp3 = "";
		 String contentId = "";
		 String partId = "";
		int ijs = getOccur(con,"manageweb/edit.content.StreamOutPartsAction.action?");  
		for(int uu=1 ; uu<=ijs ; uu++){
			if (con.indexOf("manageweb/edit.content.StreamOutPartsAction.action?")>-1){
		    	tempstr = con.substring(0,con.indexOf("/manageweb/edit.content.StreamOutPartsAction.action?"));
				temp2 = con.substring(con.indexOf("/manageweb/edit.content.StreamOutPartsAction.action?"));
				/*System.out.println("====tempstr"+tempstr);
				System.out.println("====temp2"+temp2);*/
				contentId=temp2.substring(temp2.indexOf("contentId=")+10,temp2.indexOf("&amp;"));
				partId=temp2.substring(temp2.indexOf(";partId=")+8,temp2.indexOf("\""));
				temp3=temp2.substring(temp2.indexOf(partId)+partId.length());
				//System.out.println("contentId="+contentId);
				//http://nm.tobacco.gov.cn/serviceweb/n11045/c734630/part/143315.jpg
			/*	System.out.println("partId="+partId);
				System.out.println("temp3="+temp3);*/
				//System.out.println("partId="+partId);
				String type=".doc";
				
				if(tempstr.endsWith("src=\"")){
					type=".jpg";
				}else{
					type=".doc";
				}
				con=tempstr+"http://nm.tobacco.gov.cn/serviceweb/n11045/c"+contentId+"/part/"+partId+type+temp3;
			
			
		    }
		}
		if(ijs>0){
			System.out.println(con);
		
		}
		return con;
	}
}
