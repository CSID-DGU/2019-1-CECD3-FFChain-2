package kr.ac.dgu.icip.cmm.security;
/**
 * @Class Name : Common256.java
 * @Description : Common256 KISA_SHA256 암호화 처리 - Dreams 제공
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
public class Common256 {
	
	public static final int BIG_ENDIAN = 0;
	public static final int LITTLE_ENDIAN = 1;

	public static void arraycopy(byte[] dst, byte[] src, int length) {
		for(int i=0; i<length; i++) {
			dst[i] = src[i];
		}
	}

	public static void arraycopy_offset(byte[] dst, int dst_offset, byte[] src, int src_offset, int length) {
		for(int i=0; i<length; i++) {
			dst[dst_offset+i] = src[src_offset+i];
		}
	}

	public static void arrayinit(byte[] dst, byte value, int length) {
		for(int i=0; i<length; i++) {
			dst[i] = value;
		}
	}
	
	public static void arrayinit_offset(byte[] dst, int dst_offset, byte value, int length) {
		for(int i=0; i<length; i++) {
			dst[dst_offset+i] = value;
		}
	}

	public static void memcpy(int[] dst, byte[] src, int length, int ENDIAN) {
		int iLen = length / 4;
		for(int i=0; i<iLen; i++) {
			byte_to_int(dst, i, src, i*4, ENDIAN);
		}
	}

	public static void memcpy(int[] dst, int[] src, int src_offset, int length) {
    	int iLen = length / 4 + ((length % 4 != 0)?1:0);
		for(int i=0; i<iLen; i++) {
			dst[i] = src[src_offset+i];
		}
	}

	public static void set_byte_for_int(int[] dst, int b_offset, byte value, int ENDIAN) {
		if(ENDIAN == BIG_ENDIAN) {
			int shift_value = (3-b_offset%4)*8;
			int mask_value =  0x0ff << shift_value;
			int mask_value2 = ~mask_value;
			int value2 = (value&0x0ff) << shift_value;
			dst[b_offset/4] = (dst[b_offset/4] & mask_value2) | (value2 & mask_value);    
		} else {
			int shift_value = (b_offset%4)*8;
			int mask_value =  0x0ff << shift_value;
			int mask_value2 = ~mask_value;
			int value2 = (value&0x0ff) << shift_value;
			dst[b_offset/4] = (dst[b_offset/4] & mask_value2) | (value2 & mask_value);    
		}
	}
	
	public static byte get_byte_for_int(int[] src, int b_offset, int ENDIAN) {
		if(ENDIAN == BIG_ENDIAN) {
			int shift_value = (3-b_offset%4)*8;
			int mask_value =  0x0ff << shift_value;
			int value = (src[b_offset/4] & mask_value) >> shift_value;
			return (byte)value;
		} else {
			int shift_value = (b_offset%4)*8;
			int mask_value =  0x0ff << shift_value;
			int value = (src[b_offset/4] & mask_value) >> shift_value;
			return (byte)value;
		}
		
	}
	
	public static byte[] get_bytes_for_ints(int[] src, int offset, int ENDIAN) {
		int iLen = src.length-offset;
		byte[] result = new byte[(iLen)*4];
		for(int i=0; i<iLen; i++) {
			int_to_byte(result, i*4, src, offset+i, ENDIAN);
		}
		
		return result;
	}

	public static void byte_to_int(int[] dst, int dst_offset, byte[] src, int src_offset, int ENDIAN) {
		if(ENDIAN == BIG_ENDIAN) {
			dst[dst_offset] = ((0x0ff&src[src_offset]) << 24) | ((0x0ff&src[src_offset+1]) << 16) | ((0x0ff&src[src_offset+2]) << 8) | ((0x0ff&src[src_offset+3]));
		} else {
			dst[dst_offset] = ((0x0ff&src[src_offset])) | ((0x0ff&src[src_offset+1]) << 8) | ((0x0ff&src[src_offset+2]) << 16) | ((0x0ff&src[src_offset+3]) << 24);
		}
	}
	
	public static int byte_to_int(byte[] src, int src_offset, int ENDIAN) {
		if(ENDIAN == BIG_ENDIAN) {
			return ((0x0ff&src[src_offset]) << 24) | ((0x0ff&src[src_offset+1]) << 16) | ((0x0ff&src[src_offset+2]) << 8) | ((0x0ff&src[src_offset+3]));
		} else {
			return ((0x0ff&src[src_offset])) | ((0x0ff&src[src_offset+1]) << 8) | ((0x0ff&src[src_offset+2]) << 16) | ((0x0ff&src[src_offset+3]) << 24);
		}
	}

	public static int byte_to_int_big_endian(byte[] src, int src_offset) {
		return ((0x0ff&src[src_offset]) << 24) | ((0x0ff&src[src_offset+1]) << 16) | ((0x0ff&src[src_offset+2]) << 8) | ((0x0ff&src[src_offset+3]));
	}

	public static void int_to_byte(byte[] dst, int dst_offset, int[] src, int src_offset, int ENDIAN) {
		int_to_byte_unit(dst, dst_offset, src[src_offset], ENDIAN);
	}
	
	public static void int_to_byte_unit(byte[] dst, int dst_offset, int src, int ENDIAN) {
		if(ENDIAN == BIG_ENDIAN) {
			dst[dst_offset] = (byte)((src>> 24) & 0x0ff);
			dst[dst_offset+1] = (byte)((src >> 16) & 0x0ff);
			dst[dst_offset+2] = (byte)((src >> 8) & 0x0ff);
			dst[dst_offset+3] = (byte)((src) & 0x0ff);
		} else {
			dst[dst_offset] = (byte)((src) & 0x0ff);
			dst[dst_offset+1] = (byte)((src >> 8) & 0x0ff);
			dst[dst_offset+2] = (byte)((src >> 16) & 0x0ff);
			dst[dst_offset+3] = (byte)((src >> 24) & 0x0ff);
		}
		
	}

	public static void int_to_byte_unit_big_endian(byte[] dst, int dst_offset, int src) {
		dst[dst_offset] = (byte)((src>> 24) & 0x0ff);
		dst[dst_offset+1] = (byte)((src >> 16) & 0x0ff);
		dst[dst_offset+2] = (byte)((src >> 8) & 0x0ff);
		dst[dst_offset+3] = (byte)((src) & 0x0ff);
	}

	public static int URShift(int x, int n) {
		if(n == 0)
			return x;
		if(n >= 32)
			return 0;
		int v = x >> n;
		int v_mask = ~(0x80000000 >> (n-1));
		return v & v_mask;
	}
	
	public static final long INT_RANGE_MAX = (long)Math.pow(2, 32);

	public static long intToUnsigned(int x) {
		if(x >= 0)
			return x;
		return x + INT_RANGE_MAX;
	}
}