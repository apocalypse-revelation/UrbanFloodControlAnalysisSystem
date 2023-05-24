package cn.edu.zucc.floodprevention.Dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.edu.zucc.floodprevention.Entity.PrReservoir;
import cn.edu.zucc.floodprevention.Entity.PrReservoirCom;
import cn.edu.zucc.floodprevention.Entity.Precipitation;

public interface PrReservoirRepository extends JpaRepository<PrReservoir,PrReservoirCom> {

	@Query(nativeQuery = true, value = "select distinct tm,stcd,val,rz,rwptn from prprsvr where date_format(TM,'%Y-%m-%d') =:date and stcd =:stcd")
	public Set<PrReservoir> getPrRsvr(@Param("date") String date, @Param("stcd") String stcd);
	
}
