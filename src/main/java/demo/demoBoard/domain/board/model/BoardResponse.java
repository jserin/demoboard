package demo.demoBoard.domain.board.model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BoardResponse {
    private int boardId;
    private String boardTitle;
    private String boardCnt;
    private String boardWriter;
    private int boardPwd;
    private int groupId;
    private int categoryId;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private boolean boardDel;
    private int boardLevel;

    private List<BoardResponse> reBoards;
}
