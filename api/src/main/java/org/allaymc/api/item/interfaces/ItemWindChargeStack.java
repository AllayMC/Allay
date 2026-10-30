package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemProjectileComponent;

/**
 * {@code ItemWindChargeStack} represents a wind charge stack with projectile-launching
 * capabilities. The inherited item contract supplies stack state, while
 * {@code ItemProjectileComponent} identifies the launched entity and its initial
 * throw force and attempts to launch the item for a supplied shooter.
 */
public interface ItemWindChargeStack extends ItemStack, ItemProjectileComponent {
}
