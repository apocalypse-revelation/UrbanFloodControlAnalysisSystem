package cn.edu.zucc.floodprevention.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class RiverCom implements Serializable{
	private String STCD;
	private Date TM;
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
	
}
