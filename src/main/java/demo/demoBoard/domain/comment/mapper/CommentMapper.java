package demo.demoBoard.domain.comment.mapper;

import demo.demoBoard.domain.comment.model.CommentRequest;
import demo.demoBoard.domain.comment.model.CommentResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    /*
     * 댓글 저장
     * @param params - 댓글 정보
     */
    void save(CommentRequest params);

    /*
     * 대댓글 저장
     * @param params - 댓글 정보
     */
    void saveReCmt(CommentRequest params);

    /*
     * 댓글 상세정보 조회
     * @param id - PK
     * @return 댓글 상세정보
     */
    CommentResponse findById(int id);

    /*
     * 댓글 수정
     * @param params - 댓글 정보
     */
    void update(CommentRequest params);

    /*
     * 댓글 삭제
     * @param id - PK
     */
    void deleteById(int id);

    /*
     * 전체댓글 리스트 조회
     * @param boardId
     * @return 전체댓글 리스트
     */
    List<CommentResponse> findCmtByGroup(int boardId);
}
