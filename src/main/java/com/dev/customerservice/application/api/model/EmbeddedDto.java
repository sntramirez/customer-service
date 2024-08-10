package com.dev.customerservice.application.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmbeddedDto<T> {

    @JsonProperty("_embedded")
    private T embedded;

    @JsonProperty("_links")
    private LinksDto links;

    private PageDto page;
}