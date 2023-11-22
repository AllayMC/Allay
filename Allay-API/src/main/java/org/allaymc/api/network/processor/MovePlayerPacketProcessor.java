package org.allaymc.api.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.network.DataPacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.MovePlayerPacket;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
@Slf4j
public class MovePlayerPacketProcessor extends DataPacketProcessor<MovePlayerPacket> {
    @Override
    public void handle(EntityPlayer player, MovePlayerPacket pk) {
        //In server-auth movement, the MovePlayerPacket is only used to send "onGround" state to server by client
        if (!pk.isOnGround()) {
            log.warn("Player " + player.getOriginName() + " send a invalid MovePlayerPacket (onGround=false) while using server-auth movement!");
            return;
        }
        player.setOnGround(true);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MOVE_PLAYER;
    }
}
