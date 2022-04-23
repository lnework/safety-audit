package hust.software.elon.util;

import cn.hutool.core.util.RandomUtil;

/**
 * @author elon
 * @date 2022/3/30 16:06
 */
public class SecurityUtil {
    public static Long getUser(){
        return RandomUtil.randomLong(0, 2);
    }
}
