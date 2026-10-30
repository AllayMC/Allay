package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectHungerType} represents hunger metadata and requests player
 * exhaustion on active ticks. The per-tick amount scales with the supplied
 * effect level; the player's exhaustion implementation ignores creative mode,
 * spectator mode, and peaceful difficulty, and non-player living entities
 * receive no request.
 *
 * @author daoge_cmd
 */
public class EffectHungerType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful hunger effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectHungerType() {
        super(17, new Identifier("minecraft:hunger"), new Color(0x587653), true);
    }

    /**
     * Requests exhaustion equal to five thousandths per supplied level for
     * player entities. The player's exhaustion rules can ignore the request in
     * creative or spectator mode or peaceful difficulty; non-player entities
     * receive no call.
     */
    @Override
    public void onTick(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player) {
            // Per-tick exhaustion scales with level rather than the remaining effect duration.
            player.exhaust(0.005f * effectInstance.getLevel());
        }
    }
}
