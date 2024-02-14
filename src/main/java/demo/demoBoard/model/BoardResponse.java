package demo.demoBoard.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime create_date;
}
