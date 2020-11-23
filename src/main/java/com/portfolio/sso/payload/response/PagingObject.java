package com.portfolio.sso.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingObject {
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalPage;
    private Long totalRecord;

    public PagingObject(Page page) {
        // increase 1 for consistent with JPA pageable. Due to UI start with page 1, but JPA start with page 0
        this.pageNo = page.getNumber() + 1;
        this.pageSize = page.getNumberOfElements();
        this.totalPage = page.getTotalPages();
        this.totalRecord = page.getTotalElements();
    }
}
