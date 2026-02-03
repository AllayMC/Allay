package org.allaymc.server.block.component.respawnpoint;

import org.allaymc.api.block.component.BlockRespawnPointComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author IWareQ
 */
public class BlockRespawnPointComponentImpl implements BlockRespawnPointComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_respawn_point_component");

    @Override
    public Location3ic onPlayerRespawn(EntityPlayer player, Block block) {
        return block.offsetPos(BlockFace.UP).getLocation();
    }
}
