package kr.ac.dgu.base.cmm.vo;

import org.apache.commons.fileupload.disk.DiskFileItem;

/**
 * @Class Name : FileVO.java
 * @Description : 공통 File VO
 * @author DGU
 * @시스템 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 *
 *            <pre>
 * ------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.		DGU			신규생성
 * </pre>
 */
@SuppressWarnings("serial")
public class FileVO extends DefaultVO {

    private String htmlFieldName;

    /**
     * 첨부파일내역번호
     */
    private long atchFilePtcsNo;

    /**
     * 첨부파일 순번
     */
    private long atchSeq;

    /**
     * 문서종류코드
     */
    private String docKindCd;

    /**
     * 파일명
     */
    private String atchFileNm;

    /**
     * 실제 저장되는 파일명
     */
    private String physFileNm;

    /**
     * 대체텍스트
     */
    private String sbtnTxt;

    /**
     * 작은이미지로 보여질 파일명
     */
    private String thnlNm;

    /**
     * 리스트 파일명
     */
    private String lstFileNm;

    /**
     * 상세 파일명
     */
    private String dtlFileNm;

    /**
     * 확장 파일명
     */
    private String elgmFileNm;

    /**
     * MIME정보내용
     */
    private String mimeInfoCntn;

    /**
     * 파일경로내용
     */
    private String filePthCntn;

    /**
     * 파일크기값
     */
    private String fileSizeVal;

    /**
     * 관리번호
     */
    private String cltrMnmtNo;

    /**
     * 등록일시
     */
    private String rgstDtm;

    /**
     * 수정일시
     */
    private String modiDtm;

    /**
     * 원파일명
     */
    private String orignlFileNm;
    /**
     * 저장파일명
     */
    private String streFileNm;

    /**
     * 파일객체
     */
    private DiskFileItem fileItem;

    /**
     * 생성할 이미지 길이
     */
    private int width;

    /**
     * 생성할 이미지 높이
     */
    private int height;

    /**
     * 리스트 이미지 가로 사이즈
     */
    private int lstWidth;

    /**
     * 리스트 이미지 세로 사이즈
     */
    private int lstHeight;
    /**
     * 상세 이미지 가로 사이즈
     */
    private int dtlWidth;

    /**
     * 상세 이미지 세로 사이즈
     */
    private int dtlHeight;
    /**
     * 확장 이미지 가로 사이즈
     */
    private int elgmWidth;

    /**
     * 확장 이미지 세로 사이즈
     */
    private int elgmHeight;
    /**
     * 썸네일 이미지 다운로드 여부
     */
    private boolean thnImgDownloadFlag;

    /** Download Image Kind */
    private String downloadImageKind;

    public String getHtmlFieldName() {
        return htmlFieldName;
    }

    public void setHtmlFieldName(String htmlFieldName) {
        this.htmlFieldName = htmlFieldName;
    }

    public long getAtchFilePtcsNo() {
        return atchFilePtcsNo;
    }

    public void setAtchFilePtcsNo(long atchFilePtcsNo) {
        this.atchFilePtcsNo = atchFilePtcsNo;
    }

    public long getAtchSeq() {
        return atchSeq;
    }

    public void setAtchSeq(long atchSeq) {
        this.atchSeq = atchSeq;
    }

    public String getDocKindCd() {
        return docKindCd;
    }

    public void setDocKindCd(String docKindCd) {
        this.docKindCd = docKindCd;
    }

    public String getAtchFileNm() {
        return atchFileNm;
    }

    public void setAtchFileNm(String atchFileNm) {
        this.atchFileNm = atchFileNm;
    }

    public String getPhysFileNm() {
        return physFileNm;
    }

    public void setPhysFileNm(String physFileNm) {
        this.physFileNm = physFileNm;
    }

    public String getSbtnTxt() {
        return sbtnTxt;
    }

    public void setSbtnTxt(String sbtnTxt) {
        this.sbtnTxt = sbtnTxt;
    }

    public String getThnlNm() {
        return thnlNm;
    }

    public void setThnlNm(String thnlNm) {
        this.thnlNm = thnlNm;
    }

    public String getLstFileNm() {
        return lstFileNm;
    }

    public void setLstFileNm(String lstFileNm) {
        this.lstFileNm = lstFileNm;
    }

    public String getDtlFileNm() {
        return dtlFileNm;
    }

    public void setDtlFileNm(String dtlFileNm) {
        this.dtlFileNm = dtlFileNm;
    }

    public String getElgmFileNm() {
        return elgmFileNm;
    }

    public void setElgmFileNm(String elgmFileNm) {
        this.elgmFileNm = elgmFileNm;
    }

    public String getMimeInfoCntn() {
        return mimeInfoCntn;
    }

    public void setMimeInfoCntn(String mimeInfoCntn) {
        this.mimeInfoCntn = mimeInfoCntn;
    }

    public String getFilePthCntn() {
        return filePthCntn;
    }

    public void setFilePthCntn(String filePthCntn) {
        this.filePthCntn = filePthCntn;
    }

    public String getFileSizeVal() {
        return fileSizeVal;
    }

    public void setFileSizeVal(String fileSizeVal) {
        this.fileSizeVal = fileSizeVal;
    }

    public String getCltrMnmtNo() {
        return cltrMnmtNo;
    }

    public void setCltrMnmtNo(String cltrMnmtNo) {
        this.cltrMnmtNo = cltrMnmtNo;
    }

    public String getRgstDtm() {
        return rgstDtm;
    }

    public void setRgstDtm(String rgstDtm) {
        this.rgstDtm = rgstDtm;
    }

    public String getModiDtm() {
        return modiDtm;
    }

    public void setModiDtm(String modiDtm) {
        this.modiDtm = modiDtm;
    }

    public String getOrignlFileNm() {
        return orignlFileNm;
    }

    public void setOrignlFileNm(String orignlFileNm) {
        this.orignlFileNm = orignlFileNm;
    }

    public String getStreFileNm() {
        return streFileNm;
    }

    public void setStreFileNm(String streFileNm) {
        this.streFileNm = streFileNm;
    }

    public DiskFileItem getFileItem() {
        return fileItem;
    }

    public void setFileItem(DiskFileItem fileItem) {
        this.fileItem = fileItem;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLstWidth() {
        return lstWidth;
    }

    public void setLstWidth(int lstWidth) {
        this.lstWidth = lstWidth;
    }

    public int getLstHeight() {
        return lstHeight;
    }

    public void setLstHeight(int lstHeight) {
        this.lstHeight = lstHeight;
    }

    public int getDtlWidth() {
        return dtlWidth;
    }

    public void setDtlWidth(int dtlWidth) {
        this.dtlWidth = dtlWidth;
    }

    public int getDtlHeight() {
        return dtlHeight;
    }

    public void setDtlHeight(int dtlHeight) {
        this.dtlHeight = dtlHeight;
    }

    public int getElgmWidth() {
        return elgmWidth;
    }

    public void setElgmWidth(int elgmWidth) {
        this.elgmWidth = elgmWidth;
    }

    public int getElgmHeight() {
        return elgmHeight;
    }

    public void setElgmHeight(int elgmHeight) {
        this.elgmHeight = elgmHeight;
    }

    public boolean getThnImgDownloadFlag() {
        return thnImgDownloadFlag;
    }

    public void setThnImgDownloadFlag(boolean thnImgDownloadFlag) {
        this.thnImgDownloadFlag = thnImgDownloadFlag;
    }

    public String getDownloadImageKind() {
        return downloadImageKind;
    }

    public void setDownloadImageKind(String downloadImageKind) {
        this.downloadImageKind = downloadImageKind;
    }

    /**
     * <pre>
     * 1. 개요 : 
     * </pre>
     * @Method Name : toString
     * @author : dgu
     * @date : 2019. 3. 23.
     * @see java.lang.Object#toString()
     * @return
     */ 	
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FileVO [htmlFieldName=");
        builder.append(htmlFieldName);
        builder.append(", atchFilePtcsNo=");
        builder.append(atchFilePtcsNo);
        builder.append(", atchSeq=");
        builder.append(atchSeq);
        builder.append(", docKindCd=");
        builder.append(docKindCd);
        builder.append(", atchFileNm=");
        builder.append(atchFileNm);
        builder.append(", physFileNm=");
        builder.append(physFileNm);
        builder.append(", sbtnTxt=");
        builder.append(sbtnTxt);
        builder.append(", thnlNm=");
        builder.append(thnlNm);
        builder.append(", lstFileNm=");
        builder.append(lstFileNm);
        builder.append(", dtlFileNm=");
        builder.append(dtlFileNm);
        builder.append(", elgmFileNm=");
        builder.append(elgmFileNm);
        builder.append(", mimeInfoCntn=");
        builder.append(mimeInfoCntn);
        builder.append(", filePthCntn=");
        builder.append(filePthCntn);
        builder.append(", fileSizeVal=");
        builder.append(fileSizeVal);
        builder.append(", cltrMnmtNo=");
        builder.append(cltrMnmtNo);
        builder.append(", rgstDtm=");
        builder.append(rgstDtm);
        builder.append(", modiDtm=");
        builder.append(modiDtm);
        builder.append(", orignlFileNm=");
        builder.append(orignlFileNm);
        builder.append(", streFileNm=");
        builder.append(streFileNm);
        builder.append(", fileItem=");
        builder.append(fileItem);
        builder.append(", width=");
        builder.append(width);
        builder.append(", height=");
        builder.append(height);
        builder.append(", lstWidth=");
        builder.append(lstWidth);
        builder.append(", lstHeight=");
        builder.append(lstHeight);
        builder.append(", dtlWidth=");
        builder.append(dtlWidth);
        builder.append(", dtlHeight=");
        builder.append(dtlHeight);
        builder.append(", elgmWidth=");
        builder.append(elgmWidth);
        builder.append(", elgmHeight=");
        builder.append(elgmHeight);
        builder.append(", thnImgDownloadFlag=");
        builder.append(thnImgDownloadFlag);
        builder.append(", downloadImageKind=");
        builder.append(downloadImageKind);
        builder.append("]");
        return builder.toString();
    }

}
