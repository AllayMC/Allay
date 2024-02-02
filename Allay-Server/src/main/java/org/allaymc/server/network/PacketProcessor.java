package org.allaymc.server.network;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public abstract class PacketProcessor<T extends BedrockPacket> {
    public abstract void handle(EntityPlayer player, T pk);

    public boolean isAsync(EntityPlayer player, T pk) {
        return false;
    }

    public abstract BedrockPacketType getPacketType();
}