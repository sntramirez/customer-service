package com.dev.customerservice.domain.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinksDto {

    private LinkDto self;

    private LinkDto profile;
}