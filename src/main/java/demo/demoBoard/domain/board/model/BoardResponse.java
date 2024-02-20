package demo.demoBoard.domain.board.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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
}
