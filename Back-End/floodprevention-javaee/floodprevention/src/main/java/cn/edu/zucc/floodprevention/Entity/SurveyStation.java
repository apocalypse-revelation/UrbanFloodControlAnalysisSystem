package cn.edu.zucc.floodprevention.Entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m01_st_stbprp_b") //数据库表名
public class SurveyStation {
	@Id
	@Column(name="STCD")
	private String STCD;
	
	@Column(name="STNM")
	private String STNM;
	
	@Column(name="RVNM")
	private String RVNM;
	
	@Column(name="HNNM")
	private String HNNM;
	
	@Column(name="BSNM")
	private String BSNM;
	
	@Column(name="LGTD")
	private Double LGTD;
	
	@Column(name="LTTD")
	private Double LTTD;
	
	@Column(name="STLC")
	private String STLC;
	
	@Column(name="ADDVCD")
	private String ADDVCD;
	
	@Column(name="MDBZ")
	private BigDecimal MDBZ;
	
	@Column(name="MDPR")
	private String MDPR;
	
	@Column(name="DTMNM")
	private String DTMNM;
	
	@Column(name="DTMEL")
	private BigDecimal DTMEL;
	
	@Column(name="STTP")
	private String STTP;
	
	@Column(name="DFRTMS")
	private BigDecimal DFRTMS;
	
	@Column(name="FRITM")
	private String FRITM;
	
	@Column(name="FRGRD")
	private String FRGRD;
	
	@Column(name="BGFRYM")
	private String BGFRYM;
	
	@Column(name="EDFRYM")
	private String EDFRYM;
	
	@Column(name="ADMAUTH")
	private String ADMAUTH;
	
	@Column(name="STBK")
	private String STBK;
	
	@Column(name="DRNA")
	private BigDecimal DRNA;
	
	@Column(name="PHCD")
	private String PHCD;
	
	@Column(name="ZHHS_Display")
	private Integer ZHHS_Display;
	
	@Column(name="ISIMPORTANT")
	private Integer ISIMPORTANT;
	
	@Column(name="JGSW")
	private BigDecimal JGSW;
	
	@Column(name="BZSW")
	private BigDecimal BZSW;

	public String getSTCD() {
		return STCD;
	}

	public void setSTCD(String sTCD) {
		STCD = sTCD;
	}

	public String getSTNM() {
		return STNM;
	}

	public void setSTNM(String sTNM) {
		STNM = sTNM;
	}

	public String getRVNM() {
		return RVNM;
	}

	public void setRVNM(String rVNM) {
		RVNM = rVNM;
	}

	public String getHNNM() {
		return HNNM;
	}

	public void setHNNM(String hNNM) {
		HNNM = hNNM;
	}

	public String getBSNM() {
		return BSNM;
	}

	public void setBSNM(String bSNM) {
		BSNM = bSNM;
	}

	public Double getLGTD() {
		return LGTD;
	}

	public void setLGTD(Double lGTD) {
		LGTD = lGTD;
	}

	public Double getLTTD() {
		return LTTD;
	}

	public void setLTTD(Double lTTD) {
		LTTD = lTTD;
	}

	public String getSTLC() {
		return STLC;
	}

	public void setSTLC(String sTLC) {
		STLC = sTLC;
	}

	public String getADDVCD() {
		return ADDVCD;
	}

	public void setADDVCD(String aDDVCD) {
		ADDVCD = aDDVCD;
	}

	public BigDecimal getMDBZ() {
		return MDBZ;
	}

	public void setMDBZ(BigDecimal mDBZ) {
		MDBZ = mDBZ;
	}

	public String getMDPR() {
		return MDPR;
	}

	public void setMDPR(String mDPR) {
		MDPR = mDPR;
	}

	public String getDTMNM() {
		return DTMNM;
	}

	public void setDTMNM(String dTMNM) {
		DTMNM = dTMNM;
	}

	public BigDecimal getDTMEL() {
		return DTMEL;
	}

	public void setDTMEL(BigDecimal dTMEL) {
		DTMEL = dTMEL;
	}

	public String getSTTP() {
		return STTP;
	}

	public void setSTTP(String sTTP) {
		STTP = sTTP;
	}

	public BigDecimal getDFRTMS() {
		return DFRTMS;
	}

	public void setDFRTMS(BigDecimal dFRTMS) {
		DFRTMS = dFRTMS;
	}

	public String getFRITM() {
		return FRITM;
	}

	public void setFRITM(String fRITM) {
		FRITM = fRITM;
	}

	public String getFRGRD() {
		return FRGRD;
	}

	public void setFRGRD(String fRGRD) {
		FRGRD = fRGRD;
	}

	public String getBGFRYM() {
		return BGFRYM;
	}

	public void setBGFRYM(String bGFRYM) {
		BGFRYM = bGFRYM;
	}

	public String getEDFRYM() {
		return EDFRYM;
	}

	public void setEDFRYM(String eDFRYM) {
		EDFRYM = eDFRYM;
	}

	public String getADMAUTH() {
		return ADMAUTH;
	}

	public void setADMAUTH(String aDMAUTH) {
		ADMAUTH = aDMAUTH;
	}

	public String getSTBK() {
		return STBK;
	}

	public void setSTBK(String sTBK) {
		STBK = sTBK;
	}

	public BigDecimal getDRNA() {
		return DRNA;
	}

	public void setDRNA(BigDecimal dRNA) {
		DRNA = dRNA;
	}

	public String getPHCD() {
		return PHCD;
	}

	public void setPHCD(String pHCD) {
		PHCD = pHCD;
	}

	public Integer getZHHS_Display() {
		return ZHHS_Display;
	}

	public void setZHHS_Display(Integer zHHS_Display) {
		ZHHS_Display = zHHS_Display;
	}

	public Integer getISIMPORTANT() {
		return ISIMPORTANT;
	}

	public void setISIMPORTANT(Integer iSIMPORTANT) {
		ISIMPORTANT = iSIMPORTANT;
	}

	public BigDecimal getJGSW() {
		return JGSW;
	}

	public void setJGSW(BigDecimal jGSW) {
		JGSW = jGSW;
	}

	public BigDecimal getBZSW() {
		return BZSW;
	}

	public void setBZSW(BigDecimal bZSW) {
		BZSW = bZSW;
	}

}
