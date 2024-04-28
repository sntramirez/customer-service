package com.devsu.customerservice.application.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDto {

    private Long size;

    private Long totalElements;

    private Long totalPages;

    private Long number;
}
