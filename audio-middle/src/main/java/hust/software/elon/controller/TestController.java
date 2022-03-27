package hust.software.elon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elon
 * @date 2022/3/27 16:44
 */
@RequestMapping("/test")
@RestController
public class TestController {
    @RequestMapping("/about")
    public String testMe(){
        return "safety audit";
    }
}
