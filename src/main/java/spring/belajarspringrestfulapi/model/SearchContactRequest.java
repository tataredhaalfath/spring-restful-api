package spring.belajarspringrestfulapi.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchContactRequest {
    private String name;

    private String email;

    private String phone;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;
}
