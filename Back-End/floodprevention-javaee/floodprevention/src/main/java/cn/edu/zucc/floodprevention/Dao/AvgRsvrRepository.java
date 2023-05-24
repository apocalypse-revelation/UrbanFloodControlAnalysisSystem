package cn.edu.zucc.floodprevention.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.edu.zucc.floodprevention.Entity.AvgRiver;
import cn.edu.zucc.floodprevention.Entity.AvgRsvr;

public interface AvgRsvrRepository extends JpaRepository<AvgRsvr, String>{
	@Query(nativeQuery = true, value = "select * from avgrsvr")
	public List<AvgRsvr> getAvgRsvr();
}
