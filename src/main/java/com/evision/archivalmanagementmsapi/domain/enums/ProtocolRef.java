package com.evision.archivalmanagementmsapi.domain.enums;

public enum ProtocolRef {
    FTP_("FTP_"),
    DIR_("DIR_"),
    SFTP("SFTP"),
    FTPS("FTPS"),
    MQ__("MQ__");

    public final String value;

    private ProtocolRef(String value) {
        this.value = value;
    }
}
