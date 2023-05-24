package cn.edu.zucc.floodprevention.Dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.edu.zucc.floodprevention.Entity.PrReservoir;
import cn.edu.zucc.floodprevention.Entity.Precipitation;
import cn.edu.zucc.floodprevention.Entity.SurveyStation;

public interface SurveyStationRepository extends JpaRepository<SurveyStation, String>{

	@Query(nativeQuery = true, value = "select * from m01_st_stbprp_b")
	public List<SurveyStation> getSurveyStation();
	
	@Query(nativeQuery = true, value = "select * from m01_st_stbprp_b where STCD =:stcd")
	public List<SurveyStation> getSurveyStationByStcd(@Param("stcd") String stcd);
	
	@Query(nativeQuery = true, value = "select * from m01_st_stbprp_b where lgtd =:lgtd and lttd = :lttd")
	public List<SurveyStation> getSurveyStationByCoord(@Param("lgtd") String lgtd ,@Param("lttd") String lttd);
	
}
