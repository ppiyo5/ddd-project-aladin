package io.github.wotjd243.aladin.book.ui.dto;

import lombok.Data;

@Data
public class PageOptions {

    private int pageSize = defaultPageSize();

    private int pageNumber = 1;

    protected int defaultPageSize() {
        return 10;
    }
}

