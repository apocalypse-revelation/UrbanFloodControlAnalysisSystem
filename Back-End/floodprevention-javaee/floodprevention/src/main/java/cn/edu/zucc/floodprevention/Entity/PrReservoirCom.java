package cn.edu.zucc.floodprevention.Entity;

import java.io.Serializable;
import java.util.Date;

public class PrReservoirCom implements Serializable
{
	private String stcd;
	private Date tm;
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
}
