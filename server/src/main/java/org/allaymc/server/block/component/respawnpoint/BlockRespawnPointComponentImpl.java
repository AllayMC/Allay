package org.allaymc.server.block.component.respawnpoint;

import org.allaymc.api.block.component.BlockRespawnPointComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3ic;

/**
 * {@code BlockRespawnPointComponentImpl} provides the default block respawn
 * destination one block above the supplied block. Specialized bed and anchor
 * components override the lookup with their own standing-space and resource rules;
 * this default performs the coordinate offset without those checks.
 *
 * @author IWareQ
 */
public class BlockRespawnPointComponentImpl implements BlockRespawnPointComponent {

    /**
     * Returns the location exactly one block above the supplied block. The default
     * does not inspect the player, validate standing space, or consume resources
     * before returning that destination.
     */
    @Override
    public Location3ic onPlayerRespawn(EntityPlayer player, Block block) {
        return block.offsetPos(BlockFace.UP).getLocation();
    }
}
