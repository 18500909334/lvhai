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

@Component
@Configuration
@EnableScheduling
public class ScheduleTask {
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);
	
	private static final String sql = "(TITLE LIKE '%疫情%' or TITLE LIKE  '%非典%' or TITLE LIKE  '%肺炎%' or "
			+ "TITLE LIKE  '%冠状%' or TITLE LIKE  '%新冠%' or TITLE LIKE  '%收治%' or TITLE LIKE '%救命%' "
			+ " or TITLE LIKE '%救治%' or TITLE LIKE '%感染%' or TITLE LIKE '%确诊%' or TITLE LIKE '%住院%'"
			+ " or TITLE LIKE '%出院%' or TITLE LIKE '%疑似%' or TITLE LIKE '%隔离%' or TITLE LIKE '%疫区%'"
			+ " or TITLE LIKE '%死亡%' or TITLE LIKE '%去世%' or TITLE LIKE '%病逝%' or TITLE LIKE '%口罩%'"
			+ " or TITLE LIKE '%瞒报%' or TITLE LIKE '%方舱%' or TITLE LIKE '%神山%' or TITLE LIKE '%核酸%'"
			+ " or TITLE LIKE '%试剂%' or TITLE LIKE '%复产%' or TITLE LIKE '%复工%' or TITLE LIKE '%开工%'"
			+ " or TITLE LIKE '%返工%' or TITLE LIKE '%返岗%' or TITLE LIKE '%企业%' or TITLE LIKE '%个体户%'"
			+ " or TITLE LIKE '%个体工商户%' or TITLE LIKE '%资金%' or TITLE LIKE '%贷款%' or TITLE LIKE '%税收%'"
			+ " or TITLE LIKE '%减税%' or TITLE LIKE '%社保%' or TITLE LIKE '%原材料%' or TITLE LIKE '%订单%'"
			+ " or TITLE LIKE '%审批%' or TITLE LIKE '%货运司机%' or TITLE LIKE '%补贴%' or TITLE LIKE '%公积金%'"
			+ " or TITLE LIKE '%生活必需品%' or TITLE LIKE '%物价%' or TITLE LIKE '%绿色通道%' or TITLE LIKE '%分类精准防控%'"
			+ " or TITLE LIKE '%农民工%' or TITLE LIKE '%一刀切%' or TITLE LIKE '%物流%' or TITLE LIKE '%快递%'"
			+ " or TITLE LIKE '%运输%' or TITLE LIKE '%春耕%' or TITLE LIKE '%就业%' or TITLE LIKE '%毕业生%'"
			+ " or TITLE LIKE '%电价%' or TITLE LIKE '%租金%' or TITLE LIKE '%形式主义%' or TITLE LIKE '%外贸%'"
			+ " or TITLE LIKE '%外资%' or TITLE LIKE '%贴现%' or TITLE LIKE '%保供%' or TITLE LIKE '%纾困%'" + 
			" or content LIKE '%疫情%' or content LIKE '%非典%' or content LIKE '%肺炎%' or "
			+ " content LIKE '%冠状%' or content LIKE  '%新冠%' or content LIKE  '%收治%' or content LIKE  '%救命%' "
			+ " or content LIKE '%救治%' or content LIKE '%感染%' or content LIKE '%确诊%' or content LIKE '%住院%' "
			+ " or content LIKE '%出院%' or content LIKE '%疑似%' or content LIKE '%隔离%' or content LIKE '%疫区%' "
			+ " or content LIKE '%死亡%' or content LIKE '%去世%' or content LIKE '%病逝%' or content LIKE '%口罩%'"
			+ " or content LIKE '%瞒报%' or content LIKE '%方舱%' or content LIKE '%神山%' or content LIKE '%核酸%'"
			+ " or content LIKE '%试剂%' or content LIKE '%复产%' or content LIKE '%复工%' or content LIKE '%开工%'"
			+ " or content LIKE '%返工%' or content LIKE '%返岗%' or content LIKE '%企业%' or content LIKE '%个体户%'"
			+ " or content LIKE '%个体工商户%' or content LIKE '%资金%' or content LIKE '%贷款%' or content LIKE '%税收%'"
			+ " or content LIKE '%减税%' or content LIKE '%社保%' or content LIKE '%原材料%' or content LIKE '%订单%'"
			+ " or content LIKE '%审批%' or content LIKE '%货运司机%' or content LIKE '%补贴%' or content LIKE '%公积金%'"
			+ " or content LIKE '%生活必需品%' or content LIKE '%物价%' or content LIKE '%绿色通道%' or content LIKE '%分类精准防控%'"
			+ " or content LIKE '%农民工%' or content LIKE '%一刀切%' or content LIKE '%物流%' or content LIKE '%快递%'"
			+ " or content LIKE '%运输%' or content LIKE '%春耕%' or content LIKE '%就业%' or content LIKE '%毕业生%'"
			+ " or content LIKE '%电价%' or content LIKE '%租金%' or content LIKE '%形式主义%' or content LIKE '%外贸%'"
			+ " or content LIKE '%外资%' or content LIKE '%贴现%' or content LIKE '%保供%' or content LIKE '%纾困%'" + 
			")";
	
	//@Scheduled(cron = "0 0 0-6 * * ?")
	private void sendDataTasks1() {

		log.info("开始执行0点定时任务");
		String shortDateFormat = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(shortDateFormat);
		String currentdate = formatter.format(new Date());

		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,-24);
		String yesterdayDate=formatter.format(calendar.getTime());
			
		String filepath = "/data/home/apache-tomcat-7.0.82/webapps/testdata/";
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
 
		FileOutputStream fos = null;
		PrintWriter pw = null;

		// 文件路径
		File txtfile = new File(filepath + currentdate + "-1.txt");
		if (txtfile.exists() == false) {
			log.info("创建文件");
			try {
				txtfile.createNewFile();
				// 将文件读入输入流
				fis = new FileInputStream(txtfile);
				isr = new InputStreamReader(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

			StringBuffer buf = new StringBuffer();

			buf = buf.append("#1全渠道疫情相关留言总量");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql1 = "SELECT count(*)  FROM messageinfo t WHERE " + 
					"	createtime >= '2019-12-31 00:00' " + 
					"AND createtime < '#currentdate# 00:00' " + 
					"AND " + sql + ";";
			sql1 = sql1.replace("#currentdate#", currentdate);
			
			log.info("查询sql语句：" + sql1);
			String count1 = jdbcTemplate.queryForObject(sql1, String.class);
			
			log.info("查询结果：" + count1);
			buf = buf.append(count1);
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			
			buf = buf.append("#2督查渠道疫情相关留言总量");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql2 = "SELECT count(*) FROM messageinfo t WHERE" + 
					"	createtime >= '2019-12-31 00:00' " + 
					"AND createtime < '#currentdate# 00:00' " + 
					"AND category = '国务院互联网督查' " + 
					"AND " + sql + ";";
			sql2 = sql2.replace("#currentdate#", currentdate);
			
			log.info("查询sql语句：" + sql2);
			String count2 = jdbcTemplate.queryForObject(sql2, String.class);
			
			log.info("查询结果：" + count2);
			buf = buf.append(count2);
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			
			buf = buf.append("#3督查公告发出后，全渠道疫情相关留言总量");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql3 = "SELECT count(*) FROM messageinfo t WHERE " + 
					" createtime >= '2020-01-24 11:30' " + 
					"AND createtime < '#currentdate# 00:00' " + 
					"AND " + sql + ";";
			sql3 = sql3.replace("#currentdate#", currentdate);
			
			log.info("查询sql语句：" + sql3);
			String count3 = jdbcTemplate.queryForObject(sql3, String.class);
			
			log.info("查询结果：" + count3);
			buf = buf.append(count3);
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			
			buf = buf.append("#4督查公告发出后，督查渠道疫情相关留言总量");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql4 = "SELECT count(*) FROM messageinfo t WHERE " + 
					" createtime >= '2020-01-24 11:30' " + 
					"AND createtime < '#currentdate# 00:00' " + 
					"AND category = '国务院互联网督查' " + 
					"AND " + sql + ";";
			sql4 = sql4.replace("#currentdate#", currentdate);
			
			log.info("查询sql语句：" + sql4);
			String count4 = jdbcTemplate.queryForObject(sql4, String.class);
			
			log.info("查询结果：" + count4);
			buf = buf.append(count4);
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			
			buf = buf.append("#5督查渠道疫情相关留言地域分布（含省份、部委名称）和总量");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			buf = buf.append("省份");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql5 = "SELECT LEFT (area, 2) AS item, count(id) as id FROM messageinfo t " + 
					"WHERE " + 
					" createtime >= '2019-12-31 00:00' " + 
					"AND createtime < '#currentdate# 00:00' " + 
					"AND category = '国务院互联网督查'  " + 
					"AND " + sql +
					" AND addresstype = '1' " + 
					"GROUP BY item;";
			sql5 = sql5.replace("#currentdate#", currentdate);
			
			log.info("查询sql语句：" + sql5);
			List<Rowdata> list5 = jdbcTemplate.query(sql5,new Rowdata());
			for (int i = 0; i < list5.size(); i++) {
				Rowdata r = list5.get(i);
				buf = buf.append("| " + r.getItem() + "     |    " + r.getId());
				buf = buf.append("\r\n");
			}
			
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			buf = buf.append("部委");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql52 = "SELECT department as item, count(id) as id " + 
					"FROM messageinfo t WHERE " + 
					"	createtime >= '2019-12-31 00:00' " + 
					"AND createtime < '#currentdate# 00:00' " + 
					"AND category = '国务院互联网督查'  " + 
					"AND  " + sql +
					" AND addresstype = '2' " + 
					"GROUP BY item;";
			sql52 = sql52.replace("#currentdate#", currentdate);
			
			log.info("查询sql语句：" + sql52);
			List<Rowdata> list52 = jdbcTemplate.query(sql52,new Rowdata());
			for (int i = 0; i < list52.size(); i++) {
				Rowdata r = list52.get(i);
				buf = buf.append("| " + r.getItem() + "     |    " + r.getId());
				buf = buf.append("\r\n");
			}
			
			buf = buf.append(System.getProperty("line.separator"));
			
			buf = buf.append("#6昨日督查渠道疫情相关留言地域分布（含省份、部委名称）和总量");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			buf = buf.append("省份");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql6 = "SELECT LEFT (area, 2) AS item, count(id) as id FROM messageinfo t " + 
					"WHERE " + 
					" createtime >= '#yesterdayDate# 00:00' " + 
					"AND createtime < '#currentdate# 00:00' " + 
					"AND category = '国务院互联网督查'  " + 
					"AND " + sql + 
					" AND addresstype = '1' " + 
					"GROUP BY item;";
			sql6 = sql6.replace("#currentdate#", currentdate);
			sql6 = sql6.replace("#yesterdayDate#", yesterdayDate);
			
			log.info("查询sql语句：" + sql5);
			List<Rowdata> list6 = jdbcTemplate.query(sql6,new Rowdata());
			for (int i = 0; i < list6.size(); i++) {
				Rowdata r = list6.get(i);
				buf = buf.append("| " + r.getItem() + "     |    " + r.getId());
				buf = buf.append("\r\n");
			}
			
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			buf = buf.append("部委");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql62 = "SELECT department as item, count(id) as id " + 
					"FROM messageinfo t WHERE " + 
					" createtime >= '#yesterdayDate# 00:00' " + 
					"AND createtime < '#currentdate# 00:00' " + 
					"AND category = '国务院互联网督查'  " + 
					"AND  " + sql + 
					" AND addresstype = '2' " + 
					"GROUP BY item;";
			sql62 = sql62.replace("#currentdate#", currentdate);
			sql62 = sql62.replace("#yesterdayDate#", yesterdayDate);
			
			log.info("查询sql语句：" + sql62);
			List<Rowdata> list62 = jdbcTemplate.query(sql62,new Rowdata());
			for (int i = 0; i < list62.size(); i++) {
				Rowdata r = list62.get(i);
				buf = buf.append("| " + r.getItem() + "     |    " + r.getId());
				buf = buf.append("\r\n");
			}
			
			buf = buf.append(System.getProperty("line.separator"));
			
			
			buf = buf.append("7累计疫情相关留言地域分布（含省份、部委名称）和总量");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			buf = buf.append("省份");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql7 = "SELECT LEFT (area, 2) AS item, count(id) as id FROM messageinfo t " + 
					"WHERE " + 
					" createtime >= '2019-12-31 00:00' " + 
					"AND createtime < '#currentdate# 00:00' " + 
					"AND " + sql + 
					" AND addresstype = '1' " + 
					"GROUP BY item;";
			sql7 = sql7.replace("#currentdate#", currentdate);
			
			log.info("查询sql语句：" + sql7);
			List<Rowdata> list7 = jdbcTemplate.query(sql7,new Rowdata());
			for (int i = 0; i < list7.size(); i++) {
				Rowdata r = list7.get(i);
				buf = buf.append("| " + r.getItem() + "     |    " + r.getId());
				buf = buf.append("\r\n");
			}
			
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			buf = buf.append("部委");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql72 = "SELECT department as item, count(id) as id " + 
					"FROM messageinfo t WHERE " + 
					"	createtime >= '2019-12-31 00:00' " + 
					"AND createtime < '#currentdate# 00:00' " + 
					"AND  " + sql + 
					" AND addresstype = '2' " + 
					"GROUP BY item;";
			sql72 = sql72.replace("#currentdate#", currentdate);
			
			log.info("查询sql语句：" + sql72);
			List<Rowdata> list72 = jdbcTemplate.query(sql72,new Rowdata());
			for (int i = 0; i < list72.size(); i++) {
				Rowdata r = list72.get(i);
				buf = buf.append("| " + r.getItem() + "     |    " + r.getId());
				buf = buf.append("\r\n");
			}
			
			buf = buf.append(System.getProperty("line.separator"));
			
			buf = buf.append("8昨日疫情相关留言地域分布（含省份、部委名称）和总量");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			buf = buf.append("省份");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql8 = "SELECT LEFT (area, 2) AS item, count(id) as id FROM messageinfo t " + 
					"WHERE " + 
					" createtime >= '#yesterdayDate# 00:00' " + 
					"AND createtime < '#currentdate# 00:00' " + 
					"AND " + sql + 
					" AND addresstype = '1' " + 
					"GROUP BY item;";
			sql8 = sql8.replace("#currentdate#", currentdate);
			sql8 = sql8.replace("#yesterdayDate#", yesterdayDate);
			
			log.info("查询sql语句：" + sql8);
			List<Rowdata> list8 = jdbcTemplate.query(sql8,new Rowdata());
			for (int i = 0; i < list8.size(); i++) {
				Rowdata r = list8.get(i);
				buf = buf.append("| " + r.getItem() + "     |    " + r.getId());
				buf = buf.append("\r\n");
			}
			
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			buf = buf.append("部委");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql82 = "SELECT department as item, count(id) as id " + 
					"FROM messageinfo t WHERE " + 
					" createtime >= '#yesterdayDate# 00:00' " + 
					"AND createtime < '#currentdate# 00:00' " + 
					"AND  " + sql + 
					" AND addresstype = '2' " + 
					"GROUP BY item;";
			sql82 = sql82.replace("#currentdate#", currentdate);
			sql82 = sql82.replace("#yesterdayDate#", yesterdayDate);
			
			log.info("查询sql语句：" + sql82);
			List<Rowdata> list82 = jdbcTemplate.query(sql82,new Rowdata());
			for (int i = 0; i < list82.size(); i++) {
				Rowdata r = list82.get(i);
				buf = buf.append("| " + r.getItem() + "     |    " + r.getId());
				buf = buf.append("\r\n");
			}
			
			buf = buf.append(System.getProperty("line.separator"));
			
			try {
				fos = new FileOutputStream(txtfile);
				pw = new PrintWriter(fos);
				pw.write(buf.toString().toCharArray());
				pw.flush();
				
				log.info("发送邮件！");
				sentEmail(buf.toString());
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
	}
	
	//@Scheduled(cron = "0 0,5,10,15,20,25 19 * * ?")
	private void sendDataTasks2() {

		log.info("开始执行19点定时任务");
		String shortDateFormat = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(shortDateFormat);
		String currentdate = formatter.format(new Date());
			
		String filepath = "/data/home/apache-tomcat-7.0.82/webapps/testdata/";
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
 
		FileOutputStream fos = null;
		PrintWriter pw = null;

		// 文件路径
		File txtfile = new File(filepath + currentdate + "-2.txt");
		if (txtfile.exists() == false) {
			log.info("创建文件");
			try {
				txtfile.createNewFile();
				// 将文件读入输入流
				fis = new FileInputStream(txtfile);
				isr = new InputStreamReader(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

			StringBuffer buf = new StringBuffer();

			buf = buf.append("#1督查公告发出后，全渠道疫情相关留言总量");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql1 = "SELECT count(*)  FROM messageinfo t WHERE " + 
					"	createtime >= '2020-01-24 11:30' " + 
					"AND createtime < '#currentdate# 19:00' " + 
					"AND " + sql + ";";
			sql1 = sql1.replace("#currentdate#", currentdate);
			
			log.info("查询sql语句：" + sql1);
			String count1 = jdbcTemplate.queryForObject(sql1, String.class);
			
			log.info("查询结果：" + count1);
			buf = buf.append(count1);
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			
			buf = buf.append("#2督查公告发出后，督查渠道疫情相关留言总量");
			buf = buf.append(System.getProperty("line.separator"));

			String sql2 = "SELECT count(*)  FROM messageinfo t WHERE " + 
					"	createtime >= '2020-01-24 11:30' " + 
					"AND createtime < '#currentdate# 19:00' " + 
					"AND category = '国务院互联网督查' " + 
					"AND " + sql + ";";
			sql2 = sql2.replace("#currentdate#", currentdate);
			
			log.info("查询sql语句：" + sql2);
			String count2 = jdbcTemplate.queryForObject(sql2, String.class);
			
			log.info("查询结果：" + count2);
			buf = buf.append(count2);
			
			buf = buf.append(System.getProperty("line.separator"));
			
			try {
				fos = new FileOutputStream(txtfile);
				pw = new PrintWriter(fos);
				pw.write(buf.toString().toCharArray());
				pw.flush();
				
				log.info("发送邮件！");
				sentEmail(buf.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
	}
	
	//@Scheduled(cron = "0 0,5,10,15,20,25 23 * * ?")
	private void sendDataTasks3() {

		log.info("开始执行23点定时任务");
		String shortDateFormat = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(shortDateFormat);
		String currentdate = formatter.format(new Date());
			
		String filepath = "/data/home/apache-tomcat-7.0.82/webapps/testdata/";
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
 
		FileOutputStream fos = null;
		PrintWriter pw = null;

		// 文件路径
		File txtfile = new File(filepath + currentdate + "-3.txt");
		if (txtfile.exists() == false) {
			log.info("创建文件");
			try {
				txtfile.createNewFile();
				// 将文件读入输入流
				fis = new FileInputStream(txtfile);
				isr = new InputStreamReader(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

			StringBuffer buf = new StringBuffer();

			buf = buf.append("#1昨日疫情相关留言地域分布（含省份、部委名称）和总量");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			buf = buf.append("省份");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql11 = "SELECT LEFT (area, 2) AS item, count(id) as id FROM messageinfo t " + 
					"WHERE " + 
					"createtime >= '#currentdate# 00:00' " + 
					"AND createtime < '#currentdate# 23:00' " + 
					"AND " + sql +
					" AND addresstype = '1' " + 
					"GROUP BY item;";
			sql11 = sql11.replace("#currentdate#", currentdate);
			
			log.info("查询sql语句：" + sql11);
			List<Rowdata> list5 = jdbcTemplate.query(sql11,new Rowdata());
			for (int i = 0; i < list5.size(); i++) {
				Rowdata r = list5.get(i);
				buf = buf.append("| " + r.getItem() + "     |    " + r.getId());
				buf = buf.append("\r\n");
			}
			
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");
			buf = buf.append("部委");
			buf = buf.append(System.getProperty("line.separator"));
			buf = buf.append("\r\n");

			String sql12 = "SELECT department as item, count(id) as id " + 
					"FROM messageinfo t WHERE " + 
					"createtime >= '#currentdate# 00:00' " + 
					"AND createtime < '#currentdate# 23:00' " + 
					"AND  " + sql +
					" AND addresstype = '2' " + 
					"GROUP BY item;";
			sql12 = sql12.replace("#currentdate#", currentdate);
			
			log.info("查询sql语句：" + sql12);
			List<Rowdata> list52 = jdbcTemplate.query(sql12,new Rowdata());
			for (int i = 0; i < list52.size(); i++) {
				Rowdata r = list52.get(i);
				buf = buf.append("| " + r.getItem() + "     |    " + r.getId());
				buf = buf.append("\r\n");
			}
			
			buf = buf.append(System.getProperty("line.separator"));
			
			try {
				fos = new FileOutputStream(txtfile);
				pw = new PrintWriter(fos);
				pw.write(buf.toString().toCharArray());
				pw.flush();
				
				log.info("发送邮件！");
				sentEmail(buf.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
	}
	
	private void sentEmail(String content) {
		
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
       try {

            URIBuilder uri = new URIBuilder("http://10.128.0.218/message/api/sendMail");
            // 设置post请求参数
            List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
            parameters.add(new BasicNameValuePair("email", "hesy@ucap.com.cn"));
            parameters.add(new BasicNameValuePair("content", content));
            parameters.add(new BasicNameValuePair("subject", "数据统计"));
            uri.setParameters(parameters);
        	
        	HttpGet httpGet = new HttpGet(uri.build());

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应体
                String content2 = EntityUtils.toString(response.getEntity(), "UTF-8");
                log.info("发送结果:" + content2);
            }
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
            if (response != null) {
                try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            // 关闭浏览器
            try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	private void sentEmailpost(String content) {
		
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST请求，访问开源中国
        HttpPost httpPost = new HttpPost("http://10.128.0.218/message/api/sendMail");

        // 设置post请求参数
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("email", "hesy@ucap.com.cn"));
        parameters.add(new BasicNameValuePair("content", content));
        parameters.add(new BasicNameValuePair("subject", "数据统计"));
        CloseableHttpResponse response = null;
        try {
//            // 构造一个form表单式的实体
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
             // 将请求实体设置到httpPost对象中
//            formEntity.setContentType("application/json");
//            StringEntity paramEntity = new UrlEncodedFormEntity(parameters,"utf-8");
            httpPost.setEntity(formEntity);

            // 执行请求
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应体
                String content2 = EntityUtils.toString(response.getEntity(), "UTF-8");
                log.info("发送结果:" + content2);
            }
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (response != null) {
                try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            // 关闭浏览器
            try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	private void sentEmail2(String content) {
		
		String url = "http://10.128.0.218/message/api/sendMail";
        JSONObject postData = new JSONObject();
		postData.put("email", "hesy@ucap.com.cn");
		postData.put("content", content);
		postData.put("subject", "统计结果");
		
		String jsonString = JSONObject.toJSONString(postData);
		
//		log.info("post msg:" + jsonString);
		
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //设置请求发送方式
        HttpMethod method = HttpMethod.GET;
       
        HttpEntity<String> entity = new HttpEntity<String>(jsonString, headers);
        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(url,method, entity, JSONObject.class);

        String rtnjson = responseEntity.getBody().toJSONString();
        log.info("发送结果:" + rtnjson);
	}
	
}
