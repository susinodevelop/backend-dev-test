package com.susinodevelop.backendtest.similarproduct.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Schema(name = "ProductDetail", description = "Product detail")
public class ProductDetail {

    @NotNull
    @Size(min = 1)
    @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("id")
    private String id;

    @NotBlank
    @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    private String name;

    @NotNull
    @Valid
    @Schema(name = "price", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("price")
    private BigDecimal price;

    @NotNull
    @Schema(name = "availability", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("availability")
    private Boolean availability;

}

