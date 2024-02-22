package demo.demoBoard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import demo.demoBoard.domain.board.model.BoardRequest;
import demo.demoBoard.domain.board.model.BoardResponse;
import demo.demoBoard.domain.board.service.BoardService;
import demo.demoBoard.domain.comment.model.CommentRequest;
import demo.demoBoard.domain.comment.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTest {
    @Autowired
    BoardService boardService;

    @Test
    void save() {
        for (int i = 1; i <= 1000; i++) {
            BoardRequest params = new BoardRequest();
            params.setBoardTitle(i + "번 게시글 제목");
            params.setBoardCnt(i + "번 게시글 내용");
            params.setBoardWriter("테스터" + i);
            params.setBoardPwd(1234);
            params.setGroupId(0);
            params.setCategoryId(2);
            boardService.insertBoard(params);
        }
    }

    @Test
    void findById() {
        BoardResponse board = boardService.findBoardById(2);
        try {
            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
            System.out.println(postJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void update() {
        BoardRequest params = new BoardRequest();
        params.setBoardTitle("aaa번 게시글 제목");
        params.setBoardCnt("1번 게시글 내용");
        params.setBoardWriter("테스터");
        params.setBoardPwd(123);
        params.setGroupId(1);
        params.setCategoryId(0);
        int id = boardService.insertBoard(params);
        System.out.println("생성된 게시글 ID : " + id);
    }
}
