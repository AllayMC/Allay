package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectInstantDamageType extends AbstractEffectType {
    public EffectInstantDamageType() {
        super(7, new Identifier("minecraft:instant_damage"), true);
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        var level = effectInstance.getLevel();

        if (entity.isUndead()) {
            if (!(entity instanceof EntityAttributeComponent attributeComponent)) return;
            attributeComponent.setHealth(attributeComponent.getHealth() + (float) (2 * Math.pow(2, level)));
        }

        if (!(entity instanceof EntityDamageComponent damageComponent)) return;
        damageComponent.attack(DamageContainer.magicEffect((float) (3 * Math.pow(2, level))));
    }
}
