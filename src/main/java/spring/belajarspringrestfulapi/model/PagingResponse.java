package spring.belajarspringrestfulapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingResponse {
    private Integer currentPage;

    private Integer totalPage;

    private Integer size;
}
