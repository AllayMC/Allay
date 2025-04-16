package org.allaymc.server.item.component.food;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3ic;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * @author IWareQ
 */
public class ItemChorusFruitFoodComponentImpl extends ItemFoodComponentImpl {
    public ItemChorusFruitFoodComponentImpl() {
        super(4, 2.4f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);

        var playerLoc = player.getLocation();
        var dimension = player.getDimension();

        var safePos = dimension.findSuitableGroundPosAround(this::isSafeStandingPos, (int) playerLoc.x(), (int) playerLoc.z(), 8, 16);
        if (safePos != null) {
            dimension.addLevelSoundEvent(playerLoc, SoundEvent.TELEPORT);
            player.teleport(new Location3d(safePos.x() + 0.5, safePos.y(), safePos.z() + 0.5, dimension));
            dimension.addLevelSoundEvent(playerLoc, SoundEvent.TELEPORT);
        }
    }

    protected boolean isSafeStandingPos(Position3ic pos) {
        var blockUnder = pos.dimension().getBlockState(pos.x(), pos.y() - 1, pos.z());
        if (!blockUnder.getBlockStateData().isSolid()) {
            return false;
        }
        return pos.dimension().getBlockState(pos.x(), pos.y(), pos.z()).getBlockType() == BlockTypes.AIR &&
               pos.dimension().getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockType() == BlockTypes.AIR;
    }

    @Override
    public boolean canBeAlwaysEaten() {
        return true;
    }
}
