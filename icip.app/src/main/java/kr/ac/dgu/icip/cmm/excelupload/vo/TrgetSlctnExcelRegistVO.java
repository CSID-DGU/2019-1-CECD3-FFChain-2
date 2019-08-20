package kr.ac.dgu.icip.cmm.excelupload.vo;

import kr.ac.dgu.base.cmm.vo.DefaultVO;

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
public class TrgetSlctnExcelRegistVO extends DefaultVO {

    /** 엑셀파일 **/
    String excelFile = "";

    /** 고유번호 **/
    String memberId = "";
    /** 사업자등록번호 **/
    String compNo = "";
    /** 회사/단체명 **/
    String companyKor = "";
    /** 대표자명 **/
    String presidentKor = "";

    /** 생년월일 **/
    String birthDay = "";

    /**  아이디 **/
    String icipId = "";
    /** 담당자명 **/
    String contactNm = "";
    /** 연락처_전화번호 **/
    String contactTel = "";
    /** 연락처_전화번호 수신여부 **/
    String contactTelYn = "";
    /** 연락처_팩스 **/
    String contactFax = "";
    /** 연락처_팩스 수신여부 **/
    String contactFaxYn = "";
    /** 연락처_휴대폰 **/
    String contactCell = "";
    /** 연락처_휴대폰 수신여부 **/
    String contactCellYn = "";
    /** 연락처_이메일 **/
    String contactEmail = "";
    /** 연락처_이메일 수신여부 **/
    String contactEmailYn = "";
    /** 사용자ID **/
    String userId = "";
    /** ID **/
    String tradeId = "";
    /**  풀 ID **/
    String poolSeq = "";   //CELL_NO_ERMS
    
    /**
     * Comment   : excelFile
     * @return the excelFile
     */
    public String getExcelFile() {
        return excelFile;
    }
    
    
    /**
     * Comment   : birthDay
     * @return the birthDay
     */
    public String getBirthDay() {
        return birthDay;
    }


    /**
     * Comment   : birthDay
     * @param birthDay the birthDay to set
     */
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }


    /**
     * Comment   : memberId
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }


    /**
     * Comment   : compNo
     * @return the compNo
     */
    public String getCompNo() {
        return compNo;
    }


    /**
     * Comment   : companyKor
     * @return the companyKor
     */
    public String getCompanyKor() {
        return companyKor;
    }


    /**
     * Comment   : presidentKor
     * @return the presidentKor
     */
    public String getPresidentKor() {
        return presidentKor;
    }


    /**
     * Comment   : memberId
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    /**
     * Comment   : compNo
     * @param compNo the compNo to set
     */
    public void setCompNo(String compNo) {
        this.compNo = compNo;
    }


    /**
     * Comment   : companyKor
     * @param companyKor the companyKor to set
     */
    public void setCompanyKor(String companyKor) {
        this.companyKor = companyKor;
    }


    /**
     * Comment   : presidentKor
     * @param presidentKor the presidentKor to set
     */
    public void setPresidentKor(String presidentKor) {
        this.presidentKor = presidentKor;
    }


    /**
     * Comment   : icipId
     * @return the icipId
     */
    public String geticipId() {
        return icipId;
    }
    /**
     * Comment   : contactNm
     * @return the contactNm
     */
    public String getContactNm() {
        return contactNm;
    }
    /**
     * Comment   : contactTel
     * @return the contactTel
     */
    public String getContactTel() {
        return contactTel;
    }
    /**
     * Comment   : contactTelYn
     * @return the contactTelYn
     */
    public String getContactTelYn() {
        return contactTelYn;
    }
    /**
     * Comment   : contactFax
     * @return the contactFax
     */
    public String getContactFax() {
        return contactFax;
    }
    /**
     * Comment   : contactFaxYn
     * @return the contactFaxYn
     */
    public String getContactFaxYn() {
        return contactFaxYn;
    }
    /**
     * Comment   : contactCell
     * @return the contactCell
     */
    public String getContactCell() {
        return contactCell;
    }
    /**
     * Comment   : contactCellYn
     * @return the contactCellYn
     */
    public String getContactCellYn() {
        return contactCellYn;
    }
    /**
     * Comment   : contactEmail
     * @return the contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }
    /**
     * Comment   : contactEmailYn
     * @return the contactEmailYn
     */
    public String getContactEmailYn() {
        return contactEmailYn;
    }
    /**
     * Comment   : userId
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * Comment   : tradeId
     * @return the tradeId
     */
    public String getTradeId() {
        return tradeId;
    }
    /**
     * Comment   : excelFile
     * @param excelFile the excelFile to set
     */
    public void setExcelFile(String excelFile) {
        this.excelFile = excelFile;
    }
    /**
     * Comment   : icipId
     * @param icipId the icipId to set
     */
    public void seticipId(String icipId) {
        this.icipId = icipId;
    }
    /**
     * Comment   : contactNm
     * @param contactNm the contactNm to set
     */
    public void setContactNm(String contactNm) {
        this.contactNm = contactNm;
    }
    /**
     * Comment   : contactTel
     * @param contactTel the contactTel to set
     */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }
    /**
     * Comment   : contactTelYn
     * @param contactTelYn the contactTelYn to set
     */
    public void setContactTelYn(String contactTelYn) {
        this.contactTelYn = contactTelYn;
    }
    /**
     * Comment   : contactFax
     * @param contactFax the contactFax to set
     */
    public void setContactFax(String contactFax) {
        this.contactFax = contactFax;
    }
    /**
     * Comment   : contactFaxYn
     * @param contactFaxYn the contactFaxYn to set
     */
    public void setContactFaxYn(String contactFaxYn) {
        this.contactFaxYn = contactFaxYn;
    }
    /**
     * Comment   : contactCell
     * @param contactCell the contactCell to set
     */
    public void setContactCell(String contactCell) {
        this.contactCell = contactCell;
    }
    /**
     * Comment   : contactCellYn
     * @param contactCellYn the contactCellYn to set
     */
    public void setContactCellYn(String contactCellYn) {
        this.contactCellYn = contactCellYn;
    }
    /**
     * Comment   : contactEmail
     * @param contactEmail the contactEmail to set
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    /**
     * Comment   : contactEmailYn
     * @param contactEmailYn the contactEmailYn to set
     */
    public void setContactEmailYn(String contactEmailYn) {
        this.contactEmailYn = contactEmailYn;
    }
    /**
     * Comment   : userId
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * Comment   : tradeId
     * @param tradeId the tradeId to set
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
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
        builder.append("TrgetSlctnExcelRegistVO [excelFile=");
        builder.append(excelFile);
        builder.append(", memberId=");
        builder.append(memberId);
        builder.append(", compNo=");
        builder.append(compNo);
        builder.append(", companyKor=");
        builder.append(companyKor);
        builder.append(", presidentKor=");
        builder.append(presidentKor);
        builder.append(", birthDay=");
        builder.append(birthDay);
        builder.append(", icipId=");
        builder.append(icipId);
        builder.append(", contactNm=");
        builder.append(contactNm);
        builder.append(", contactTel=");
        builder.append(contactTel);
        builder.append(", contactTelYn=");
        builder.append(contactTelYn);
        builder.append(", contactFax=");
        builder.append(contactFax);
        builder.append(", contactFaxYn=");
        builder.append(contactFaxYn);
        builder.append(", contactCell=");
        builder.append(contactCell);
        builder.append(", contactCellYn=");
        builder.append(contactCellYn);
        builder.append(", contactEmail=");
        builder.append(contactEmail);
        builder.append(", contactEmailYn=");
        builder.append(contactEmailYn);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", tradeId=");
        builder.append(tradeId);
        builder.append(", poolSeq=");
        builder.append(poolSeq);
        builder.append("]");
        return builder.toString();
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
    

}
