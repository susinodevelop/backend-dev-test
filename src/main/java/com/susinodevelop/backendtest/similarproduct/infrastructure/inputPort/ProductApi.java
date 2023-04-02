package com.susinodevelop.backendtest.similarproduct.infrastructure.inputPort;

import com.susinodevelop.backendtest.similarproduct.domain.ProductDetail;

import java.util.Set;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@Validated
@RequestMapping("product")
@Tag(name = "product", description = "the product API")
public interface ProductApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /product/{productId}/similar : Similar products
     *
     * @param productId (required)
     * @return OK (status code 200)
     * or Product Not found (status code 404)
     */
    @Operation(
            operationId = "getProductSimilar",
            description = "Similar products",
            summary = "Similar products",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDetail.class)))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not found")

            }
    )
    @GetMapping(path = "/{productId}/similar", produces = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<Set<ProductDetail>> getProductSimilar(
            @Parameter(name = "productId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("productId") String productId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
