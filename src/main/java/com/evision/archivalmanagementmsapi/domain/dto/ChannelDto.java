package com.evision.archivalmanagementmsapi.domain.dto;

import com.evision.archivalmanagementmsapi.domain.enums.ProtocolRef;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDto {
    @JsonProperty(value = "uuid")
    private UUID uuid;

    @JsonProperty(value = "protocolType")
    private ProtocolRef protocolType;

    @JsonProperty(value = "connectionConfiguration")
    private ConnectionConfigDto connectionConfiguration;

    @JsonProperty(value = "directoryConfiguration")
    private DirectoryConfigDto directoryConfiguration;
}
