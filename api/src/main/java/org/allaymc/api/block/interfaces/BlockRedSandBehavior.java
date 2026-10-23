package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockFallableComponent;

/**
 * {@code BlockRedSandBehavior} exposes general block operations and falling effects for red
 * sand blocks. The inherited falling component contract handles landing and supplies impact
 * damage calculations and damage containers used when a falling block entity reaches the
 * ground.
 */
public interface BlockRedSandBehavior extends BlockBehavior, BlockFallableComponent {
}
