package cn.edu.zucc.floodprevention.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@IdClass(PrecipitationCom.class)
@Table(name="m02_st_pptn_r0") //数据库表名
public class Precipitation {
	@Id
	@Column(name="stcd")
	private String stcd;
	
	@Id
	@Column(name="TM")
	private Date tm;
	
	@Column(name="Val")
	private BigDecimal val;
	
	@Column(name="Sub_val")
	private BigDecimal sub_val;
	
	@Column(name="err")
	private String err;
	
	@Column(name="tSTCD")
	private String tstcd;
	
	//getter&setter
	
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
	public BigDecimal getSub_val() {
		return sub_val;
	}
	public void setSub_val(BigDecimal sub_val) {
		this.sub_val = sub_val;
	}
	//	public double getVal() {
//		return val;
//	}
//	public void setVal(double val) {
//		this.val = val;
//	}
//	public double getSub_val() {
//		return sub_val;
//	}
//	public void setSub_val(double sub_val) {
//		this.sub_val = sub_val;
//	}
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public String getTstcd() {
		return tstcd;
	}
	public void setTstcd(String tstcd) {
		this.tstcd = tstcd;
	}
	
	
}
