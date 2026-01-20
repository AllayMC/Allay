package org.allaymc.api.item.component;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Component interface for shield items.
 * <p>
 * Provides functionality for damage blocking mechanics including:
 * <ul>
 *   <li>Directional blocking calculation</li>
 *   <li>Shield durability consumption</li>
 *   <li>Axe shield-breaking detection</li>
 * </ul>
 *
 * @author daoge_cmd
 */
public interface ItemShieldBaseComponent extends ItemBaseComponent {

    /**
     * The cooldown duration in ticks when a shield is disabled by an axe attack.
     * 100 ticks = 5 seconds
     */
    int SHIELD_DISABLE_COOLDOWN = 100;

    /**
     * The cooldown category identifier for shield cooldown.
     */
    String SHIELD_COOLDOWN_CATEGORY = "shield";

    /**
     * Attempts to block incoming damage with the shield.
     * <p>
     * This method checks if the damage can be blocked based on:
     * <ul>
     *   <li>Whether the damage has a valid entity attacker</li>
     *   <li>Whether the attack is coming from the front (directional blocking)</li>
     *   <li>Whether the damage type can be reduced by armor</li>
     * </ul>
     *
     * @param player the player holding the shield
     * @param damage the incoming damage container
     * @return {@code true} if the damage was successfully blocked, {@code false} otherwise
     */
    boolean tryBlockDamage(EntityPlayer player, DamageContainer damage);

    /**
     * Applies durability damage to the shield after blocking.
     * <p>
     * Following Minecraft mechanics:
     * <ul>
     *   <li>If blocked damage &gt;= 3: durability loss = (int)damage + 1</li>
     *   <li>If blocked damage &lt; 3: no durability loss</li>
     * </ul>
     *
     * @param blockedDamage the amount of damage that was blocked
     */
    void applyBlockDurability(float blockedDamage);

    /**
     * Checks if the attacker's weapon should disable the shield.
     * <p>
     * In Minecraft, axes can disable shields, causing a 5-second cooldown.
     *
     * @param attacker the attacking entity (can be null)
     * @return {@code true} if the shield should be disabled, {@code false} otherwise
     */
    boolean shouldDisableShield(Object attacker);
}
