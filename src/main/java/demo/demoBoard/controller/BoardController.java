package demo.demoBoard.controller;

import demo.demoBoard.mapper.BoardMapper;
import demo.demoBoard.model.BoardRequest;
import demo.demoBoard.model.BoardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardMapper boardMapper;

    @GetMapping("/")
    public String index(Model model) {
        List<BoardResponse> boards = boardMapper.getAllBoards();
        model.addAttribute("boards", boards);
        return "index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("board", new BoardRequest());
        return "add";
    }

    @PostMapping("/add")
    public String addSubmit(BoardRequest board) {
        boardMapper.insertBoard(board);
        return "redirect:/";
    }
}
