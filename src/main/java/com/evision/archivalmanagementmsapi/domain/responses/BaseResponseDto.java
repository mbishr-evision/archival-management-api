package com.evision.archivalmanagementmsapi.domain.responses;

import com.evision.archivalmanagementmsapi.domain.enums.ServiceResponseRef;
import com.evision.archivalmanagementmsapi.domain.interfaces.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@Slf4j
public class BaseResponseDto<T> implements Logger {
    private ServiceResponseRef responseCode;
    private String message;
    private T returnObject;

    @Override
    public void log() {
        log.info("Response Code: " + this.responseCode.label);
        if (this.message != null && !this.message.isEmpty())
            log.info("Response Message: " + this.message);
    }
}
