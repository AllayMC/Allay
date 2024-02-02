package org.allaymc.server.network;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public abstract class PacketProcessor<T extends BedrockPacket> {
    public abstract void handleSync(EntityPlayer player, T pk);

    public PacketSignal handleAsync(EntityPlayer player, T pk) {
        return PacketSignal.UNHANDLED;
    }

    public abstract BedrockPacketType getPacketType();
}