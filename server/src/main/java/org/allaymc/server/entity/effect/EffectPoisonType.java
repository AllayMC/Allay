package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectPoisonType} represents the built-in poison effect and supplies
 * periodic damage for active instances. The constructor registers its fixed ID,
 * identifier, color, and classification. The owning living component retains
 * mutable instance settings and decrements remaining duration before invoking
 * the callback.
 *
 * <p>
 * The schedule uses remaining duration rather than a separate elapsed timer.
 * Changing duration therefore changes the next trigger phase even when strength
 * stays the same, and cancelled expiry removal permits further callbacks with
 * nonpositive duration. The interval derives from a Java integer shift, whose
 * masked shift distance matters for unrestricted large levels. Damage uses the
 * ordinary magic-effect attack path and its cooldown, modifiers, and health
 * updates.
 *
 * @author daoge_cmd
 */
public class EffectPoisonType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful poison effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectPoisonType() {
        super(19, new Identifier("minecraft:poison"), new Color(0x87a363), true);
    }

    /**
     * Applies periodic damage when remaining duration is divisible by the
     * interval. The raw interval is {@code 50 >> (effectInstance.getLevel())},
     * clamped to at least one tick; each eligible invocation applies {@code 20f
     * / ticksPerDamage} health points.
     *
     * <p>
     * The owner has already decremented duration, so zero duration can still
     * trigger this operation before expiry removal. The callback skips raw
     * damage that would reduce current health to zero or below; later damage
     * and health processing still determine the final outcome. The callback
     * itself does not decrement duration or remove the stored instance.
     */
    @Override
    public void onTick(EntityLiving entity, EffectInstance effectInstance) {
        var level = effectInstance.getLevel();

        var ticksPerDamage = 50 >> level;
        // Keep the duration modulus usable when shifting reduces the interval to zero.
        if (ticksPerDamage < 1) ticksPerDamage = 1;

        if (effectInstance.getDuration() % ticksPerDamage != 0) {
            return;
        }

        // This amount is applied per eligible callback, despite the variable's per-second name.
        var damagePerSecond = 20f / ticksPerDamage;
        // Check the raw request against current health before attack processing; downstream
        // modifiers and health setter rules still determine the eventual result of an accepted hit.
        if (entity.getHealth() - damagePerSecond <= 0) {
            return;
        }

        entity.attack(DamageContainer.magicEffect(damagePerSecond));
    }
}
