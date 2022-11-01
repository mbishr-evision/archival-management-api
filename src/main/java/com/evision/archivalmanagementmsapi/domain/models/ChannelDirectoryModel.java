package com.evision.archivalmanagementmsapi.domain.models;

import com.evision.archivalmanagementmsapi.domain.enums.ChannelStatusRef;
import com.evision.archivalmanagementmsapi.domain.enums.DirectoryRef;
import com.evision.archivalmanagementmsapi.domain.enums.ProtocolRef;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "CHANNEL_DIRECTORY")
public class ChannelDirectoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "path")
    private String path;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DirectoryRef type;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private ChannelModel channel;
}
