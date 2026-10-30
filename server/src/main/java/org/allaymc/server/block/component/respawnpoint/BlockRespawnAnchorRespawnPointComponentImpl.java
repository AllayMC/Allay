package org.allaymc.server.block.component.respawnpoint;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.world.sound.SimpleSound;

/**
 * {@code BlockRespawnAnchorRespawnPointComponentImpl} spends a respawn-anchor
 * charge after finding an accepted standing position. Its search requires solid
 * ground with air at the destination and above; failed charge or position checks
 * send the invalid-anchor message and return {@code null}.
 *
 * <p>
 * Candidate priority combines a fixed horizontal order with two vertical passes.
 * Each column first tests the anchor's height and the block below it; only after
 * all columns fail does the search test one block above every column. This keeps
 * upper positions lower priority than every lower candidate. A successful lookup
 * consumes one charge before returning the selected location and playing the
 * deplete sound.
 *
 * @author IWareQ
 * @see <a href="https://minecraft.wiki/w/Respawn_Anchor#Respawning">Respawn Anchor
 *         - Respawning</a>
 */
public class BlockRespawnAnchorRespawnPointComponentImpl extends BlockRespawnPointComponentImpl {
    /**
     * {@code RESPAWN_OFFSETS} orders the eight horizontal candidate columns as
     * south, southwest, southeast, west, east, north, northwest, and northeast.
     * Each pair stores an X and Z offset relative to the anchor.
     */
    protected static final int[][] RESPAWN_OFFSETS = {
            {0, 1}, {-1, 1}, {1, 1},
            {-1, 0}, {1, 0},
            {0, -1}, {-1, -1}, {1, -1}
    };

    /**
     * Returns a standing position and consumes one stored anchor charge when
     * available. Zero charges or an unsuccessful search sends the invalid-anchor
     * message and returns {@code null} without consuming a charge.
     */
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
     * Returns the first accepted candidate in the anchor's defined column and
     * layer order. Each horizontal column first tests the anchor height and then
     * the block below it. Only after every column fails does a second pass test
     * the upper layer, so one column's upper position cannot outrank another
     * column's lower position. Each candidate uses {@code isSafeStandingPos};
     * complete failure produces {@code null}. The returned location uses integer
     * block coordinates without a centering offset.
     */
    protected Location3ic findSafeRespawnPosition(Block block) {
        // Test each column at the anchor height and below before considering any
        // upper-layer candidate, preserving lower-layer priority across the entire
        // horizontal search.
        for (var offset : RESPAWN_OFFSETS) {
            var layer1 = block.offsetPos(offset[0], 0, offset[1]);
            if (isSafeStandingPos(layer1)) {
                return layer1.getLocation();
            }

            var layer2 = block.offsetPos(offset[0], -1, offset[1]);
            if (isSafeStandingPos(layer2)) {
                return layer2.getLocation();
            }
        }

        // All same-height and lower candidates have failed. The second pass
        // therefore admits an upper candidate only after every higher-priority
        // position was rejected.
        for (var offset : RESPAWN_OFFSETS) {
            var layer3 = block.offsetPos(offset[0], 1, offset[1]);
            if (isSafeStandingPos(layer3)) {
                return layer3.getLocation();
            }
        }

        return null;
    }

    /**
     * Returns whether the candidate is air above solid ground with air headroom.
     * The check uses the two air block identities and ground solidity, without
     * additional hazard or detailed collision checks.
     */
    protected boolean isSafeStandingPos(Block block) {
        return block.offsetPos(BlockFace.DOWN).getBlockStateData().isSolid() &&
               block.isAir() &&
               block.offsetPos(BlockFace.UP).isAir();
    }
}
