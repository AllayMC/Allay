package org.allaymc.server.item.component.edible;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3ic;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * @author IWareQ
 */
public class ItemChorusFruitEdibleComponentImpl extends ItemEdibleComponentImpl {
    public ItemChorusFruitEdibleComponentImpl() {
        super(4, 2.4f, DEFAULT_EATING_TIME, false, true);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);

        var playerLoc = player.getLocation();
        var dimension = player.getDimension();

        var safePos = dimension.findSuitableGroundPosAround(this::isSafeStandingPos, (int) playerLoc.x(), (int) playerLoc.z(), 8, 16);
        if (safePos != null) {
            dimension.addLevelSoundEvent(playerLoc, SoundEvent.TELEPORT);
            if (player.teleport(new Location3d(safePos.x() + 0.5, safePos.y(), safePos.z() + 0.5, dimension), EntityTeleportEvent.Reason.CHORUS_FRUIT)) {
                dimension.addLevelSoundEvent(playerLoc, SoundEvent.TELEPORT);
            }
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
}
