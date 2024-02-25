package demo.demoBoard.file.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FileResponse {
    private int fileId;
    private String fileName;
    private String saveName;
    private long fileSize;
    private int boardId;
    private Boolean fileDel;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
