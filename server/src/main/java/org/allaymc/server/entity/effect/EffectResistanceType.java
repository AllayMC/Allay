package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectResistanceType extends AbstractEffectType {
    public EffectResistanceType() {
        super(11, new Identifier("minecraft:resistance"), new Color(0x9146f0));
    }

    @Override
    public void onEntityDamage(EntityLiving entity, EffectInstance effectInstance, DamageContainer damage) {
        if (
                damage.getDamageType() == DamageType.VOID ||
                damage.getDamageType() == DamageType.STARVE ||
                damage.getDamageType() == DamageType.COMMAND
        ) return;

        var level = entity.getEffectLevel(EffectTypes.RESISTANCE);
        if (level > 0) {
            damage.updateFinalDamage(d ->
                    (float) Math.max(0, d - d * (0.20 * level))
            );
        }
    }
}
