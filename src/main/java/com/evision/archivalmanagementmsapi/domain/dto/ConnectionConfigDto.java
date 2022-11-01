package com.evision.archivalmanagementmsapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionConfigDto {
    @JsonProperty(value = "principal")
    private String principal;

    @JsonProperty(value = "credential")
    private String credential;

    @JsonProperty(value = "host")
    private String host;

    @JsonProperty(value = "port")
    private Integer port;
}
