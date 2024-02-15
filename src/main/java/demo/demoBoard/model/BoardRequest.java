package demo.demoBoard.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardRequest {
    private int boardId;
    private String boardTitle;
    private String boardCnt;
    private String boardWriter;
    private int boardPwd;
    private int groupId;
    private int categoryId;
    private LocalDateTime modifyDate;
}
