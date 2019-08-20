package kr.ac.dgu.icip.cmm.excelupload.vo;

import kr.ac.dgu.icip.cmm.vo.LoginInfoVO;

/**
 * <pre>
 * kr.ac.dgu.icip.cmm.excelupload.vo 
 * @Class Name : TrgetSlctnExcelRegistVO.java
 * @Description :  대상 고객 엑셀 업로드 VO
 * @Modification Information
 * </pre>
 * 
 * @author : dgu
 * @date : 2019. 3. 17. 오후 2:51:23
 * @version : 1.0
 * 
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * 
 * @history : 
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용  
 *  ----------- ------------------- ---------------------------------------
 *  2019. 3. 29.        DGU             최초 작성 
 *  -----------------------------------------------------------------------
 *  
 */
public class TrgetSlctnRegistVO extends LoginInfoVO {

    /** ID **/
    String tradeId = "";
    /**  풀 ID **/
    String poolSeq = "";  
    

    /**
     * Comment   : tradeId
     * @return the tradeId
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * Comment   : tradeId
     * @param tradeId the tradeId to set
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public TrgetSlctnRegistVO(LoginInfoVO vo) {
        this.setJibucd(vo.getJibucd());
        this.setJibunm(vo.getJibunm());
        this.setDeptcode(vo.getDeptcode());
        this.setDeptcode2(vo.getDeptcode2());
        this.setSno(vo.getSno());
        this.setUsername(vo.getUsername());
        this.setAuthgcode(vo.getAuthgcode()); 
        this.setUserid(vo.getUserid());
    }

    /**
     * Comment   : 
     * @return the poolSeq
     */
    public String getPoolSeq() {
        return poolSeq;
    }

    /**
     * Comment   : 
     * @param poolSeq the poolSeq to set
     */
    public void setPoolSeq(String poolSeq) {
        this.poolSeq = poolSeq;
    }

    /**
     * <pre>
     * 1. 개요 : 
     * </pre>
     * @Method Name : toString
     * @author : dgu
     * @date : 2018. 10. 25.
     * @see java.lang.Object#toString()
     * @return
     */ 	
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TrgetSlctnRegistVO [tradeId=");
        builder.append(tradeId);
        builder.append(", poolSeq=");
        builder.append(poolSeq);
        builder.append("]");
        return builder.toString();
    }
    
    
}
