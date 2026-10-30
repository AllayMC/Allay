package org.allaymc.api.item.component;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code ItemShieldBaseComponent} exposes shield blocking eligibility, durability
 * loss, and attacker rules for disabling a shield. The player damage handler uses
 * these operations separately: eligibility does not itself cancel damage, damage
 * the shield, or start the shared cooldown.
 *
 * @author daoge_cmd
 */
public interface ItemShieldBaseComponent extends ItemBaseComponent {

    /**
     * {@code SHIELD_DISABLE_COOLDOWN} is the five-second disable interval in ticks.
     * The player damage handler applies this interval after a successful block
     * against an attacker that disables shields.
     */
    int SHIELD_DISABLE_COOLDOWN = 100;

    /**
     * {@code SHIELD_COOLDOWN_CATEGORY} identifies the shared {@code "shield"}
     * cooldown. The player damage handler uses this category both to reject blocking
     * attempts during cooldown and to start a disable interval.
     */
    String SHIELD_COOLDOWN_CATEGORY = "shield";

    /**
     * Returns whether incoming armor-reducible damage has a qualifying entity
     * attacker under the shield's computed direction check. The server accepts
     * a strictly positive horizontal dot product; zero or {@code NaN} results
     * return {@code false}, and the caller applies damage cancellation or shield
     * effects.
     */
    boolean tryBlockDamage(EntityPlayer player, DamageContainer damage);

    /**
     * Requests shield durability loss for damage of at least three points.
     * The requested loss is the integer portion of {@code blockedDamage} plus one;
     * smaller values cause no request, and ordinary item damage rules still apply.
     */
    void applyBlockDurability(float blockedDamage);

    /**
     * Returns whether the attacker disables a shield after a successful block.
     * A warden qualifies directly, while an inventory holder qualifies when its
     * held item is an axe; {@code null} and other attackers return {@code false}.
     */
    boolean shouldDisableShield(Object attacker);
}
