package demo.demoBoard.domain.comment.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private int cmtId;
    private String cmtCnt;
    private String cmtWriter;
    private int cmtPwd;
    private int groupId;
    private int boardId;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private boolean cmtDel;
    private int cmtLevel;
}
