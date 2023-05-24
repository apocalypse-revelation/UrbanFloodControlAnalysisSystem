package cn.edu.zucc.floodprevention.Dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;

import cn.edu.zucc.floodprevention.Entity.Precipitation;
import cn.edu.zucc.floodprevention.Entity.PrecipitationCom;

public interface PrecipitationRepository extends JpaRepository<Precipitation,PrecipitationCom>
{

	@Query(nativeQuery = true, value = "select distinct tm,stcd,val,err,tstcd,sub_val from m02_st_pptn_r0 where date_format(TM,'%Y-%m-%d') =:date and stcd =:stcd")
	public Set<Precipitation> findPrp(@Param("date") String date, @Param("stcd") String stcd);
	
	@Query(nativeQuery = true, value = "SELECT stcd,date_format(TM,'%Y-%m') as daytm ,avg(val) as avgval FROM `flood-prevention`.m02_st_pptn_r0 where stcd=:stcd group by stcd,date_format(TM,'%Y-%m') ")
	public Set<Object> getPrpByStcd(@Param("stcd") String stcd);
	
//	@Query(nativeQuery = true, value = "select count(*) from m01_st_stbprp_b")
//	public int getCount();
//	
//	@Query(value="SELECT * FROM st_rvfcch_b where stcd=:stcd;")
//	public Precipitation findByStcd(@Param("stcd") String stcd);
//	
//	@Query(value = "SELECT * FROM Precipitation")
//	public List<Precipitation> getstcd();
	
}
