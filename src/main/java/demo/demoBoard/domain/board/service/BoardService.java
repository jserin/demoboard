package demo.demoBoard.domain.board.service;

import demo.demoBoard.domain.board.mapper.BoardMapper;
import demo.demoBoard.domain.board.model.BoardRequest;
import demo.demoBoard.domain.board.model.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    /*
     * 게시글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public int insertBoard(final BoardRequest params) {
        boardMapper.insertBoard(params);
        return params.getBoardId();
    }

    /*
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    public List<BoardResponse> findAllBoards() {
        return boardMapper.getAllBoards();
    }

    /*
     * 게시글 상세정보 조회
     * @param boardId - PK
     * @return 게시글 상세정보
     */
    public BoardResponse findBoardById(final int boardId) {
        return boardMapper.findById(boardId);
    }

    /*
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public int updateBoard(final BoardRequest params) {
        boardMapper.update(params);
        return params.getBoardId();
    }

    /*
     * 게시글 삭제
     * @param boardId - PK
     * @return PK
     */
    public void deleteBoard(final int boardId) {
        boardMapper.deleteById(boardId);
    }
}
