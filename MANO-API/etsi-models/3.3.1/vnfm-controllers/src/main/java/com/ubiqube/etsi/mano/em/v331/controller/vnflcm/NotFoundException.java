package com.ubiqube.etsi.mano.em.v331.controller.vnflcm;

public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
