package org.allaymc.server.block.component.respawnpoint;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.message.TrKeys;
import org.joml.Vector3i;

import java.util.Map;

/**
 * {@code BlockBedRespawnPointComponentImpl} selects bed respawn positions from
 * orientation-specific candidate offsets. The bed head determines facing and the
 * foot anchors every candidate coordinate, allowing either part supplied by the
 * respawn processor to resolve the same search geometry.
 *
 * <p>
 * {@code BED_OFFSETS} preserves the per-facing candidate order. Each candidate
 * needs nonsolid foot and head space with a solid block below, and the first
 * candidate satisfying those three checks becomes the destination. The checks use
 * solidity rather than detailed collision or hazard validation. A search with no
 * accepted candidate reports an invalid bed and returns {@code null} for the
 * processor's global-spawn fallback.
 *
 * @author IWareQ
 */
public class BlockBedRespawnPointComponentImpl extends BlockRespawnPointComponentImpl {

    /**
     * {@code BED_OFFSETS} stores ordered offsets from the foot for each horizontal
     * bed facing. {@code onPlayerRespawn} tests each array in stored order and
     * uses the first candidate passing its solidity checks.
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

    /**
     * Returns the first accepted standing position around the bed, or {@code null}
     * after sending the invalid-bed message. The method resolves the head and
     * facing from either supplied part, then measures candidate offsets from the
     * foot. A candidate requires two nonsolid blocks over solid ground. The
     * returned location uses integer block coordinates without centering, and the
     * method consumes no block resource. The supplied block must expose the bed
     * head and direction properties.
     */
    @Override
    public Location3ic onPlayerRespawn(EntityPlayer player, Block block) {
        var headBlock = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT) ? block : getPairBlock(block);
        var facing = Preconditions.checkNotNull(
                BlockFace.fromHorizontalIndex(headBlock.getPropertyValue(BlockPropertyTypes.DIRECTION_4))
        );

        var footBlock = getPairBlock(headBlock);
        var footPos = footBlock.getPosition();

        var offsets = BED_OFFSETS.get(facing);
        if (offsets != null) {
            var dimension = block.getDimension();
            for (var offset : offsets) {
                var targetX = footPos.x() + offset.x();
                var targetY = footPos.y() + offset.y();
                var targetZ = footPos.z() + offset.z();

                // Evaluate foot space, head space, and ground together for each
                // ordered candidate; accepting only one empty block could place a
                // respawn inside an obstruction.
                var targetState = dimension.getBlockState(targetX, targetY, targetZ);
                if (targetState.getBlockStateData().isSolid()) {
                    continue;
                }

                var aboveState = dimension.getBlockState(targetX, targetY + 1, targetZ);
                if (aboveState.getBlockStateData().isSolid()) {
                    continue;
                }

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

    /**
     * Returns a wrapper at the expected other bed part. The head bit reverses the
     * facing offset for head-to-foot lookup; the target block's type is not
     * validated.
     */
    protected Block getPairBlock(Block block) {
        var head = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT);
        var face = Preconditions.checkNotNull(BlockFace.fromHorizontalIndex(block.getPropertyValue(BlockPropertyTypes.DIRECTION_4)));
        return block.offsetPos(head ? face.opposite() : face);
    }
}
