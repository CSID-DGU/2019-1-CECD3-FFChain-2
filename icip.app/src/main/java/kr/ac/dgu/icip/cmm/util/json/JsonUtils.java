package kr.ac.dgu.icip.cmm.util.json;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * <pre>
 * kr.ac.dgu.icip.cmm.util.json
 *    |_ JsonUtils.java
 * 1. 개요 : 
 * 2. 처리내용 : 
 * </pre>
 * @date : 2019. 03. 14. 오후 1:24:24
 * @version : 
 * @author : DGU
 * @history : 
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용  
 *  ----------- ------------------- ---------------------------------------
 *  2019. 03. 14.       DGU             최초 작성 
 *  -----------------------------------------------------------------------
 *  
 */
public class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
	public String postJsonStr1(String postUrl, HashMap<String, String> jsonMap) throws Exception {
		String strJson = "";
		
		URL url = new URL(postUrl);
        HttpURLConnection urlConn = null;
        DataOutputStream output = null;
        
        Iterator<String> iterator = jsonMap.keySet().iterator();
        strJson += "{";
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            
            strJson += key + ":" + jsonMap.get(key);
            if(iterator.hasNext()) {
            	// 마지막 속성이 아니면 ','콤마 붙힘
            	strJson += ",";
            }
        }
        strJson += "}";
        
        LOGGER.info("JsonString : " + strJson);
        
        try{
        	urlConn = (HttpURLConnection) url.openConnection();
        	urlConn.setRequestMethod("POST");
        	urlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        	urlConn.setConnectTimeout(5000);
        	urlConn.setReadTimeout(30000);
        	urlConn.setDoInput (true);
        	urlConn.setDoOutput (true);
//        	urlConn.setUseCaches (false);
        	urlConn.connect();
        	output = new DataOutputStream(urlConn.getOutputStream());	        
        	output.write(strJson.getBytes("UTF-8"));
        	output.flush();
        	output.close();
        }catch ( IOException e){
        	throw  e;
        }catch ( NullPointerException e){
        	throw  e;
        }catch ( Exception e){
        	throw  e;
        }finally{
        	try{
        		if(output != null)
        			output.close();
        	}catch(IOException e){
        		LOGGER.error("error",e);
        	}
        }
        
        String inputLine;
        StringBuffer response = new StringBuffer();	        
        BufferedReader  in = new BufferedReader( new InputStreamReader( urlConn.getInputStream(),"UTF-8"));
        
        while( (inputLine = in.readLine()) != null ){
        	response.append(inputLine);
        }
        in.close();	        
        return response.toString();
	}
	public String postJsonStr(String postUrl, HashMap<String, String> jsonMap) throws Exception {
		String strJson = "";
		
		URL url = new URL(postUrl);
        HttpURLConnection urlConn = null;
        DataOutputStream output = null;

    	JSONObject cred   = new JSONObject();
    	
        Iterator<String> iterator = jsonMap.keySet().iterator();
        strJson += "{";
        while (iterator.hasNext()) {
            String key = (String) iterator.next();

            cred.put(key, jsonMap.get(key));
            strJson += key + ":" + jsonMap.get(key);
            if(iterator.hasNext()) {
            	// 마지막 속성이 아니면 ','콤마 붙힘
            	strJson += ",";
            }
        }
        strJson += "}";
        
        LOGGER.debug("JsonString : " + strJson);
        
        try{
        	urlConn = (HttpURLConnection) url.openConnection();
        	urlConn.setRequestProperty("Accept", "application/json");
        	urlConn.setRequestMethod("POST");
        	urlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        	urlConn.setConnectTimeout(5000);
        	urlConn.setReadTimeout(30000);
        	urlConn.setDoInput (true);
        	urlConn.setDoOutput (true);
//        	urlConn.setUseCaches (false);
        	urlConn.connect();
        	
        	
        	OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream());
        	wr.write(cred.toString());
        	wr.flush();
        	
        	//output = new DataOutputStream(urlConn.getOutputStream());
        	
        	
        	
        	//output.write(strJson.getBytes("UTF-8"));
        	//output.flush();
        	//output.close();
        }catch (IOException e){
        	throw  e;
        }finally{
        	try{
        		if(output != null)
        			output.close();

        	}catch(IOException e){
        		LOGGER.error("error",e);
        	}
        }
        
        String inputLine;
        StringBuffer response = new StringBuffer();	        
        BufferedReader  in = new BufferedReader( new InputStreamReader( urlConn.getInputStream(),"UTF-8"));
        
        while( (inputLine = in.readLine()) != null ){
        	response.append(inputLine);
        }
        in.close();	        
        return response.toString();
	}	
}
