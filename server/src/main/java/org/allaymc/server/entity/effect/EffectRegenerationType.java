package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectRegenerationType extends AbstractEffectType {
    public EffectRegenerationType() {
        super(10, new Identifier("minecraft:regeneration"), new Color(0xcd5cab));
    }

    @Override
    public void onTick(EntityLiving entity, EffectInstance effectInstance) {
        var level = effectInstance.getLevel() - 1;

        var ticksPerHealth = 50 >> level;
        if (ticksPerHealth < 1) ticksPerHealth = 1;

        if (effectInstance.getDuration() % ticksPerHealth != 0) return;

        var healthPerSecond = 20f / ticksPerHealth;
        entity.setHealth(entity.getHealth() + healthPerSecond);
    }
}
