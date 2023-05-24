package cn.edu.zucc.floodprevention.Controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.zucc.floodprevention.Dao.ReservoirRepository;
import cn.edu.zucc.floodprevention.Entity.Reservoir;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reservoir")
public class ReservoirController {

	@Autowired
	private ReservoirRepository reservoirRepository;
	
	@GetMapping("/findRsvrByStcd/{stcd}")
	public Set<Object> findRsvrByStcd(@PathVariable("stcd") String stcd)
	{
		Set<Object> sr = reservoirRepository.getRsvrByStcd(stcd);
		return sr;
	}
}
