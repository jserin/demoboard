package demo.demoBoard.domain.comment.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class CommentRequest {
    private int cmtId;
    private String cmtCnt;
    private String cmtWriter;
    private int cmtPwd;
    private int boardId;
}
