package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;

/**
 * {@code BlockLiquidBehavior} combines ordinary block behavior with liquid depth and flow
 * characteristics. Consumers can recognize liquids through this contract and query source
 * states, spread timing, containment, and hardening without selecting a concrete server
 * implementation.
 */
public interface BlockLiquidBehavior extends BlockBehavior, BlockLiquidBaseComponent {
}
