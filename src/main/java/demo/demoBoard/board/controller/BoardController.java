package demo.demoBoard.board.controller;

import demo.demoBoard.board.model.BoardRequest;
import demo.demoBoard.board.model.BoardResponse;
import demo.demoBoard.board.service.BoardService;
import demo.demoBoard.file.model.FileRequest;
import demo.demoBoard.file.service.FileService;
import demo.demoBoard.file.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;
    private final FileUtils fileUtils;

    @GetMapping("/")
    public String index(Model model) {
        List<BoardResponse> boards = boardService.findAllBoards();
        model.addAttribute("boards", boards);
        return "board/index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("board", new BoardRequest());
        return "board/add";
    }

    @PostMapping("/add")
    public String addSubmit(BoardRequest board, Model model) {
        int id = boardService.insertBoard(board);
        List<FileRequest> files = fileUtils.uploadFiles(board.getFiles());
        fileService.saveFiles(id, files);
//        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return "redirect:/";
    }
}
