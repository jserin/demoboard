package demo.demoBoard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import demo.demoBoard.board.mapper.BoardMapper;
import demo.demoBoard.board.model.BoardRequest;
import demo.demoBoard.board.model.BoardResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardMapperTest {
    @Autowired
    BoardMapper boardMapper;

    @Test
    void findById() {
        BoardResponse board = boardMapper.findById(1);
        try {
            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
            System.out.println(postJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void update() {
        // 1. 게시글 수정
        BoardRequest params = new BoardRequest();
        params.setBoardId(1);
        params.setBoardTitle("1번 게시글 제목 수정합니다.");
        params.setBoardCnt("1번 게시글 내용 수정합니다.");
        params.setBoardWriter("modify");
        boardMapper.update(params);

        // 2. 게시글 상세정보 조회
        BoardResponse board = boardMapper.findById(1);
        try {
            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
            System.out.println(postJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void delete() {
        System.out.println("삭제 이전의 전체 게시글 개수는 : " + boardMapper.getAllBoards().size() + "개입니다.");
        boardMapper.deleteById(2);
        System.out.println("삭제 이후의 전체 게시글 개수는 : " + boardMapper.getAllBoards().size() + "개입니다.");
    }
}
