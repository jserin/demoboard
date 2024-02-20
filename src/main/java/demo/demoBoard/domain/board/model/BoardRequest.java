package demo.demoBoard.domain.board.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BoardRequest {
    private int boardId;
    private String boardTitle;
    private String boardCnt;
    private String boardWriter;
    private int boardPwd;
    private int groupId;
    private int categoryId;
    private List<MultipartFile> files = new ArrayList<>();
}
