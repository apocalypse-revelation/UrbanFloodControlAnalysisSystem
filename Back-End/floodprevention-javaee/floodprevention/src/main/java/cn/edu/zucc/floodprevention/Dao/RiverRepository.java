package cn.edu.zucc.floodprevention.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.edu.zucc.floodprevention.Entity.River;
import cn.edu.zucc.floodprevention.Entity.RiverCom;

public interface RiverRepository extends JpaRepository<River, RiverCom>{
	@Query(nativeQuery = true, value = "select * from m02_st_river_r0 where date_format(TM,'%Y-%m-%d') =:date and stcd =:stcd")
	public List<River> getRiverByStcdAndTime(@Param("date") String date, @Param("stcd") String stcd);
	
	@Query(nativeQuery = true, value = "SELECT stcd,date_format(TM,'%Y-%m') as daytm ,avg(z) as avgz FROM `flood-prevention`.m02_st_river_r0 where stcd=:stcd group by stcd,date_format(TM,'%Y-%m') ")
	public List<Object> getRiverByStcd(@Param("stcd") String stcd);
}
