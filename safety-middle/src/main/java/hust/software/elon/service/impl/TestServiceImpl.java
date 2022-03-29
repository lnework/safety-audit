package hust.software.elon.service.impl;

import hust.software.elon.domain.TestTable;
import hust.software.elon.mapper.TestTableMapper;
import hust.software.elon.safety.test.domain.TestDto;
import hust.software.elon.safety.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

/**
 * @author elon
 * @date 2022/3/29 15:49
 */
@Service("testServiceImpl")
@RequiredArgsConstructor
public class TestServiceImpl implements TestService.Iface {

    private final TestTableMapper testTableMapper;

    @Override
    public TestDto queryById(long id) throws TException {
        TestTable testTable = testTableMapper.selectByPrimaryKey(id);
        return new TestDto(testTable.getId(), testTable.getName());
    }
}
