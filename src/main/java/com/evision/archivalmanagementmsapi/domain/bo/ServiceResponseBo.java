package com.evision.archivalmanagementmsapi.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseBo<T> {
    private T returnObject;
    private String message;
}
