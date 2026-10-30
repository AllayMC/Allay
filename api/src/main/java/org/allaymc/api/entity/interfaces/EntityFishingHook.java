package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityFishingHookBaseComponent;

/**
 * {@code EntityFishingHook} represents a cast hook with projectile ownership,
 * fishing phases, and reeling behavior. The hook can retain a caught entity or
 * wait for water-based loot, and its fishing component resolves the applicable
 * result when reeled.
 *
 * @author daoge_cmd
 */
public interface EntityFishingHook extends
        EntityProjectile,
        EntityFishingHookBaseComponent {
}
