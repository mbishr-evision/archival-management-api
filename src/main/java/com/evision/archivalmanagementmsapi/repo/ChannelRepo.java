package com.evision.archivalmanagementmsapi.repo;

import com.evision.archivalmanagementmsapi.domain.enums.ProtocolRef;
import com.evision.archivalmanagementmsapi.domain.models.ChannelModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChannelRepo extends JpaRepository<ChannelModel, Long> {
    List<ChannelModel> findByBic(String bic);
    Optional<ChannelModel> findByBicAndProtocol(String bic, ProtocolRef protocolRef);
    Optional<ChannelModel> findByBicAndUuid(String bic, UUID uuid);
}
