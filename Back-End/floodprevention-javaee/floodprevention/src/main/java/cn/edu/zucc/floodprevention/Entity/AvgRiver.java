package cn.edu.zucc.floodprevention.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="avgriver") //数据库表名
public class AvgRiver {
	@Column(name="stcd")
	private String stcd;
	
	@Id
	@Column(name="tm")
	private String tm;
	
	@Column(name="avgz")
	private BigDecimal avgz;
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
	public BigDecimal getAvgz() {
		return avgz;
	}
	public void setAvgz(BigDecimal avgz) {
		this.avgz = avgz;
	}
	
	
}
