package hust.software.elon.dto;

import cn.hutool.core.util.ObjectUtil;
import hust.software.elon.enums.ArbiterFunctionEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author elon
 * @date 2022/5/7 20:42
 */
@Data
@NoArgsConstructor
public class ArbiterNodeDto {
    private String id;

    private List<ArbiterNodeDto> children;

    private RecordAbstract record;

    private boolean leaf;

    private ArbiterFunctionEnum arbiterFunctionEnum;

    public boolean addNode(ArbiterNodeDto child){
//        叶子节点或上一层 不允许添加
        if(isLeaf()|| ObjectUtil.isNull(this.children))return false;
        this.children.add(child);
        return true;
    }

    public boolean addNodes(List<ArbiterNodeDto> nodes){
//        叶子节点或上一层 不允许添加
        if(isLeaf()|| ObjectUtil.isNull(children))return false;
        this.children.addAll(nodes);
        return true;
    }

//    获取结果时 自动仲裁
    public RecordAbstract getRecord(){
//        如果之前已经仲裁过
        if(isLeaf()||ObjectUtil.isNotNull(record))return record;
        RecordAbstract result = null;
        for(ArbiterNodeDto child: this.children){
            if(child.getRecord().arbiterCompare(arbiterFunctionEnum,result)){
                result = child.getRecord();
            }
        }
        this.record = result;
        return result;
    }

    public static ArbiterNodeDto  newLeafNode(RecordAbstract record){
        ArbiterNodeDto arbiterNodeDto = new ArbiterNodeDto();
        arbiterNodeDto.setRecord(record);
        arbiterNodeDto.setLeaf(true);
        return arbiterNodeDto;
    }

    public static ArbiterNodeDto  newParentNode(List<ArbiterNodeDto> children, ArbiterFunctionEnum arbiterFunctionEnum){
        ArbiterNodeDto arbiterNodeDto = new ArbiterNodeDto();
        arbiterNodeDto.setChildren(children);
        arbiterNodeDto.setArbiterFunctionEnum(arbiterFunctionEnum);
        arbiterNodeDto.setLeaf(false);
        return arbiterNodeDto;
    }

    public static ArbiterNodeDto  newParentNode(String id, ArbiterFunctionEnum arbiterFunctionEnum){
        ArbiterNodeDto arbiterNodeDto = new ArbiterNodeDto();
        arbiterNodeDto.setId(id);
        arbiterNodeDto.setLeaf(false);
        arbiterNodeDto.setArbiterFunctionEnum(arbiterFunctionEnum);
        arbiterNodeDto.setChildren(new ArrayList<>());
        return arbiterNodeDto;
    }

}