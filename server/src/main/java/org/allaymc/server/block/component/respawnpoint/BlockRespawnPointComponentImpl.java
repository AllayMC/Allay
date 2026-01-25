package org.allaymc.server.block.component.respawnpoint;

import org.allaymc.api.block.component.BlockRespawnPointComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author IWareQ
 */
public class BlockRespawnPointComponentImpl implements BlockRespawnPointComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_respawn_point_component");

    @Override
    public Location3ic onPlayerRespawn(EntityPlayer player, Block block) {
        return new Location3i((Position3i) block.getPosition().add(0, 1, 0, new Position3i(block.getDimension())));
    }
}
