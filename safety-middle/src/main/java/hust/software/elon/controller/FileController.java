package hust.software.elon.controller;

import hust.software.elon.annotation.IgnoreResponseAdvice;
import hust.software.elon.dto.SysFileDto;
import hust.software.elon.response.FileResponse;
import hust.software.elon.service.SysFileService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author elon
 * @date 2022/3/30 16:52
 */
@RequestMapping("/file")
@RestController
@RequiredArgsConstructor
public class FileController {
    private final SysFileService fileService;

    // 上传音频文件
    @PostMapping("/upload-audio")
    public FileResponse uploadFile(@RequestParam MultipartFile uploadFile){
        SysFileDto sysFileDto = fileService.saveAudioFile(uploadFile);
       return FileResponse.convertFromDto(sysFileDto);
    }

    // 查找音频文件
    @GetMapping("/find")
    public FileResponse findById(@RequestParam Long id){
        SysFileDto sysFileDto = fileService.findById(id);
        return FileResponse.convertFromDto(sysFileDto);
    }


    @IgnoreResponseAdvice
    @RequestMapping("/download-big")
    public void downloadBigFile(@RequestParam Long fileId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        SysFileDto fileDTO = fileService.findById(fileId);
        String path = fileDTO.getLocation();
        File downloadFile = new File(path);
        ServletContext context = request.getServletContext();
        // get MIME type of the file
        String mimeType = context.getMimeType(path);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        // Copy the stream to the response's output stream.
        InputStream myStream = new FileInputStream(path);
        IOUtils.copy(myStream, response.getOutputStream());
        response.flushBuffer();
    }

    @IgnoreResponseAdvice
    @RequestMapping("/download-back")
    public String downloadFileBack(@RequestParam Long fileId, HttpServletRequest request, HttpServletResponse response){
        SysFileDto fileDTO = fileService.findById(fileId);
        String path = fileDTO.getLocation();
        File file = new File(path);
        if (file.exists()) {
            response.setContentType("application/octet-stream");//
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileDTO.getName());// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
