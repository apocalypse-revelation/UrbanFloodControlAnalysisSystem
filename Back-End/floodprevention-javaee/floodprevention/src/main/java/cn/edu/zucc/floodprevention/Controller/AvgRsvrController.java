package cn.edu.zucc.floodprevention.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.zucc.floodprevention.Dao.AvgRsvrRepository;
import cn.edu.zucc.floodprevention.Entity.AvgRsvr;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/avgRsvr")
public class AvgRsvrController {
	@Autowired
	private AvgRsvrRepository avgRsvrRepository;
	
	@GetMapping("/findAvgRsvr")
	public List<AvgRsvr> getAvgRsvr()
	{
		List<AvgRsvr> lar = avgRsvrRepository.getAvgRsvr();
		return lar;
	}
}
