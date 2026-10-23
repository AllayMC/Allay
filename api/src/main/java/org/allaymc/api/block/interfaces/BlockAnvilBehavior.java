package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockAnvilBaseComponent;
import org.allaymc.api.block.component.BlockFallableComponent;

/**
 * {@code BlockAnvilBehavior} combines ordinary anvil block operations with falling
 * support and damage-state transitions. Callers use the same behavior reference
 * for the inherited block operations and {@code damage}, which calculates the
 * replacement state for another anvil damage step.
 */
public interface BlockAnvilBehavior extends BlockBehavior, BlockAnvilBaseComponent, BlockFallableComponent {
}
