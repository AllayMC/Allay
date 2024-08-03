package org.allaymc.api.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class PacketEvent extends NetworkEvent implements CancellableEvent {
    protected EntityPlayer player;
    @Setter
    protected BedrockPacket packet;
}
