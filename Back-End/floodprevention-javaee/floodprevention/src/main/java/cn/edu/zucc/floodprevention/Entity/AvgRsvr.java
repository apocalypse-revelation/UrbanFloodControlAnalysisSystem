package cn.edu.zucc.floodprevention.Entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="avgrsvr") //数据库表名
public class AvgRsvr {
	@Column(name="stcd")
	private String stcd;
	@Id
	@Column(name="tm")
	private String tm;
	
	@Column(name="avgrz")
	private BigDecimal avgrz;
	
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public BigDecimal getAvgrz() {
		return avgrz;
	}
	public void setAvgrz(BigDecimal avgrz) {
		this.avgrz = avgrz;
	}
	
	
	
}
