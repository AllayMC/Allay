package org.allaymc.api.network;

import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public abstract class DataPacketProcessor<T extends BedrockPacket> {
    public abstract void handle(EntityPlayer player, T pk);

    public abstract BedrockPacketType getPacketType();
}