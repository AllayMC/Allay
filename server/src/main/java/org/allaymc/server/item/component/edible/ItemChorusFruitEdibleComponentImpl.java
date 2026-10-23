package org.allaymc.server.item.component.edible;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.sound.SimpleSound;

/**
 * {@code ItemChorusFruitEdibleComponentImpl} supplies chorus fruit consumption
 * behavior with a search for a nearby safe teleport destination. The
 * superclass handles use eligibility, timing, and eating-event checks; this
 * specialization defines the nutrition and additional player effects applied
 * by its callback.
 *
 * @author IWareQ
 */
public class ItemChorusFruitEdibleComponentImpl extends ItemEdibleComponentImpl {
    /**
     * Creates chorus fruit behavior with four food points and 2.4 saturation
     * points. The component permits use without the ordinary hunger check and
     * uses the inherited default eating duration.
     */
    public ItemChorusFruitEdibleComponentImpl() {
        super(4, 2.4f, DEFAULT_EATING_TIME, false, true);
    }

    /**
     * Applies ordinary nutrition, then attempts to teleport the player to
     * nearby suitable ground. The search can block for chunk loading; failure
     * or teleport cancellation leaves the already applied nutrition intact.
     */
    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);

        var playerLoc = player.getLocation();
        var dimension = player.getDimension();

        // Search at most sixteen candidate columns within eight blocks; failure leaves the
        // player in place while retaining the fruit's already applied nutrition.
        var safePos = dimension.findSuitableGroundPosAround(this::isSafeStandingPos, (int) playerLoc.x(), (int) playerLoc.z(), 8, 16);
        if (safePos != null) {
            dimension.addSound(playerLoc, SimpleSound.TELEPORT);
            // The half-block offset centers the player within the accepted ground column.
            if (player.teleport(new Location3d(safePos.x() + 0.5, safePos.y(), safePos.z() + 0.5, dimension), EntityTeleportEvent.Reason.CHORUS_FRUIT)) {
                dimension.addSound(playerLoc, SimpleSound.TELEPORT);
            }
        }
    }

    /**
     * Returns whether a destination has solid support and two air blocks. The
     * predicate checks the standing block and head space using exact
     * {@code AIR} types before the search accepts that candidate.
     */
    protected boolean isSafeStandingPos(Position3ic pos) {
        var blockUnder = pos.dimension().getBlockState(pos.x(), pos.y() - 1, pos.z());
        if (!blockUnder.getBlockStateData().isSolid()) {
            return false;
        }
        return pos.dimension().getBlockState(pos.x(), pos.y(), pos.z()).getBlockType() == BlockTypes.AIR &&
               pos.dimension().getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockType() == BlockTypes.AIR;
    }
}
