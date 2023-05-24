package cn.edu.zucc.floodprevention.Controller;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.zucc.floodprevention.Dao.SurveyStationRepository;
import cn.edu.zucc.floodprevention.Entity.SurveyStation;


//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/surveyStation")
public class SurveyStationController {
	@Autowired
	private SurveyStationRepository surveyStationRepository;
	
	@GetMapping("/findSurveyStation")
	public List<SurveyStation> findSurveyStation()
	{
		//		for(SurveyStation ss:sss)
//		{
//			System.out.println(ss.getSTCD()+" "+ss.getSTNM());
//		}
		return surveyStationRepository.getSurveyStation();
	}
	
	@GetMapping("/findSurveyStationByStcd/{num}")
	public List<SurveyStation> getSurveyStationByStcd(@PathVariable("num") String stcd)
	{
		List<SurveyStation> lss = surveyStationRepository.getSurveyStationByStcd(stcd);
//		for(SurveyStation ss:sss)
//		{
//			System.out.println(ss.getSTCD()+" "+ss.getSTNM());
//		}
		return lss;
	}
	@GetMapping("/getSurveyStationByCoord/{lgtd}/{lttd}")
	public List<SurveyStation> getSurveyStationByCoord(@PathVariable("lgtd") String lgtd ,@PathVariable("lttd") String lttd)
	{
		List<SurveyStation> lss = surveyStationRepository.getSurveyStationByCoord(lgtd, lttd);
		return lss;
	}
	
}
