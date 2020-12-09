package com.eureka.sso.main.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PagingAndSortUtil {
    private static final Integer DEFAULT_PAGE_NO = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    public static Sort.Direction getSortDirection(String sortDir) {
        if (Sort.Direction.ASC.toString().equalsIgnoreCase(sortDir)) {
            return Sort.Direction.ASC;
        } else if (Sort.Direction.DESC.toString().equalsIgnoreCase(sortDir)) {
            return Sort.Direction.DESC;
        } else {
            return null;
        }
    }

    public static Pageable makePageable(Integer page, Integer size, String sortDir, String sortBy) {
        Sort sort;
        if ((sortBy == null) || (sortBy.isEmpty())) {
            sort = Sort.unsorted();
        } else {
            Sort.Direction direction = getSortDirection(sortDir);
            if (direction == null) {
                sort = Sort.by(sortBy);
            } else {
                sort = Sort.by(direction, sortBy);
            }
        }
        return PageRequest.of(
                page == null ? DEFAULT_PAGE_NO - 1 : page - 1,
                size == null ? DEFAULT_PAGE_SIZE : size,
                sort);
    }
}
