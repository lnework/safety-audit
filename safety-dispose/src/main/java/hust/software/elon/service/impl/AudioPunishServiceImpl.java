package hust.software.elon.service.impl;

import hust.software.elon.dto.RecordAbstract;
import hust.software.elon.service.PunishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elon
 * @date 2022/5/11 19:59
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AudioPunishServiceImpl implements PunishService {

    @Override
    public boolean executeReviewResult(RecordAbstract record) {
        log.info("punish.executeReviewResult record={}", record);
        return true;
    }
}
