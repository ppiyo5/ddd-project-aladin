package io.github.wotjd243.aladin.book.ui.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageConverter {

    public static Pageable convert(PageOptions pageOptions) {
        return convert(pageOptions, Sort.unsorted());
    }

    public static Pageable convert(PageOptions pageOptions, Sort sort) {
        return PageRequest.of(pageOptions.getPageNumber() - 1, pageOptions.getPageSize(), sort);
    }

}

