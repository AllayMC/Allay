package org.allaymc.server.block.component.respawnpoint;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.message.TrKeys;

/**
 * @author IWareQ
 */
public class BlockBedRespawnPointComponentImpl extends BlockRespawnPointComponentImpl {
    @Override
    public Location3ic onPlayerRespawn(EntityPlayer player, Block block) {
        var headBlock = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT) ? block : this.getPairBlock(block);
        if (this.hasSpaceAbove(headBlock)) {
            return headBlock.getLocation();
        }

        var footBlock = this.getPairBlock(headBlock);
        if (this.hasSpaceAbove(footBlock)) {
            return footBlock.getLocation();
        }

        player.sendTranslatable(TrKeys.MC_TILE_BED_NOTVALID);
        return null;
    }

    protected Block getPairBlock(Block block) {
        var head = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT);
        var face = Preconditions.checkNotNull(BlockFace.fromHorizontalIndex(block.getPropertyValue(BlockPropertyTypes.DIRECTION_4)));
        return block.offsetPos(head ? face.opposite() : face);
    }

    protected boolean hasSpaceAbove(Block block) {
        return block.offsetPos(0, 1, 0).isAir() && block.offsetPos(0, 2, 0).isAir();
    }
}
