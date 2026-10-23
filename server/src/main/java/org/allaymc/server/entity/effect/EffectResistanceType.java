package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectResistanceType} represents resistance metadata and reduces
 * selected damage containers during damage processing. Void, starvation, and
 * command damage bypass the callback; other damage uses the entity's currently
 * stored resistance level rather than reading strength from the supplied
 * instance.
 *
 * @author daoge_cmd
 */
public class EffectResistanceType extends AbstractEffectType {
    /**
     * Creates and registers the built-in resistance effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectResistanceType() {
        super(11, new Identifier("minecraft:resistance"), new Color(0x9146f0));
    }

    /**
     * Reduces eligible final damage by twenty percent per current resistance
     * level, clamped at zero. Void, starvation, and command damage remain
     * unchanged, and the level is read from the entity's active resistance
     * entry.
     */
    @Override
    public void onEntityDamage(EntityLiving entity, EffectInstance effectInstance, DamageContainer damage) {
        if (
                damage.getDamageType() == DamageType.VOID ||
                damage.getDamageType() == DamageType.STARVE ||
                damage.getDamageType() == DamageType.COMMAND
        ) return;

        var level = entity.getEffectLevel(EffectTypes.RESISTANCE);
        if (level > 0) {
            // The reduction can exhaust all final damage at high resistance levels.
            damage.updateFinalDamage(d ->
                    (float) Math.max(0, d - d * (0.20 * level))
            );
        }
    }
}
