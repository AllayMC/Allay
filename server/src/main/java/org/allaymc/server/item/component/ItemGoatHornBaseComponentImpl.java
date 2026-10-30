package org.allaymc.server.item.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.data.GoatHornInstrument;
import org.allaymc.api.world.sound.CustomSound;

/**
 * {@code ItemGoatHornBaseComponentImpl} plays the instrument selected by a goat
 * horn's metadata and enforces its player cooldown. Ready activations notify
 * players in the source dimension whose controlled entity is within the sound
 * radius; invalid metadata selects the default instrument.
 *
 * @author Miroshka000
 */
public class ItemGoatHornBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * {@code COOLDOWN_CATEGORY} identifies the player cooldown shared by goat-horn
     * activations, so switching instruments does not bypass the same category check.
     */
    public static final String COOLDOWN_CATEGORY = "goat_horn";
    /**
     * {@code COOLDOWN_TICKS} sets the horn cooldown to seven seconds, expressed as
     * twenty game ticks per second for the player's cooldown service.
     */
    public static final int COOLDOWN_TICKS = 7 * 20;
    /**
     * {@code SOUND_RANGE_SQUARED} is the squared-block cutoff for a 256-block horn
     * radius, compared with controlled-entity positions before sending sound.
     */
    private static final double SOUND_RANGE_SQUARED = 256 * 256;

    /**
     * Creates a goat-horn item component from the supplied stack initialization.
     */
    public ItemGoatHornBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Attempts horn activation for a player using the item in air.
     * An active cooldown produces no sound and does not refresh the cooldown;
     * a ready activation selects the instrument from current metadata.
     */
    @Override
    public void rightClickItemInAir(EntityPlayer player) {
        activateIfReady(player);
    }

    /**
     * Starts the shared horn cooldown and broadcasts the selected instrument when
     * the player is ready. The cooldown is set before broadcasting; a blocked
     * activation changes neither the cooldown nor nearby viewers' sound state.
     */
    private void activateIfReady(EntityPlayer player) {
        if (!player.isCooldownEnd(COOLDOWN_CATEGORY)) {
            return;
        }

        player.setCooldown(COOLDOWN_CATEGORY, COOLDOWN_TICKS, true);
        broadcastSound(player, GoatHornInstrument.fromMeta(getMeta()));
    }

    /**
     * Sends the instrument sound to eligible players in the source dimension.
     * Each viewer must have a controlled entity within the squared range cutoff;
     * the sound remains positioned at the source player's location.
     */
    private static void broadcastSound(EntityPlayer source, GoatHornInstrument instrument) {
        var sourcePosition = source.getLocation();
        var sound = new CustomSound(instrument.getSoundName());
        for (var viewer : source.getDimension().getPlayers()) {
            var controlledEntity = viewer.getControlledEntity();
            if (controlledEntity != null &&
                controlledEntity.getLocation().distanceSquared(sourcePosition) <= SOUND_RANGE_SQUARED) {
                viewer.viewSound(sound, sourcePosition, true);
            }
        }
    }
}
