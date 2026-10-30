package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectSpeedType} represents speed metadata and adjusts movement
 * multipliers for actual player controllers. Addition changes ground, flight,
 * and vertical-flight multipliers by the supplied level's contribution, while
 * removal applies the opposite contribution using the removed instance's
 * current level. The callbacks preserve base speed values and do not retain a
 * separate original contribution.
 *
 * @author daoge_cmd
 */
public class EffectSpeedType extends AbstractEffectType {
    /**
     * Creates and registers the built-in speed effect metadata. Registration
     * requires an initialized mutable effect registry and can replace existing
     * associations under this type's fixed numeric or namespaced key.
     */
    public EffectSpeedType() {
        super(1, new Identifier("minecraft:speed"), new Color(0x33ebff));
    }

    /**
     * Adds {@code 0.2 * level} to each existing ground, flight, and
     * vertical-flight speed multiplier for an actual player controller. Other
     * entities receive no update, and each controller speed retains its base
     * value.
     */
    @Override
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player && player.isActualPlayer()) {
            var level = effectInstance.getLevel();
            // Each level adds 0.2 to the multiplier while preserving the base speed value.
            var multiplier = level * 0.2;
            var controller = player.getController();
            controller.setSpeed(controller.getSpeed().addMultiplier(multiplier));
            controller.setFlySpeed(controller.getFlySpeed().addMultiplier(multiplier));
            controller.setVerticalFlySpeed(controller.getVerticalFlySpeed().addMultiplier(multiplier));
        }
    }

    /**
     * Subtracts {@code 0.2 * level} from each existing ground, flight, and
     * vertical-flight speed multiplier for an actual player controller. The
     * removed instance supplies the level, so cleanup does not restore an
     * original multiplier snapshot.
     */
    @Override
    public void onRemove(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player && player.isActualPlayer()) {
            var level = effectInstance.getLevel();
            // Subtract the supplied level's contribution rather than restoring an earlier speed snapshot.
            var multiplier = -level * 0.2;
            var controller = player.getController();
            controller.setSpeed(controller.getSpeed().addMultiplier(multiplier));
            controller.setFlySpeed(controller.getFlySpeed().addMultiplier(multiplier));
            controller.setVerticalFlySpeed(controller.getVerticalFlySpeed().addMultiplier(multiplier));
        }
    }
}
