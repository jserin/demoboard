package demo.demoBoard.domain.comment.service;

import demo.demoBoard.domain.comment.mapper.CommentMapper;
import demo.demoBoard.domain.comment.model.CommentRequest;
import demo.demoBoard.domain.comment.model.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    /*
     * 댓글 저장
     * @param params - 댓글 정보
     * @return Generated PK
     */
    @Transactional
    public int saveComment(final CommentRequest params) {
        commentMapper.save(params);
        return params.getBoardId();
    }

    /*
     * 대댓글 저장
     * @param params - 댓글 정보
     * @return Generated PK
     */
    @Transactional
    public int saveReCmt(final CommentRequest params) {
        commentMapper.saveReCmt(params);
        return params.getBoardId();
    }

    /*
     * 댓글 상세정보 조회
     * @param id - PK
     * @return 댓글 상세정보
     */
    public CommentResponse findCommentById(final int id) {
        return commentMapper.findById(id);
    }

    /*
     * 댓글 수정
     * @param params - 댓글 정보
     * @return PK
     */
    @Transactional
    public int updateComment(final CommentRequest params) {
        commentMapper.update(params);
        CommentResponse comment = findCommentById(params.getCmtId());
        return comment.getBoardId();
    }

    /*
     * 댓글 삭제
     * @param id - PK
     * @return PK
     */
    @Transactional
    public int deleteComment(final int id) {
        commentMapper.deleteById(id);
        return id;
    }

    /*
     * 댓글 리스트 조회
     * @param boardId - 게시글 번호 (FK)
     * @return 특정 게시글에 등록된 댓글 리스트
     */
    public List<CommentResponse> findCmtByGroup(final int boardId) {
        System.out.println(boardId);
        return commentMapper.findCmtByGroup(boardId);
    }
}
