package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectSlownessType extends AbstractEffectType {
    public EffectSlownessType() {
        super(2, new Identifier("minecraft:slowness"), new Color(0x8bafe0), true);
    }

    @Override
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player && player.isActualPlayer()) {
            var level = effectInstance.getLevel();
            var multiplier = Math.max(-level * 0.15, -1);
            var controller = player.getController();
            controller.setSpeed(controller.getSpeed().addMultiplier(multiplier));
            controller.setFlySpeed(controller.getFlySpeed().addMultiplier(multiplier));
            controller.setVerticalFlySpeed(controller.getVerticalFlySpeed().addMultiplier(multiplier));
        }
    }

    @Override
    public void onRemove(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player && player.isActualPlayer()) {
            var level = effectInstance.getLevel();
            var multiplier = Math.min(level * 0.15, 1);
            var controller = player.getController();
            controller.setSpeed(controller.getSpeed().addMultiplier(multiplier));
            controller.setFlySpeed(controller.getFlySpeed().addMultiplier(multiplier));
            controller.setVerticalFlySpeed(controller.getVerticalFlySpeed().addMultiplier(multiplier));
        }
    }
}
