package hust.software.elon.controller;

import hust.software.elon.domain.TestTable;
import hust.software.elon.mapper.TestTableMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elon
 * @date 2022/3/27 21:14
 */

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestTableMapper testTableMapper;

    @RequestMapping("/me")
    public TestTable testMe(){
        log.info("test me!");
        return testTableMapper.selectByPrimaryKey(0L);
    }
}
