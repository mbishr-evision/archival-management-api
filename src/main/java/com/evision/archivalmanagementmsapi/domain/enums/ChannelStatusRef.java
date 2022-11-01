package com.evision.archivalmanagementmsapi.domain.enums;

public enum ChannelStatusRef {
    ACTIVE(1, "Active"),
    INACTIVE(2, "Inactive");

    public final int code;
    public final String label;

    private ChannelStatusRef(int code, String label) {
        this.code = code;
        this.label = label;
    }
}
