package demo.demoBoard.common.dto;

import demo.demoBoard.common.paging.Pagination;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {

    private int page;
    private int recordSize;
    private int pageSize;
    private String kw;
    private String kwc;
    private Pagination pagination;

    public SearchDto() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
    }

}
