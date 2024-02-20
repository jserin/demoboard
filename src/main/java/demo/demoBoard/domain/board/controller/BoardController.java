package demo.demoBoard.domain.board.controller;

import demo.demoBoard.domain.board.model.BoardRequest;
import demo.demoBoard.domain.board.model.BoardResponse;
import demo.demoBoard.domain.board.service.BoardService;
import demo.demoBoard.common.dto.MessageDto;
import demo.demoBoard.file.model.FileRequest;
import demo.demoBoard.file.service.FileService;
import demo.demoBoard.file.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;
    private final FileUtils fileUtils;

    // 자유게시판 목록
    @GetMapping("")
    public String index(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "kw", defaultValue = "") String kw) {
        int pageSize = 15;
        int offset = page * pageSize;
        List<BoardResponse> boards = boardService.findAllBoards(offset, pageSize, kw);
        model.addAttribute("kw", kw);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/{boardId}")
    public String detail(@PathVariable("boardId") Integer boardId, Model model) {
        BoardResponse board = boardService.findBoardById(boardId);
        model.addAttribute("board", board);
        return "board/detail";
    }
    
    // 게시글 추가 페이지
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("board", new BoardRequest());
        return "board/add";
    }

    // 게시글 추가
    @PostMapping("/add")
    public String addSubmit(BoardRequest board, Model model) {
        int id = boardService.insertBoard(board);
        List<FileRequest> files = fileUtils.uploadFiles(board.getFiles());
        fileService.saveFiles(id, files);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/board/" + id, RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 게시글 수정 페이지
    @GetMapping("/modify/{boardId}")
    public String modifyForm(@PathVariable("boardId") Integer boardId, Model model) {
        BoardResponse board = boardService.findBoardById(boardId);
        model.addAttribute("board", board);
        return "board/add";
    }

    // 게시글 수정
    @PostMapping("/modify/{boardId}")
    public String modifySubmit(@PathVariable("boardId") Integer boardId, BoardRequest board, Model model) {
        int id = boardService.updateBoard(board);
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/board/" + id, RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 게시글 삭제
    @GetMapping("/delete/{boardId}")
    public String deleteBoard(@PathVariable("boardId") Integer boardId, Model model) {
//        BoardResponse board = boardService.findBoardById(boardId);
        boardService.deleteBoard(boardId);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/board", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 메세지 표시
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }

}