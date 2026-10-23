package org.allaymc.api.block.component;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3ic;

/**
 * {@code BlockRespawnPointComponent} defines block-specific player respawn
 * destination lookup. The respawn processor asks the selected spawn block for a
 * location and uses the global spawn after a {@code null} result. Implementations
 * determine their standing-space validation and resource-consumption rules; the
 * default component performs only a coordinate offset.
 *
 * @author IWareQ
 */
public interface BlockRespawnPointComponent extends BlockComponent {
    /**
     * Returns this implementation's respawn destination, or {@code null} to
     * decline the request. Specialized implementations may validate standing space
     * and consume block resources during the call; the default component returns
     * one block above without those checks. The respawn processor replaces the
     * player's stored spawn preference with the global spawn after a {@code null}
     * result.
     */
    Location3ic onPlayerRespawn(EntityPlayer player, Block block);
}
