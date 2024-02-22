package demo.demoBoard.domain.comment.controller;

import demo.demoBoard.domain.board.model.BoardResponse;
import demo.demoBoard.domain.board.service.BoardService;
import demo.demoBoard.domain.comment.model.CommentRequest;
import demo.demoBoard.domain.comment.model.CommentResponse;
import demo.demoBoard.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comment")
@Controller
@RequiredArgsConstructor
public class CommentController {
    private final BoardService boardService;
    private final CommentService commentService;

    // 신규 댓글 생성
}
