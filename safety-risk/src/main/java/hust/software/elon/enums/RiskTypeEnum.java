package hust.software.elon.enums;

import hust.software.elon.safety.model.domain.RiskType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


/**
 * @author elon
 * @date 2022/4/20 20:22
 */
@Getter
@AllArgsConstructor
public enum  RiskTypeEnum {
    AUDIO_ASR("audio_asr_risk", "audio_asr_risk", 0.0, RiskType.MODEL_ASR),
    AUDIO_SENSITIVE_WORD("audio_sensitive_word_risk", "audio_sensitive_word_risk", 0.0, RiskType.MODEL_ASR),
    AUDIO_REPEAT("audio_repeat_risk", "audio_repeat_risk", 0.0, RiskType.MODEL_REPEAT),
    AUDIO_VOICEPRINT("audio_voiceprint_risk", "audio_voiceprint_risk", 0.0, RiskType.MODEL_VOICE_PRINT),
    AUDIO_PORN("audio_porn_risk", "audio_porn_risk", 0.0, RiskType.MODEL_NONE),
    NONE("none_risk", "none", 0.0, RiskType.MODEL_NONE)
    ;

    private final String configKey;
    private final String token;
    private final Double threshold;
    private final RiskType riskType;

    private static final List<RiskTypeEnum> RISK_TYPE_ENUM_LIST = Arrays.asList(RiskTypeEnum.AUDIO_SENSITIVE_WORD,
            RiskTypeEnum.AUDIO_REPEAT, RiskTypeEnum.AUDIO_VOICEPRINT, RiskTypeEnum.AUDIO_PORN);

    public static RiskTypeEnum checkAndGet(String configKey, String token){
        for (RiskTypeEnum riskTypeEnum: RISK_TYPE_ENUM_LIST){
            if (riskTypeEnum.getConfigKey().equals(configKey) && riskTypeEnum.getToken().equals(token)){
                return riskTypeEnum;
            }
        }
        return NONE;
    }

}
