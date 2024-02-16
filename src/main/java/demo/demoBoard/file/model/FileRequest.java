package demo.demoBoard.file.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class FileRequest {
    private int fileId;
    private String fileName;
    private String saveName;
    private long fileSize;
    private int boardId;

    @Builder
    public FileRequest(String fileName, String saveName, long fileSize) {
        this.fileName = fileName;
        this.saveName = saveName;
        this.fileSize = fileSize;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
}
