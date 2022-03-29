package hust.software.elon.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author elon
 * @date 2022/3/29 14:40
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BaseException.class)
    public CommonResponse handleAppException(HttpServletRequest request, BaseException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex, request.getRequestURI());
        return CommonResponse.create(errorResponse, CommonResponse.STATUS_FAIL);
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResponse handleAppException(HttpServletRequest request, Exception ex) {
        ex.printStackTrace();
        return CommonResponse.create(CommonResponse.STATUS_FAIL);
    }
}
