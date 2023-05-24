package cn.edu.zucc.floodprevention.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(ReservoirCom.class)
@Table(name="m02_st_rsvr_r0") //数据库表名
public class Reservoir {
	@Id
	@Column(name="STCD")
	private String stcd;
	
	@Id
	@Column(name="TM")
	private Date tm;
	
	@Column(name="RZ")
	private BigDecimal RZ;
	
	@Column(name="INQ")
	private BigDecimal INQ;
	
	@Column(name="W")
	private BigDecimal W;
	
	@Column(name="OTQ")
	private BigDecimal OTQ;
	
	@Column(name="RWCHRCD")
	private String RWCHRCD;
	
	@Column(name="RWPTN")
	private String RWPTN;
	
	@Column(name="INQDR")
	private BigDecimal INQDR;
	
	@Column(name="MSQMT")
	private String MSQMT;
	
	@Column(name="ERR")
	private String ERR;
	
	@Column(name="tSTCD")
	private String tSTCD;

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public Date getTm() {
		return tm;
	}

	public void setTm(Date tm) {
		this.tm = tm;
	}

	public BigDecimal getRZ() {
		return RZ;
	}

	public void setRZ(BigDecimal rZ) {
		RZ = rZ;
	}

	public BigDecimal getINQ() {
		return INQ;
	}

	public void setINQ(BigDecimal iNQ) {
		INQ = iNQ;
	}

	public BigDecimal getW() {
		return W;
	}

	public void setW(BigDecimal w) {
		W = w;
	}

	public BigDecimal getOTQ() {
		return OTQ;
	}

	public void setOTQ(BigDecimal oTQ) {
		OTQ = oTQ;
	}

	public String getRWCHRCD() {
		return RWCHRCD;
	}

	public void setRWCHRCD(String rWCHRCD) {
		RWCHRCD = rWCHRCD;
	}

	public String getRWPTN() {
		return RWPTN;
	}

	public void setRWPTN(String rWPTN) {
		RWPTN = rWPTN;
	}

	public BigDecimal getINQDR() {
		return INQDR;
	}

	public void setINQDR(BigDecimal iNQDR) {
		INQDR = iNQDR;
	}

	public String getMSQMT() {
		return MSQMT;
	}

	public void setMSQMT(String mSQMT) {
		MSQMT = mSQMT;
	}

	public String getERR() {
		return ERR;
	}

	public void setERR(String eRR) {
		ERR = eRR;
	}

	public String gettSTCD() {
		return tSTCD;
	}

	public void settSTCD(String tSTCD) {
		this.tSTCD = tSTCD;
	}
}
