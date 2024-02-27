package demo.demoBoard.file.controller;

import demo.demoBoard.common.util.FileUtils;
import demo.demoBoard.file.model.FileResponse;
import demo.demoBoard.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FileApiController {

    private final FileService fileService;
    private final FileUtils fileUtils;

    @GetMapping("/board/{boardId}/file/delete/{fileId}")
    public String deleteFile(@PathVariable(name = "boardId") int boardId, @PathVariable(name = "fileId") int fileId) {
        fileService.deleteById(fileId);
        return String.format("redirect:/board/modify/%s", boardId );
    }

    // 파일 리스트 조회
    @GetMapping("/board/{boardId}/files")
    public List<FileResponse> findAllFileByPostId(@PathVariable final int boardId) {
        return fileService.findAllFileByBoardId(boardId);
    }

    // 첨부파일 다운로드
    @GetMapping("/files/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable(name = "fileId") final int fileId) {

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
