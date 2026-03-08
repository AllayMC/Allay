package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerActionPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
@Slf4j
public class PlayerActionPacketProcessor extends PacketProcessor<PlayerActionPacket> {
    @Override
    public PacketSignal handleAsync(Player player, PlayerActionPacket packet, long receiveTime) {
        var entity = player.getControlledEntity();
        return switch (packet.getAction()) {
            case START_ITEM_USE_ON -> {
                if (entity.isUsingItemOnBlock()) {
                    log.debug("Player {} tried to start item use on without stopping", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }

                entity.setUsingItemOnBlock(true);
                yield PacketSignal.HANDLED;
            }
            case STOP_ITEM_USE_ON -> {
                if (!entity.isUsingItemOnBlock()) {
                    log.debug("Player {} tried to stop item use on without starting", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }

                entity.setUsingItemOnBlock(false);
                yield PacketSignal.HANDLED;
            }
            default -> PacketSignal.UNHANDLED;
        };
    }

    @Override
    public void handleSync(Player player, PlayerActionPacket packet, long receiveTime) {
        if (packet.getAction() == PlayerActionType.STOP_SLEEP) {
            player.getControlledEntity().wake();
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_ACTION;
    }
}
