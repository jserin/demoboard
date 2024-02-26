package demo.demoBoard.domain.board.mapper;

import demo.demoBoard.common.dto.SearchDto;
import demo.demoBoard.domain.board.model.BoardRequest;
import demo.demoBoard.domain.board.model.BoardResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    /*
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    List<BoardResponse> findAll(SearchDto params);

    /*
     * 답글 리스트 조회
     * @return 답글 리스트
     */
    List<BoardResponse> findByGroupId(int groupId);

    /*
     * 게시글 수 카운팅
     * @return 게시글 수
     */
    int count(SearchDto params);

    /*
     * 게시글 저장
     * @param board - 게시글 정보
     */
    void insertBoard(BoardRequest board);

    /*
     * 답글 저장
     * @param board - 게시글 정보
     */
    void insertReBoard(BoardRequest board);

    /*
     * 게시글 상세정보 조회
     * @param boardId - PK
     * @return 게시글 상세정보
     */
    BoardResponse findById(int boardId);

    /*
     * 게시글 수정
     * @param params - 게시글 정보
     */
    void update(BoardRequest params);

    /*
     * 게시글 삭제
     * @param boardId - PK
     */
    void deleteById(int boardId);
}
