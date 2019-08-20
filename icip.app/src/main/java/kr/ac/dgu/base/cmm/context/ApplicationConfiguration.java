package kr.ac.dgu.base.cmm.context; 

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Class Name : ApplicationConfiguration.java
 * @Description :
 * ApplicationConfiguration 관련 Class
 * @author DGU
 * @시스템 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0
 
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.		DGU			신규생성
 * </pre>
 */
public class ApplicationConfiguration implements ServletContextListener{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        try {
        	String instanceName = System.getProperty("weblogic.Name");
        	if(instanceName==null || instanceName.equals("")){
        		
        		instanceName = System.getProperty("instanceName");
        		
        		if(instanceName==null || instanceName.equals("")) instanceName = "local";
        	}
        	//instanceName = System.getProperty("instanceName");
        	
        	String hostName = InetAddress.getLocalHost().getHostName();
        	
        	System.setProperty("instanceName", instanceName);
            // step-1 : set hostName into System's property, which will use by log4j
            System.setProperty("hostName", hostName);
            //step - 2 : set currentDate into System's property, which will use by log4j
            System.setProperty("currentDate", new SimpleDateFormat("dd-MMM-yyyy").format(new Date()));
            
            //LOGGER.info("########listener properties load########## "+instanceName+" ##");
            System.out.println("########listener properties load########## "+instanceName+" ##");
        } catch (UnknownHostException e) {
        	//LOGGER.error("Error Message : " + e.getMessage(),e);
        	System.out.println("Error Message : " + e.getMessage());
            //e.printStackTrace();
        } 


    }


}