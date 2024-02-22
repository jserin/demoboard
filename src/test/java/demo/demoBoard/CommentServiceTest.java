package demo.demoBoard;

import demo.demoBoard.domain.board.model.BoardRequest;
import demo.demoBoard.domain.comment.model.CommentRequest;
import demo.demoBoard.domain.comment.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentServiceTest {
    @Autowired
    CommentService commentService;

    @Test
    void save() {
        for (int i = 1; i <= 5; i++) {
            CommentRequest params = new CommentRequest();
            params.setCmtCnt(i + "번 댓글 내용");
            params.setCmtWriter("테스터" + i);
            params.setCmtPwd(1234);
            params.setBoardId(1001);
            commentService.saveComment(params);
        }
    }
}
