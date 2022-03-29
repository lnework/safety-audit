package hust.software.elon.common;

import lombok.Data;

/**
 * @author elon
 * @date 2022/3/29 14:40
 */
@Data
public class CommonResponse {
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAIL = "fail";

    private String status;
    private Object data;


    public static  CommonResponse create(String status){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(status);
        return commonResponse;
    }


    public static CommonResponse create(Object data){
        return create(data, STATUS_SUCCESS);
    }

    public static CommonResponse create(Object data, String status){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(data);
        commonResponse.setStatus(status);
        return commonResponse;
    }


    public static CommonResponse Failed(){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(STATUS_FAIL);
        return commonResponse;
    }

}

