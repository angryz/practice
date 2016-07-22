package springboot.sample.tomcat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.sample.tomcat.service.SampleService;

/**
 * Created by zzp on 7/21/16.
 */
@Controller
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return sampleService.getHelloMessage();
    }

}
