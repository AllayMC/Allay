package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectWitherType extends AbstractEffectType {
    public EffectWitherType() {
        super(20, new Identifier("minecraft:wither"), new Color(0x736156), true);
    }

    @Override
    public void onTick(EntityLiving entity, EffectInstance effectInstance) {
        var level = effectInstance.getLevel() - 1;

        var ticksPerDamage = 40 >> level;
        if (ticksPerDamage < 1) ticksPerDamage = 1;

        if (effectInstance.getDuration() % ticksPerDamage != 0) {
            return;
        }

        var damagePerSecond = 20f / ticksPerDamage;
        entity.attack(DamageContainer.magicEffect(damagePerSecond));
    }
}
