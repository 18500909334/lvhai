package com.ucap.zfw.util;

import java.util.Random;
import java.util.UUID;



/**
 * @author hesy
 *
 */
public class Uuid {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}
	
	public static String getSearchCode(){
        final int maxNum = 36;
        int i; //生成的随机数
        int count = 0; //生成的密码的长度
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' , 'A', 'B', 'C'
        		,'D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T'
        		,'U','V','W','X','Y','Z'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < 4){
         //生成随机数，取绝对值，防止生成负数，
       
         i = Math.abs(r.nextInt(maxNum)); //生成的数最大为36-1
       
         if (i >= 0 && i < str.length) {
          pwd.append(str[i]);
          count ++;
         }
        }
        return pwd.toString();
	}
	
}
