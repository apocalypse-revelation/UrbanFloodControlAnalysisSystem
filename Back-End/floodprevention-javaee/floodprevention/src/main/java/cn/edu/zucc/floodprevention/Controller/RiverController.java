package cn.edu.zucc.floodprevention.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.zucc.floodprevention.Dao.RiverRepository;
import cn.edu.zucc.floodprevention.Entity.River;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/river")
public class RiverController {

	@Autowired
	private RiverRepository riverRepository;
	
	@GetMapping("/findRiverByStcdAndTime/{tm}/{stcd}")
	public List<River> findRiverByStcdAndTime(@PathVariable("tm") String tm, @PathVariable("stcd") String stcd)
	{
		List<River> lr = riverRepository.getRiverByStcdAndTime(tm, stcd);
		return lr;
	}
	
	@GetMapping("/findRiverByStcd/{stcd}")
	public List<Object> findRiverByStcd(@PathVariable("stcd") String stcd)
	{
		List<Object> lr = riverRepository.getRiverByStcd(stcd);
		return lr;
	}
}
