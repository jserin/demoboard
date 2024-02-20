package demo.demoBoard.domain.board.mapper;

import demo.demoBoard.domain.board.model.BoardRequest;
import demo.demoBoard.domain.board.model.BoardResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    /*
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    List<BoardResponse> findAllByKeyword(@Param("kw") String kw, @Param("offset") int offset, @Param("limit") int limit);

    /*
     * 게시글 저장
     * @param board - 게시글 정보
     */
    void insertBoard(BoardRequest board);

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
