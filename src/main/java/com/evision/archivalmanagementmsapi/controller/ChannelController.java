package com.evision.archivalmanagementmsapi.controller;

import com.evision.archivalmanagementmsapi.domain.dto.ChannelDto;
import com.evision.archivalmanagementmsapi.domain.enums.ProtocolRef;
import com.evision.archivalmanagementmsapi.domain.requests.ChannelRequest;
import com.evision.archivalmanagementmsapi.domain.responses.BaseResponseDto;
import com.evision.archivalmanagementmsapi.service.ChannelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ChannelController extends BaseController{
    private final ChannelService channelService;

    @PostMapping("/api/channels")
    public BaseResponseDto<ChannelDto> create(@RequestBody ChannelRequest request) {
        return this.execute(request, channelService::create, "Channel Create");
    }

    @PostMapping("/api/channels/{id}")
    public BaseResponseDto<ChannelDto> update(@PathVariable UUID id, @RequestBody ChannelRequest request) {
        return this.execute(request, (request1) -> channelService.update(id, request1), "Update Channel by Id");
    }

    @GetMapping("/api/channels")
    public BaseResponseDto<List<ChannelDto>> loadListByProtocol() {
        return this.execute(channelService::loadList, "Load Channels");
    }

    @GetMapping("/api/channels/{id}")
    public BaseResponseDto<ChannelDto> load(@PathVariable UUID id) {
        return this.execute(() -> channelService.load(id), "Load Channel by Id");
    }
}
