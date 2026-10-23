package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemProjectileComponent;

/**
 * {@code ItemExperienceBottleStack} represents an experience bottle stack with
 * specialized behavior exposed through the item API. The inherited
 * {@link ItemStack} contract supplies ordinary stack operations, while
 * {@link ItemProjectileComponent} exposes projectile entity selection, throw
 * force, and launching behavior.
 */
public interface ItemExperienceBottleStack extends ItemStack, ItemProjectileComponent {
}
