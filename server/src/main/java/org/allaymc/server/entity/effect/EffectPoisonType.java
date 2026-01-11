package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectPoisonType extends AbstractEffectType {
    public EffectPoisonType() {
        super(19, new Identifier("minecraft:poison"), new Color(0x87a363), true);
    }

    @Override
    public void onTick(EntityLiving entity, EffectInstance effectInstance) {
        var level = effectInstance.getLevel();

        var ticksPerDamage = 50 >> level;
        if (ticksPerDamage < 1) ticksPerDamage = 1;

        if (effectInstance.getDuration() % ticksPerDamage != 0) {
            return;
        }

        var damagePerSecond = 20f / ticksPerDamage;
        if (entity.getHealth() - damagePerSecond <= 0) {
            return;
        }

        entity.attack(DamageContainer.magicEffect(damagePerSecond));
    }
}
