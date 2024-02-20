package demo.demoBoard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import demo.demoBoard.domain.board.mapper.BoardMapper;
import demo.demoBoard.domain.board.model.BoardRequest;
import demo.demoBoard.domain.board.model.BoardResponse;
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
        params.setBoardId(4);
        params.setBoardTitle("4번 게시글 제목 수정합니다.");
        params.setBoardCnt("여유로운 오후, 산책을 떠나 꽃 향기가 풍긴 공원으로 향했습니다. 공원은 이미 벌써 봄의 발자취로 가득 차 있었습니다. 새들이 지저귀며 나무에서 나들이를 즐기고, 어린이들은 잔디밭에서 뛰놀고 있었습니다. 나뭇잎은 신선한 초록색으로 빛나며 바람에 스치면서 상쾌한 냄새를 풍깁니다. 햇빛은 부드럽게 내리쬐고 있어 따뜻한 느낌이 들었습니다. 길가의 꽃들은 다채롭게 피어나 눈을 즐겁게 만들어줍니다. 산책로를 따라 걷다 보니 작은 연못이 보였습니다. 연못 주변에는 연꽃이 피어 있었고, 물 속에서는 작은 물고기들이 수영하고 있었습니다. 모든 것이 조화롭고 아름다웠습니다. 나무 그늘 아래에 앉아 조용히 쉬어가며 주위의 아름다움을 감상했습니다. 공원은 마치 특별한 세계처럼 마음을 안정시켜주고, 일상의 스트레스와 괴로움을 잊게 해줍니다. 이곳은 진정한 안식처이며, 자연이 주는 큰 행복입니다. 함께 이곳을 방문한 친구와 함께 나누는 이야기도 좋았습니다. 시간 가는 줄 모르고 함께 즐겁게 놀았습니다. 결국 이 공원은 우리에게 큰 힐링을 주고, 잊지 못할 소중한 추억을 만들어 주었습니다.b");
        params.setBoardWriter("modify");
        boardMapper.update(params);

        // 2. 게시글 상세정보 조회
        BoardResponse board = boardMapper.findById(4);
        try {
            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
            System.out.println(postJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    void delete() {
//        System.out.println("삭제 이전의 전체 게시글 개수는 : " + boardMapper.getAllBoards().size() + "개입니다.");
//        boardMapper.deleteById(2);
//        System.out.println("삭제 이후의 전체 게시글 개수는 : " + boardMapper.getAllBoards().size() + "개입니다.");
//    }
}
