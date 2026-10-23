package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.component.ItemProjectileComponent;

/**
 * {@code ItemLingeringPotionStack} represents a lingering potion stack with
 * specialized behavior exposed through the item API. The inherited
 * {@link ItemStack} contract supplies ordinary stack operations, while
 * {@link ItemProjectileComponent} supplies launching behavior and
 * {@link ItemPotionComponent} selects the potion effects.
 */
public interface ItemLingeringPotionStack extends ItemStack, ItemProjectileComponent, ItemPotionComponent {
}
