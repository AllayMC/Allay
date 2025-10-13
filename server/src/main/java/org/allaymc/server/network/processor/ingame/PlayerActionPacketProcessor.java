package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerActionPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
@Slf4j
public class PlayerActionPacketProcessor extends PacketProcessor<PlayerActionPacket> {
    @Override
    public PacketSignal handleAsync(EntityPlayer player, PlayerActionPacket packet, long receiveTime) {
        return switch (packet.getAction()) {
            case START_ITEM_USE_ON -> {
                if (player.isUsingItemOnBlock()) {
                    log.debug("Player {} tried to start item use on without stopping", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }

                player.setUsingItemOnBlock(true);
                yield PacketSignal.HANDLED;
            }
            case STOP_ITEM_USE_ON -> {
                if (!player.isUsingItemOnBlock()) {
                    log.debug("Player {} tried to stop item use on without starting", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }

                player.setUsingItemOnBlock(false);
                yield PacketSignal.HANDLED;
            }
            default -> PacketSignal.UNHANDLED;
        };
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_ACTION;
    }
}
