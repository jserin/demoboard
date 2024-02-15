package demo.demoBoard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import demo.demoBoard.model.BoardRequest;
import demo.demoBoard.model.BoardResponse;
import demo.demoBoard.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTest {
    @Autowired
    BoardService boardService;

    @Test
    void save() {
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

    @Test
    void findById() {
        BoardResponse board = boardService.findBoardById(3);
        try {
            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
            System.out.println(postJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
