package org.allaymc.server.item.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.data.GoatHornInstrument;
import org.allaymc.api.world.sound.CustomSound;

/**
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
public class ItemGoatHornBaseComponentImpl extends ItemBaseComponentImpl {
    public static final String COOLDOWN_CATEGORY = "goat_horn";
    public static final int COOLDOWN_TICKS = 7 * 20;
    private static final double SOUND_RANGE_SQUARED = 256 * 256;

    public ItemGoatHornBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void rightClickItemInAir(EntityPlayer player) {
        activateIfReady(player);
    }

    private void activateIfReady(EntityPlayer player) {
        if (!player.isCooldownEnd(COOLDOWN_CATEGORY)) {
            return;
        }

        player.setCooldown(COOLDOWN_CATEGORY, COOLDOWN_TICKS, true);
        broadcastSound(player, GoatHornInstrument.fromMeta(getMeta()));
    }

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
