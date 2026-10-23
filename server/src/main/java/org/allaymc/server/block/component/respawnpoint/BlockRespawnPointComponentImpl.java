package org.allaymc.server.block.component.respawnpoint;

import org.allaymc.api.block.component.BlockRespawnPointComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.utils.identifier.Identifier;

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
     * {@code IDENTIFIER} names the respawn-point component contract during
     * component registration and dependency resolution. Specialized
     * implementations inherit this component identity unless they define another
     * identifier.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_respawn_point_component");

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
