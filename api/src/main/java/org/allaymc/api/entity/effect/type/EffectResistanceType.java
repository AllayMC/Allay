package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectResistanceType extends AbstractEffectType {
    public EffectResistanceType() {
        super(11, new Identifier("minecraft:resistance"));
    }

    @Override
    public void onEntityDamage(Entity entity, EffectInstance effectInstance, DamageContainer damage) {
        if (
                damage.getDamageType() == DamageContainer.DamageType.VOID ||
                damage.getDamageType() == DamageContainer.DamageType.STARVE ||
                damage.getDamageType() == DamageContainer.DamageType.COMMAND
        ) return;

        var level = entity.getEffectLevel(EffectTypes.RESISTANCE);
        if (level > 0) {
            damage.updateFinalDamage(d ->
                    (float) Math.max(0, d - d * (0.20 * level))
            );
        }
    }
}
