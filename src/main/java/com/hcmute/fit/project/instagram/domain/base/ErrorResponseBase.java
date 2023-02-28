package com.hcmute.fit.project.instagram.domain.base;

import org.springframework.http.HttpStatus;


public class ErrorResponseBase extends ResponseBaseAbstract {
    public ErrorResponseBase(HttpStatus statusCode) {
        this.setStatusCode(statusCode);
        this.setStatus("FAIL");
    }

    @Override
    public void setStatusCode(HttpStatus statusCode) {
        assert statusCode.value() >= 400;
        super.setStatusCode(statusCode);
    }
}