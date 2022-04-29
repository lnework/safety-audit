package hust.software.elon.request;


import java.util.Date;
import java.util.List;

/**
 * @author elon
 * @date 2022/4/29 19:23
 */
public class PeopleAuditPolicyRequest {
    private Long id;

    private String name;

    private List<Long> tagIdList;

    private String judgeJson;

    private String description;
}
