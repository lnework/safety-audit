package hust.software.elon.dto;

import cn.hutool.core.util.ObjectUtil;
import hust.software.elon.enums.ArbiterFunctionEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author elon
 * @date 2022/5/8 14:34
 */
@Data
public abstract class RecordAbstract {

    private Long id;
    private Long ObjectId;
    private String extraJson;
    private Date auditTime;

//    必须能够根据结果比较
    public boolean arbiterCompare(ArbiterFunctionEnum arbiterFunctionEnum, RecordAbstract record){
        if(ObjectUtil.isNull(record))return true;
        return this.getPriority(arbiterFunctionEnum) <= record.getPriority(arbiterFunctionEnum);
    }

    public abstract long getPriority(ArbiterFunctionEnum arbiterFunctionEnum);
}
