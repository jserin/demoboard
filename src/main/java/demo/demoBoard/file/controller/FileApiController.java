package demo.demoBoard.file.controller;

import demo.demoBoard.common.util.FileUtils;
import demo.demoBoard.file.model.FileResponse;
import demo.demoBoard.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class FileApiController {

    private final FileService fileService;
    private FileUtils fileUtils;

    @DeleteMapping("/file/delete/{fileId}")
    public String deleteFile(@PathVariable int fileId) {
        fileService.deleteById(fileId);
        return "파일이 성공적으로 삭제되었습니다.";
    }

    // 첨부파일 다운로드
    @GetMapping("/board/{boardId}/files/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable final int boardId, @PathVariable final int fileId) {
        FileResponse file = fileService.findFileById(fileId);
        Resource resource = fileUtils.readFileAsResource(file);

        try {
            String filename = URLEncoder.encode(file.getFileName(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getFileSize() + "")
                    .body(resource);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getFileName());
        }
    }
}
