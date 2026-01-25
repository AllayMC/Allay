package org.allaymc.server.block.component.respawnpoint;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;

/**
 * @author IWareQ
 * @see <a href="https://minecraft.wiki/w/Respawn_Anchor#Respawning">Respawn Anchor - Respawning</a>
 */
public class BlockRespawnAnchorRespawnPointComponentImpl extends BlockRespawnPointComponentImpl {
    /**
     * Respawn position search offsets around the anchor.
     * Order follows Minecraft's priority: south, south-west, south-east, west, east, north, north-west, north-east.
     */
    protected static final int[][] RESPAWN_OFFSETS = {
            {0, 1}, {-1, 1}, {1, 1},
            {-1, 0}, {1, 0},
            {0, -1}, {-1, -1}, {1, -1}
    };

    @Override
    public Location3ic onPlayerRespawn(EntityPlayer player, Block block) {
        var charge = block.getPropertyValue(BlockPropertyTypes.RESPAWN_ANCHOR_CHARGE);
        if (charge <= 0) {
            player.sendTranslatable(TrKeys.MC_TILE_RESPAWN_ANCHOR_NOTVALID);
            return null;
        }

        var safePos = this.findSafeRespawnPosition(block);
        if (safePos == null) {
            player.sendTranslatable(TrKeys.MC_TILE_RESPAWN_ANCHOR_NOTVALID);
            return null;
        }

        block.updateBlockProperty(BlockPropertyTypes.RESPAWN_ANCHOR_CHARGE, charge - 1);
        block.addSound(SimpleSound.RESPAWN_ANCHOR_DEPLETE);
        return safePos;
    }

    /**
     * Finds a safe respawn position around the anchor following Minecraft's priority order.
     * <p>
     * Layer priority (per column): layer1 (y=0), layer2 (y=-1), then layer3 (y=+1).
     * Columns are checked in order: S, SW, SE, W, E, N, NW, NE.
     *
     * @param block the respawn anchor block
     * @return a safe spawn location, or {@code null} if no safe position found
     */
    protected Location3ic findSafeRespawnPosition(Block block) {
        var pos = block.getPosition();
        var dimension = block.getDimension();

        // Check layer 1 (y=0) and layer 2 (y=-1) for each column
        for (var offset : RESPAWN_OFFSETS) {
            var x = pos.x() + offset[0];
            var z = pos.z() + offset[1];

            // Layer 1: same Y level
            if (isSafeStandingPos(dimension, x, pos.y(), z)) {
                return createSpawnLocation(dimension, x, pos.y(), z);
            }

            // Layer 2: one block below
            if (isSafeStandingPos(dimension, x, pos.y() - 1, z)) {
                return createSpawnLocation(dimension, x, pos.y() - 1, z);
            }
        }

        // Check layer 3 (y=+1) for each column
        for (var offset : RESPAWN_OFFSETS) {
            var x = pos.x() + offset[0];
            var z = pos.z() + offset[1];

            if (isSafeStandingPos(dimension, x, pos.y() + 1, z)) {
                return createSpawnLocation(dimension, x, pos.y() + 1, z);
            }
        }

        return null;
    }

    protected boolean isSafeStandingPos(Dimension dimension, int x, int y, int z) {
        var blockUnder = dimension.getBlockState(x, y - 1, z);
        if (!blockUnder.getBlockStateData().isSolid()) {
            return false;
        }

        return dimension.getBlockState(x, y, z).getBlockType() == BlockTypes.AIR &&
               dimension.getBlockState(x, y + 1, z).getBlockType() == BlockTypes.AIR;
    }

    protected Location3ic createSpawnLocation(Dimension dimension, int x, int y, int z) {
        return new Location3i(x, y, z, dimension);
    }
}
