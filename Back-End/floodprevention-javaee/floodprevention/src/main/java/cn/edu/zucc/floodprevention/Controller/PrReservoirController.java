package cn.edu.zucc.floodprevention.Controller;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.zucc.floodprevention.Dao.PrReservoirRepository;
import cn.edu.zucc.floodprevention.Entity.PrReservoir;
import cn.edu.zucc.floodprevention.Entity.Precipitation;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/prrsvr")
public class PrReservoirController {
	@Autowired
	private PrReservoirRepository prReservoirRepository;
	
//	@Test
	@GetMapping("/findPrRsvr/{tm}/{stcd}")
	public Set<PrReservoir> findPrRsvr(@PathVariable("tm") String tm, @PathVariable("stcd") String stcd)
	{
		//"2009-08-11", "k1106"
		Set<PrReservoir> spr = prReservoirRepository.getPrRsvr(tm, stcd);
//		for(PrReservoir p:spr)
//		{
//			System.out.println(p.getTm()+"  "+p.getStcd()+"  "+p.getVal()+p.getRz());
//		}
		return spr;
	}

}
