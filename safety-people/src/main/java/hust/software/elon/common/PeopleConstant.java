package hust.software.elon.common;

/**
 * @author elon
 * @date 2022/4/30 15:17
 */
public class PeopleConstant {
//    审核队列的基本信息缓存
    public static final String PEOPLE_AUDIT_QUEUE_CACHE_KEY_PREFIX = "people_audit_queue_";

//    待审任务缓存前缀
    public static final String PEOPLE_NEED_AUDIT_TASK_QUEUE_CACHE_KEY_PREFIX = "people_need_audit_task_list_pool_";

//    正在审核的任务前缀
    public static final String PEOPLE_AUDITING_TASK_QUEUE_CACHE_KEY_PREFIX = "people_auditing_task_list_pool_";

//    设置过期时间
    public static final String PEOPLE_AUDIT_TASK_CACHE_KEY_PREFIX = "people_auditing_task_";

    public static final long AUDITING_TASK_EXPIRE_TIME = 5*60;


}
