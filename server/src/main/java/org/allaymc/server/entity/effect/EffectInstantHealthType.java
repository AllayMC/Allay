package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.EntityUndeadComponent;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectInstantHealthType extends AbstractEffectType {
    public EffectInstantHealthType() {
        super(6, new Identifier("minecraft:instant_health"), new Color(0xf82423));
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        var level = effectInstance.getLevel();

        if (entity instanceof EntityUndeadComponent) {
            if (!(entity instanceof EntityDamageComponent damageComponent)) return;
            damageComponent.attack(DamageContainer.magicEffect((float) (3 * Math.pow(2, level))));
            return;
        }

        if (!(entity instanceof EntityAttributeComponent attributeComponent)) return;
        attributeComponent.setHealth(attributeComponent.getHealth() + (float) (2 * Math.pow(2, level)));
    }
}
