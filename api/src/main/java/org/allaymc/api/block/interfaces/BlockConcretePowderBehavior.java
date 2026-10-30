package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockConcretePowderBaseComponent;
import org.allaymc.api.block.component.BlockFallableComponent;

/**
 * {@code BlockConcretePowderBehavior} exposes block operations, falling effects, and
 * solid concrete lookup for concrete powder. The inherited component contracts
 * describe landing behavior and adjacent liquid checks, allowing concrete powder
 * behavior to combine these capabilities in one type.
 */
public interface BlockConcretePowderBehavior extends BlockBehavior, BlockFallableComponent, BlockConcretePowderBaseComponent {
}
