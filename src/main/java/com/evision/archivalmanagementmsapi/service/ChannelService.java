package com.evision.archivalmanagementmsapi.service;

import com.evision.archivalmanagementmsapi.domain.bo.ServiceResponseBo;
import com.evision.archivalmanagementmsapi.domain.dto.ChannelDto;
import com.evision.archivalmanagementmsapi.domain.enums.ProtocolRef;
import com.evision.archivalmanagementmsapi.domain.requests.ChannelRequest;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    ServiceResponseBo<ChannelDto> create(ChannelRequest request);
    ServiceResponseBo<ChannelDto> update(UUID id, ChannelRequest request);
    ServiceResponseBo<List<ChannelDto>> loadList();
    ServiceResponseBo<ChannelDto> load(UUID id);
}
