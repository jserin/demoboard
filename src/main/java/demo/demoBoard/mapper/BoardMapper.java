package demo.demoBoard.mapper;

import demo.demoBoard.model.BoardRequest;
import demo.demoBoard.model.BoardResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardResponse> getAllBoards();
    void insertBoard(BoardRequest board);
}
