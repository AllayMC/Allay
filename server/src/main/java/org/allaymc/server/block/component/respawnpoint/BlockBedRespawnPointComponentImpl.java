package org.allaymc.server.block.component.respawnpoint;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.message.TrKeys;
import org.joml.Vector3ic;

/**
 * @author IWareQ
 */
public class BlockBedRespawnPointComponentImpl extends BlockRespawnPointComponentImpl {
    @Override
    public Location3ic onPlayerRespawn(EntityPlayer player, Block block) {
        var headBlock = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT) ? block : this.getPairBlock(block);
        if (this.hasSpaceAbove(headBlock)) {
            return this.createSpawnLocation(headBlock);
        }

        var footBlock = this.getPairBlock(headBlock);
        if (this.hasSpaceAbove(footBlock)) {
            return this.createSpawnLocation(footBlock);
        }

        player.sendTranslatable(TrKeys.MC_TILE_BED_NOTVALID);
        return null;
    }

    protected Block getPairBlock(Block block) {
        var otherPos = this.posOfOtherPart(block);
        return new Block(block.getDimension(), otherPos);
    }

    protected Vector3ic posOfOtherPart(Block block) {
        var head = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT);
        var face = Preconditions.checkNotNull(BlockFace.fromHorizontalIndex(block.getPropertyValue(BlockPropertyTypes.DIRECTION_4)));
        return (head ? face.opposite() : face).offsetPos(block.getPosition());
    }

    protected boolean hasSpaceAbove(Block block) {
        var pos = block.getPosition();
        var dimension = block.getDimension();

        return dimension.getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockType() == BlockTypes.AIR &&
               dimension.getBlockState(pos.x(), pos.y() + 2, pos.z()).getBlockType() == BlockTypes.AIR;
    }

    protected Location3ic createSpawnLocation(Block block) {
        var position = block.getPosition();
        return new Location3i(position.x(), position.y(), position.z(), block.getDimension());
    }
}
