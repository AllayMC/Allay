package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectHealthBoostType extends AbstractEffectType {
    public EffectHealthBoostType() {
        super(21, new Identifier("minecraft:health_boost"), new Color(0xf87d23));
    }

    @Override
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        entity.setMaxHealth(entity.getMaxHealth() + (effectInstance.getLevel() * 4));
    }

    @Override
    public void onRemove(EntityLiving entity, EffectInstance effectInstance) {
        entity.setMaxHealth(entity.getMaxHealth() - (effectInstance.getLevel() * 4));
        if (entity.getHealth() > entity.getMaxHealth()) {
            entity.setHealth(entity.getMaxHealth());
        }
    }
}
