package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectRegenerationType} represents the built-in regeneration effect
 * and supplies periodic healing for active instances. The constructor registers
 * its fixed ID, identifier, color, and classification. The owning living
 * component retains mutable instance settings and decrements remaining duration
 * before invoking the callback.
 *
 * <p>
 * The schedule uses remaining duration rather than a separate elapsed timer.
 * Changing duration therefore changes the next trigger phase even when strength
 * stays the same, and cancelled expiry removal permits further callbacks with
 * nonpositive duration. The interval derives from a Java integer shift, whose
 * masked shift distance matters for unrestricted large levels. Healing uses the
 * living health setter and its clamping and event rules.
 *
 * @author daoge_cmd
 */
public class EffectRegenerationType extends AbstractEffectType {
    /**
     * Creates and registers the built-in regeneration effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectRegenerationType() {
        super(10, new Identifier("minecraft:regeneration"), new Color(0xcd5cab));
    }

    /**
     * Applies periodic healing when remaining duration is divisible by the
     * interval. The raw interval is {@code 50 >> (effectInstance.getLevel() -
     * 1)}, clamped to at least one tick; each eligible invocation applies
     * {@code 20f / ticksPerHealth} health points.
     *
     * <p>
     * The owner has already decremented duration, so zero duration can still
     * trigger this operation before expiry removal. The health setter controls
     * clamping and health-change events. The callback itself does not decrement
     * duration or remove the stored instance.
     */
    @Override
    public void onTick(EntityLiving entity, EffectInstance effectInstance) {
        var level = effectInstance.getLevel() - 1;

        var ticksPerHealth = 50 >> level;
        // Keep the duration modulus usable when shifting reduces the interval to zero.
        if (ticksPerHealth < 1) ticksPerHealth = 1;

        if (effectInstance.getDuration() % ticksPerHealth != 0) return;

        // This amount is applied per eligible callback, despite the variable's per-second name.
        var healthPerSecond = 20f / ticksPerHealth;
        entity.setHealth(entity.getHealth() + healthPerSecond);
    }
}
