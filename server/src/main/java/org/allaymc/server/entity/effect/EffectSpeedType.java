package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectSpeedType extends AbstractEffectType {
    public EffectSpeedType() {
        super(1, new Identifier("minecraft:speed"), new Color(0x33ebff));
    }

    @Override
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player && player.isActualPlayer()) {
            var level = effectInstance.getLevel();
            var multiplier = level * 0.2;
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
            var multiplier = -level * 0.2;
            var controller = player.getController();
            controller.setSpeed(controller.getSpeed().addMultiplier(multiplier));
            controller.setFlySpeed(controller.getFlySpeed().addMultiplier(multiplier));
            controller.setVerticalFlySpeed(controller.getVerticalFlySpeed().addMultiplier(multiplier));
        }
    }
}
