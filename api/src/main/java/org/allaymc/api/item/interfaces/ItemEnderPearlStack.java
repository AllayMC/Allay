package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemProjectileComponent;

/**
 * {@code ItemEnderPearlStack} represents an ender pearl stack with projectile-launching
 * capabilities. The inherited item contract supplies stack state, while
 * {@code ItemProjectileComponent} identifies the launched entity and its initial
 * throw force and attempts to launch the item for a supplied shooter.
 */
public interface ItemEnderPearlStack extends ItemStack, ItemProjectileComponent {
}
