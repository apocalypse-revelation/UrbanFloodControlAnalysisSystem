package cn.edu.zucc.floodprevention.Dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.edu.zucc.floodprevention.Entity.Reservoir;
import cn.edu.zucc.floodprevention.Entity.ReservoirCom;

public interface ReservoirRepository extends JpaRepository<Reservoir, ReservoirCom>{

	@Query(nativeQuery = true, value = "SELECT stcd,date_format(TM,'%Y-%m') as daytm ,avg(rz) as avgrz FROM `flood-prevention`.m02_st_rsvr_r0 where stcd=:stcd group by stcd,date_format(TM,'%Y-%m')")
	public Set<Object> getRsvrByStcd(@Param("stcd") String stcd);
}
