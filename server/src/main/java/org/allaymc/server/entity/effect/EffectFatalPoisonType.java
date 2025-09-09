package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectFatalPoisonType extends AbstractEffectType {
    public EffectFatalPoisonType() {
        super(25, new Identifier("minecraft:fatal_poison"), new Color(0x4e9331), true);
    }

    @Override
    public void onTick(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityDamageComponent damageComponent)) return;
        var level = effectInstance.getLevel() - 1;

        var ticksPerDamage = 25 >> level;
        if (ticksPerDamage < 1) ticksPerDamage = 1;

        if (effectInstance.getDuration() % ticksPerDamage != 0) return;

        var damagePerSecond = 20f / ticksPerDamage;
        damageComponent.attack(DamageContainer.magicEffect(damagePerSecond));
    }
}
