package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockFallableComponent;

/**
 * {@code BlockSnowLayerBehavior} combines ordinary block behavior with falling-block
 * operations for snow layers. Its component composition supplies the falling behavior
 * separately from snow placement, melting, and drop rules implemented by the base block
 * component.
 */
public interface BlockSnowLayerBehavior extends BlockBehavior, BlockFallableComponent {
}
