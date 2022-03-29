package hust.software.elon.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @author elon
 * @date 2022/3/29 14:38
 */
@Getter
@AllArgsConstructor
@ToString
public enum ErrorCode {
//    No provider available for remote service
    THRIFT_NO_REMOTE_SERVICE(101, HttpStatus.INTERNAL_SERVER_ERROR, "无可用的远程服务"),
//    register service address to zookeeper exception
    THRIFT_NOT_REGISTER_SERVICE_ADDRESS(102, HttpStatus.INTERNAL_SERVER_ERROR, "zookeeper注册服务地址异常"),
//    cant find server ip
    THRIFT_NOT_FIND_SERVER_IP(103, HttpStatus.INTERNAL_SERVER_ERROR, "无法找到服务IP"),
    RESOURCE_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "未找到该资源"),
    REQUEST_VALIDATION_FAILED(1002, HttpStatus.BAD_REQUEST, "请求数据格式验证失败"),
    USER_PASSWORD_ERROR(2002, HttpStatus.BAD_REQUEST, "用户名或密码错误"),
    BAD_TOKEN(2003, HttpStatus.BAD_REQUEST, "TOKEN错误"),
    BAD_REQUEST(2004, HttpStatus.BAD_REQUEST, "请求有误"),
    LOW_POWER(2005, HttpStatus.BAD_REQUEST, "权限不足"),
    File_ERROR(2006, HttpStatus.BAD_REQUEST, "文件异常"),
    ;


    int code;
    HttpStatus status;
    String message;
}
