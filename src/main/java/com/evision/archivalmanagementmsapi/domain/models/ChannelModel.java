package com.evision.archivalmanagementmsapi.domain.models;

import com.evision.archivalmanagementmsapi.domain.enums.ChannelStatusRef;
import com.evision.archivalmanagementmsapi.domain.enums.DirectoryRef;
import com.evision.archivalmanagementmsapi.domain.enums.ProtocolRef;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "CHANNEL")
public class ChannelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "bic", nullable = false)
    private String bic;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "protocol", nullable = false)
    private ProtocolRef protocol;

    @Column(name = "connection")
    private String connection;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ChannelStatusRef status;

    @OneToMany(mappedBy = "channel", orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ChannelDirectoryModel> channelDirectories;
}
