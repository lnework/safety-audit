package hust.software.elon.controller;

import hust.software.elon.safety.test.domain.TestDto;
import hust.software.elon.safety.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elon
 * @date 2022/3/29 16:03
 */
@RestController
@RequestMapping("/admin/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService.Iface testService;

    @RequestMapping("/me")
    public TestDto testMe(@RequestParam Long id) throws TException {
        return testService.queryById(id);
    }
}
