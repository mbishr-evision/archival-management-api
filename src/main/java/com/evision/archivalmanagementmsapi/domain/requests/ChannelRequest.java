package com.evision.archivalmanagementmsapi.domain.requests;

import com.evision.archivalmanagementmsapi.domain.dto.ConnectionConfigDto;
import com.evision.archivalmanagementmsapi.domain.dto.DirectoryConfigDto;
import com.evision.archivalmanagementmsapi.domain.enums.ProtocolRef;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChannelRequest {
    @JsonProperty(value = "uuid", required = false)
    private UUID uuid;

    @JsonProperty(value = "protocolType", required = true)
    private ProtocolRef protocolType;

    @JsonProperty(value = "connectionConfiguration", required = false)
    private ConnectionConfigRequest connectionConfiguration;

    @JsonProperty(value = "directoryConfiguration", required = true)
    private DirectoryConfigRequest directoryConfiguration;
}
