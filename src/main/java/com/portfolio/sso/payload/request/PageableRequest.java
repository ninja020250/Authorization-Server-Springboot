package com.portfolio.sso.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableRequest {

    @Nullable
    private String search;

    @Nullable
    private Integer page;

    @Nullable
    private Integer size;

    @Nullable
    private String sort;

    @Nullable
    private String sortField;

    public Integer getPage() {
        return this.page == null ? 0 : this.page;
    }

    public Integer getSize() {
        return this.size == null ? 1 : this.size;
    }

    public String getSort() {
        return this.sort == null ? "DESC" : this.sort;
    }

    public String getSortField() {
        return this.sortField == null ? "" : this.sortField;
    }

    public String getSearch() {
        return this.search == null ? "" : this.search;
    }
}
