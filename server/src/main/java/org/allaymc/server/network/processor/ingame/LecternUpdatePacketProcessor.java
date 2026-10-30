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
 * {@code LecternUpdatePacketProcessor} applies page-turn requests to lectern
 * blocks near the controlled entity. The
 * target must still be a lectern and its block center must lie within six
 * blocks of the entity location. Valid requests delegate the supplied page
 * value to lectern behavior; this processor does not handle book removal.
 *
 * @author daoge_cmd
 */
@Slf4j
public class LecternUpdatePacketProcessor extends PacketProcessor<LecternUpdatePacket> {

    /**
     * {@code MAX_INTERACTION_DISTANCE} limits page-turn requests to six blocks
     * from the player location to the
     * lectern block center.
     */
    private static final double MAX_INTERACTION_DISTANCE = 6.0;

    /**
     * Validates the target lectern and a six-block center-distance limit before
     * forwarding its page value. Non-lectern targets and distant requests are
     * logged and ignored, while page validation and effects remain with the
     * lectern behavior.
     */
    @Override
    public void handleSync(Player player, LecternUpdatePacket packet, long receiveTime) {
        var entity = player.getControlledEntity();
        var dimension = entity.getDimension();
        var blockPos = packet.getBlockPosition();
        int x = blockPos.getX(), y = blockPos.getY(), z = blockPos.getZ();

        var blockState = dimension.getBlockState(x, y, z);
        if (blockState.getBlockType() != BlockTypes.LECTERN) {
            log.warn("Player {} tried to update non-lectern block at ({}, {}, {})", player.getOriginName(), x, y, z);
            return;
        }

        // Compare squared center distance to the squared block-distance limit,
        // preserving the six-block reach test without computing a square root.
        var playerPos = entity.getLocation();
        double distanceSquared = playerPos.distanceSquared(x + 0.5, y + 0.5, z + 0.5);
        if (distanceSquared > MAX_INTERACTION_DISTANCE * MAX_INTERACTION_DISTANCE) {
            log.warn("Player {} is too far from lectern at ({}, {}, {})", player.getOriginName(), x, y, z);
            return;
        }

        var block = new Block(blockState, new Position3i(x, y, z, dimension), 0);

        var behavior = (BlockLecternBehavior) blockState.getBehavior();
        behavior.onPageTurn(block, packet.getPage());
    }

    /**
     * Returns the lectern update packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LECTERN_UPDATE;
    }
}
