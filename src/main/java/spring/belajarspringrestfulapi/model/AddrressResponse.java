package spring.belajarspringrestfulapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddrressResponse {
    private String id;

    private String street;

    private String city;

    private String province;

    private String country;

    private String postalCode;
}
