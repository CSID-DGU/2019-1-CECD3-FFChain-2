package kr.ac.dgu.icip.cmm.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class Name : OneSecurity.java
 * @Description : sha256 암호화 처리
 * @file KISA_SHA256.java
 * @brief SHA256 암호 알고리즘
 * @author Copyright (c) 2013 by KISA
 * @remarks http://seed.kisa.or.kr/ * 
 * @author 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * 
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.		DGU		신규생성
 * </pre>
 */
public class OneSecurity {

	private static final Logger LOGGER = LoggerFactory.getLogger(OneSecurity.class);
	/**
	 * 암화화
	 * @param passwd
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws Exception
	 */
	public String getSHA256Encode(String passwd) throws UnsupportedEncodingException{
		 
		  KisaSha256 SHA256 = new KisaSha256();
		  
		  byte[] plainText = passwd.getBytes("UTF-8");
		  
		  String cipherTextStr = SHA256.getString(SHA256.Sha256EncryptB(plainText));
		  
		  String rtnValue = cipherTextStr;
		  
		  return rtnValue;
	}
	
	/**
	 * SHA256 값의 검증
	 * @param cipherOriTextStr
	 * @param inputpasswd
	 * @return true 암호화검증 완료
	 */
	public boolean isCheckSHA256(String cipherOriTextStr,String inputpasswd)
	{
		String inputpasswdchiperText =  "";
		boolean ret = false;
		try {
			if(cipherOriTextStr==null||inputpasswd==null)
			{
				throw new NullPointerException("isCheckSHA256 no input data "+cipherOriTextStr+":"+inputpasswd+"");
			}
			inputpasswdchiperText = getSHA256Encode(inputpasswd);
			inputpasswdchiperText = inputpasswdchiperText.toUpperCase();
			cipherOriTextStr = cipherOriTextStr.toUpperCase();
			
			if(inputpasswdchiperText.equals(cipherOriTextStr)){
				ret = true;
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("isCheckSHA256 : ",e);
		} catch (NullPointerException e) {
			LOGGER.error("isCheckSHA256 : ",e);
		} catch (Exception e) {
			LOGGER.error("isCheckSHA256 : ",e);
		}
		
		return ret;
	}
	/**
	 * 암화화
	 * @param passwd
	 * @return
	 * @throws Exception
	 */
	public String getSHA512Encode(String passwd) throws Exception {

		ShaEnc se = new ShaEnc();

		String sha512txt = se.getSha512(passwd);

		return sha512txt;
	}
	/**
	 * SHA512 값의 검증
	 * @param cipherOriTextStr
	 * @param inputpasswd
	 * @return true 암호화검증 완료
	 */
	public boolean isCheckSHA512(String cipherOriTextStr,String inputpasswd)
	{
		String inputpasswdchiperText =  "";
		boolean ret = false;
		try {
			if(cipherOriTextStr==null||inputpasswd==null)
			{
				throw new NullPointerException("isCheckSHA512 no input data "+cipherOriTextStr+":"+inputpasswd+"");
			}
			ShaEnc se = new ShaEnc();
			inputpasswdchiperText = se.getSha512(inputpasswd);
			//inputpasswdchiperText = inputpasswdchiperText.toUpperCase();
			//cipherOriTextStr = cipherOriTextStr.toUpperCase();
			if(inputpasswdchiperText.equals(cipherOriTextStr)){
				ret = true;
			}
		} catch (NullPointerException e) {
			LOGGER.error("isCheckSHA512 : ",e);
		} catch (Exception e) {
			LOGGER.error("isCheckSHA512 : ",e);
		}
		
		return ret;
	}	
	
	
	/**
	 * SHA512 값의 검증
	 * @param cipherOriTextStr
	 * @param inputpasswd
	 * @return true 암호화검증 완료
	 */
	public boolean isCheckEnotisSHA512(String cipherOriTextStr,String inputpasswd)
	{
		String inputpasswdchiperText =  "";
		boolean ret = false;
		try {
			if(cipherOriTextStr==null||inputpasswd==null)
			{
				throw new NullPointerException("isCheckSHA512 no input data "+cipherOriTextStr+":"+inputpasswd+"");
			}
			ShaEnc se = new ShaEnc();
			inputpasswdchiperText = se.getSha512(inputpasswd);
			//inputpasswdchiperText = inputpasswdchiperText.toUpperCase();
			//cipherOriTextStr = cipherOriTextStr.toUpperCase();
			if(inputpasswdchiperText.equals(cipherOriTextStr)){
				ret = true;
			}
		} catch (NullPointerException e) {
			LOGGER.error("isCheckSHA512 : ",e);
		}catch (Exception e) {
			LOGGER.error("isCheckSHA512 : ",e);
		}
		
		return ret;
	}		
	 /**
	 * <pre>
	 * 1. 개요 : enotis 512 암호화 방식
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : enotis_crypt512
	 * @date : 2019. 03. 13.
	 * @author : DGU
	 * @param original
	 * @return
	 */ 	
	public static final String enotis_crypt512( String original ) {
		String output = "";
		StringBuffer sb = new StringBuffer();
		MessageDigest md;
		String salt = "WF20_ORG";
		try {
			md = MessageDigest.getInstance("SHA-512");

			md.update(salt.getBytes());
			md.update(original.getBytes());
			byte[] msgb = md.digest();

			for (int i = 0; i < msgb.length; i++) {

				byte temp = msgb[i];
				String str = Integer.toHexString(temp & 0xFF);
				while (str.length() < 2) {
					str = "0" + str;
				}
				str = str.substring(str.length() - 2);
				sb.append(str);
			}

			output = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("enotis_crypt512 : ",e);
		}

		return output;
	    }
/*
	     public static void main(String[] args) throws Exception {

	      XFCrypt crypt = new XFCrypt();
	     System.out.println( "---------[" +XFCrypt.crypt( "WF20_ORG", "dbtjdgus") +     "]" );
	 
	      if(args.length >= 2)
	      {
	       System.out.println("[" + args[0] + "] [" + args[1] + "] => [" +crypt.crypt(args[0], args[1]) + "]" );
	      
	      }
	      if(args.length >= 2)
	      {
	       System.out.println("[" + args[0] + "] [" + args[1] + "] => [" + crypt.crypt(args[0], args[1]) + "]" );
	      
	      }
	     }
*/
	
	
/*	public static void main(String[] args) throws Exception {
		
		OneSecurity osec = new OneSecurity();
		String plainstr = "test123!";
		String cipheruserpw = osec.getSHA256Encode(plainstr);
		
		
		
		ShaEnc se = new ShaEnc();
		plainstr = "init";
		cipheruserpw = osec.getSHA256Encode(plainstr);
		String sha256txt = se.getSha256(plainstr);
		System.out.println(cipheruserpw+":::::"+osec.isCheckSHA256(cipheruserpw, plainstr));
		
		plainstr = "test123!";
		
		sha256txt = se.getSha256(plainstr);
		*//**
		 * 기본 비밀번호 암호화는 기존 Dreams 와 각 사이트의 방법을 준수한다.
		 * osec.isCheckSHA256(cipheruserpw, plainstr)
		 * *//*
		
		System.out.println(cipheruserpw+":::::"+osec.isCheckSHA256(cipheruserpw, plainstr));
		System.out.println(cipheruserpw+":::::"+osec.isCheckSHA256(cipheruserpw, "12345"));
		plainstr = "12345678901234567890123456789012345678901234567890";
		cipheruserpw = osec.getSHA256Encode(plainstr);
		sha256txt = se.getSha256(plainstr);
		*//**
		 * 기본 비밀번호 암호화는 기존 Dreams 와 각 사이트의 방법을 준수한다.
		 * osec.isCheckSHA256(cipheruserpw, plainstr)
		 * *//*
		
		System.out.println(cipheruserpw+":::::"+osec.isCheckSHA256(cipheruserpw, plainstr));
		System.out.println(cipheruserpw+":::::"+osec.isCheckSHA256(cipheruserpw, "12345"));
		
		String sha512txt = se.getSha512(plainstr);
		//sha512txt = sha512txt.toUpperCase();
		System.out.println(sha512txt+":::::"+osec.isCheckSHA512(sha512txt, plainstr));
		System.out.println(osec.enotis_crypt512( plainstr));

		System.out.println(osec.enotis_crypt512( plainstr)+":::::"+osec.isCheckEnotisSHA512(osec.enotis_crypt512( plainstr), plainstr));
		
		
		String pass256 = "3BBC917A90A0228D617248CCEC7F5ACC053915FCEB9C5BC63BBE1D1AADB20964";
		plainstr = "admin135!";
		System.out.println(pass256+":::::"+osec.isCheckSHA256(pass256, plainstr));
		cipheruserpw = osec.getSHA256Encode(plainstr);
		System.out.println(cipheruserpw+":::::"+osec.isCheckSHA256(cipheruserpw, plainstr));
		pass256 = "DAFF92471965427A5166D709AADA91C6633C19EA35B95D4D77CD5FF891720F75";
		plainstr = "test135!";
		System.out.println(pass256+":::::"+osec.isCheckSHA256(pass256, plainstr));
		cipheruserpw = osec.getSHA256Encode(plainstr);
		System.out.println(cipheruserpw+":::::"+osec.isCheckSHA256(cipheruserpw, plainstr));
		
		//3BBC917A90A0228D617248CCEC7F5ACC053915FCEB9C5BC63BBE1D1AADB20964
		//
		//3BBC917A90A0228D617248CCEC7F5ACC53915FCEB9C5BC63BBE1D1AADB2964
		//3BBC917A90A0228D617248CCEC7F5ACC053915FCEB9C5BC63BBE1D1AADB20964		
		
		
		System.out.println(sha256txt);
		System.out.println(se.getSha256(plainstr));
		System.out.println(se.getSha512(plainstr));
		
		for (int i = 0; i < 50; i++) {
			System.out.println(i + " : " + ShaEnc.getTempPassword(10));
			Thread.sleep(10);
		}
	}
*/
}
