package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInstantDamageType extends AbstractEffectType {
    public EffectInstantDamageType() {
        super(7, new Identifier("minecraft:instant_damage"), true);
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityDamageComponent damageComponent)) return;
        var level = effectInstance.getLevel();
        damageComponent.attack(DamageContainer.magicEffect((float) (3 * Math.pow(2, level))));
        // TODO: heal undead mobs
    }
}
