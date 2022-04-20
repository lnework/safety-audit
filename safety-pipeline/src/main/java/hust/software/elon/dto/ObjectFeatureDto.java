package hust.software.elon.dto;

import hust.software.elon.safety.common.domain.ObjectType;
import lombok.Data;

/**
 * @author elon
 * @date 2022/4/20 10:13
 */
@Data
public class ObjectFeatureDto {
//    送审数据特征
    private Long objectId;
    private ObjectType objectType;
    private String extraJson;
    private String featureExtraJson;
}
