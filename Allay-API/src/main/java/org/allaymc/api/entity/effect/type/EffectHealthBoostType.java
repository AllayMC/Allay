package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectHealthBoostType extends AbstractEffectType {
    public static final EffectHealthBoostType HEALTH_BOOST_TYPE = new EffectHealthBoostType();

    private EffectHealthBoostType() {
        super(21, new Identifier("minecraft:health_boost"), new Color(248, 125, 35));
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        var amplifier = effectInstance.getAmplifier() + 1;
        entity.setMaxHealth(entity.getMaxHealth() + (amplifier * 4));
    }

    @Override
    public void onRemove(Entity entity, EffectInstance effectInstance) {
        var amplifier = effectInstance.getAmplifier() + 1;
        entity.setMaxHealth(entity.getMaxHealth() - (amplifier * 4));
        if (entity.getHealth() > entity.getMaxHealth()) {
            entity.setHealth(entity.getMaxHealth());
        }
    }
}
