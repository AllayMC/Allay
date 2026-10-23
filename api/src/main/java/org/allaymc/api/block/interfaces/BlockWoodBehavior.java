package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockStrippableComponent;

/**
 * {@code BlockWoodBehavior} exposes general block operations and stripping operations for
 * wood blocks. The inherited stripping contract resolves a stripped state from an existing
 * block state and checks whether a supplied state already represents stripped wood.
 */
public interface BlockWoodBehavior extends BlockBehavior, BlockStrippableComponent {
}
