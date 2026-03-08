package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.component.EntityUndeadComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectInstantDamageType extends AbstractEffectType {
    public EffectInstantDamageType() {
        super(7, new Identifier("minecraft:instant_damage"), new Color(0xa9656a), true);
    }

    @Override
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        var level = effectInstance.getLevel();

        if (entity instanceof EntityUndeadComponent) {
            entity.setHealth(entity.getHealth() + (float) (2 * Math.pow(2, level)));
            return;
        }

        entity.attack(DamageContainer.magicEffect((float) (3 * Math.pow(2, level))));
    }
}
