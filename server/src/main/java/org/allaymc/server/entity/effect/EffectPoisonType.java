package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectPoisonType extends AbstractEffectType {
    public EffectPoisonType() {
        super(19, new Identifier("minecraft:poison"), new Color(0x87a363), true);
    }

    @Override
    public void onTick(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityDamageComponent damageComponent)) return;
        if (!(entity instanceof EntityAttributeComponent attributeComponent)) return;
        var level = effectInstance.getLevel();

        var ticksPerDamage = 50 >> level;
        if (ticksPerDamage < 1) ticksPerDamage = 1;

        if (effectInstance.getDuration() % ticksPerDamage != 0) return;

        var damagePerSecond = 20f / ticksPerDamage;
        if (attributeComponent.getHealth() - damagePerSecond <= 0) return;
        damageComponent.attack(DamageContainer.magicEffect(damagePerSecond));
    }
}
