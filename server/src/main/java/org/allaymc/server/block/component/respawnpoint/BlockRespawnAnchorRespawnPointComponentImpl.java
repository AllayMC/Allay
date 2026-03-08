package org.allaymc.server.block.component.respawnpoint;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.message.TrKeys;
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
        // Check layer 1 (y=0) and layer 2 (y=-1) for each column
        for (var offset : RESPAWN_OFFSETS) {
            // Layer 1: same Y level
            var layer1 = block.offsetPos(offset[0], 0, offset[1]);
            if (isSafeStandingPos(layer1)) {
                return layer1.getLocation();
            }

            // Layer 2: one block below
            var layer2 = block.offsetPos(offset[0], -1, offset[1]);
            if (isSafeStandingPos(layer2)) {
                return layer2.getLocation();
            }
        }

        // Check layer 3 (y=+1) for each column
        for (var offset : RESPAWN_OFFSETS) {
            var layer3 = block.offsetPos(offset[0], 1, offset[1]);
            if (isSafeStandingPos(layer3)) {
                return layer3.getLocation();
            }
        }

        return null;
    }

    protected boolean isSafeStandingPos(Block block) {
        return block.offsetPos(BlockFace.DOWN).getBlockStateData().isSolid() &&
               block.isAir() &&
               block.offsetPos(BlockFace.UP).isAir();
    }
}
