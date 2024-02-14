package demo.demoBoard.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardRequest {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime create_date;
}
