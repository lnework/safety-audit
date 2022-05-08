package hust.software.elon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.ImmutableList;
import hust.software.elon.domain.AudioAuditRecord;
import hust.software.elon.dto.ArbiterNodeDto;
import hust.software.elon.dto.AudioArbiterResultDto;
import hust.software.elon.dto.AudioRecordDto;
import hust.software.elon.enums.ArbiterFunctionEnum;
import hust.software.elon.enums.AudioAuditSourceEnum;
import hust.software.elon.mapper.AudioAuditRecordMapper;
import hust.software.elon.service.AudioArbiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/5/8 16:48
 */
@Service
@RequiredArgsConstructor
public class AudioArbiterServiceImpl implements AudioArbiterService {
    private final AudioAuditRecordMapper recordMapper;

    @Override
    public AudioRecordDto judgeRecord(AudioRecordDto audioRecordDto) {
        AudioArbiterResultDto audioArbiterResultDto = judgeRecordDetail(audioRecordDto);
        return audioArbiterResultDto.getJudgeResult();
    }

    @Override
    public AudioArbiterResultDto judgeRecordDetail(AudioRecordDto audioRecordDto) {
        List<AudioRecordDto> audioRecordDtoList = getHistoryRecord(audioRecordDto.getObjectId());
//        不管数据库里是有记录 此次结果都是幂等的
        audioRecordDtoList.add(audioRecordDto);
        ArbiterNodeDto arbiterTree = getArbiterTree(audioRecordDtoList);
        AudioRecordDto judgeResult = (AudioRecordDto) arbiterTree.getRecord();
        Map<String, AudioRecordDto> id2record = new HashMap<>();
//        层序遍历获取每一节点的结果(非叶子)
        Queue<ArbiterNodeDto> que = new LinkedList<>();
        que.add(arbiterTree);
        while(!que.isEmpty()){
            ArbiterNodeDto node = que.poll();
            id2record.put(node.getId(), (AudioRecordDto) node.getRecord());
            for(ArbiterNodeDto child: node.getChildren())
                if (!child.isLeaf())que.add(child);
        }
        return new AudioArbiterResultDto(judgeResult, id2record);
    }


    private List<AudioRecordDto> getHistoryRecord(Long objectId){
        List<AudioAuditRecord> audioAuditRecordList = recordMapper.selectByObjectId(objectId);
        return audioAuditRecordList.stream()
                .map(AudioRecordDto::convertFromEntity).collect(Collectors.toList());
    }


    private ArbiterNodeDto getArbiterTree(List<AudioRecordDto> audioRecordDtoList){
        ArbiterNodeDto firstReviewNode = ArbiterNodeDto.newParentNode("firstReview", ArbiterFunctionEnum.STRICT);
        ArbiterNodeDto secondReviewNode = ArbiterNodeDto.newParentNode("secondReview",ArbiterFunctionEnum.STRICT);

        ArbiterNodeDto reviewNode = ArbiterNodeDto.newParentNode("review",ArbiterFunctionEnum.STRICT);
        reviewNode.addNodes(ImmutableList.of(firstReviewNode, secondReviewNode));
//        这两个再取最严 之后与下面的取最新
        ArbiterNodeDto userReportNode = ArbiterNodeDto.newParentNode("userReport",ArbiterFunctionEnum.NEW);
        ArbiterNodeDto userAppealNode = ArbiterNodeDto.newParentNode("userAppeal",ArbiterFunctionEnum.NEW);
        ArbiterNodeDto adminAdjustNode = ArbiterNodeDto.newParentNode("adminAdjust",ArbiterFunctionEnum.NEW);

        ArbiterNodeDto audioArbiterTree = ArbiterNodeDto.newParentNode("audioArbiter",ArbiterFunctionEnum.NEW);
        audioArbiterTree.addNodes(ImmutableList.of(adminAdjustNode, userAppealNode,
                userReportNode, reviewNode));

        Map<Integer, ArbiterNodeDto> source2arbiter = new HashMap<>();
        source2arbiter.put(AudioAuditSourceEnum.FIRST_REVIEW.getCode(), firstReviewNode);
        source2arbiter.put(AudioAuditSourceEnum.SECOND_REVIEW.getCode(), secondReviewNode);
        source2arbiter.put(AudioAuditSourceEnum.USER_REPORT.getCode(), userReportNode);
        source2arbiter.put(AudioAuditSourceEnum.USER_APPEAL.getCode(), userAppealNode);
        source2arbiter.put(AudioAuditSourceEnum.ADMIN_ADJUST.getCode(), adminAdjustNode);

        for(AudioRecordDto audioRecordDto: audioRecordDtoList){
            ArbiterNodeDto parentArbiterNodeDto = source2arbiter.get(audioRecordDto.getSourceEnum().getCode());
            if(ObjectUtil.isNull(audioRecordDto))
                continue;
            parentArbiterNodeDto.addNode(ArbiterNodeDto.newLeafNode(audioRecordDto));
        }
        return audioArbiterTree;
    }

}
