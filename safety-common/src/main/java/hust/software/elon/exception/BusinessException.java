package hust.software.elon.exception;

import hust.software.elon.common.BaseException;
import hust.software.elon.common.ErrorCode;

import java.util.Map;

/**
 * @author elon
 * @date 2022/3/29 14:54
 */
public class BusinessException extends BaseException {

    public BusinessException(ErrorCode error) {
        super(error, null);
    }
    public BusinessException(ErrorCode error, Map<String, Object> data) {
        super(error, data);
    }

    public BusinessException(ErrorCode error, Map<String, Object> data, Throwable cause) {
        super(error, data, cause);
    }
}
