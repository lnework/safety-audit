package hust.software.elon.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author elon
 * @date 2022/4/27 20:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleAuditTagRequest {
    private Long id;

    private String tag;

    private String auditResultJson;

    private String description;
}
