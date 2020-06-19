package com.ucap.zfw.util;
import java.io.BufferedOutputStream;  
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.net.HttpURLConnection;  
import java.net.URL;  
import java.net.URLConnection;  
import java.util.Date;
import java.util.HashMap;
import java.util.Map;  
import java.util.Map.Entry; 

import com.alibaba.fastjson.JSONObject;




public class HttpUtil {
	public static String get(String urlStr) throws Exception  
    {  
  
        URL url = new URL(urlStr);  
        URLConnection urlConnection = url.openConnection(); // 打开连接  
  
        System.out.println(urlConnection.getURL().toString());  
  
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8")); // 获取输入流  
        String line = null;  
        StringBuilder sb = new StringBuilder();  
        while ((line = br.readLine()) != null)  
        {  
            sb.append(line + "\n");  
        }  
        br.close();  
        System.out.println(sb.toString());  
       
        return sb.toString();
    }  
  
    /** 
     * 使用HttpURLConnection实现POST请求 
     *  
     * 1.实例化一个java.net.URL对象； 2.通过URL对象的openConnection()方法得到一个java.net.URLConnection; 
     * 3.通过URLConnection对象的getOutputStream()方法获得输出流； 4.向输出流中写数据； 5.关闭资源； 
     */  
    public static String post(String urlStr, String parameter) throws IOException  
    {  
  
        URL url = new URL(urlStr);  
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();  
        
        httpURLConnection.setDoInput(true);  
        httpURLConnection.setDoOutput(true); // 设置该连接是可以输出的  
        httpURLConnection.setRequestMethod("POST"); // 设置请求方式  
        httpURLConnection.setRequestProperty("charset", "utf-8");  
        httpURLConnection.setRequestProperty("Content-Type", "application/json");  
        System.out.println(httpURLConnection.getURL().toString());  
  
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(httpURLConnection.getOutputStream()));  
          
      /*  StringBuffer parameter = new StringBuffer();  
        parameter.append("1=1");  
        for (Entry<String, String> entry : parameterMap.entrySet())  
        {  
            parameter.append("&" + entry.getKey() + "=" + entry.getValue());  
        } */ 
        pw.write(parameter);// 向连接中写数据（相当于发送数据给服务器）  
          
        pw.flush();  
        pw.close();  
  
        System.out.println("parameter: " + parameter.toString());  
  
        BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));  
        String line = null;  
        StringBuilder sb = new StringBuilder();  
        while ((line = br.readLine()) != null)  
        { // 读取数据  
            sb.append(line + "\n");  
        }  
        br.close();  
        System.out.println(sb.toString());  
        return sb.toString();
    }  
    
    public static void main(String[] args) throws Exception {
    	String urlStr = "http://my-api-test.app.xinhuanet.com/h5/partner/info";
		//String param = "key=2020207&timestamp=1581070023414&sign=ad9047fefc02157b9501a45cb1d2928a&userUuid=cc8c7ac9-13c4-407f-813d-ee0731b0bb73";
    	//String time = DateUtil.getTime();
    	long time2 = new Date().getTime();
    	String time = String.valueOf(time2);
    	System.out.println("time:"+new Date().getTime());
    	String userUuid="cc8c7ac9-13c4-407f-813d-ee0731b0bb73";
    	String key="2020207";
    	Map<String, String> parameterMap = new HashMap<String, String>();  
         parameterMap.put("key", "2020207");  
         parameterMap.put("timestamp", time);  
       
  
         parameterMap.put("userUuid", "cc8c7ac9-13c4-407f-813d-ee0731b0bb73");
         JSONObject fromObject=(JSONObject) JSONObject.toJSON(parameterMap);
         //JSONObject fromObject = JSONObject.fromObject(parameterMap);
         /*System.out.println(fromObject);
         String string = fromObject.toString();*/
         String string = fromObject.toString();
         try {
			HttpUtil.post(urlStr, string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	/*String mobile="18500909334";//13641247747
    	String md5 = MD5.getMD5("trseeaL2krmfASx2y3vNkZBwKOGCwiyG"+mobile);
    	System.out.println(md5.toLowerCase());
    	String code="535186";
    	//HttpUtil.get("http://my-api-test.app.xinhuanet.com/h5/tencent/sendCode?mobile="+mobile+"&sign="+md5.toLowerCase());
    	String md51 = MD5.getMD5("trseeaL2krmfASx2y3vNkZBwKOGCwiyG"+code+mobile);
    	HttpUtil.get("http://my-api-test.app.xinhuanet.com/h5/tencent/checkCode?mobile="+mobile+"&sign="+md51.toLowerCase()+"&code="+code);*/

    	//  HttpUtil.root(1, 2, 4);3b22706a8e1ef6720d3c448c30b646c8
         
	}
     static void  root(double a,double b,double c){
    	/* double  x1,x2,q,p;  q=b*b-4*a*c;
    	 if(q==0.0){
    		 System.out.println("x1=x2="+(-b/(2.0*a)));
    	 }else if(q>0){
    		 p=Math.sqrt(q);
    		 x1=(-b+p)/2/a;
    		 x2=(-b-p)/2/a;
    		 System.out.println(x1+"=="+x2);
    	 }else{
    		 p=Math.sqrt(-q);
    		 x1=(-b)/2/a;
    		 x2=(p)/2/a;
    		 System.out.println("x1="+x1+"x2="+x2+"i"+"x2="+x1+"-"+x2+"i");
    	 }*/
    	 
    	
     }
}
