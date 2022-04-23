package hust.software.elon.controller;


import com.alibaba.fastjson.JSONObject;
import com.hlin.sensitive.KWSeeker;
import com.hlin.sensitive.KWSeekerManage;
import com.hlin.sensitive.KeyWord;
import com.hlin.sensitive.SensitiveWordResult;
import hust.software.elon.dto.KeyWordDto;
import hust.software.elon.safety.model.domain.*;
import hust.software.elon.safety.model.service.AudioModelService;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author elon
 * @date 2022/4/16 10:47
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final AudioModelService.Iface modelService;


    @RequestMapping("/risk/sensitive-word")
    public void testSensitiveWord(){
        // 初始化敏感词
        Map<String, KeyWord> kwsMap = new HashMap<>();
        KeyWordDto keyWordDto1 = new KeyWordDto("黄色");
        KeyWordDto keyWordDto2 = new KeyWordDto("AV");
        kwsMap.put(keyWordDto1.getWord(), keyWordDto1);
        kwsMap.put(keyWordDto2.getWord(), keyWordDto2);
        Set<KeyWord> kws1 = new HashSet<>(kwsMap.values());

        Set<KeyWord> kws2 = new HashSet<KeyWord>();
        kws2.add(new KeyWordDto("中国"));
        kws2.add(new KeyWordDto("美国"));
        kws2.add(new KeyWordDto("黄色"));

// 根据敏感词,初始化敏感词搜索器
        KWSeeker kwSeeker1 = new KWSeeker(kws1);
        KWSeeker kwSeeker2 = new KWSeeker(kws2);

// 搜索器组,构建敏感词管理器,可同时管理多个搜索器，map的key为自定义搜索器标识
        Map<String, KWSeeker> seekers = new HashMap<String, KWSeeker>();
        String wordType1 = "sensitive-word-1";
        seekers.put(wordType1, kwSeeker1);
        String wordType2 = "sensitive-word-2";
        seekers.put(wordType2, kwSeeker2);

        KWSeekerManage kwSeekerManage = new KWSeekerManage(seekers);

// 开始测试
        String text1 = "这是一部黄色电影，也是一部AV电影";
        System.out.println("原文：" + text1);
        System.out.println("敏感词：" + kws1);
        System.out.println("识别结果:");

        List<SensitiveWordResult> words1 = kwSeekerManage.getKWSeeker(wordType1).findWords(text1);

        System.out.println("返回敏感词及下标：" + words1);
        String s1 = kwSeekerManage.getKWSeeker(wordType1).htmlHighlightWords(text1);
        System.out.println("html高亮：" + s1);
        String r1 = kwSeekerManage.getKWSeeker(wordType1).replaceWords(text1);
        System.out.println("字符替换：" + r1);
        for (SensitiveWordResult result: words1){
            KeyWordDto keyWordDto = (KeyWordDto) kwsMap.get(result.getWord());
            System.out.println(keyWordDto);
            System.out.println(JSONObject.toJSONString(keyWordDto));
        }

        System.out.println("\n=================================================\n");
// 开始测试
        String text2 = "在中国这是一部黄色电影，在日本这不是一部黄色电影，在美国这不是一部黄色电影";
        System.out.println("原文：" + text2);
        System.out.println("敏感词：" + kws2);
        System.out.println("识别结果:");

        List<SensitiveWordResult> words2 = kwSeekerManage.getKWSeeker(wordType2).findWords(text2);
        System.out.println("返回敏感词及下标：" + words2);
        String s2 = kwSeekerManage.getKWSeeker(wordType2).htmlHighlightWords(text2);
        System.out.println("html高亮：" + s2);
        String r2 = kwSeekerManage.getKWSeeker(wordType2).replaceWords(text2);
        System.out.println("字符替换：" + r2);
    }

    @RequestMapping("/model/asr")
    public AsrModelResponse testAsrModel(@RequestBody AudioQueryModelRequest req) throws TException {
        return modelService.queryAudioAsr(req);
    }

    @RequestMapping("/model/repeat")
    public RepeatModelResponse testRepeatModel(@RequestBody AudioQueryModelRequest req) throws TException {
        return modelService.queryAudioRepeat(req);
    }

    @RequestMapping("/model/voiceprint")
    public VoiceprintModelResponse testVoiceprintModel(@RequestBody AudioQueryModelRequest req) throws TException {
        return modelService.queryAudioVoiceprint(req);
    }

    @RequestMapping("/model/seed/add")
    public SeedAudioResponse testVoiceprintModel(@RequestBody SeedAudioRequest req) throws TException {
        return modelService.addSeedAudio(req);
    }
}
