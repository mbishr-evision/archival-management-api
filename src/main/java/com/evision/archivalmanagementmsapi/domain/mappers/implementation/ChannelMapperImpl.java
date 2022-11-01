package com.evision.archivalmanagementmsapi.domain.mappers.implementation;

import com.evision.archivalmanagementmsapi.domain.dto.ChannelDto;
import com.evision.archivalmanagementmsapi.domain.dto.ConnectionConfigDto;
import com.evision.archivalmanagementmsapi.domain.dto.DirectoryConfigDto;
import com.evision.archivalmanagementmsapi.domain.enums.DirectoryRef;
import com.evision.archivalmanagementmsapi.domain.mappers.ChannelMapper;
import com.evision.archivalmanagementmsapi.domain.models.ChannelDirectoryModel;
import com.evision.archivalmanagementmsapi.domain.models.ChannelModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ChannelMapperImpl implements ChannelMapper {
    @Override
    public List<ChannelDto> mapModelListToDtoList(List<ChannelModel> models) throws JsonProcessingException {
        List<ChannelDto> dtos = new ArrayList<>();
        for (ChannelModel model : models) {
            dtos.add(mapModelToDto(model));
        }
        return dtos;
    }

    @Override
    public ChannelDto mapModelToDto(ChannelModel model) throws JsonProcessingException {
        ChannelDto dto = new ChannelDto();
        dto.setUuid(model.getUuid());
        dto.setProtocolType(model.getProtocol());
        if (model.getConnection() != null && !model.getConnection().isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            ConnectionConfigDto connectionConfigDto = mapper.readValue(model.getConnection(), ConnectionConfigDto.class);
            dto.setConnectionConfiguration(connectionConfigDto);
        }
        DirectoryConfigDto directoryConfigDto = new DirectoryConfigDto();
        for(ChannelDirectoryModel channelDirectoryModel : model.getChannelDirectories()){
            if (channelDirectoryModel.getType() == DirectoryRef.OUTGOING)
                directoryConfigDto.setOutgoing(channelDirectoryModel.getPath());
            else if (channelDirectoryModel.getType() == DirectoryRef.INCOMING)
                directoryConfigDto.setIncoming(channelDirectoryModel.getPath());
            else if (channelDirectoryModel.getType() == DirectoryRef.TOSWIFT_)
                directoryConfigDto.setToSwift(channelDirectoryModel.getPath());
            else if (channelDirectoryModel.getType() == DirectoryRef.MSGHISTO)
                directoryConfigDto.setMsgHistory(channelDirectoryModel.getPath());
            else if (channelDirectoryModel.getType() == DirectoryRef.POSTING_)
                directoryConfigDto.setPosting(channelDirectoryModel.getPath());
            else if (channelDirectoryModel.getType() == DirectoryRef.GPIUC___)
                directoryConfigDto.setGpiUc(channelDirectoryModel.getPath());
        }
        dto.setDirectoryConfiguration(directoryConfigDto);
        return dto;
    }
}
