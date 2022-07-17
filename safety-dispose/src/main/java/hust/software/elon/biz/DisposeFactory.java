package hust.software.elon.biz;

import hust.software.elon.service.ArbiterService;
import hust.software.elon.service.HandlerService;
import hust.software.elon.service.PunishService;
import org.springframework.stereotype.Service;

/**
 * @author elon
 * @date 2022/5/11 19:57
 */
public class DisposeFactory {
    private HandlerService handlerService;
    private ArbiterService arbiterService;
    private PunishService punishService;
}
