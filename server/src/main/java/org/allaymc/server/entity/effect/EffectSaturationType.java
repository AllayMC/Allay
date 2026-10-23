package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectSaturationType} represents saturation metadata and replenishes
 * player food on active ticks. Each invocation supplies food equal to the
 * effect level and saturation equal to twice that level; non-player living
 * entities receive no update from this implementation.
 *
 * @author daoge_cmd
 */
public class EffectSaturationType extends AbstractEffectType {
    /**
     * Creates and registers the built-in saturation effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectSaturationType() {
        super(23, new Identifier("minecraft:saturation"), new Color(0xf82423));
    }

    /**
     * Requests food equal to the effect level and saturation equal to twice
     * that level for player entities. The player's food API controls clamping
     * and food-change events; non-player living entities receive no request.
     */
    @Override
    public void onTick(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player) {
            var level = effectInstance.getLevel();
            // Saturation points are supplied at twice the food-point contribution per callback.
            player.saturate(level, 2 * level);
        }
    }
}
