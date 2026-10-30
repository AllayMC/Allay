package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectSlownessType} represents harmful slowness metadata and adjusts
 * movement multipliers for actual player controllers. Addition changes ground,
 * flight, and vertical-flight multipliers by the supplied level's contribution,
 * while removal applies the opposite contribution using the removed instance's
 * current level. Slowness caps its own contribution at one, without clamping
 * the controller's combined multiplier.
 *
 * @author daoge_cmd
 */
public class EffectSlownessType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful slowness effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectSlownessType() {
        super(2, new Identifier("minecraft:slowness"), new Color(0x8bafe0), true);
    }

    /**
     * Decreases all three speed multipliers for an actual player controller.
     * The contribution uses the supplied instance's current level, capped at
     * one for this effect; non-player entities and players without an actual
     * controller receive no update.
     */
    @Override
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player && player.isActualPlayer()) {
            var level = effectInstance.getLevel();
            // Cap this effect's reduction, leaving other multiplier contributions separately represented.
            var multiplier = Math.max(-level * 0.15, -1);
            var controller = player.getController();
            controller.setSpeed(controller.getSpeed().addMultiplier(multiplier));
            controller.setFlySpeed(controller.getFlySpeed().addMultiplier(multiplier));
            controller.setVerticalFlySpeed(controller.getVerticalFlySpeed().addMultiplier(multiplier));
        }
    }

    /**
     * Increases all three speed multipliers for an actual player controller.
     * The contribution uses the supplied instance's current level, capped at
     * one for this effect; non-player entities and players without an actual
     * controller receive no update.
     */
    @Override
    public void onRemove(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player && player.isActualPlayer()) {
            var level = effectInstance.getLevel();
            // Match the addition cap when computing this removal instance's restoring contribution.
            var multiplier = Math.min(level * 0.15, 1);
            var controller = player.getController();
            controller.setSpeed(controller.getSpeed().addMultiplier(multiplier));
            controller.setFlySpeed(controller.getFlySpeed().addMultiplier(multiplier));
            controller.setVerticalFlySpeed(controller.getVerticalFlySpeed().addMultiplier(multiplier));
        }
    }
}
