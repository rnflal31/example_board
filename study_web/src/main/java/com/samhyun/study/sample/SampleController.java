package com.samhyun.study.sample;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {

	Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="/sample/openSamplePage.do")
	public ModelAndView openSamplePage() throws Exception {
		ModelAndView mv = new ModelAndView("");
		log.info("asdfs");
		return mv;		
	}
}
