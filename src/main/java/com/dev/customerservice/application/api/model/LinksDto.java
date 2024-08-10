package com.dev.customerservice.application.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinksDto {

    private LinkDto self;

    private LinkDto profile;
}