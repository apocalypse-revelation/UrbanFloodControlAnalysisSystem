package cn.edu.zucc.floodprevention.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.zucc.floodprevention.Dao.AvgRiverRepository;
import cn.edu.zucc.floodprevention.Entity.AvgRiver;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/avgRiver")
public class AvgRiverController {
	@Autowired
	private AvgRiverRepository avgRiverRepository;
	
	@GetMapping("/findAvgRiver")
	public List<AvgRiver> findAvgRiver()
	{
		List<AvgRiver> lar = avgRiverRepository.getAvgRiver();
		return lar;
	}
}
