package com.evision.archivalmanagementmsapi.service.implementation;

import com.evision.archivalmanagementmsapi.domain.bo.ServiceResponseBo;
import com.evision.archivalmanagementmsapi.domain.dto.ChannelDto;
import com.evision.archivalmanagementmsapi.domain.enums.ChannelStatusRef;
import com.evision.archivalmanagementmsapi.domain.enums.DirectoryRef;
import com.evision.archivalmanagementmsapi.domain.enums.ProtocolRef;
import com.evision.archivalmanagementmsapi.domain.exceptions.ServerErrorException;
import com.evision.archivalmanagementmsapi.domain.exceptions.ValidationException;
import com.evision.archivalmanagementmsapi.domain.mappers.ChannelMapper;
import com.evision.archivalmanagementmsapi.domain.models.ChannelDirectoryModel;
import com.evision.archivalmanagementmsapi.domain.models.ChannelModel;
import com.evision.archivalmanagementmsapi.domain.requests.ChannelRequest;
import com.evision.archivalmanagementmsapi.repo.ChannelRepo;
import com.evision.archivalmanagementmsapi.service.ChannelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final ChannelMapper channelMapper;
    private final ChannelRepo channelRepo;

    @Override
    public ServiceResponseBo<ChannelDto> create(ChannelRequest request) {
        String bic = "CIB"; //TODO:: load bic by license

        Optional<ChannelModel> existingModel = channelRepo.findByBicAndProtocol(bic, request.getProtocolType());
        if (existingModel.isPresent())
            throw new ValidationException("Protocol already exists for specific BIC");

        ChannelModel model = new ChannelModel();
        model.setBic(bic);
        model.setCreatedAt(LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());
        model.setUuid(UUID.randomUUID());
        model.setStatus(ChannelStatusRef.ACTIVE);

        model.setProtocol(request.getProtocolType());
        if (request.getConnectionConfiguration() != null) {
            model.setConnection(
                    "{\"host\":\"" + request.getConnectionConfiguration().getHost() +
                            "\",\"port\":\"" + request.getConnectionConfiguration().getPort() +
                            "\",\"principal\":\"" + request.getConnectionConfiguration().getPrincipal() +
                            "\",\"credentials\":\"" + request.getConnectionConfiguration().getCredential() + "\"}");
        }

        List<ChannelDirectoryModel> channelDirectories = new ArrayList<>();

        ChannelDirectoryModel channelOutgoingDirectoryModel = new ChannelDirectoryModel();
        channelOutgoingDirectoryModel.setType(DirectoryRef.OUTGOING);
        channelOutgoingDirectoryModel.setPath(request.getDirectoryConfiguration().getOutgoing());
        channelOutgoingDirectoryModel.setChannel(model);
        channelDirectories.add(channelOutgoingDirectoryModel);

        ChannelDirectoryModel channelIncomingDirectoryModel = new ChannelDirectoryModel();
        channelIncomingDirectoryModel.setType(DirectoryRef.INCOMING);
        channelIncomingDirectoryModel.setPath(request.getDirectoryConfiguration().getIncoming());
        channelIncomingDirectoryModel.setChannel(model);
        channelDirectories.add(channelIncomingDirectoryModel);

        ChannelDirectoryModel channelToSwiftDirectoryModel = new ChannelDirectoryModel();
        channelToSwiftDirectoryModel.setType(DirectoryRef.TOSWIFT_);
        channelToSwiftDirectoryModel.setPath(request.getDirectoryConfiguration().getToSwift());
        channelToSwiftDirectoryModel.setChannel(model);
        channelDirectories.add(channelToSwiftDirectoryModel);

        ChannelDirectoryModel channelMsgHistoryDirectoryModel = new ChannelDirectoryModel();
        channelMsgHistoryDirectoryModel.setType(DirectoryRef.MSGHISTO);
        channelMsgHistoryDirectoryModel.setPath(request.getDirectoryConfiguration().getMsgHistory());
        channelMsgHistoryDirectoryModel.setChannel(model);
        channelDirectories.add(channelMsgHistoryDirectoryModel);

        ChannelDirectoryModel channelGpiUCDirectoryModel = new ChannelDirectoryModel();
        channelGpiUCDirectoryModel.setType(DirectoryRef.GPIUC___);
        channelGpiUCDirectoryModel.setPath(request.getDirectoryConfiguration().getGpiUc());
        channelGpiUCDirectoryModel.setChannel(model);
        channelDirectories.add(channelGpiUCDirectoryModel);

        ChannelDirectoryModel channelPostingDirectoryModel = new ChannelDirectoryModel();
        channelPostingDirectoryModel.setType(DirectoryRef.POSTING_);
        channelPostingDirectoryModel.setPath(request.getDirectoryConfiguration().getPosting());
        channelPostingDirectoryModel.setChannel(model);
        channelDirectories.add(channelPostingDirectoryModel);

        model.setChannelDirectories(channelDirectories);
        channelRepo.save(model);

        try {
            return new ServiceResponseBo<>(channelMapper.mapModelToDto(model), "Channel created successfully");
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error while parsing channel connection configuration");
        }
    }

    @Override
    public ServiceResponseBo<ChannelDto> update(UUID id, ChannelRequest request) {
        String bic = "CIB"; //TODO:: load bic by license

        ChannelModel model = channelRepo.findByBicAndUuid(bic, id)
                .orElseThrow(() -> new ValidationException("Channel not found"));

        model.setUpdatedAt(LocalDateTime.now());

        model.setProtocol(request.getProtocolType());
        if (request.getConnectionConfiguration() != null) {
            model.setConnection(
                    "{\"host\":\"" + request.getConnectionConfiguration().getHost() +
                            "\",\"port\":\"" + request.getConnectionConfiguration().getPort() +
                            "\",\"principal\":\"" + request.getConnectionConfiguration().getPrincipal() +
                            "\",\"credentials\":\"" + request.getConnectionConfiguration().getCredential() + "\"}");
        }

        for(ChannelDirectoryModel channelDirectoryModel : model.getChannelDirectories()){
            if (channelDirectoryModel.getType() == DirectoryRef.OUTGOING)
                channelDirectoryModel.setPath(request.getDirectoryConfiguration().getOutgoing());
            else if (channelDirectoryModel.getType() == DirectoryRef.INCOMING)
                channelDirectoryModel.setPath(request.getDirectoryConfiguration().getIncoming());
            else if (channelDirectoryModel.getType() == DirectoryRef.TOSWIFT_)
                channelDirectoryModel.setPath(request.getDirectoryConfiguration().getToSwift());
            else if (channelDirectoryModel.getType() == DirectoryRef.MSGHISTO)
                channelDirectoryModel.setPath(request.getDirectoryConfiguration().getMsgHistory());
            else if (channelDirectoryModel.getType() == DirectoryRef.POSTING_)
                channelDirectoryModel.setPath(request.getDirectoryConfiguration().getPosting());
            else if (channelDirectoryModel.getType() == DirectoryRef.GPIUC___)
                channelDirectoryModel.setPath(request.getDirectoryConfiguration().getGpiUc());
        }
        channelRepo.save(model);

        try {
            return new ServiceResponseBo<>(channelMapper.mapModelToDto(model), "Channel updated successfully");
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error while parsing channel connection configuration");
        }
    }

    @Override
    public ServiceResponseBo<List<ChannelDto>> loadList() {
        try {
            String bic = "CIB"; //TODO:: load bic by license
            return new ServiceResponseBo<>(channelMapper.mapModelListToDtoList(channelRepo.findByBic(bic)), null);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error while parsing channel connection configuration");
        }
    }

    @Override
    public ServiceResponseBo<ChannelDto> load(UUID id) {
        try {
            String bic = "CIB"; //TODO:: load bic by license
            return new ServiceResponseBo<>(channelMapper.mapModelToDto(channelRepo.findByBicAndUuid(bic, id).orElseThrow(() -> new ValidationException("Channel not found")))
                    , null);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error while parsing channel connection configuration");
        }
    }
}
