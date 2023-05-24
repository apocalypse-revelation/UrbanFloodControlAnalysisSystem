package cn.edu.zucc.floodprevention.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(PrReservoirCom.class)
@Table(name="prprsvr") //数据库表名
public class PrReservoir {
	@Id
	@Column(name="STCD")
	private String stcd;
	
	@Id
	@Column(name="TM")
	private Date tm;
	
	@Column(name="Val")
	private BigDecimal val;
	
	@Column(name="RZ")
	private BigDecimal rz;
	
	@Column(name="RWPTN")
	private BigDecimal wrptn;

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

	public BigDecimal getVal() {
		return val;
	}

	public void setVal(BigDecimal val) {
		this.val = val;
	}

	public BigDecimal getRz() {
		return rz;
	}

	public void setRz(BigDecimal rz) {
		this.rz = rz;
	}

	public BigDecimal getWrptn() {
		return wrptn;
	}

	public void setWrptn(BigDecimal wrptn) {
		this.wrptn = wrptn;
	}
	
	
}
