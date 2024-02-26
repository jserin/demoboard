package demo.demoBoard.domain.comment.model;

import lombok.Data;

@Data
public class CommentRequest {
    private int cmtId;
    private String cmtCnt;
    private String cmtWriter;
    private int cmtPwd;
    private int groupId;
    private int boardId;
}
