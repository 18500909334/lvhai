package com.ucap.zfw.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.ucap.zfw.util.Constant;
import com.ucap.zfw.util.DateUtil;
import com.ucap.zfw.util.ExcelTest;
import com.ucap.zfw.util.FileUtil;



@Component
@Configuration
@EnableScheduling
public class ScheduleToExcelTask {


	@Autowired
	@Qualifier("formalJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	private static final Logger log = LoggerFactory.getLogger(ScheduleToExcelTask.class);
	
	private static final String sql = "(TITLE LIKE '%复工%' or TITLE LIKE  '%复产%' "
		    + " or content LIKE '%复工%' or content LIKE '%复产%'"
		    + ")";
	
//	@Scheduled(cron = "0 0 14 * * ?")
	 //@Scheduled(cron="0/30 * *  * * ? ") 
	private void sendDataTasks() {

		log.info("开始执行15点定时任务");
		String currentdate = DateUtil.today();
		String yesterdayDate = DateUtil.yestoday();
		String todayFormat = DateUtil.todayFormat();
		String sql = " SELECT  LEFT (t.AREACODE, 2)  AS item ,t.ALLNAME  as id from base_area  t  where t.PID=0";
		log.info("查询sql语句：" + sql);
		List<Rowdata> list = jdbcTemplate.query(sql,new Rowdata());
		for (int i = 0; i < list.size(); i++) {
			Rowdata r = list.get(i);
			messageToExcel(currentdate,yesterdayDate,r.getItem(),r.getId());
		}
		try {
			System.out.println("=======压缩开始========");
			FileUtil.ZipCompress( Constant.fileParh+todayFormat+"/", "统计数据-"+Constant.fileParh+currentdate+".zip");
			log.info("压缩路径--"+Constant.fileParh);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void messageToExcel(String currentdate,String yesterdayDate,String area,String id) {
		String sql5 = "SELECT t.message_id AS msgID, DATE_FORMAT(t.message_create_time, '%Y-%m-%d')  AS createTime, t.area_name AS area, " + 
				" t.true_name AS fullName,t.phone AS tel,t.content AS msgContent  from message t LEFT JOIN classify c ON t.classify_id = c.id "+
				" WHERE " + 
				" t.message_create_time > '#yesterdayDate# 15:00' " + 
				" AND t.message_create_time <= '#currentdate# 14:00' " + 
				" AND category = '国务院互联网督查'  " + 
				" AND " + sql +
				" AND  c. NAME != '其他'"+
				" AND  NAME != '涉法涉诉' and c.parent_id !='40'"+
				" AND  deal_style is NULL "+
				" AND LEFT (area, 2) = #area#";
		sql5 = sql5.replace("#currentdate#", currentdate);
		sql5 = sql5.replace("#yesterdayDate#", yesterdayDate);
		sql5 = sql5.replace("#area#", area);
		
		log.info("查询sql5语句：" + sql5);
		List<MessageInfo> list = jdbcTemplate.query(sql5,new MessageInfo());
		try {
			ExcelTest.MesstoEcxel(list, id);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
