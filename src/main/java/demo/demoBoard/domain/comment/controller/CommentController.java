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
    @PostMapping("/add")
    public String addComment(CommentRequest comment) {
        commentService.saveComment(comment);
        return String.format("redirect:/board/%s", comment.getBoardId());
    }

    // 신규 대댓글 생성
    @PostMapping("/add/{cmtId}")
    public String addReCmt(CommentRequest comment, @PathVariable("cmtId") int cmtId) {
        comment.setGroupId(cmtId);
        int id = commentService.saveReCmt(comment);
        return String.format("redirect:/board/%s", id);
    }

    // 댓글 수정
    @PostMapping("/modify")
    public String modifyComment(CommentRequest comment) {
        System.out.println("dddd");
        System.out.println(comment);
        int id = commentService.updateComment(comment);
        System.out.println(id);
        return String.format("redirect:/board/%s", id);
    }

    // 댓글 삭제
    @GetMapping("/delete/{cmtId}")
    public String deleteComment(@PathVariable("cmtId") int cmtId) {
        int id = commentService.findCommentById(cmtId).getBoardId();
        commentService.deleteComment(cmtId);
        return String.format("redirect:/board/%s", id);
    }
}
