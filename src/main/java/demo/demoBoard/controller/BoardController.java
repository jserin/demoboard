package demo.demoBoard.controller;

import demo.demoBoard.model.BoardRequest;
import demo.demoBoard.model.BoardResponse;
import demo.demoBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

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
    public String addSubmit(BoardRequest board) {
        boardService.insertBoard(board);
        return "redirect:/";
    }
}
