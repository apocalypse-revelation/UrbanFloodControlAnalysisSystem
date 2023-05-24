package cn.edu.zucc.floodprevention.Dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.edu.zucc.floodprevention.Entity.AvgRiver;

public interface AvgRiverRepository extends JpaRepository<AvgRiver, String>{
	@Query(nativeQuery = true, value = "select * from avgriver")
	public List<AvgRiver> getAvgRiver();
}
