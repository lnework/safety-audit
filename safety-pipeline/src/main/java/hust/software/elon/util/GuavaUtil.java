package hust.software.elon.util;

import cn.hutool.core.util.RandomUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author elon
 * @date 2022/4/20 19:39
 */
@Component
public class GuavaUtil {
    private Cache<String, Object> commonCache = null;

    @PostConstruct
    public void initGuavaCache(){
//        缓存时间随机 避免同一时间雪崩
        long expireTime = RandomUtil.randomLong(5, 15);
        commonCache = CacheBuilder.newBuilder()
                .initialCapacity(16)
                .maximumSize(256)
                .expireAfterWrite(expireTime, TimeUnit.SECONDS)
                .build();
    }

    public void setCommonCache(String key, Object value) {
        commonCache.put(key, value);
    }

    public Object getFromCommonCache(String key) {
//        存在返回对象，不存在返回空
        return commonCache.getIfPresent(key);
    }

}
