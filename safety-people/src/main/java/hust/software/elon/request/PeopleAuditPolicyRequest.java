package hust.software.elon.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author elon
 * @date 2022/4/29 19:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleAuditPolicyRequest {
    private Long id;

    private String name;

    private List<Long> tagIdList;

    private String judgeJson;

    private String description;
}
