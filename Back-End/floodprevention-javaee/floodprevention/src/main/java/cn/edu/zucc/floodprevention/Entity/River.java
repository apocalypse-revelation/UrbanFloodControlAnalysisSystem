package cn.edu.zucc.floodprevention.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(RiverCom.class)
@Table(name="m02_st_river_r0") //数据库表名
public class River {

	@Id
	@Column(name="STCD")
	private String STCD;
	@Id
	@Column(name="TM")
	private Date TM;
	
	@Column(name="Z")
	private BigDecimal Z;
	
	@Column(name="Q")
	private BigDecimal Q;
	
	@Column(name="XSA")
	private BigDecimal XSA;
	
	@Column(name="XSAVV")
	private BigDecimal XSAVV;
	
	@Column(name="XSMXV")
	private BigDecimal XSMXV;
	
	@Column(name="FLWCHRCD")
	private String FLWCHRCD;
	
	@Column(name="WPTN")
	private String WPTN;
	
	@Column(name="MSQMT")
	private String MSQMT;
	
	@Column(name="MSAMT")
	private String MSAMT;
	
	@Column(name="MSVMT")
	private String MSVMT;
	
	@Column(name="Err")
	private String Err;
	
	@Column(name="tSTCD")
	private String tSTCD;

	public String getSTCD() {
		return STCD;
	}

	public void setSTCD(String sTCD) {
		STCD = sTCD;
	}

	public Date getTM() {
		return TM;
	}

	public void setTM(Date tM) {
		TM = tM;
	}

	public BigDecimal getZ() {
		return Z;
	}

	public void setZ(BigDecimal z) {
		Z = z;
	}

	public BigDecimal getQ() {
		return Q;
	}

	public void setQ(BigDecimal q) {
		Q = q;
	}

	public BigDecimal getXSA() {
		return XSA;
	}

	public void setXSA(BigDecimal xSA) {
		XSA = xSA;
	}

	public BigDecimal getXSAVV() {
		return XSAVV;
	}

	public void setXSAVV(BigDecimal xSAVV) {
		XSAVV = xSAVV;
	}

	public BigDecimal getXSMXV() {
		return XSMXV;
	}

	public void setXSMXV(BigDecimal xSMXV) {
		XSMXV = xSMXV;
	}

	public String getFLWCHRCD() {
		return FLWCHRCD;
	}

	public void setFLWCHRCD(String fLWCHRCD) {
		FLWCHRCD = fLWCHRCD;
	}

	public String getWPTN() {
		return WPTN;
	}

	public void setWPTN(String wPTN) {
		WPTN = wPTN;
	}

	public String getMSQMT() {
		return MSQMT;
	}

	public void setMSQMT(String mSQMT) {
		MSQMT = mSQMT;
	}

	public String getMSAMT() {
		return MSAMT;
	}

	public void setMSAMT(String mSAMT) {
		MSAMT = mSAMT;
	}

	public String getMSVMT() {
		return MSVMT;
	}

	public void setMSVMT(String mSVMT) {
		MSVMT = mSVMT;
	}

	public String getErr() {
		return Err;
	}

	public void setErr(String err) {
		Err = err;
	}

	public String gettSTCD() {
		return tSTCD;
	}

	public void settSTCD(String tSTCD) {
		this.tSTCD = tSTCD;
	}
	
	
}
