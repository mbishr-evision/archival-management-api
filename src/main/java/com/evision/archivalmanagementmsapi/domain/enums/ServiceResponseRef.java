package com.evision.archivalmanagementmsapi.domain.enums;

public enum ServiceResponseRef {
    SUCCESS(200, "Success"),
    VALIDATION_ERROR(400, "Validation Error"),
    SECURITY_ERROR(401, "Security Error"),
    SERVER_ERROR(500, "SERVER ERROR");

    public final int code;
    public final String label;

    private ServiceResponseRef(int code, String label) {
        this.code = code;
        this.label = label;
    }
}
