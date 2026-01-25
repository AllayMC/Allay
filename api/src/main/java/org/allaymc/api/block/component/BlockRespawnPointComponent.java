package org.allaymc.api.block.component;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3ic;

/**
 * @author IWareQ
 */
public interface BlockRespawnPointComponent extends BlockComponent {
    /**
     * Called when a player attempts to respawn at this block.
     * <p>
     * This method should validate the respawn point and consume any resources
     * (e.g., respawn anchor charges) if applicable.
     *
     * @param player the player attempting to respawn
     * @param block  the respawn block
     * @return the respawn location if valid, or {@code null} if respawn is not possible
     * (e.g., no charges left or obstructed), in which case the player will respawn at the global spawn point
     */
    Location3ic onPlayerRespawn(EntityPlayer player, Block block);
}