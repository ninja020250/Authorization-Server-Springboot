package com.eureka.flame.main.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SortingObject {
    private String orderBy;
    private String sortBy;
}
