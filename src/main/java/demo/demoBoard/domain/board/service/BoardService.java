package demo.demoBoard.domain.board.service;

import demo.demoBoard.common.dto.SearchDto;
import demo.demoBoard.common.paging.Pagination;
import demo.demoBoard.common.paging.PagingResponse;
import demo.demoBoard.domain.board.mapper.BoardMapper;
import demo.demoBoard.domain.board.model.BoardRequest;
import demo.demoBoard.domain.board.model.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
     * 답글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public int insertReBoard(final BoardRequest params) {
        boardMapper.insertReBoard(params);
        return params.getBoardId();
    }

    /*
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    public PagingResponse<BoardResponse> findAll(final SearchDto params) {

        // 조건 해당 데이터가 없는 경우
        int count = boardMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<BoardResponse> list = boardMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }

    public List<BoardResponse> findAllBoards() {
        return boardMapper.findAllBoards();
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

    /*
     * 답글 리스트 조회
     * @param boardId - PK
     * @return List
     */
    public List<BoardResponse> findByGroupId(final int boardId) {
        return boardMapper.findByGroupId(boardId);
    }

    /*
     * 답글 리스트 조회
     * @param boardId - PK
     * @return List
     */
    public List<BoardResponse> findBoardByGroup(final int boardId) {
        return boardMapper.findBoardByGroup(boardId);
    }

    public List<BoardResponse> test(final int boardId) {
        return boardMapper.findBoardByGroup(boardId);
    }

    public List<BoardResponse> findReBoardsByBoardId(int boardId) {
        return boardMapper.findBoardByGroup(boardId);
    }
}
