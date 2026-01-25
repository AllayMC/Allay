package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockLecternBehavior;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.LecternUpdatePacket;

/**
 * Handles lectern update packets from the client.
 * <p>
 * This processor handles page turns from lecterns.
 * Note: Book dropping is handled via player punch action (left-click),
 * not through this packet. The droppingBook field is deprecated since v662.
 *
 * @author daoge_cmd
 */
@Slf4j
public class LecternUpdatePacketProcessor extends PacketProcessor<LecternUpdatePacket> {

    private static final double MAX_INTERACTION_DISTANCE = 6.0;

    @Override
    public void handleSync(Player player, LecternUpdatePacket packet, long receiveTime) {
        var entity = player.getControlledEntity();
        var dimension = entity.getDimension();
        var blockPos = packet.getBlockPosition();
        int x = blockPos.getX(), y = blockPos.getY(), z = blockPos.getZ();

        // Check if block is a lectern
        var blockState = dimension.getBlockState(x, y, z);
        if (blockState.getBlockType() != BlockTypes.LECTERN) {
            log.warn("Player {} tried to update non-lectern block at ({}, {}, {})", player.getOriginName(), x, y, z);
            return;
        }

        // Check interaction distance
        var playerPos = entity.getLocation();
        double distanceSquared = playerPos.distanceSquared(x + 0.5, y + 0.5, z + 0.5);
        if (distanceSquared > MAX_INTERACTION_DISTANCE * MAX_INTERACTION_DISTANCE) {
            log.warn("Player {} is too far from lectern at ({}, {}, {})", player.getOriginName(), x, y, z);
            return;
        }

        var block = new Block(blockState, new Position3i(x, y, z, dimension), 0);

        // Handle page turn
        var behavior = (BlockLecternBehavior) blockState.getBehavior();
        behavior.onPageTurn(block, packet.getPage());
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LECTERN_UPDATE;
    }
}
