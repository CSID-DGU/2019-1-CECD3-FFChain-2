package kr.ac.dgu.icip.cmm.security;
/**
 * @Class Name : ShaEnc.java
 * @Description : ShaEnc 암호화 처리 - java 기본 제공 
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShaEnc {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShaEnc.class);
	
	/**
	 * <pre>
	 * 1. 개요 : sha 256 암호화
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getSha256
	 * @date : 2019. 03. 13.
	 * @author : DGU
	 * @param str
	 * @return
	 */ 	
	public String getSha256(String str)
	{
		String SHA = "";

		try {

			MessageDigest sh = MessageDigest.getInstance("SHA-256");

			sh.update(str.getBytes());

			byte byteData[] = sh.digest();

			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			SHA = sb.toString();

		} catch (NoSuchAlgorithmException e) {

			LOGGER.error("NoSuchAlgorithmException ",e);
			SHA = null;

		}

		return SHA;

	}

	/**
	 * <pre>
	 * 1. 개요 : sha 512 암호화
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getSha512
	 * @date : 2019. 03. 13.
	 * @author : DGU
	 * @param str
	 * @return
	 */ 	
	public String getSha512(String str)
	{
		String SHA = "";
		String salt = "WF20_ORG";
		try {

			MessageDigest sh = MessageDigest.getInstance("SHA-512");

			sh.update(salt.getBytes());
			
			sh.update(str.getBytes());

			byte byteData[] = sh.digest();

			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			SHA = sb.toString();

		} catch (NoSuchAlgorithmException e) {

			LOGGER.error("NoSuchAlgorithmException ",e);
			SHA = null;

		}

		return SHA;

	}	

	/**
	 * <pre>
	 * 1. 개요 : 임시비밀번호 생성 함수
	 * </pre>
	 * @Method Name : temporaryPassword
	 * @date : 2019. 03. 13.
	 * @author : DGU
	 * @param size
	 * @return
	 */ 	
	public static String getTempPassword(int size) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		random.setSeed(new java.util.Date().getTime());
		/*
		String chars[] = ("A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,"
				+ "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,").split(",");
		String chars_special[] = "~,!,@,#,$,%,^,&,*,(,),_,+,-,=,{,},[,],;,:,.".split(",");	
		*/
		//혼동할수 있는 문자를 삭제
		String chars[] = ("A,B,C,D,E,F,G,H,I,J,K,L,M,N,P,R,S,T,U,V,W,X,Y,Z,"
				+ "a,b,c,d,e,f,g,h,i,j,k,l,m,n,p,r,s,t,u,v,w,x,y,z,").split(",");
		String chars_special[] = "~,!,@,#,$,%,^,&,*,_,+,-,=,:,.".split(",");	
		int rndfix = random.nextInt(100);
		for (int i = 0; i < size; i++) {
			
			buffer.append(chars[random.nextInt(chars.length)]);
			//숫자
			rndfix = random.nextInt(100+chars.length);
			if (rndfix % 3 == 0) {
				i++;
				buffer.append(random.nextInt(10));
			}
			//특수문자
			rndfix = random.nextInt(100+chars_special.length);
			if (size % 6 == 0) {
				i++;
				buffer.append(chars_special[random.nextInt(chars_special.length)]);
			}
		}
		return buffer.toString(); 
	}

}
