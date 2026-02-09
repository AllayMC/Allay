package org.allaymc.server.block.component.respawnpoint;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.message.TrKeys;
import org.joml.Vector3i;

import java.util.Map;

/**
 * @author IWareQ
 */
public class BlockBedRespawnPointComponentImpl extends BlockRespawnPointComponentImpl {

    /**
     * Offsets (relative to the foot block) used to find a valid respawn position, indexed by bed facing direction.
     * These match the vanilla Minecraft Bedrock behavior as implemented in Dragonfly (DFMC).
     */
    private static final Map<BlockFace, Vector3i[]> BED_OFFSETS = Map.of(
            BlockFace.NORTH, new Vector3i[]{
                    new Vector3i(-1, 0, 0), new Vector3i(-1, 0, 1), new Vector3i(0, 0, 1),
                    new Vector3i(1, 0, 1), new Vector3i(1, 0, 0), new Vector3i(1, 0, -1),
                    new Vector3i(1, 0, -2), new Vector3i(0, 0, -2), new Vector3i(-1, 0, -2),
                    new Vector3i(-1, 0, -1), new Vector3i(0, 1, -1), new Vector3i(0, 1, 0)
            },
            BlockFace.EAST, new Vector3i[]{
                    new Vector3i(0, 0, -1), new Vector3i(-1, 0, -1), new Vector3i(-1, 0, 0),
                    new Vector3i(-1, 0, 1), new Vector3i(-1, 0, 1), new Vector3i(0, 0, 1),
                    new Vector3i(1, 0, 1), new Vector3i(2, 0, 1), new Vector3i(2, 0, 0),
                    new Vector3i(2, 0, -1), new Vector3i(1, 0, -1), new Vector3i(1, 1, 0),
                    new Vector3i(0, 1, 0)
            },
            BlockFace.SOUTH, new Vector3i[]{
                    new Vector3i(1, 0, 0), new Vector3i(1, 0, -1), new Vector3i(0, 0, -1),
                    new Vector3i(-1, 0, -1), new Vector3i(-1, 0, 0), new Vector3i(-1, 0, 1),
                    new Vector3i(-1, 0, 2), new Vector3i(0, 0, 2), new Vector3i(1, 0, 2),
                    new Vector3i(1, 0, 1), new Vector3i(0, 1, 1), new Vector3i(0, 1, 0)
            },
            BlockFace.WEST, new Vector3i[]{
                    new Vector3i(0, 0, 1), new Vector3i(1, 0, 1), new Vector3i(1, 0, 0),
                    new Vector3i(1, 0, -1), new Vector3i(1, 0, -1), new Vector3i(0, 0, -1),
                    new Vector3i(-1, 0, -1), new Vector3i(-2, 0, -1), new Vector3i(-2, 0, 0),
                    new Vector3i(-2, 0, 1), new Vector3i(-1, 0, 1), new Vector3i(-1, 1, 0),
                    new Vector3i(0, 1, 0)
            }
    );

    @Override
    public Location3ic onPlayerRespawn(EntityPlayer player, Block block) {
        var headBlock = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT) ? block : getPairBlock(block);
        var facing = Preconditions.checkNotNull(
                BlockFace.fromHorizontalIndex(headBlock.getPropertyValue(BlockPropertyTypes.DIRECTION_4))
        );

        // The foot block is on the opposite side of the facing direction
        var footBlock = getPairBlock(headBlock);
        var footPos = footBlock.getPosition();

        var offsets = BED_OFFSETS.get(facing);
        if (offsets != null) {
            var dimension = block.getDimension();
            for (var offset : offsets) {
                var targetX = footPos.x() + offset.x();
                var targetY = footPos.y() + offset.y();
                var targetZ = footPos.z() + offset.z();

                // Check if the target position is not solid (passable - player can stand there)
                var targetState = dimension.getBlockState(targetX, targetY, targetZ);
                if (targetState.getBlockStateData().isSolid()) {
                    continue;
                }

                // Check if the block above is also not solid (player needs 2 blocks of space)
                var aboveState = dimension.getBlockState(targetX, targetY + 1, targetZ);
                if (aboveState.getBlockStateData().isSolid()) {
                    continue;
                }

                // Check if the block below is solid (player needs solid ground)
                var belowState = dimension.getBlockState(targetX, targetY - 1, targetZ);
                if (!belowState.getBlockStateData().isSolid()) {
                    continue;
                }

                return new Location3i(targetX, targetY, targetZ, block.getDimension());
            }
        }

        player.sendTranslatable(TrKeys.MC_TILE_BED_NOTVALID);
        return null;
    }

    protected Block getPairBlock(Block block) {
        var head = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT);
        var face = Preconditions.checkNotNull(BlockFace.fromHorizontalIndex(block.getPropertyValue(BlockPropertyTypes.DIRECTION_4)));
        return block.offsetPos(head ? face.opposite() : face);
    }
}
