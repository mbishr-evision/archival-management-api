package com.evision.archivalmanagementmsapi.domain.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectoryConfigRequest {
    @JsonProperty(value = "incoming")
    private String incoming;

    @JsonProperty(value = "outgoing")
    private String outgoing;

    @JsonProperty(value = "toSwift")
    private String toSwift;

    @JsonProperty(value = "msgHistory")
    private String msgHistory;

    @JsonProperty(value = "gpiUc")
    private String gpiUc;

    @JsonProperty(value = "posting")
    private String posting;
}
