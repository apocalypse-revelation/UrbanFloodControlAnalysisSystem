package cn.edu.zucc.floodprevention.Controller;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.zucc.floodprevention.Dao.PrecipitationRepository;
import cn.edu.zucc.floodprevention.Entity.Precipitation;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/precipitation")
public class PrecipitationController{
	@Autowired
	private PrecipitationRepository precipitationRepository;	
//	@Test
	@GetMapping("/findPpValAndTime/{tm}/{stcd}")
	public Set<Precipitation> findPrep(@PathVariable("tm") String tm, @PathVariable("stcd") String stcd)
	{
		//"2009-08-11", "k1106"
		Set<Precipitation> spp = precipitationRepository.findPrp(tm, stcd);
//		for(Precipitation p:spp)
//		{
//			System.out.println(p.getStcd()+"  "+p.getTm()+"  "+p.getVal());
//		}
		return spp;
	}
	@GetMapping("/getPrpByStcd/{stcd}")
	public Set<Object> getPrpByStcd(@PathVariable("stcd") String stcd)
	{
		Set<Object> spp = precipitationRepository.getPrpByStcd(stcd);
		return spp;
	}
//	
}
