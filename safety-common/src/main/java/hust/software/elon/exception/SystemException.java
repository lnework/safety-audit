package hust.software.elon.exception;

import hust.software.elon.common.BaseException;
import hust.software.elon.common.ErrorCode;

import java.util.Map;

/**
 * @author elon
 * @date 2022/3/29 14:55
 */
public class SystemException extends BaseException {
    public SystemException(ErrorCode error, Map<String, Object> data, Throwable cause) {
        super(error, data, cause);
    }

    public SystemException(ErrorCode error, Map<String, Object> data) {
        super(error, data, null);
    }

    public SystemException(ErrorCode error, Throwable cause) {
        super(error, null, cause);
    }

    public SystemException(ErrorCode error) {
        super(error, null, null);
    }
}
