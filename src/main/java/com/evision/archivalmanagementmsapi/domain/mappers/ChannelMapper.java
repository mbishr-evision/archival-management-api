package com.evision.archivalmanagementmsapi.domain.mappers;

import com.evision.archivalmanagementmsapi.domain.dto.ChannelDto;
import com.evision.archivalmanagementmsapi.domain.models.ChannelModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ChannelMapper {
    List<ChannelDto> mapModelListToDtoList(List<ChannelModel> models) throws JsonProcessingException;
    ChannelDto mapModelToDto(ChannelModel model) throws JsonProcessingException;
}
