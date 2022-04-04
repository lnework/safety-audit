package hust.software.elon.controller;

import hust.software.elon.dto.AudioDto;
import hust.software.elon.request.AudioRequest;
import hust.software.elon.response.AudioResponse;
import hust.software.elon.service.AudioService;
import hust.software.elon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author elon
 * @date 2022/4/3 21:08
 */
@RequestMapping("/audio")
@RestController
@RequiredArgsConstructor
public class AudioController {
    private final AudioService audioService;

    @PostMapping("/create")
    public AudioResponse createAudio(@RequestBody AudioRequest audioRequest){
        Long userId = SecurityUtil.getUser();
        AudioDto audioDto = audioService.createAudio(audioRequest.getName(), audioRequest.getFileId(), userId);
        return AudioResponse.convertFromDto(audioDto);
    }

    @GetMapping("/find")
    public AudioResponse findAudioByNumber(@RequestParam Long audioNumber){
        AudioDto audioDto = audioService.findAudioByNumber(audioNumber);
        return AudioResponse.convertFromDto(audioDto);
    }

    @PostMapping("/delete")
    public Boolean deleteAudioByNumber(@RequestParam Long audioNumber){
        Long userId = SecurityUtil.getUser();
        return audioService.deleteAudio(audioNumber, userId);
    }

    @PostMapping("/update")
    public Boolean updateAudioByNumber(@RequestBody AudioRequest audioRequest){
        Long userId = SecurityUtil.getUser();
        return audioService.updateAudio(audioRequest.getAudioNumber(), audioRequest.getName(), audioRequest.getFileId(), userId);
    }
}
