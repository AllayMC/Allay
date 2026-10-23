package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectHealthBoostType} represents health-boost metadata and changes
 * maximum health through addition and removal callbacks. Addition increases the
 * current maximum by four health points per supplied level, while removal
 * subtracts the amount computed from the removed instance's current level. The
 * callback does not store a separate original contribution.
 *
 * <p>
 * The living component replaces an existing same-type instance without
 * repeating these callbacks. If replacement changes the level, eventual removal
 * can subtract a different amount from the amount originally added. Other
 * maximum-health changes also remain part of the current value used by
 * subtraction. The living maximum-health setter clamps current health when that
 * maximum falls.
 *
 * @author daoge_cmd
 */
public class EffectHealthBoostType extends AbstractEffectType {
    /**
     * Creates and registers the built-in health boost effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectHealthBoostType() {
        super(21, new Identifier("minecraft:health_boost"), new Color(0xf87d23));
    }

    /**
     * Adds four health points per supplied level to the current maximum health.
     * The callback does not heal the entity or retain the previous maximum for
     * later restoration.
     */
    @Override
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        // Each level contributes four health points, leaving current health unhealed.
        entity.setMaxHealth(entity.getMaxHealth() + (effectInstance.getLevel() * 4));
    }

    /**
     * Subtracts four health points per supplied level from the current maximum.
     * The living implementation clamps current health inside {@code
     * setMaxHealth}; this callback also checks for health above the resulting
     * maximum.
     */
    @Override
    public void onRemove(EntityLiving entity, EffectInstance effectInstance) {
        entity.setMaxHealth(entity.getMaxHealth() - (effectInstance.getLevel() * 4));
        if (entity.getHealth() > entity.getMaxHealth()) {
            entity.setHealth(entity.getMaxHealth());
        }
    }
}
