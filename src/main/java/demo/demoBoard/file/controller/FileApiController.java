package demo.demoBoard.file.controller;

import demo.demoBoard.file.model.FileResponse;
import demo.demoBoard.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileApiController {

    private final FileService fileService;

    // 파일 리스트 조회
    @GetMapping("/board/{boardId}/files")
    public List<FileResponse> findAllFileByBoardId(@PathVariable final int boardId) {
        return fileService.findAllFileByBoardId(boardId);
    }

}
