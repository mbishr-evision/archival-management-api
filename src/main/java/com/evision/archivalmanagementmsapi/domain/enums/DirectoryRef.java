package com.evision.archivalmanagementmsapi.domain.enums;

public enum DirectoryRef {
    OUTGOING("OUTGOING"),
    INCOMING("INCOMING"),
    TOSWIFT_("TOSWIFT_"),
    MSGHISTO("MSGHISTO"),
    GPIUC___("GPIUC___"),
    POSTING_("POSTING_");

    public final String value;

    private DirectoryRef(String value) {
        this.value = value;
    }
}
