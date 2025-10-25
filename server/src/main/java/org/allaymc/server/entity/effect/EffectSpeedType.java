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
public class EffectSpeedType extends AbstractEffectType {
    public EffectSpeedType() {
        super(1, new Identifier("minecraft:speed"), new Color(0x33ebff));
    }

    @Override
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player) {
            var level = effectInstance.getLevel();
            var multiplier = level * 0.2;
            player.setSpeed(player.getSpeed().addMultiplier(multiplier));
            player.setFlySpeed(player.getFlySpeed().addMultiplier(multiplier));
            player.setVerticalFlySpeed(player.getVerticalFlySpeed().addMultiplier(multiplier));
        }
    }

    @Override
    public void onRemove(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player) {
            var level = effectInstance.getLevel();
            var multiplier = -level * 0.2;
            player.setSpeed(player.getSpeed().addMultiplier(multiplier));
            player.setFlySpeed(player.getFlySpeed().addMultiplier(multiplier));
            player.setVerticalFlySpeed(player.getVerticalFlySpeed().addMultiplier(multiplier));
        }
    }
}
