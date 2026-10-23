package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemProjectileComponent;

/**
 * {@code ItemEggStack} represents an egg stack that exposes projectile launching through
 * {@code ItemProjectileComponent}. The inherited item contract supplies stack
 * state, while the projectile contract identifies the launched entity, supplies
 * its initial throw force, and attempts a shot for a supplied shooter.
 */
public interface ItemEggStack extends ItemStack, ItemProjectileComponent {
}
