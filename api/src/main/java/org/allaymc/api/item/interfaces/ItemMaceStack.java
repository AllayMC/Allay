package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemToolComponent;

/**
 * {@code ItemMaceStack} represents a mace stack with tool classification and repair
 * capabilities. The inherited item contract supplies stack state, damage, and
 * attack operations, while the additional component contracts mark the item
 * as a tool and check compatible repair materials.
 */
public interface ItemMaceStack extends ItemStack, ItemToolComponent, ItemRepairableComponent {
}
