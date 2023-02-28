package com.hcmute.fit.project.instagram.domain.exception;

import com.hcmute.fit.project.instagram.domain.base.ResponseBaseAbstract;

public class ServiceExceptionResponse extends ResponseBaseAbstract {
    public ServiceExceptionResponse() {
        super();
        this.setStatus("FAIL");
    }
}