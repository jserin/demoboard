package demo.demoBoard.domain.board.controller;

import demo.demoBoard.common.dto.SearchDto;
import demo.demoBoard.common.paging.PagingResponse;
import demo.demoBoard.domain.board.model.BoardRequest;
import demo.demoBoard.domain.board.model.BoardResponse;
import demo.demoBoard.domain.board.service.BoardService;
import demo.demoBoard.common.dto.MessageDto;
import demo.demoBoard.domain.comment.model.CommentResponse;
import demo.demoBoard.domain.comment.service.CommentService;
import demo.demoBoard.file.model.FileRequest;
import demo.demoBoard.file.model.FileResponse;
import demo.demoBoard.file.service.FileService;
import demo.demoBoard.common.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final FileService fileService;
    private final FileUtils fileUtils;

    // 자유게시판 목록
    @GetMapping("")
    public String index(@ModelAttribute("params") final SearchDto params, Model model) {
        PagingResponse<BoardResponse> boards = boardService.findAll(params);
        List<BoardResponse> reBoards = boardService.findBoardByGroup();

        model.addAttribute("boards", boards);
        model.addAttribute("reBoards", reBoards);

        return "board/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/{boardId}")
    public String detail(@PathVariable("boardId") Integer boardId, Model model) {

        BoardResponse board = boardService.findBoardById(boardId);
        List<FileResponse> files = fileService.findAllFileByBoardId(boardId);
        List<BoardResponse> reBoards = boardService.findByGroupId(boardId);
        List<CommentResponse> comments = commentService.findCmtByGroup(boardId);



        model.addAttribute("board", board);
        model.addAttribute("files", files);
        model.addAttribute("reBoards", reBoards);
        model.addAttribute("comments", comments);;
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

    // 답글 추가 페이지
    @GetMapping("/add/{boardId}")
    public String addReBoard(@PathVariable("boardId") Integer boardId, Model model) {

        model.addAttribute("board", new BoardRequest());
        return "board/add";
    }

    // 답글 추가
    @PostMapping("/add/{boardId}")
    public String addReBoard(@PathVariable("boardId") Integer boardId, BoardRequest reBoard, Model model) {
        reBoard.setGroupId(boardId);

        int id = boardService.insertReBoard(reBoard);

        List<FileRequest> files = fileUtils.uploadFiles(reBoard.getFiles());
        fileService.saveFiles(id, files);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/board/" + id, RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 게시글 수정 페이지
    @GetMapping("/modify/{boardId}")
    public String modifyForm(@PathVariable("boardId") Integer boardId, Model model) {
        BoardResponse board = boardService.findBoardById(boardId);
        model.addAttribute("board", board);
        List<FileResponse> files = fileService.findAllFileByBoardId(boardId);
        model.addAttribute("files", files);
        return "board/add";
    }

    // 게시글 수정
    @PostMapping("/modify/{boardId}")
    public String modifySubmit(@PathVariable("boardId") Integer boardId, BoardRequest board, Model model) {
        int id = boardService.updateBoard(board);
        List<FileRequest> files = fileUtils.uploadFiles(board.getFiles());
        fileService.saveFiles(id, files);
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/board/" + id, RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 게시글 삭제
    @GetMapping("/delete/{boardId}")
    public String deleteBoard(@PathVariable("boardId") Integer boardId, Model model) {
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